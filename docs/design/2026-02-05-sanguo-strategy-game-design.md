# 三国战略游戏总体设计文档

**日期**: 2026-02-05
**版本**: 1.0
**状态**: 初稿

---

## 1. 项目概述

打造一款界面优秀、可玩性高、支持模组的一流三国战略游戏。

### 1.1 核心目标

- **游戏类型**: 大地图即时战略 (RTS)，类似《信长之野望12》
- **游戏模式**: 纯单机游戏
- **画面风格**: 3D水墨风地图 + 2D武将头像
- **模组支持**: 借鉴《钢铁雄心4》的模组系统
- **游戏规模**: 大规模全图 (100+城池，1000+武将)
- **剧本系统**: 多剧本选择 (黄巾之乱、赤壁之战、三国归晋等)

---

## 2. 技术栈

| 层级 | 技术选择 | 说明 |
|-----|---------|------|
| **游戏引擎** | Unity 2022+ (URP) | 成熟的3D引擎，支持水墨风渲染 |
| **脚本语言** | Lua | 游戏逻辑脚本，模组制作者友好 |
| **数据格式** | YAML | 静态数据配置，易于编辑 |
| **主逻辑语言** | C# | Unity原生语言 |
| **UI框架** | Unity UI Toolkit / UGUI | 待定 |

---

## 3. 整体架构

```
┌─────────────────────────────────────────────────────────────────┐
│                        Unity 游戏客户端                         │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                   渲染层 (3D地图 + 2D UI)                │   │
│  │  - 水墨风3D地形  - 粒子系统  - 光影效果                  │   │
│  │  - 2D武将头像   - UI界面                                 │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                  游戏逻辑层 (C#)                         │   │
│  │  ┌─────────────────────────────────────────────────┐    │   │
│  │  │  战斗系统  │  内政系统  │  AI系统  │  事件系统   │    │   │
│  │  └─────────────────────────────────────────────────┘    │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                  脚本引擎 (Lua)                          │   │
│  │  - 事件脚本  - 触发器  - 决策系统  - AI行为配置         │   │
│  └─────────────────────────────────────────────────────────┘   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │                  数据加载层                              │   │
│  │  - 武将数据  - 兵种配置  - 势力设定  - 地图数据         │   │
│  └─────────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────────┘
                          ↕
┌─────────────────────────────────────────────────────────────────┐
│                      模组系统                                   │
│  core/                    mods/                                │
│  ├── scenarios/             ├── [模组名]/                       │
│  ├── heroes/               │   └── ... (覆盖core数据)          │
│  ├── events/                                                   │
│  └── ...                                                      │
└─────────────────────────────────────────────────────────────────┘
```

---

## 4. 模组系统设计

### 4.1 目录结构

```
Sanguo/
├── Sanguo.exe
├── core/                          # 官方数据（不可编辑）
│   ├── scenarios/                 # 剧本定义
│   │   ├── 184_yellow_turban.yml  # 黄巾之乱
│   │   ├── 208_chibi.yml          # 赤壁之战
│   │   └── 263_three_kingdoms.yml # 三国归晋
│   ├── heroes/                    # 武将数据
│   │   ├── _hero_index.yml        # 武将索引
│   │   ├── wei/                   # 魏国武将
│   │   ├── shu/                   # 蜀国武将
│   │   └── wu/                    # 吴国武将
│   ├── units/                     # 兵种定义
│   ├── events/                    # 事件脚本
│   ├── map/                       # 地图数据
│   │   ├── provinces.png          # 省份着色图
│   │   ├── terrain.png            # 地形图
│   │   ├── positions.bmp          # 城市位置图
│   │   └── definition.csv         # 省份定义
│   ├── localisation/              # 本地化
│   │   ├── simpl_chinese/
│   │   ├── english/
│   │   └── ...
│   └── common/                    # 通用配置
│       ├── game_rules.yml
│       └── ai_config.yml
└── mods/                          # 模组目录
    └── [模组名]/
        ├── descriptor.mod         # 模组描述文件
        └── ...                    # 覆盖core中的文件
```

### 4.2 数据格式示例

**武将数据 (YAML)**

```yaml
# data/heroes/wei/caocao.yml
hero:
  id: "caocao"
  name: "HERO_CAOCAO"              # 本地化key
  portraits:
    idle: "wei/caocao_idle.png"
    happy: "wei/caocao_happy.png"
    angry: "wei/caocao_angry.png"

  lifespan:
    birth_year: 155
    death_year: 220

  attributes:                       # 五维属性
    command: 92
    bravery: 83
    intelligence: 91
    politics: 94
    charisma: 96

  traits:                           # 特质
    - id: "weiwei"
    - id: "xujishi"

  relationships:                    # 人际关系
    - type: "sworn_brother"
      target: "guanyu"

  starting_army:                    # 初始部队
    unit: "heavy_cavalry"
    count: 5000
```

**事件脚本 (Lua)**

```lua
-- data/events/three_brothers_event.lua
Event {
    id = "taoyuan_oath",
    title = "EVENT_TITLE_TAOYUAN_OATH",
    desc = "EVENT_DESC_TAOYUAN_OATH",

    trigger = function(game_state)
        local year = game_state.current_date.year
        local has_liubei = game_state:get_hero("liubei") ~= nil
        local has_guanyu = game_state:get_hero("guanyu") ~= nil
        local has_zhangfei = game_state:get_hero("zhangfei") ~= nil
        local not_oathed = not game_state:has_flag("taoyuan_oathed")

        return year == 184 and has_liubei and has_guanyu
               and has_zhangfei and not_oathed
    end,

    options = {
        {
            name = "OPTION_OATH",
            trigger = function(game_state)
                game_state:set_flag("taoyuan_oathed")
                game_state:add_relationship("liubei", "guanyu", 100)
            end
        },
        { name = "OPTION_DECLINE" }
    }
}
```

