# AndroidTest2

**文档日期：2025.6.26**

AndroidTest2 是一个面向初学者和自动化集成场景的 Android Demo 仓库，旨在帮助用户无需本地 Android 环境即可体验 Android 应用开发、页面跳转、自动化构建与产物下载的完整流程。

## 仓库简介

- **用途**：本仓库用于演示如何通过 GitHub Actions 实现 Android 应用的自动化构建和 APK 产出，适合用于学习、CI/CD 流程测试、快速原型开发等场景。
- **技术栈**：
  - 语言：Java（主 Activity）
  - 构建工具：Gradle
  - 依赖管理：官方 AndroidX 组件、Material Design
  - 持续集成：GitHub Actions（无需本地 Android 环境）
- **主要特性**：
  - 推送代码到 GitHub 后自动构建 APK
  - 构建产物自动上传，可直接下载
  - 主界面可跳转至 Demo 页面、计数器页面、随机数页面
  - 代码结构清晰，便于二次开发
  - 适合 Android 新手、CI/CD 流程演示、自动化测试等
- **适用人群**：
  - 没有本地 Android 环境但想体验 Android 自动化构建的开发者
  - 需要快速验证 Android CI/CD 流程的团队
  - 需要最小可用 Android 工程模板的学习者

## 应用页面与功能

### 主界面（MainActivity）
- 显示欢迎文本"Hello World!"。
- 提供三个按钮，分别跳转到 Demo 页面、计数器页面、随机数页面。

### Demo 页面（DemoActivity）
- 仅展示一个大号文本："Demo 页面"。
- 适合作为自定义页面开发的模板。

### 计数器页面（CounterActivity）
- 显示一个数字（初始为 0）。
- 点击"计数+1"按钮，数字加一。
- 适合演示状态管理、事件响应等基础交互。

### 随机数页面（RandomNumberActivity）
- 显示"随机数结果："及一个数字。
- 点击"生成随机数"按钮，会在下方显示一个 1~10000 的随机整数。
- 适合演示简单逻辑处理和 UI 更新。

## 主界面导航示意

主界面按钮顺序如下：
1. 进入 Demo 页面
2. 进入计数器页面
3. 进入随机数页面

每个页面均可通过主界面按钮一键跳转。

## 程序流程图

```mermaid
flowchart TD
    A[启动应用] --> B{主界面}
    B --> C[进入 Demo 页面]
    B --> D[进入计数器页面]
    B --> E[进入随机数页面]
    D --> F[点击"计数+1"按钮，数字加一]
    E --> G[点击"生成随机数"按钮，显示1~10000的随机数]
```

## 目录结构

```
AndroidTest2/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/androidtest2/
│           │   ├── MainActivity.java
│           │   ├── DemoActivity.java
│           │   ├── CounterActivity.java
│           │   └── RandomNumberActivity.java
│           └── res/
│               ├── layout/activity_main.xml
│               ├── layout/activity_demo.xml
│               ├── layout/activity_counter.xml
│               ├── layout/activity_random_number.xml
│               ├── mipmap-anydpi-v26/ic_launcher.xml
│               ├── mipmap-anydpi-v26/ic_launcher_round.xml
│               └── values/
│                   ├── colors.xml
│                   ├── strings.xml
│                   └── themes.xml
├── build.gradle
├── settings.gradle
├── .gitignore
├── local.properties
└── .github/
    └── workflows/android.yml
```

## GitHub Actions 自动构建 APK

本项目已配置好 GitHub Actions 工作流，推送到 GitHub 后会自动构建 APK 并上传。

**现在也支持在 GitHub Actions 页面手动触发工作流（workflow_dispatch）！**

### 手动触发方法
1. 进入你的 GitHub 仓库页面，点击上方的 "Actions" 选项卡。
2. 在左侧选择 `Build-App` 工作流。
3. 点击 "Run workflow" 按钮，即可手动触发一次构建。

### 步骤
1. 将本项目代码推送到你的 GitHub 仓库。
2. Actions 会自动运行，无需本地 Android 环境。
3. 构建完成后，可在 GitHub Actions 的构建详情页面下载 APK（app-debug-apk）。

### 工作流配置（.github/workflows/android.yml）
```yaml
name: Android CI
on:
  push:
    branches: [ main ]
  pull_request:
    branches: [ main ]
  workflow_dispatch:
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - name: Set up JDK 17
        uses: actions/setup-java@v4
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Build with Gradle
        run: ./gradlew assembleDebug
      - name: Upload APK
        uses: actions/upload-artifact@v4
        with:
          name: app-debug-apk
          path: app/build/outputs/apk/debug/app-debug.apk
```

## 本地构建（可选）
如需本地构建，请确保已安装 Android Studio 和 Android SDK，并在 `local.properties` 中配置 sdk.dir。

```
sdk.dir=/your/Android/Sdk/path
```

然后运行：
```
./gradlew assembleDebug
```

## 常见问题
- **Q: 本地没有 Android 环境可以构建吗？**
  - A: 可以直接推送到 GitHub，Actions 会自动构建。
- **Q: APK 在哪里下载？**
  - A: 在 GitHub Actions 的构建详情页面，找到 "app-debug-apk" 下载。
- **Q: 如何更改包名/应用名？**
  - A: 修改 `app/build.gradle` 和相关资源文件即可。
- **Q: 各页面如何体验？**
  - A: 安装 APK 后，主界面有三个按钮，分别进入 Demo、计数器、随机数页面，体验各自功能。

---
如有问题欢迎提 issue！

**联系人：lili@cursor.com**

## License

This project is licensed under the MIT License.

```
MIT License

Copyright (c) 2024 YOUR_NAME

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
``` 