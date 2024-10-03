package TicTacToe.src;

import java.util.Arrays;
import java.util.Scanner;

public class GameLauncher {
    
    static TicTacToe[][] tictactoe = new TicTacToe[3][3];   // 設定棋盤
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        // 初始化棋盤
        Arrays.setAll(tictactoe, x -> {
            TicTacToe[] row = new TicTacToe[3];
            Arrays.setAll(row, y -> new TicTacToe());
            return row;
        });

        boolean players = false;    // 用boolean值代表兩位玩家: {true: "O", false: "X"}
        String player;      // 當局玩家

        do {
            players = !players;     // 更換玩家
            player = players ? "O" : "X";   // 玩家參照
            setPlace(player);   // 玩家下棋
            showTable();    // 回合顯示
            System.out.println();
        } while (!ifWin() && !ifDraw());

        if (ifDraw()) {     // 若是平局
            System.out.println("平局!");
        } else {    // 若是有一方獲勝 -> 判斷獲勝玩家並輸出
            String output = player.equals("O") ? "玩家一" : "玩家二";
            System.out.printf("「%s」 勝利!!\n", output);
        }
        
    }

    // Function -> 顯示棋盤
    public static void showTable() {

        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                String output = (y%3!=2) ? tictactoe[x][y].getPlace() + "|" : tictactoe[x][y].getPlace(); 
                System.out.print(output);
            }
            System.out.print((x%3!=2) ? "\n------\n" : "\n");
        }
    }

    // Function -> 下棋(輸入該回合玩家)
    public static void setPlace(String player) {
        
        int x, y;   // 棋盤位置
        while (true) {
            System.out.printf("[%s的回合]\n", player);
            try {
                System.out.print("請輸入行數(1~3): ");
                x = scanner.nextInt();
                System.out.print("請輸入列數(1~3): ");
                y = scanner.nextInt();
                // 放置棋子
                if (tictactoe[x-1][y-1].getPlace().equals(" ")) {
                    if (player.equals("O")) {
                        tictactoe[x-1][y-1].setNought();
                    } else {
                        tictactoe[x-1][y-1].setCrosses();
                    }
                    break;
                } else {    // 偵錯情況一: 已有棋子
                    System.out.println("該座標已經有值了");
                    continue;
                }
            } catch (Exception e) {};
            System.out.println("請輸入 1~3 的數字!");   // 偵錯情況二: 不在棋盤範圍內
        }
    }

    // Function -> 確認是否平手
    public static boolean ifDraw() {
        for (int x=0; x<3; x++) {
            for (int y=0; y<3; y++) {
                if (tictactoe[x][y].getPlace().equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }

    // Function -> 確認是否獲勝
    public static boolean ifWin() {
        // 斜向159(00 11 22)
        if (tictactoe[0][0].getPlace().equals(tictactoe[1][1].getPlace()) &&
            tictactoe[0][0].getPlace().equals(tictactoe[2][2].getPlace()) &&
            !tictactoe[0][0].getPlace().equals(" ")) {
                return true;
        } else if (     // 斜向357(02 11 20)
            tictactoe[0][2].getPlace().equals(tictactoe[1][1].getPlace()) &&
            tictactoe[0][2].getPlace().equals(tictactoe[2][0].getPlace()) &&
            !tictactoe[0][2].getPlace().equals(" ")) {
                return true;
        } else if (     // 橫向123(00 01 02)
            tictactoe[0][0].getPlace().equals(tictactoe[0][1].getPlace()) &&
            tictactoe[0][0].getPlace().equals(tictactoe[0][2].getPlace()) &&
            !tictactoe[0][0].getPlace().equals(" ")) {
                return true;
        } else if (     // 橫向456(10 11 12)
            tictactoe[1][0].getPlace().equals(tictactoe[1][1].getPlace()) &&
            tictactoe[1][0].getPlace().equals(tictactoe[1][2].getPlace()) &&
            !tictactoe[1][0].getPlace().equals(" ")) {
                return true;
        } else if (     // 橫向789(20 21 22)
            tictactoe[2][0].getPlace().equals(tictactoe[2][1].getPlace()) &&
            tictactoe[2][0].getPlace().equals(tictactoe[1][2].getPlace()) &&
            !tictactoe[2][0].getPlace().equals(" ")) {
                return true;
        } else if (     // 直向147(00 10 20)
            tictactoe[0][0].getPlace().equals(tictactoe[1][0].getPlace()) &&
            tictactoe[0][0].getPlace().equals(tictactoe[2][0].getPlace()) &&
            !tictactoe[0][0].getPlace().equals(" ")) {
                return true;
        } else if (     // 直向258(01 11 21)
            tictactoe[0][1].getPlace().equals(tictactoe[1][1].getPlace()) &&
            tictactoe[0][1].getPlace().equals(tictactoe[2][1].getPlace()) &&
            !tictactoe[0][1].getPlace().equals(" ")) {
                return true;
        } else if (     // 直向369(02 12 22)
            tictactoe[0][2].getPlace().equals(tictactoe[1][2].getPlace()) &&
            tictactoe[0][2].getPlace().equals(tictactoe[2][2].getPlace()) &&
            !tictactoe[0][2].getPlace().equals(" ")) {
                return true;
        } 
        return false;   // 未獲勝
    }
}
