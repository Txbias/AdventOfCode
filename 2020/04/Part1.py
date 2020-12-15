with open('data.txt') as file:
    content = file.read()


passports = content.split('\n\n')
keywords = ['byr', 'iyr', 'eyr', 'hgt', 'hcl', 'ecl', 'pid']

valid_passports = 0

for passport in passports:
    valid = True
    for keyword in keywords:
        if keyword not in passport:
            valid = False
            break

    if valid:
        valid_passports += 1

print(valid_passports)
