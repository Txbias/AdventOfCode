solve = lambda passports=open('data.txt').read().split('\n\n'): print((lambda t=[1 if keyword in passport else 0 for passport in passports for keyword in ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid']]: sum(map(min, [t[i:i+len(t) // len(passports)] for i in range(0, len(t), len(t) // len(passports))])))())


solve()
