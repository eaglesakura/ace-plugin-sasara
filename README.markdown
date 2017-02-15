# さとうささらボイスプラグイン

| 想定される開発ブランチ | 内容 | ビルドステータス |
|---|---|---|
| master | 最新ビルド（Nightly Build対象） | [![CircleCI](https://circleci.com/gh/eaglesakura/android-app-template/tree/master.svg?style=svg)](https://circleci.com/gh/eaglesakura/android-app-template/tree/master) |
| develop | 最新の開発版, DEBUG版デプロイ用 | - |
| feature/id/{issue num} | issue対応 | - |
| v${バージョン名} | リリースビルド | - |

## 環境変数設定

| 環境変数名 | 内容 | 備考 |
|---|---|---|
| IMGMAGICK_CONVERT | ImageMagick convertコマンドパス | mipmap出力を行う場合 |
| IMGMAGICK_IDENTIFY | ImageMagick identifyコマンドパス | mipmap出力を行う場合 |
| DEPLOYGATE_USER_NAME | CIからDeploygateへDeployする場合のアカウント名 | CIのみ |
| DEPLOYGATE_API_KEY | CIからDeploygateへDeployする場合のAPIキー | CIのみ |
| SLACK_HOOK_URL | CIからSlack通知を行う場合のURL | CIのみ |
