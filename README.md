# AndroidTest2

AndroidTest2 是一个面向初学者和自动化集成场景的 Android Demo 仓库，旨在帮助用户无需本地 Android 环境即可体验 Android 应用开发与自动化构建流程。

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
  - 代码结构清晰，便于二次开发
  - 适合 Android 新手、CI/CD 流程演示、自动化测试等
- **适用人群**：
  - 没有本地 Android 环境但想体验 Android 自动化构建的开发者
  - 需要快速验证 Android CI/CD 流程的团队
  - 需要最小可用 Android 工程模板的学习者

## 目录结构

```
AndroidTest2/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/example/androidtest2/MainActivity.java
│           └── res/
│               ├── layout/activity_main.xml
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

---
如有问题欢迎提 issue！ 