### Mission

請撰寫撲克牌類別 Card, 代表一張撲克牌, 可定義其花色(enum 型態:Spades,Hears,Diamonds, Clubs)和點數(int, 範圍 1-13). 自行建立一撲克牌陣列 Card cards[], 包含 52 張撲克牌. 列印出此副撲克牌. 然後對此副撲克牌執行洗牌(自行撰寫 shuffle 方法), 並進行發牌(自行撰寫 dealCards 方法), 發給四家 (存入 4 個 LinkedQueue), 並列印四家拿到的牌.

#### Funciton

- method
  - [v] Shuffle : 洗牌功能
  - [v] DealCards : 給玩家發牌 (預設四人，每位玩家 13 張)
  - [v] GenerateCards : 初始化牌組

- card
  - [v] Card

- main.java :
  1. 生成牌組
  2. 隨機洗牌
  3. 發四副牌
