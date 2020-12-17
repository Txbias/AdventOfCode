solve=lambda:__import__('functools').reduce(lambda x,y:x*y,[(lambda right,down,map_array=[x for x in open('data.txt').read().split('\n') if x!='']:sum([1 if map_array[y][x]=='#'else 0 for y,x in zip([y for y in range(0,len(map_array),down)],[x*right-(len(map_array[0])*((x*right)//len(map_array[0])))for x in range(len(map_array)//down)])]))(r,l)for r,l in [(1,1),(3,1),(5,1),(7,1),(1,2)]],1)

print(solve())
