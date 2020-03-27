import re
import sys

filename  = sys.argv[1]
print("filename:",filename)
res_list  = []
file = open(filename, 'r', encoding='utf-8')
for line in file:
    res_list.extend(re.findall(r"\d+\.?\d*", line))
print(res_list)

# string="A1.45，b5，6.45，8.82"
# print re.findall(r"\d+\.?\d*",string)

# ['1.45', '5', '6.45', '8.82']
