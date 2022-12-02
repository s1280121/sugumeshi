# 簡易仕様書

### 作者
荒 遥斗

### アプリ名
スグめし

### コンセプト
お腹が空いた時、すぐに近くの飲食店を探せる

### 該当プロジェクトのリポジトリ URL（GitHub,GitLab など Git ホスティングサービスを利用されている場合）
https://github.com/s1280121/sugumeshi


## 開発環境
### 開発環境
Android Studio Dolphin | 2021.3.1 Patch 1

### 開発言語
Kotlin 1.5.0

## 動作対象端末・OS
### エミュレータ
Pixel 5 / Android 12.0.0
### 実機
SOV34 / Android 8.0.0


## 開発期間
2週間


## アプリケーション機能

### 機能一覧
- 位置情報取得：GPSを使用して、現在地を取得する。
- レストラン検索：ホットペッパーグルメサーチAPIを使用して、現在地から3000m以内までの距離にある飲食店を検索し、一覧表示する。
- レストラン情報取得：ホットペッパーグルメサーチAPIを使用して、飲食店の詳細情報を取得する。


### 画面一覧
- タイトル画面 ：アプリのタイトルを表示する。
- ローディング画面 ：現在地が取得できるまで表示される。
- 検索&一覧画面 ：キーワードや範囲を指定してレストランを検索する。また、条件に合った飲食店を一覧表示する。
- 詳細画面 ：一覧画面で選択された飲食店の詳細を表示する。  

<img width="1096" alt="screen" src="https://user-images.githubusercontent.com/87113276/203691490-e7930abd-d8ed-424c-b270-6a8cadad2dc4.png">


### 使用しているAPI,SDK,ライブラリなど
- ホットペッパーグルメサーチAPI
- OkHttp3
- Retrofit2
- Glide
- Data Binding  
- ExpandableLayout


## こだわったポイント
・詳細画面でExpandableLayout(折りたたみ)を使用したため、知りたい情報だけ選んで見れる  
・自分が飲食店でバイトしているため、よくお客様に聞かれる内容を詳細画面に追加した  
・位置情報取得と店舗検索を同じ画面で行うことで、位置情報取得画面に戻り画面が動かなくなる不具合を解決した  
・画面数が少なくなったため、スプラッシュ画面を作成した  

## デザイン面でこだわったポイント
・ボタンやitemをタップした際、色が変わるようにした  
・範囲選択をSpinnerで行える  
・ExpandableLayoutの開閉に合わせて、カードの矢印も上下が入れ替わる  
・駐車場や予算の情報が何もない場合、カードごとレイアウトが消える  


## アドバイスして欲しいポイント
・詳細画面で、カードごとにクリックイベントを作成しているのですが、もっと簡単にできる方法があれば教えてほしいです。  
・カードの矢印の上下をアニメーションで入れ替わるようにしたのですが、selectorでは出来ないのでしょうか？selectorでは上手く出来ませんでした。  
・ホットペッパーグルメサーチAPIのPhotoのレスポンスフィールドで、PCとMobileの違いはなんでしょうか？最初はMobileを使用していたのですが、画質が綺麗だったためPCの方を使用することにしました。  

## 自己評価 
APIについて全く知らないところからの開発だったが、調べていく中で理解を深めることが出来た。RecyclerViewなど以前に使ったことがある技術もあったためそこは実装しやすかった。しかし、RepositoryやViewModelあたりがよく分かっていないため今後理解していく必要がある。今回の開発では、ユーザーにとって使いやすいデザインを意識し、色や動作にかなり気を使った。見た目や使いやすさを優先しすぎて、コードが雑になっている部分もあるため、改善していきたい。

## アプリ動画
https://user-images.githubusercontent.com/87113276/203689687-c5a7f89b-55ff-4eb2-bef6-a5335a63d4ba.mov
