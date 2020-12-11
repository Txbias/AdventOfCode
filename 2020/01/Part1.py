with open('data.txt') as file:
    numbers = file.read().split('\n')
    numbers = [int(num) for num in numbers if num != '']

    numbers.sort()
    for index, num in enumerate(numbers):
        for index2, num2 in enumerate(numbers):
            if num + num2 == 2020:
                print(num*num2)
                exit(0)
