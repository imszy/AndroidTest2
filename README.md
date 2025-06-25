# AndroidTest2

这是一个简单的 Android Demo 项目，主界面显示 "Hello World"。

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