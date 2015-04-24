#include <iostream>
#include <sstream>
#include <string>
#include <cstdio>
#include <cstdlib>
#include <unistd.h>
#include <cstring>
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <map>
#include <list>
#include "utility.h"
using namespace std;

#define PORT 80
string http_wiki_req(const string &url);
string http_wiki_req_cat(const string &category);

enum ElementType {
	CATEGORY,
	PAGE
};

enum LinkType {
	C_SUP_C,
	C_SUB_C,
	C_P,
	P_C
};

struct Link {
	LinkType type;
	int dest;
};

struct Node {
	string label;
	ElementType type;
	list<Link> adj;
};

typedef map<int, Node> Graph;
typedef map<string, int> NameMap;

string ElementType_to_string(const ElementType &et);
string LinkType_to_string(const LinkType &lt);

bool http_wiki_reply_to_graph(string &reply, Graph &g, NameMap &catnm, NameMap &pagenm, int &curid, int maxnodesPerCat, int maxnodes)
{
	if (curid >= maxnodes) return false;
	int origid = curid;

	// Go to the "nodes" field
	reply = reply.substr(reply.find("[categorymembers] => Array"));

	while (g[origid].adj.size() < maxnodesPerCat) {
		// Go to the title of the node
		size_t pos = reply.find("[title] => ");
		if (pos == string::npos) break;
		reply = reply.substr(pos + strlen("[title] => "));

		// Read the title of the node
		stringstream ss(reply);
		string title;
		getline(ss, title);

		Utility::replaceChar(title, ' ', '_');
		Utility::toAscii(title);
		//Utility::replaceChar(title, '–', '_'); // Š, β

		// Skip the title of the node
		reply = reply.substr(title.length());

		// Check if the node is a Page or a Category
		size_t pos2 = title.find(":");
		if (pos2 != string::npos) { //Portal or Category
			size_t pos3 = title.find("Category:");
			if (pos3 != string::npos) { // Category
				// Skip the "Category:" thing
				string cat = title.substr(pos3 + strlen("Category:"));

				// Check if the category already exists
				map<string, int>::iterator it = catnm.find(cat);

				if (it == catnm.end()) { // Create a new Category
					//cout << "NEW CATEGORY: " << cat << endl;
					int newid = ++curid;
					// Add the new category to the NameMap
					catnm.insert(pair<string, int>(cat, newid));

					// Add the new Category to the graph
					Node node = (Node){cat, CATEGORY, list<Link>()};
					g.insert(g.end(), pair<int, Node>(newid, node));
				}

				// Create the new edge
				g[origid].adj.insert(g[origid].adj.end(), (Link){C_SUB_C, curid});

				//Recursive!
				string req = http_wiki_req_cat(Utility::urlEncode(cat));
				if (not http_wiki_reply_to_graph(req, g, catnm, pagenm, curid, maxnodesPerCat, maxnodes)) return false;

			}
		} else { // Page
			//cout << "Page: " + title << endl;

			Utility::replaceChar(title, ' ', '_');

			// Check if the page already exists
			map<string, int>::iterator it = pagenm.find(title);

			if (it == pagenm.end()) { // Create a new Page
				//cout << "NEW PAGE: " << title << endl;
				int newid = ++curid;
				// Add the new Page to the NameMap
				pagenm.insert(pair<string, int>(title, newid));

				// Add the new Page to the graph
				Node node = (Node){title, PAGE, list<Link>()};
				g.insert(g.end(), pair<int, Node>(newid, node));
			}

			// Create the new edge
			g[origid].adj.insert(g[origid].adj.end(), (Link){C_P, curid});

		}
	}
	return true;
}

void graph_export_wp_format(const Graph &g)
{
	map<int, Node>::const_iterator map_it;
	list<Link>::const_iterator link_it;

	for (map_it = g.begin(); map_it != g.end(); map_it++) {
		const Node &node = map_it->second;
		for (link_it = node.adj.begin(); link_it != node.adj.end(); link_it++) {
			//a	cat	CsupC	b	cat
			const Node &nodeDest = g.at(link_it->dest);
			cout << node.label << "\t" << ElementType_to_string(node.type) << "\t"
			     << LinkType_to_string(link_it->type) << "\t"
			     << nodeDest.label << "\t" << ElementType_to_string(nodeDest.type)
			     << endl;
		}
	}

}

int main(int argc, char *argv[])
{
	int curid = 0;
	Graph g;
	NameMap catnm, pagenm;

	string root_name = (argc > 1) ? argv[1] : "Medicine";
	int maxnodesPerCat = (argc > 2) ? atoi(argv[2]) : 10;
	int maxnodes = (argc > 3) ? atoi(argv[3]) : 100;

	cerr << "Root category:     " << root_name << endl
	     << "Max nodes per cat: " << maxnodesPerCat << endl
	     << "Max nodes:         " << maxnodes << endl;

	// Add the root category to the NameMap
	catnm.insert(pair<string, int>(root_name, curid));
	// Add the the root category to the graph
	Node node = (Node){root_name, CATEGORY, list<Link>()};
	g.insert(g.end(), pair<int, Node>(curid, node));

	string reply = http_wiki_req_cat(root_name);
	http_wiki_reply_to_graph(reply, g, catnm, pagenm, curid, maxnodesPerCat, maxnodes);
	graph_export_wp_format(g);
}

string http_wiki_req_cat(const string &category)
{
	return http_wiki_req("/w/api.php?action=query&list=categorymembers&cmtitle=Category:" + category + "&format=txt&cmlimit=500");
}

string http_wiki_req(const string &url)
{
	struct hostent *host;
	struct sockaddr_in client;
	host = gethostbyname("en.wikipedia.org");
	
	bzero(&client, sizeof(client));
	client.sin_family = AF_INET;
	client.sin_port = htons(PORT);
	memcpy(&client.sin_addr, host->h_addr, host->h_length);

	while (1) {
		
		int sock = socket(AF_INET, SOCK_STREAM, 0);
		if (sock < 0) {
			cout << "error creating socket" << endl;
			return "";
		}
		
		int on = 1;
		setsockopt(sock, SOL_SOCKET, SO_REUSEADDR, &on, sizeof(on));
		
		if (connect(sock, (struct sockaddr *)&client, sizeof(client))) {
			close(sock);
			cout << "error connecting" << endl;
			return "";
		}
		
		string req = "GET " + url + "\r\nHost: en.wikipedia.org\r\nConnection: close\r\n\r\n";
		send(sock, req.c_str(), req.length(), 0);
		stringstream ss;
		char c;
		while (read(sock, &c, sizeof(c)) > 0) {
			ss << c;
		}
		string tmp;
		int err;
		ss >> tmp >> err;
		close(sock);
		if (err == 200) {
			return ss.str();
		}
		usleep(100*1000);
	}
}

string ElementType_to_string(const ElementType &et)
{
	switch (et) {
	case CATEGORY:
		return "cat";
	case PAGE:
	default:
		return "page";
	}
}

string LinkType_to_string(const LinkType &lt)
{
	switch (lt) {
	case C_P:
		return "CP";
	case P_C:
		return "CP";
	case C_SUB_C:
		return "CsubC";
	case C_SUP_C:
	default:
		return "CsupC";
	}

}