### 4.3 模组加载机制

1. 启动时扫描 `mods/` 目录
2. 根据 `descriptor.mod` 的元数据加载
3. 模组文件覆盖 `core/` 中的同名文件
4. 支持多模组同时启用（按优先级合并）

---

## 5. 核心游戏系统

### 5.1 系统架构总览

```
┌─────────────────────────────────────────────────────────────┐
│                        游戏核心系统                          │
│  ┌──────────────┐  ┌──────────────┐  ┌──────────────┐      │
│  │   地图系统    │  │   时间系统    │  │   势力系统    │      │
│  │  MapSystem   │  │  TimeSystem  │  │ FactionSystem│      │
│  └──────────────┘  └──────────────┘  └──────────────┘      │
│         │                  │                  │             │
│         └──────────────────┼──────────────────┘             │
│                            ▼                                │
│  ┌──────────────────────────────────────────────────────┐  │
│  │                     武将系统                          │  │
│  │  ┌─────────┐  ┌─────────┐  ┌─────────┐              │  │
│  │  │ Hero    │  │Relationship │ Skills │              │  │
│  │  │         │  │   System  │  │         │              │  │
│  │  └─────────┘  └─────────┘  └─────────┘              │  │
│  └──────────────────────────────────────────────────────┘  │
│         │                            │                     │
│         ▼                            ▼                     │
│  ┌──────────────┐          ┌──────────────┐               │
│  │   战斗系统    │          │   内政系统    │               │
│  │ BattleSystem │          │  EconomySystem│              │
│  └──────────────┘          └──────────────┘               │
└─────────────────────────────────────────────────────────────┘
```

### 5.2 地图与省份系统

```yaml
provinces:
  - id: "luoyang"
    name: "PROVINCE_LUOYANG"
    terrain: "plains"               # plains | forest | mountain | water | marsh
    development_level: 5            # 1-10

    base_income:
      gold: 8000
      food: 12000
      materials: 5000

    population:
      total: 500000
      conscription_rate: 0.02
      support_limit: 50000

    city:
      level: 5
      walls: 5000
      buildings:
        - "imperial_palace"
        - "market"

    special:
      - type: "capital_bonus"
        effect: "gold_income +20%"

    neighbors:
      - "chencang"
      - "hulaoguan"
```

### 5.3 时间与季节系统

```yaml
time:
  start_year: 184
  end_year: 280

  speed_settings:
    pause: 0
    slow: "1 day/sec"
    normal: "5 days/sec"
    fast: "15 days/sec"
    very_fast: "30 days/sec"

  seasons:
    spring:
      months: [2, 3, 4]
      effects:
        movement: 1.0
        food_consumption: 1.0

    summer:
      months: [5, 6, 7]
      effects:
        movement: 1.2
        food_consumption: 1.2
        special: "fire_damage +30%"

    autumn:
      months: [8, 9, 10]
      effects:
        food_consumption: 0.8

    winter:
      months: [11, 12, 1]
      effects:
        movement: 0.5
        food_consumption: 1.5
        special: "cavalry -20%"
```

### 5.4 武将系统

```yaml
hero:
  # 基本信息
  id: string
  name: localization_key
  lifespan: { birth_year, death_year }

  # 五维属性 (0-100)
  attributes:
    command: int        # 统帅：军团上限、部队战斗力
    bravery: int        # 勇武：单挑、冲锋
    intelligence: int   # 智力：计谋、火攻
    politics: int       # 政治：内政产出、民心
    charisma: int       # 魅力：招揽、搜人

  # 特质
  traits:
    - id: "weiwei"
      category: "personality"
      effects:
        diplomacy: "can_betray"

  # 技能
  skills:
    - id: "spring_autumn_cut"
      type: "active"
      cooldown: 30

  # 人际关系
  relationships:
    - type: "sworn_brother" | "rival" | "spouse" | "friend"
      target: hero_id
      strength: 0-100
```

---

## 6. AI扩展接口 (预留)

```yaml
# data/ai/ai_config.yml
ai:
  llm_enabled: false              # 默认关闭

  decision_hooks:
    - name: "diplomacy_decision"
      handler: "rule_based"       # rule_based | llm (future)
    - name: "battle_tactics"
      handler: "rule_based"
    - name: "hero_conversation"
      handler: "rule_based"
```

```csharp
// 预留的AI接口
public interface IAIDecisionHandler
{
    AIDecision MakeDecision(string decisionType, Dictionary<string, object> context);
}

// 当前实现：规则引擎
public class RuleBasedAI : IAIDecisionHandler { }

// 预留：未来LLM实现
// public class LLMBasedAI : IAIDecisionHandler { }
```

---

## 7. 待深入设计的子系统

以下子系统需要后续详细设计：

- [ ] 战斗系统 - 兵团战斗、单挑、地形影响
- [ ] 内政系统 - 城池建设、资源管理、人口系统
- [ ] 外交系统 - 势力关系、结盟、背叛
- [ ] 势力系统 - 势力特性、技术树、主公特性
- [ ] 事件系统 - 随机事件、历史事件、剧本事件
- [ ] UI系统 - 大地图界面、城池界面、武将界面

---

## 8. 实施路线 (待定)

1. **阶段1**: 搭建Unity项目基础架构
2. **阶段2**: 数据加载系统和模组框架
3. **阶段3**: 大地图渲染和基本交互
4. **阶段4**: 核心系统实现 (武将、势力、战斗)
5. **阶段5**: UI和游戏循环
6. **阶段6**: 优化和打磨
