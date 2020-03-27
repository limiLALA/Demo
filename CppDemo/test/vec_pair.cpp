#include<iostream>
#include<map> 
#include<vector>

using namespace std;

int main(){
	string str = "°ü×Ó";
	map<string, vector<pair<int,int> > >mp;
	map<string, vector<pair<int,int> > >::iterator it;
	vector<pair<int,int> >v;
	pair<int,int>pa;
	pa.first = 1;
	pa.second = 2;
	v.push_back(pa);
	mp.insert(pair<string, vector<pair<int,int> > >(str, v));
	mp.insert(map<string, vector<pair<int,int> > >::value_type (str, v));
//	cout<<mp[str][0].first; 
	it = mp.find(str);
	cout<<*it; 
	
	
	
	
//	cout<<"1£º"<<mp["Mina"][0].first<<"\n2£º"<<mp["Mina"][0].second;
//	mp["Mina"][0].first = 3;
//	mp["Mina"][0].second = 4;
//	cout<<"\n1£º"<<mp["Mina"][0].first<<"\n2£º"<<mp["Mina"][0].second;
	return 0;
}
