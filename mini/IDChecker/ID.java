package IDChecker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ID {

    // 功能: 獲取英文代號所代表之中文名稱與數字
    private static List<String> search(char code) {

        List<List<String>> codeList = new ArrayList<>() {{
            add(Arrays.asList("台北市", "10"));
            add(Arrays.asList("臺中市", "11"));
            add(Arrays.asList("基隆市", "12"));
            add(Arrays.asList("台南市", "13"));
            add(Arrays.asList("高雄市", "14"));
            add(Arrays.asList("新北市", "15"));
            add(Arrays.asList("宜蘭縣", "16"));
            add(Arrays.asList("桃園市", "17"));
            add(Arrays.asList("嘉義市", "34"));
            add(Arrays.asList("新竹縣", "18"));
            add(Arrays.asList("苗栗縣", "19"));
            add(Arrays.asList("臺中縣", "20"));
            add(Arrays.asList("南投縣", "21"));
            add(Arrays.asList("彰化縣", "22"));
            add(Arrays.asList("新竹市", "35"));
            add(Arrays.asList("雲林縣", "23"));
            add(Arrays.asList("嘉義縣", "24"));
            add(Arrays.asList("台南縣", "25"));
            add(Arrays.asList("高雄縣", "26"));
            add(Arrays.asList("屏東縣", "27"));
            add(Arrays.asList("花蓮縣", "28"));
            add(Arrays.asList("台東縣", "29"));
            add(Arrays.asList("金門縣", "32"));
            add(Arrays.asList("澎湖縣", "30"));
            add(Arrays.asList("陽明山", "31"));
            add(Arrays.asList("連江縣", "33"));
        }};

        return codeList.get(code-65);
    }

    // 性別對照
    static Map<Character, String> genderMap = new HashMap<>() {{
        put('1', "男");
        put('2', "女");
    }};
    // 驗證權重
    static Integer[] weight = {1, 9, 8, 7, 6, 5, 4, 3, 2, 1};


    // 功能一: 驗證身分證
    public static void verify(String id) {

        /*
        * 驗證規則:
        *  ++ 英文轉換2位數字後，分別 *1、9
        *  ++ 前八位數字，分別 *8、7、6、5、4、3、2、1
        *  ++ 以上數字加總 +檢核碼，若能被10整除，即正確
        */

        // 測試一: 第一位是否為字母(地區)
        boolean rightRegion = id.charAt(0) >= 'A' && id.charAt(0) <= 'Z';
        // 測試二: 第二位是否為性別(1/2)
        boolean rightGender = id.charAt(1) == '1' || id.charAt(1) == '2';
        // 測試三: 長度是否正確
        boolean rightLength = id.length() == 10;

        if (rightRegion && rightGender && rightLength) {
            // 符合正確格式

            String regionNum = search(id.charAt(0)).get(1); // 地區數字
            String region = search(id.charAt(0)).get(0);    // 地區名稱
            Integer checkNum = Integer.parseInt(id.substring(9, 10));   // 檢核碼
            id = regionNum.concat(id.substring(1, 9));
            Integer sum = 0;

            // 計算驗證權重
            for (int i=0; i<id.length(); i++) {
                sum += Character.getNumericValue(id.charAt(i)) * weight[i]; 
            }
            sum += checkNum;
            if (sum % 10 == 0) {
                // 通過驗證
                System.out.printf("是位出生在%s的%s性朋友呢\n", 
                                region, genderMap.get(id.charAt(2)));
            } else {
                // 未通過驗證
                System.out.println("身分證字號錯誤");
            }

        } else {
            System.out.println("格式不符");
        }    
    }


    // 功能二: 產生身分證
    public static void generator(String city, String gender) {

        /*
        * 台灣身分證規則:
        *  ++ 1 個英文字母作為"出生地"
        *  ++ 1 個數字代表"性別" (男:1 女:2)
        *  ++ 7 個0-9"隨機數字"
        *  ++ 1 個"檢核碼"
        */
        
        // 地區代號
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        Character regionCode = ' ';

        // 檢測使用者輸入區域是否在範圍內，若是則給定地區代號
        for (char c: alphabet) {
            if (city.equals(search(c).get(0))) {
                regionCode = c;
            }
        }

        // 測試區域與性別是否正確
        boolean wrongRegion = regionCode.equals(' ');
        boolean rightGender = genderMap.containsValue(gender);

        if (wrongRegion) {
            System.out.println("縣市錯誤");
        } else if (!rightGender) {
            System.out.println("性別錯誤");
        } else {
            
            String regionNum = search(regionCode).get(1);   // 地區數字
            String genderCode = gender.equals("男") ? "1" : "2";    // 性別數字
            String id = Character.toString(regionCode).concat(genderCode);  // 建立ID
            Integer sum = Character.getNumericValue(regionNum.charAt(0)) * weight[0] + 
                          Character.getNumericValue(regionNum.charAt(1)) * weight[1] +
                          Integer.parseInt(genderCode) * weight[2]; // 驗證權重

            Random random = new Random();
            for (int i=0; i<7; i++) {
                Integer num = random.nextInt(10);   // 亂數生成
                id = id.concat(Integer.toString(num));   // 連接id
                sum += num * weight[3+i];   // 加權驗證
            }

            // 計算檢核碼
            Integer checkNum = (sum/10 + 1) * 10 - sum;
            id = id.concat(Integer.toString(checkNum));

            System.out.println(id);
        }

    }
}
