public class StackExample {
    
    public static void main(String[] args) {
            
            // Stack 基於 Vector 實作，後進先出 (LIFO)
            // 常用方法: push, pop, peek, empty, search
            
            java.util.Stack<String> stack = new java.util.Stack<>();
            stack.push("Hello");
            stack.push("World");
            stack.push("I'm Stack");
            
            System.out.println(stack.peek());

            while (!stack.empty()) {
                System.out.println(stack.pop());
            }
    }

}
