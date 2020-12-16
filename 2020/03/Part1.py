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


print(traverse_map(3, 1, content))
