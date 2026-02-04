# 📤 上传到 GitHub 指南

## 🎯 超简单！只需3步，5分钟完成

---

## 准备工作

### 1. 在 GitHub 创建新仓库

1. 打开浏览器，访问：https://github.com/new
2. 登录你的 GitHub 账号
3. 填写信息：
   - **Repository name**: `CrazyCrates-Custom`（或你喜欢的名字）
   - **Description**: `CrazyCrates 插件 - 添加了 RandomBox 动画和五连抽功能`
   - **Public/Private**: 选择 Private（私有）或 Public（公开）
   - ⚠️ **重要**：不要勾选 "Initialize this repository with a README"
4. 点击 **"Create repository"**

---

## 方法1：使用命令行（最快，推荐）

### 第1步：打开 PowerShell

在当前文件夹按住 Shift + 右键，选择"在此处打开 PowerShell 窗口"

或者直接在 PowerShell 中输入：
```powershell
cd "D:\WorkFiles\PlayChest_插件\CascadeProjects\CrazyCrates-main"
```

### 第2步：复制粘贴以下命令

**⚠️ 重要：将下面的 `你的用户名` 和 `你的仓库名` 替换成你刚才创建的！**

```powershell
# 添加远程仓库
git remote add origin https://github.com/你的用户名/你的仓库名.git

# 推送到 GitHub
git push -u origin master
```

### 第3步：输入 GitHub 凭证

- 如果弹出登录窗口，输入你的 GitHub 用户名和密码
- 如果需要 Token，去 https://github.com/settings/tokens 创建一个

**完成！** 🎉

---

## 方法2：使用 GitHub Desktop（最简单，适合新手）

### 第1步：下载并安装 GitHub Desktop

1. 访问：https://desktop.github.com/
2. 下载并安装
3. 打开后登录你的 GitHub 账号

### 第2步：添加本地仓库

1. 点击 **File** → **Add Local Repository**
2. 选择文件夹：`D:\WorkFiles\PlayChest_插件\CascadeProjects\CrazyCrates-main`
3. 点击 **Add Repository**

### 第3步：发布到 GitHub

1. 点击顶部的 **Publish repository** 按钮
2. 填写信息：
   - **Name**: `CrazyCrates-Custom`
   - **Description**: `CrazyCrates 插件 - 添加了 RandomBox 动画和五连抽功能`
   - 选择是否 **Keep this code private**（保持私有）
3. 点击 **Publish Repository**

**完成！** 🎉

---

## 方法3：手动上传（最慢，不推荐）

如果上面两个方法都不行，可以手动上传：

1. 在 GitHub 创建新仓库
2. 点击 **uploading an existing file**
3. 将整个文件夹拖拽到浏览器
4. 点击 **Commit changes**

⚠️ 注意：这个方法会丢失 Git 历史记录

---

## 📋 完整命令参考

如果你想一次性复制所有命令，这里是完整版本：

```powershell
# 进入项目目录
cd "D:\WorkFiles\PlayChest_插件\CascadeProjects\CrazyCrates-main"

# 查看当前状态
git status

# 添加远程仓库（替换成你的信息）
git remote add origin https://github.com/你的用户名/你的仓库名.git

# 推送到 GitHub
git push -u origin master
```

---

## ❓ 常见问题

### Q1: 提示 "remote origin already exists"
**A:** 先删除旧的远程仓库：
```powershell
git remote remove origin
git remote add origin https://github.com/你的用户名/你的仓库名.git
```

### Q2: 提示需要 Personal Access Token
**A:** 
1. 访问：https://github.com/settings/tokens
2. 点击 **Generate new token (classic)**
3. 勾选 **repo** 权限
4. 生成后复制 Token
5. 在推送时用 Token 代替密码

### Q3: 推送失败，提示 "rejected"
**A:** 可能是远程仓库有内容，使用强制推送：
```powershell
git push -u origin master --force
```

### Q4: 中文文件名显示乱码
**A:** 这是正常的，GitHub 上会正确显示

---

## 🎉 上传成功后

访问你的 GitHub 仓库：
```
https://github.com/你的用户名/你的仓库名
```

你会看到：
- ✅ 所有源代码
- ✅ RandomBox 动画
- ✅ 五连抽功能
- ✅ 保底系统
- ✅ 完整的配置文件
- ✅ 使用说明文档

---

## 📞 需要帮助？

如果遇到问题，告诉我：
1. 你使用的是哪个方法？
2. 出现了什么错误提示？
3. 你的 GitHub 用户名是什么？

我会帮你解决！

---

**版本**: v5.0.14  
**日期**: 2026-02-04  
**准备完成**: ✅ 所有文件已提交到本地 Git
