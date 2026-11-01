# Show me your damage

在Minecraft中添加了伤害数字和血条(WIP)

---


## 伤害数字

伤害数字同时支持数字与文字，默认只会使用数字形式的伤害数字

```java
// 为附近的玩家发送一个数字形式的伤害数字
DamageNumber.send(level, position, 伤害数字);

// 为附近的玩家发送一个文字形式的伤害数字
DamageNumber.send(level, position, 伤害文字);
```

默认使用粗体字体，如果安装了ModernUI，将禁用粗体

可以根据不同的伤害类型自定义伤害数字的颜色

```json
// data/<伤害类型的命名空间>/showmeyourdamage/damage_color/<伤害类型名称>.json
{
  "color": 4294967295 // 颜色信息: 转换为10进制的16进制颜色代码，如#ffffffff对应4294967295
}
```