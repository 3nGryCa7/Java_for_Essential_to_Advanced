public class PriorityQueueExample {

    public static void main(String[] args) {
            
            // PriorityQueue 基於堆積實作，元素會依照優先權排序，不允許 null 元素
            // 常用方法: add, remove, element, offer, poll, peek
            
            java.util.PriorityQueue<String> queue = new java.util.PriorityQueue<>();
            queue.add("Hello");
            queue.add("World");
            queue.add("I'm Queue");
            
            System.out.println(queue.peek());
            
            while (!queue.isEmpty()) {
                System.out.println(queue.poll());
            }
    }
    
}
