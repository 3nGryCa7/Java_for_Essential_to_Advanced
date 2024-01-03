import java.util.Random;
import java.util.Scanner;

public class GuessGame {
    
    public static void main(String[] args) {
        
        /* 亂數產生器 */
        Random random = new Random();
        
        /* 使用者輸入接收器 */
        Scanner scanner = new Scanner(System.in);
        Integer input, guess;
        boolean keep = true;
        boolean right = false;

        while (keep == true) {
            /* 初始公告 */
            System.out.println("********** Welcome to GuessGame **********");
            System.out.println("The answer\'s range is between 1 to 100");
            System.out.println();

            /* 輸入欲嘗試次數 */
            System.out.print("Please set the maximum tries: ");
            input = scanner.nextInt();

            while (input <= 0) {
                System.out.print("Maximum tries cannot be zero or negative, please provide a positive number: ");
                input = scanner.nextInt();
            }
            System.out.println();
            System.out.printf("You'll have %d turns.\n", input);
            Integer left = input;
            Integer now;
            Integer answer = random.nextInt(100) + 1;   // 1~100

            /* 進入遊戲環節(直到嘗試次數歸零) */
            while (left > 0) {

                now = input - left  + 1;

                // 判斷前贅詞
                String suffix;

                if (now == 11 || now == 12 || now == 13) {
                    suffix = "th";
                } else if (now % 10 == 1) {
                    suffix = "st";
                } else if (now % 10 == 2) {
                    suffix = "nd";
                } else if (now % 10 == 3) {
                    suffix = "rd";
                } else {
                    suffix = "th";
                }
                // 如果使用者剩最後一次機會
                if (left == 1) {
                    System.out.print("This is your last chance! Last try: ");
                } else {
                    System.out.printf("You have %d turns left, %d%s try: ", left, now, suffix); 
                }
                guess = scanner.nextInt();

                // 判斷使用者輸入是否在範圍內
                while (guess > 100 || guess < 1) {
                    if (guess < 0) {
                        System.out.print("Please input a positive number: ");
                    } else {
                        System.out.print("Out of range. Please think a number between 1 to 100 and try again: ");
                    }
                    guess = scanner.nextInt();
                }

                // 判斷使用者輸入與隨機亂數的大小
                if (guess > answer) {
                    System.out.println("Your guess is larger than the answer");
                } else if (guess < answer) {
                    System.out.println("Your guess is smaller than the answer.");
                } else {
                    System.out.printf("It took you %d turn to guess the answer, which was %d.\n", now, answer);
                    right = true;
                    break;
                }
                
                left --;
            }
            
            /* 若結束仍未猜中，則公布答案 */
            if (left == 0 && right == false) {
                System.out.printf("Oops! No turns left. The number was %d\n", answer);
            }

            /* 使用者是否想再次嘗試 */
            while (keep == true) {
                System.out.print("Do you want to play again? (Y/n)");
                String again = scanner.next();
                if (again.equals("Y") || again.equals("y")) {
                    break;
                } else if (again.equals("N") || again.equals("n")) {
                    keep = false;
                } else {
                    System.out.println("Please input (Y/n).");
                }
            }
        }


        scanner.close();
    }
}
