#include <iostream>
#include <string>
#include <vector>
using namespace std;
#define BUFFER_SIZE 15

int isKeyword(const string &buffer, const vector<string> &keywords)
{
    for (const auto &keyword : keywords)
    {
        if (keyword == buffer)
        {
            return 1;
        }
    }
    return 0;
}

int main()
{
    char ch;
    string buffer;
    const char operators[] = "+-*/%=";
    const char separators[] = "(){}[],";

    vector<string> keywords;
    cout << "Enter keywords (one per line, type 'done' when finished):" << endl;
    while (true)
    {
        string keyword;
        cin >> keyword;
        if (keyword == "done")
        {
            break;
        }
        keywords.push_back(keyword);
    }

    cout << "Enter the input to analyze" << endl;

    while (cin.get(ch))
    {
        for (int i = 0; i < sizeof(operators) / sizeof(operators[0]); ++i)
        {
            if (ch == operators[i])
            {
                cout << " OPERATOR:     " << ch << endl;
            }
            else if (ch == separators[i])
            {
                cout << " SEPARATOR:    " << ch << endl;
            }
        }

        if (isalnum(ch))
        {
            buffer += ch;
        }
        else if ((ch == ' ' || ch == '\n') && (!buffer.empty()))
        {
            if (isKeyword(buffer, keywords) == 1)
            {
                cout << " KEYWORD:      " << buffer << endl;
            }
            else
            {
                cout << " IDENTIFIER:   " << buffer << endl;
            }
            buffer.clear();
        }
    }

    return 0;
}
