import csv, json

"""
通过damage_color.csv自动生成伤害数字颜色
在scripts目录下运行
"""

with open("./damage_color.csv") as file:
    damage_color = list(csv.reader(file))

for namespace, damage_type, color in damage_color:
    with open(f"../src/main/resources/data/{namespace}/showmeyourdamage/damage_color/{damage_type}.json", "w") as file:
        json.dump({"color": int(color, 16)}, file)

