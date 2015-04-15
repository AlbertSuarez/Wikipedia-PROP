#include <iostream>
#include <string>
#include <map>
#include <set>
using namespace std;

/*
drug_targeting	cat	CsupC	drugs	cat
drug_targeting	cat	CsupC	medicinal_chemistry	cat
drug_targeting	cat	CsupC	pharmacology	cat
drug_targeting	cat	CP	targeted_drug_delivery	page
endoscopic_images	cat	CsupC	gastroenterology	cat
medicinal_herbs_and_fungi	cat	CsupC	drugs	cat
medicinal_herbs_and_fungi	cat	CsupC	biologically_based_therapies	cat
*/

struct Node {
	int id;
	bool isCategory;
	set<string> adj;
};

int main()
{
	int id = 0;
	map<string, Node> nodeMap;

	
	
	string name1, type1, link, name2, type2;
	while (cin >> name1 >> type1 >> link >> name2 >> type2) {
		
		bool name1_isCat = (type1 == "cat");
		bool name2_isCat = (type2 == "cat");
		
		nodeMap.insert(name1, {id++, name1_isCat
		
		
		
		
	}
	
} 
