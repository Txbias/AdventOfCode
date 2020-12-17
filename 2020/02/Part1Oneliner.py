solve=lambda file=open('data.txt'): print(sum([1 if int(line[:line.index('-')]) <= sum([1 if l == line[line.index(' ')+1] else 0 for l in line[line.index(' ')+4:]]) <= int(line[line.index('-')+1:line.index(' ')]) else 0 for line in [k for k in file.read().split('\n') if k != '']]))


solve()
