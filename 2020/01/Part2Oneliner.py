solve=lambda nums=[int(num)for num in open('data.txt').read().split('\n')if num!='']:print(str(set([num1*num2*num3 for index1,num1 in enumerate(nums)for index2,num2 in enumerate(nums) for index3, num3 in enumerate(nums)if num1+num2+num3==2020]))[1:-1])


solve()
