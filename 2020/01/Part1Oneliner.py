solve=lambda nums=[int(num) for num in open('data.txt').read().split('\n') if num != '']: print(str(set([num1*num2 for index1, num1 in enumerate(nums) for index2, num2 in enumerate(nums) if num1+num2==2020 and index1!=index2]))[1:-1])

solve()
