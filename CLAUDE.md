# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Sanguo is a Three Kingdoms strategy game built with Java 8 and Spring Boot 2.4.2. The project follows Domain-Driven Design (DDD) architecture with a multi-module Maven structure.

## Build and Run

```bash
# Build the entire project (from sanguo-game/ directory)
mvn clean install

# Run the application
mvn spring-boot:run -pl launcher

# Run with specific profile (if applicable)
mvn spring-boot:run -pl launcher -Dspring-boot.run.arguments=--spring.profiles.active=dev
```

The application runs on port 8080 by default. Swagger UI is available at http://localhost:8080/swagger-ui/ when enabled.

## Architecture

This is a DDD (Domain-Driven Design) multi-module Maven project located in `sanguo-game/`:

### Module Structure

- **launcher** - Application entry point (`SanguoLauncher.java`). Contains Spring Boot bootstrap and main configuration.
- **adapter** - External interface adapters
  - `rest` - REST controllers (e.g., `HeroController`)
  - `persistence` - Data persistence adapters
- **application** - Application services layer (orchestrates domain logic)
- **domain** - Core business logic
  - `hero` - Hero domain with battle-related entities
  - `common` - Shared domain concepts like `Person` with naming value objects (FamilyName, GivenName, StyleName, NickName)
- **common** - Cross-cutting technical modules
  - `lang` - Base DDD annotations (@Entity, @ValueObject, @Repository, @DomainService, @ApplicationService, @DomainEvent)
  - `facade` - API facade definitions (currently empty, reserved)
  - `openapi` - OpenAPI/Swagger definitions (currently empty, reserved)
- **contrib** - Third-party contributions or utilities

### Key Domain Concepts

- **Hero (武将)**: Core entity representing military generals. Interface `Hero` with abstract base `AbstractHero`.
- **Person**: Represents person naming conventions from the Three Kingdoms era:
  - Family Name (姓) - e.g., Guan for GuanYu
  - Given Name (名) - e.g., Yu for GuanYu
  - Style Name (字) - e.g., Yunchang for GuanYu
  - Pseudo Name (号) - rarely used in Three Kingdoms era
  - Nick Names (昵称) - common names like "Guan Gong", "Er Ye"

- **Battle System**: Located in `domain/hero/battle/` with concepts like OffenseSide, DefenseSide, BattleResult, HeroGroup

## Game Design Notes

The game aims to recreate Three Kingdoms strategy with:
- Real-time tactical battles inspired by Nobunaga's Ambition 12
- Duel (单挑) system inspired by Romance of the Three Kingdoms 11
- Debate (舌战) system
- Domestic affairs with city planning elements
- Five hero stats: Command (统帅), Bravery (勇武), Intelligence (智力), Politics (政治), Charisma (魅力)

See `sanguo-game/doc/idea_about_game.md` for detailed game mechanics.

## Dependency Management

The project uses `${revision}` property for version management (default: 1.0.0-SNAPSHOT). All dependencies are managed in the root `sanguo-game/pom.xml`.

Key dependencies:
- Spring Boot 2.4.2
- Lombok 1.18.16
- Guava 30.1-jre
- Swagger 2.1.6 with SpringFox 3.0.0

## Git Commit Conventions

This project follows [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) specification. See `docs/git-conventions.md` for detailed guidelines.

**Quick reference**:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types**: `feat`, `fix`, `docs`, `style`, `refactor`, `perf`, `test`, `chore`, `revert`

**Scopes**: `core`, `data`, `map`, `hero`, `battle`, `economy`, `ui`, `mod`, `render`, `build`

**Examples**:
```
feat(hero): 添加武将五维属性系统

- 添加 command, bravery, intelligence, politics, charisma 属性
- 实现属性对战斗的影响计算

Closes #123
```

```
fix(battle): 修复骑兵在森林地形不正确的移动速度

Fixes #145
```
