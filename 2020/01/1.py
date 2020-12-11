with open('Beispiel1.txt') as file:
    numbers = file.read().split('\n')
    numbers = [int(num) for num in numbers if num != '']

    numbers.sort()
    print(numbers)
    for index, num in enumerate(numbers):
        for index2, num2 in enumerate(numbers):
            for index3, num3 in enumerate(numbers):

                if index == index2 or index == index3 or index2 == index3:
                    continue

                if sum([num, num2, num3]) > 2020:
                    break

                if num + num2 + num3 == 2020:
                    print(num * num2 * num3)
                    print(num)
                    print(num2)
                    print(num3)
                    exit()
