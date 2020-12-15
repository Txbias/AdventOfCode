from functools import reduce

with open('data.txt') as file:
    content = [x for x in file.read().split('\n') if x != '']


def traverse_map(right: int, down: int, map_array: list) -> int:
    x = 0
    y = 0

    encountered_trees = 0

    while y < len(map_array):
        if x >= len(map_array[0]):
            x = x - len(map_array[0])

        position = map_array[y][x]

        if position == '#':
            encountered_trees += 1

        y += down
        x += right

    return encountered_trees

print(reduce(lambda x, y: x * y,map(traverse_map, [1,3,5,7,1], [1,1,1,1,2], [content]*5), 1))
