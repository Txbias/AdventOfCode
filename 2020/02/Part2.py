def valid(line):
    dividerIndex = line.index('-')

    pos1 = int(line[:dividerIndex])
    pos2 = int(line[dividerIndex+1:line.index(' ')])

    letter = line[line.index(' ')+1]
    password = line[line.index(letter)+3:]

    letter_occurences = 0
    if password[pos1 - 1] == letter:
        letter_occurences+=1
    if password[pos2 - 1] == letter:
        letter_occurences+=1

    return letter_occurences == 1


with open('data.txt') as file:
    content = file.read().split('\n')
    content = [k for k in content if k != '']
    print(sum([1 if valid(line) else 0 for line in content]))
