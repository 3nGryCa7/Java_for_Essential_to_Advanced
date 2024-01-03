import java.util.HashSet;
import java.util.Set;

public class SetExample {

    public static void main(String[] args) {

        // Collection 衍生出 List, Set, Queue

        // Set 儲存無序且不重複的元素，常見的實作有 HashSet, TreeSet
        // HashSet 基於 HashMap 實作，不保證元素的排列順序，允許 null 元素
        // TreeSet 基於 TreeMap 實作，保證元素的排列順序，不允許 null 元素
        // 常用方法: add, remove, contains, size, isEmpty, clear, toArray, iterator

        Set<String> set = new HashSet<>();
        set.add("Hello");
        set.add("World");

        for (String s : set) {
            System.out.println(s);
        }

        if (set.contains("Hello")) {
            System.out.println("Has Hello");
        }

    }
}