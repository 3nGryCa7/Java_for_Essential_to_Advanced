# 成績排序
### 前情提要
- scores.txt: 檔案為500位同學的考試成績,每行資料依序為學號,國文,英文,數學

### 須完成任務
1. 請設計資料結構可載入所有同學成績,計算總分並列印.

2. 請將所有同學依(a)總分、(b)國文、(c)英文、(d)數學進行排序, 印出排序結果.
    > 若總分一樣則先比較國文成績，國文成績又一樣則再比較英文成績，英文成績又一樣最後才比較數學成績，所以只會有一種output，而不是分別用總分、國文、英文、數學排
    
### 實現方法
實作 Comparator<T>，內部型別套用 (Class) Student，根據比較條件覆寫(@Override) compare function ```-1|0|1 -> le|e|ge```.
> Comparators can be passed to a sort method (such as Collections.sort or Arrays.sort) to allow precise control over the sort order

<br>

# 成績分發
### 前情提要
- school.txt: 是十所學校的錄取名額
- wish.txt: 是各考生選填志願序列(0代表終止符號)

### 須完成任務
1. 請分別印出十所學校的錄取考生編號
2. 請印出沒有錄取任何學校的考生編號
3. 請將所有考生的編號,總分與錄取學校號碼印出

### 注意事項 
依照成績高低順序,高分者優先選擇志願

### 實現方法
關鍵是「依照成績高低順序,高分者優先選擇志願」.

所以可以從排序結果，查看該學生相對應的志願學校。若是該校招生額滿 (可招名額=0)，則往下一志願尋找，若是皆招滿，則落榜無校 ; 反之有名額則錄取 (可招生名額-1)。

新增 Student 類別的屬性: this.wishes, this.school。若是錄取，須將學校加入該學生的 this.School。

每間學校的招生名額與所招的學生列表，使用 HashTable 結構。
> HashMap v.s. HashTable
> 
> HashMap 可接受 `null` 作為 key/value值，沒有支援執行緒安全。
> 
> HashTable 不接受 `null` 作為 key/value值，有支援執行緒安全。