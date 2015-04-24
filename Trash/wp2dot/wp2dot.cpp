/*
Instruccions:
	1) Tenir "dot" instalat
	2) Compilar wp2dot (make)
	3) Executar wp2dot (./wp2dot < in.txt > out.dot)
	4) Executar DOT sobre el .dot
	   (dot -Tps out.dot -o out.ps)
	   ps és l'imatge
*/

#include <iostream>
#include <string>
#include <map>
#include <list>
using namespace std;

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

ElementType string_to_ElementType(const string &s);
LinkType string_to_LinkType(const string &s);
void print_dot(map<int, Node> &nodeMap);

int main()
{
	int id = 0;
	map<int, Node> nodeMap;
	map<string, int> stringMap;

	string name1, type1, link, name2, type2;
	while (cin >> name1 >> type1 >> link >> name2 >> type2) {
		int id1, id2;
		map<int, Node>::iterator it1, it2;

		map<string, int>::iterator s1 = stringMap.find(name1);
		map<string, int>::iterator s2 = stringMap.find(name2);

		if (s1 == stringMap.end()) {
			id1 = id++;
			Node n1 = (Node){name1, string_to_ElementType(type1), list<Link>()};
			it1 = nodeMap.insert(nodeMap.end(), pair<int, Node>(id1, n1));
			stringMap.insert(pair<string, int>(name1, id1));
		} else {
			id1 = s1->second;
			it1 = nodeMap.find(id1);
		}

		if (s2 == stringMap.end()) {
			id2 = id++;
			Node n2 = (Node){name2, string_to_ElementType(type2), list<Link>()};
			it2 = nodeMap.insert(nodeMap.end(), pair<int, Node>(id2, n2));
			stringMap.insert(pair<string, int>(name2, id2));
		} else {
			id2 = s2->second;
			it2 = nodeMap.find(id2);
		}
		Link l1 = (Link){string_to_LinkType(link), id2};
		
		it1->second.adj.push_back(l1);
	}
	print_dot(nodeMap);
}

void print_dot(map<int, Node> &nodeMap)
{
	map<int, Node>::const_iterator map_it;
	list<Link>::const_iterator link_it;

	cout << "digraph {" << endl;

	// Print nodes
	for (map_it = nodeMap.begin(); map_it != nodeMap.end(); map_it++) {
		cout << "\t" << map_it->first << "\t[name=\"node " << map_it->first << "\"," << endl
		     << "\t\tlabel=\"" << map_it->second.label << "\"" << 
		     ((map_it->second.type == PAGE) ? (",\n\t\tshape=box") : "") <<
		     "];" << endl;
	}
	// Print edges
	for (map_it = nodeMap.begin(); map_it != nodeMap.end(); map_it++) {
		for (link_it = map_it->second.adj.begin(); link_it != map_it->second.adj.end(); link_it++) {
			if (link_it->type != P_C) {
				cout << "\t" << map_it->first << " -> " << link_it->dest <<
				((link_it->type == C_P) ? (" [style=dashed, color=blue]") : "") <<
				";" << endl;
			}
		}
	}

	cout << "}" << endl;
}

ElementType string_to_ElementType(const string &s)
{
	if (s == "cat") return CATEGORY;
	else return PAGE;
}

LinkType string_to_LinkType(const string &s)
{
	if (s == "CsupC") return C_SUP_C;
	else if (s == "CsubC") return C_SUB_C;
	else if (s == "CP") return C_P;
	else if (s == "PC") return P_C;
}
