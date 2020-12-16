# include <iostream>
# include <vector>
# include <fstream>


std::vector<std::string> readFile(std::string path);
bool isValid(std::string line);


int main(int argc, char* argv[]) {

    if(argc != 2) {
        std::cout << "Usage: ./1 [datafile]" << std::endl;
        return 1;
    }

    std::string path = argv[1];
    std::vector<std::string> content = readFile(path);

    int validPasswords = 0;
    for(auto line : content) {
        if(isValid(line)) {
            validPasswords++;
        }
    }

    std::cout << validPasswords << std::endl;

    return 1;
}


bool isValid(std::string line) {
    int indexDivider = line.find("-");

    int pos1 = std::stoi(line.substr(0, indexDivider));
    int pos2 = std::stoi(line.substr(indexDivider + 1, line.find(" ")));

    char letter = line[line.find(" ") + 1];
    std::string password = line.substr(line.find(letter) + 3);

    int letterOccurences = 0;
    if(password[pos1-1] == letter) letterOccurences++;
    if(password[pos2-1] == letter) letterOccurences++;

    return letterOccurences == 1;
}


std::vector<std::string> readFile(std::string path) {
    std::fstream file;
    file.open(path, std::ios::in);

    std::vector<std::string> content;

    if(file.is_open()) {
        std::string line;

        while(std::getline(file, line)) {
            content.push_back(line);
        }

        file.close();
    }

    return content;
}
