#include <bits/stdc++.h>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    answer.reserve(arr.size());

    for (int x : arr) {
        if (answer.empty() || answer.back() != x) {
            answer.emplace_back(x);
        }
    }
    return answer;
}