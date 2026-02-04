# 🏆 致谢与贡献者

## 📦 项目信息

### 本项目
- **项目名称**: CrazyCrates - KillGoddess 定制版
- **仓库地址**: https://github.com/KillGoddess/zoo
- **维护者**: KillGoddess
- **版本**: v5.0.14
- **协议**: MIT License

---

## 🎯 原始项目

本项目基于 **CrazyCrew** 团队开发的 **CrazyCrates** 插件进行修改和扩展。

### 原项目信息
- **项目名称**: CrazyCrates
- **仓库地址**: https://github.com/Crazy-Crew/CrazyCrates
- **作者**: CrazyCrew
- **官方网站**: https://docs.crazycrew.us/docs/plugins/crazycrates
- **Discord 社区**: https://discord.gg/badbones-s-live-chat-182615261403283459
- **协议**: MIT License
- **版权**: Copyright (c) 2016-2024 CrazyCrew

### 原项目主要贡献者
- **CrazyCrew 团队** - 核心开发团队
- **ryderbelserion** - 主要维护者
- 以及所有为原项目做出贡献的开发者

感谢 CrazyCrew 团队创建了这个优秀的开源项目，为 Minecraft 服务器社区提供了强大的抽奖系统！

---

## ✨ 本定制版新增功能

### 由 KillGoddess 开发的功能

#### 🎰 RandomBox 抽奖动画
- **灵感来源**: random-box-master 插件
- **开发者**: KillGoddess
- **实现文件**: `RandomBoxCrate.java`
- **配置示例**: `RandomBox.yml`, `RandomBoxExample.yml`
- **文档**: `RandomBox箱子使用说明.md`, `RandomBox配置示例说明.md`

**功能特点**:
- 传送带风格动画（物品从右向左滚动）
- 4段变速系统（快→中→慢→极慢）
- 54格界面设计（6行×9列）
- 灰色玻璃边框装饰
- 完美兼容保底系统

#### 🎁 五连抽功能
- **开发者**: KillGoddess
- **实现文件**: `CrateInteractListener.java`
- **触发方式**: Shift + 右键点击箱子

**功能特点**:
- 智能检测钥匙数量（物理+虚拟）
- 自动消耗5把钥匙
- 即时发放5个奖品
- 正确累计保底进度
- 中文提示消息

#### 🎲 保底系统（单层机制）
- **开发者**: KillGoddess
- **实现文件**: 
  - `Prize.java` - 保底配置字段
  - `Crate.java` - 保底逻辑实现
  - `BukkitUserManager.java` - 保底计数管理
  - `PlaceholderAPISupport.java` - PlaceholderAPI 支持

**功能特点**:
- 每个箱子独立计数
- 灵活的保底配置
- 触发时的华丽提示
- 自动重置机制
- PlaceholderAPI 变量支持

#### 🔧 兼容性修复
- **开发者**: KillGoddess
- **修复内容**:
  - Purpur 1.21.4 兼容性（`NoSuchFieldError: UNBREAKABLE` 修复）
  - 不可变集合问题修复
  - 保底系统计数逻辑修复

#### 📚 中文文档
- **作者**: KillGoddess
- **文档列表**:
  - `README.md` - 项目主文档（完全重写）
  - `更新日志.md` - 详细的版本更新记录
  - `CREDITS.md` - 本文件
  - `RandomBox箱子使用说明.md` - RandomBox 使用指南
  - `RandomBox配置示例说明.md` - 配置详解
  - `上传到GitHub指南.md` - GitHub 上传教程

---

## 🛠️ 使用的技术和库

### 核心依赖
- **Paper API** - Minecraft 服务端 API
  - 官网: https://papermc.io
  - 用途: 插件核心功能实现

- **Fusion Library** - 工具库
  - 作者: ryderbelserion
  - 用途: 物品构建、配置管理等

- **PlaceholderAPI** - 变量系统
  - 官网: https://www.spigotmc.org/resources/placeholderapi.6245/
  - 用途: 保底计数变量支持

### 构建工具
- **Gradle 8.12** - 项目构建工具
- **Java 21** - 开发语言

