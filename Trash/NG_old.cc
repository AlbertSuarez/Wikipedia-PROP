#include <iostream>
#include <vector>
#include <queue>
#include <stack>
#include <climits>
using namespace std;

// Asumimos en este caso peso 1

typedef pair<int,int> P; // nodoDestino, idArco
typedef vector< vector<P> > Graph; // Grafo no dirigido

const int PESO = 1;

void readG(Graph& G, vector<double>& arco, int m) {
	while (m--) {
		int ni, nj;
		cin >> ni >> nj;
		
		// Por pushear algo...
		arco.push_back(1);
		
		G[ni].push_back(P (nj, arco.size() - 1));
		G[nj].push_back(P (ni, arco.size() - 1));
	}
}

void writeG(Graph& G, const vector<double>& arco) {
	for (int i = 0; i < G.size(); ++i) {
		cout << "Nodo " << i << ": ";
		for (int j = 0; j < G[i].size(); ++j) {
			cout << "nodoDestino " << G[i][j].first << " idArco " << G[i][j].second << " ";
		}
		cout << endl;
	}
}

void stage1_BFS(Graph& G, vector<int>& d, vector<int>& w, int s, stack<int>& pila) {
	d = vector<int> (G.size(), INT_MAX);
	w = vector<int> (G.size(), 0);
	
	vector<bool> vist(G.size(), false);
	
	d[s] = 0;
	w[s] = 1;
	
	queue<int> q;
	q.push(s);
	pila.push(s);
	
	while (not q.empty()) {
		int u = q.front(); q.pop();
		if (not vist[u]) {
			vist[u] = true;
			for (int i = 0; i < G[u].size(); ++i) {
				int v = G[u][i].first;
				if (d[v] > d[u] + PESO) {
					d[v] = d[u] + PESO;
					w[v] = w[u];
					q.push(v);
					pila.push(v);
				}
				else if (d[v] == d[u] + PESO) w[v] += w[u];
			}
		}
	}
	/*for (int i = 0; i < d.size(); ++i) {
		cout << "nodo " << i << " dist " << d[i] << " ways " << w[i] << " " << endl;
	}*/
	
	/*while (not pila.empty()) {
		cout << pila.top() << " ";
		pila.pop();
	}*/
	cout << endl;
}

void stage2_betweenness(Graph& G, stack<int>& pila, vector<int>& d, 
						vector<double>& b, vector<int>& w, vector<double>& arco) {
	while (not pila.empty()) {
		int u = pila.top(); pila.pop();
		b[u] += 1;
		for (int i = 0; i < G[u].size(); ++i) {
			int v = G[u][i].first;
			int uvArco = G[u][i].second;
			if (d[v] < d[u]) {
				arco[uvArco] = (double)w[v]/w[u]*b[u];
				b[v] += (double)w[v]/w[u]*b[u];
			}
		}
	}
	/*for (int i = 0; i < b.size(); ++i) {
		cout << "nodo " << i << " b " << b[i] << " arco " << arco[i] << endl;
	}*/
}

void community(Graph& G, vector<double>& arco, int s) {
	vector<int> d; //Distancia
	vector<int> w; //Number shortest path from source to i
	stack<int> pila;
	stage1_BFS(G, d, w, s, pila);
	
	vector<double> b(G.size(), 0); //Number shortest path between source to any
								//vertex in graph pass through vertex i
	//b[s] = 0;
	stage2_betweenness(G, pila, d, b, w, arco);
	
	for (int i = 0; i < G.size(); ++i) {
		for (int j = 0; j < G[i].size(); ++j) {
			cout << i << " " << G[i][j].first << " -> " << arco[G[i][j].second] << endl;
		}
	}
}

int main() {
	int n, m;
	cin >> n >> m;
	
	Graph G(n, vector<P> ());
	vector<double> arco;
	readG(G, arco, m);
	
	int s;
	cin >> s;
	community(G, arco, s);
	
	//writeG(G, arco);
}
