traverse_map=lambda right=3,down=1,map_array=[x for x in open('data.txt').read().split('\n') if x!='']:sum([1 if map_array[y][x]=='#' else 0 for y,x in zip([y for y in range(0,len(map_array),down)],[x*right-(len(map_array[0])*((x*right)//len(map_array[0])))for x in range(len(map_array)//down)])])


print(traverse_map())
