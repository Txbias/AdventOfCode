# include <iostream>
# include <fstream>
# include <vector>

std::vector<std::string> readFile(std::string path);


int main(int argc, char* argv[]) {

    if(argc != 2) {
        std::cout << "Usage ./1 [datafile]" << std::endl;
        return 0;
    }


    std::string path = argv[1];
    std::vector<std::string> content = readFile(path);

    for(int i = 0; i < content.size(); i++) {
        for(int j = 0; j < content.size(); j++) {
            if(i == j) {
                continue;
            }

            int num1 = std::stoi(content[i]);
            int num2 = std::stoi(content[j]);

            if(num1 + num2 == 2020) {
                std::cout << num1 * num2 << std::endl;
                return 0;
            }
        }
    }

    return 0;
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
