# Git 提交规范 (Git Commit Conventions)

本文档定义了 Sanguo 项目的 Git 提交消息规范，基于 [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) 规范。

---

## 提交消息格式

```
<type>(<scope>): <subject>

<body>

<footer>
```

### 规则

- **type** 和 **scope** 必须使用小写
- **subject** 首字母大写，结尾不加句号
- **subject** 限制在 50 字符以内
- **body** 每行限制在 72 字符以内
- 使用中文描述项目相关内容（本项目为中文项目）

---

## Type 类型

| Type | 说明 | 示例 |
|------|------|------|
| `feat` | 新功能 | 添加武将系统 |
| `fix` | Bug 修复 | 修复城池税收计算错误 |
| `docs` | 文档变更 | 更新 API 文档 |
| `style` | 代码格式（不影响逻辑） | 调整缩进 |
| `refactor` | 重构（既非新功能也非修复） | 重构 Hero 类继承结构 |
| `perf` | 性能优化 | 优化地图渲染性能 |
| `test` | 测试相关 | 添加英雄战斗测试 |
| `chore` | 构建过程或辅助工具变动 | 更新 Maven 依赖 |
| `revert` | 回滚之前的提交 | Revert "feat: 添加新功能" |

---

## Scope 范围

Scope 表示提交影响的模块，根据项目架构选择：

| Scope | 说明 |
|-------|------|
| `core` | 核心游戏逻辑 |
| `data` | 数据文件（YAML/Lua 配置） |
| `map` | 地图系统 |
| `hero` | 武将系统 |
| `battle` | 战斗系统 |
| `economy` | 内政经济系统 |
| `ui` | 用户界面 |
| `mod` | 模组系统 |
| `render` | 渲染相关 |
| `build` | 构建配置 |

---

## 示例

### 新功能

```
feat(hero): 添加武将五维属性系统

- 添加 command, bravery, intelligence, politics, charisma 属性
- 实现属性对战斗的影响计算
- 添加武将升级时的属性增长逻辑

Closes #123
```

### Bug 修复

```
fix(battle): 修复骑兵在森林地形不正确的移动速度

之前骑兵在森林地形移动速度计算错误，
未正确应用地形惩罚系数。

Fixes #145
```

### 文档更新

```
docs: 更新游戏设计文档

添加 AI 决策系统的详细设计说明
```

### 重构

```
refactor(core): 重构事件系统为模组化架构

将硬编码的事件逻辑迁移到 Lua 脚本，
支持模组自定义事件
```

### 数据变更

```
feat(data): 添加赤壁之战剧本

- 添加208年历史武将配置
- 配置初始势力分布
- 添加赤壁之战专属事件
```

---

## 提交最佳实践

1. **原子化提交**：每个提交只做一件事
2. **及时提交**：完成一个功能点立即提交，避免堆积
3. **清晰的描述**：让他人（和未来的你）能理解提交的目的
4. **参考 Issue**：相关提交在 footer 中引用 Issue 号

---

## 参考资源

- [Conventional Commits Specification](https://www.conventionalcommits.org/en/v1.0.0/)
- [47 Git Best Practices to follow (in 2025)](https://acompiler.com/git-best-practices/)
- [How to Write a Good Git Commit Message](https://www.gitkraken.com/learn/git/best-practices/git-commit-message)
