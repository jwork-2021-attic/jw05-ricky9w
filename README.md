[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-f059dc9a6f8d3a56e377f745f24479a46679e63a5d9fe6f495e02850cd0d8118.svg)](https://classroom.github.com/online_ide?assignment_repo_id=6245499&assignment_repo_type=AssignmentRepo)
# jw05
191220119 王毓琦

ricky.wang@smail.nju.edu.cn

## 文件结构

```
.
└── roguelike
    ├── src
    │   ├── main
    │   │   ├── java
    │   │   │   └── com
    │   │   │       └── ricky
    │   │   │           ├── asciiPanel
    │   │   │           ├── control
    │   │   │           ├── maze
    │   │   │           ├── resources
    │   │   │           ├── screen
    │   │   │           └── world
    │   │   └── resources
    │   └── test
    │       └── java
    │           └── com
    │               └── ricky
    └── target
        ├── classes
        │   └── com
        │       └── ricky
        │           ├── asciiPanel
        │           ├── control
        │           ├── maze
        │           ├── resources
        │           ├── screen
        │           └── world
        └── test-classes
            └── com
                └── ricky
```

## 操作方法

<kbd>W</kbd> <kbd>A</kbd> <kbd>S</kbd> <kbd>D</kbd> 控制上下移动, <kbd>J</kbd> 键攻击.

## 架构设计

使用一个独立的线程负责屏幕显示内容的更新, 每个怪物都具有一个独立的线程.

`PlayScreen` 类中负责处理玩家输入和游戏数据, 并调用 `World` 类中的各类接口实现游戏主要逻辑控制和画面更新. `Screen` 类提供统一的接口 `update()` 用于进行游戏中相关数据的更新.

## 效果演示

<video id="video" controls="" preload="none">
      <source id="mp4" src="./demo.mp4" type="video/mp4">
</videos>
