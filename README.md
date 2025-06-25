# AndroidTest2

这是一个简单的 Android Demo 项目，主界面显示 "Hello World"。

## 使用 GitHub Actions 自动构建 APK

1. 推送代码到 GitHub。
2. 在 `.github/workflows` 目录下添加 CI 配置（见下方示例）。
3. 每次 push 会自动构建 APK。

### 示例 GitHub Actions Workflow

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