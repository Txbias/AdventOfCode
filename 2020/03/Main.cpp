# include<iostream>
# include <fstream>
# include <vector>


std::vector<std::string> readFile(std::string path);
unsigned long int traverseMap(int right, int down, std::vector<std::string> map);


int main(int argc, char* argv[]) {

    if(argc != 2) {
        std::cout << "Usage: ./Main [datafile]" << std::endl;
        return 1;
    }

    std::string path = argv[1];
    std::vector<std::string> lines = readFile(path);

    unsigned long int rightOneDownOne = traverseMap(1, 1, lines);
    unsigned long int rightThreeDownOne = traverseMap(3, 1, lines);
    unsigned long int rightFiveDownOne = traverseMap(5, 1, lines);
    unsigned long int rightSevenDownOne = traverseMap(7, 1, lines);
    unsigned long int rightOneDownTwo = traverseMap(1, 2, lines);

    unsigned long int product = 1;
    product *= rightOneDownOne;
    product *= rightThreeDownOne;
    product *= rightFiveDownOne;
    product *= rightSevenDownOne;
    product *= rightOneDownTwo;


    std::cout << "Part 1: " << rightThreeDownOne << std::endl;
    std::cout << "Part 2: " << product << std::endl;

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



unsigned long int traverseMap(int right, int down, std::vector<std::string> map) {
    unsigned int x = 0;
    unsigned int y = 0;

    unsigned long int encounteredTrees = 0;

    while(y < map.size()) {
        if(x > map[0].length() - 1) {
            x -= map[0].length();
        }

        char position = map[y][x];

        if(position == '#') {
            encounteredTrees++;
        }

        y += down;
        x += right;
    }

    return encounteredTrees;
}
