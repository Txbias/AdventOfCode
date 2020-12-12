with open('data.txt') as file:
    content = file.read()

solve=lambda nums=[int(num) for num in content.split('\n') if num != '']: print(str(set([num1*num2 for index1, num1 in enumerate(nums) for index2, num2 in enumerate(nums) if num1+num2 == 2020 and index1 != index2])))

solve()
