solve=lambda file=open('data.txt'):print(sum([1 if sum([1 if line[line.index(' ')+4:][k]==line[line.index(' ')+1]else 0 for k in [int(line[:line.index('-')])-1,int(line[line.index('-')+1:line.index(' ')])-1]])==1 else 0 for line in [k for k in file.read().split('\n')if k!='']]))

solve()
