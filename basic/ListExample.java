import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListExample {
    
    public static void main(String[] args) {
        
        // List 常用的實作有 ArrayList, LinkedList
        // ArrayList 基於陣列實作，查詢快，增刪慢
        // LinkedList 基於雙向鏈結串列實作，查詢慢，增刪快
        // 常用方法: add, remove, contains, size, isEmpty, clear, toArray, iterator, get, set, indexOf, lastIndexOf, subList

        List<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("World");
        list.add("I'm List");

        // 尋訪元素 
        // Way 1
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        // Way 2
        for (String s : list) {
            System.out.println(s);
        }

        // Way 3
        list.forEach(s -> System.out.println(s));
        // list.forEach(System.out::println);

        // Way 4
        for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
            System.out.println(iter.next());
        }

        // List 與 Array 互轉
        // 序列轉陣列
        String[] array = list.toArray(new String[list.size()]);
        for (String s : array) {
            System.out.println("Array: " + s);
        }

        // 陣列轉序列
        // Arrays.asList(array) 會回傳一個 List，但是該 List 不支援增刪元素
        // 如果要增刪元素，可以使用 ArrayList、LinkedList 的建構子

    }

}
