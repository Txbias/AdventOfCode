with open('data.txt') as file:
    content = file.read()

solve = lambda passports=content.split('\n\n'), t=[1 if keyword in passport else 0 for passport in content.split('\n\n') for keyword in ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid']]: print(sum(map(min, [t[i:i+len(t) // len(passports)] for i in range(0, len(t), len(t) // len(passports))])))


solve()

