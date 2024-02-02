## 一、简介

该插件监听玩家的shift+f事件，来执行相应的指令，通常是打开菜单

本插件适用于DeluxeMenu等没有该功能的菜单插件，TrMenu无需安装

## 二、插件适用

#### 服务端核心

Bukkit、Spigot 以及其分支

Foila 不可用

#### 版本

1.12+

## 三、获取插件

github下载

## 四、安装插件

直接将插件文件拖入plugins，可选择重启服务端，也可热加载

## 五、配置文件

```yaml
# 插件作者 沙酱紫漏
# 插件文档 https://plugin.sandtripper.cn/ShiftAndF/

# 要执行的命令，支持 PlaceholderAPI 变量
# 示例
#commands:
#  - 'c:example1' 以控制台身份执行example1命令(无需加/)
#  - 'p:example2' 以玩家身份执行example1命令(无需加/)
# 如果想设置成空，则需要 commands: []
commands:
  - 'c:example1'
  - 'p:example2'

#请勿修改
version: 1.0
```

## 六、指令

* /saf reload 重载插件
* /saf on 开启监听（启动时默认开启）
* /saf off 关闭监听
* /saf help 查看帮助

## 七、权限

所有指令权限都是

shiftandf.command

## 八、使用统计

![](https://bstats.org/signatures/bukkit/ShiftAndF.svg)
