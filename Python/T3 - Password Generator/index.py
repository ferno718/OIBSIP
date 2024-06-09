import random

alp = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z']
num = ['0', '1', '2', '3', '4', '5', '6', '7', '8', '9']
sym = ['!', '#', '@', '$', '%', '&', '*', '_', '/', '(', ')', '.', '?']

print("\nWelcome to the Simple Random Password Generator!")
na = int(input("\nHow many letters would you like to have in your password?\n\n")) 
nn = int(input(f"\nHow many numbers would you like to have in your password?\n\n"))
ns = int(input(f"\nHow many symbols would you like to have in your password?\n\n"))

pas = []
str = ""

for i in range(0,na):
    pas.append(random.choice(alp))
for i in range(0,nn):
        pas.append(random.choice(num))
for i in range(0,ns):
    pas.append(random.choice(sym))

random.shuffle(pas)

for i in pas:
    str = str + i
print("\nThe randomly generated password for you is: ",str,"\n")