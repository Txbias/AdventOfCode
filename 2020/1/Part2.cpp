# include <iostream>
# include <fstream>
# include <vector>

std::vector<std::string> readFile(std::string path);

int main(int argc, char* argv[]) {


    if(argc != 2) {
        std::cout << "Usage: ./part2 [datafile]" << std::endl;
        return 0;
    }

    std::string path = argv[1];
    std::vector<std::string> lines = readFile(path);

    for(int i = 0; i < lines.size(); i++) {
        int num1 = std::stoi(lines[i]);
        for(int j = 0; j < lines.size(); j++) {
            if(i == j) {
                continue;
            }

            int num2 = std::stoi(lines[j]);
            for(int k = 0; k < lines.size(); k++) {
                if(k == i || k == j) {
                    continue;
                }

                int num3 = std::stoi(lines[k]);
                if(num1 + num2 + num3 == 2020) {
                    std::cout << num1 * num2 * num3 << std::endl;
                    return 0;
                }
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
