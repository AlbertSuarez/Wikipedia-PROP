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
		
		// Por pushear algo :D
		arco.push_back(0);
		
		G[ni].push_back(P (nj, arco.size() - 1));
		G[nj].push_back(P (ni, arco.size() - 1));
	}
}

void writeG(Graph& G, const vector<double>& arco) {
	for (int i = 0; i < G.size(); ++i) {
		cout << "Nodo " << i << ": ";
		for (int j = 0; j < G[i].size(); ++j) {
			cout << "nodoDest " << G[i][j].first << " idArco " << G[i][j].second << " ";
		}
		cout << endl;
	}
	
	for (int i = 0; i < G.size(); ++i) {
		for (int j = 0; j < G[i].size(); ++j) {
			if (i < G[i][j].first) cout << i << " " << G[i][j].first << " -> " << arco[G[i][j].second] << endl;
		}
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
	pila.push(s); // Guardar orden inverso
	
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
					pila.push(v); // Guardar orden inverso
				}
				else if (d[v] == d[u] + PESO) w[v] += w[u];
			}
		}
	}
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
				arco[uvArco] += (double)w[v]/w[u]*b[u]; // fraccion de shortest paths
														// que pasan por la arista
				b[v] += (double)w[v]/w[u]*b[u];			// fraccion de shortest paths
														// que pasan por el vertice
			}
		}
	}
}


/* Algoritmo: Para cada nodo hacer :
 * 			STAGE1 :Bfs y obtener la distancia,
 * 					y las ways posibles al resto de nodos;
 * 			STAGE2: Obtener edge beetweeness recorriendo el orden 
 *					inverso al del bfs e ir acumulandolas 
 */
void community(Graph& G, vector<double>& arco) {
	// Reset vector<> arco
	for (int i = 0; i < arco.size(); ++i) arco[i] = 0;
	
	for (int i = 0; i < G.size(); ++i) {
		vector<int> d; //Distancia
		vector<int> w; //Number shortest path from source to i
		stack<int> pila;
		stage1_BFS(G, d, w, i, pila);
		
		vector<double> b(G.size(), 0);		//Number shortest path between source to any
											//vertex in graph pass through vertex i
		stage2_betweenness(G, pila, d, b, w, arco);
	}
}

int main() {
	int n, m;
	cin >> n >> m;
	
	Graph G(n, vector<P> ());
	vector<double> arco;
	readG(G, arco, m);
	
	// Cada llamada a community es una iteración
	// del Girvan
	// Falta quitar las aristas con betweeness mas
	// altas y mirar si se ha descompuesto el grafo
	community(G, arco);
	
	writeG(G, arco);
}