### 开发环境
- **IntelliJ IDEA** - 推荐的 IDE
- **Git** - 版本控制

---

## 🌟 特别感谢

### 原项目团队
感谢 **CrazyCrew** 团队开发了 CrazyCrates 这个优秀的开源项目，为本定制版提供了坚实的基础。

### 社区支持
- **Paper 团队** - 提供优秀的服务端实现
- **Purpur 团队** - 提供增强的服务端功能
- **PlaceholderAPI 团队** - 提供强大的变量系统
- **Minecraft 插件开发社区** - 提供技术支持和灵感

### 灵感来源
- **random-box-master** - RandomBox 动画的灵感来源
- **原神（Genshin Impact）** - 保底系统的设计灵感

---

## 📊 项目统计

### 代码贡献
- **原始代码**: CrazyCrew/CrazyCrates
- **新增代码**: KillGoddess
  - RandomBox 动画实现 (~500 行)
  - 五连抽功能 (~100 行)
  - 保底系统 (~300 行)
  - 兼容性修复 (~50 行)
  - 中文文档 (~2000 行)

### 文件统计
- **新增 Java 文件**: 1 个 (`RandomBoxCrate.java`)
- **修改 Java 文件**: 5 个
- **新增配置文件**: 2 个
- **新增文档文件**: 6 个

---

## 🤝 如何贡献

### 为本定制版贡献
如果你想为本定制版做出贡献：

1. **Fork 本仓库**: https://github.com/KillGoddess/zoo
2. **创建特性分支**: `git checkout -b feature/AmazingFeature`
3. **提交你的修改**: `git commit -m 'Add some AmazingFeature'`
4. **推送到分支**: `git push origin feature/AmazingFeature`
5. **开启 Pull Request**

### 为原项目贡献
如果你想为原始项目做出贡献：

1. **访问原项目**: https://github.com/Crazy-Crew/CrazyCrates
2. **阅读贡献指南**: 查看原项目的 CONTRIBUTING.md
3. **提交 Pull Request** 到原项目仓库

---

## 📜 版权声明

### 原始项目
```
MIT License
Copyright (c) 2016-2024 CrazyCrew
```

### 修改版本
```
MIT License
Copyright (c) 2026 KillGoddess
```

### 声明
- 本项目是基于 CrazyCrates 的修改版本
- 所有修改内容均以 MIT 协议开源
- 原项目的所有权利归 CrazyCrew 所有
- 修改部分的版权归 KillGoddess 所有
- 使用本插件造成的任何问题，开发者不承担责任

---

## 🔗 相关链接

### 本定制版
- **GitHub**: https://github.com/KillGoddess/zoo
- **Issues**: https://github.com/KillGoddess/zoo/issues
- **维护者**: KillGoddess

### 原始项目
- **GitHub**: https://github.com/Crazy-Crew/CrazyCrates
- **官方文档**: https://docs.crazycrew.us/docs/plugins/crazycrates
- **Discord**: https://discord.gg/badbones-s-live-chat-182615261403283459
- **bStats**: https://bstats.org/plugin/bukkit/CrazyCrates/4514

### 相关资源
- **Paper**: https://papermc.io
- **Purpur**: https://purpurmc.org
- **PlaceholderAPI**: https://www.spigotmc.org/resources/placeholderapi.6245/

---

## 💖 支持项目

### 支持本定制版
- ⭐ 给本项目一个 Star
- 🐛 报告 Bug 和问题
- 💡 提出新功能建议
- 🤝 提交 Pull Request
- 📢 分享给其他人

### 支持原始项目
- ⭐ 给原项目一个 Star: https://github.com/Crazy-Crew/CrazyCrates
- 💬 加入官方 Discord 社区
- 📖 完善官方文档
- 🤝 为原项目贡献代码

---

<div align="center">

**再次感谢所有为开源社区做出贡献的开发者！**

**特别感谢 CrazyCrew 团队创建了这个优秀的项目！**

Made with ❤️ by KillGoddess

Based on CrazyCrates by CrazyCrew

</div>
