package Maze;
import java.util.Scanner;

public class MazeLauncher {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Maze.initMaze();
        Maze.loadMap("map.txt");
        Maze.printMaze();

        System.out.println("請輸入1開始迷宮");
        int start = sc.nextInt();
        if (start == 1) {
            Maze.setStartEnd();
            Maze.printMaze();
            // Start from start point (up/down/left/right)
            Maze.mazeSolver();
            Maze.printAnswer();
        } else {
            System.out.println("If you really don't want to play, then bye bye~");
        }
    }
}
