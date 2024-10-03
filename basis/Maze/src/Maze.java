package Maze.src;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Point;

public class Maze {

    private static final int SIZE = 12;     // 10 + 2 (wall)
    private static final char WALL = 'T';
    private static final char PATH = '-';
    private static final char VISITED = '+';
    private static final char START = 'S';
    private static final char END = 'E';
    private static final char ANSWER = 'O';
    public static char[][] maze = new char[SIZE][SIZE];
    static Scanner sc = new Scanner(System.in);
    static Point start;
    static Point end;

    // Initialize maze board
    public static void initMaze() {
        for (int y=0; y < SIZE; y++) {
            for (int x=0; x < SIZE; x++) {
                if (y == 0 || y == SIZE-1 || x == 0 || x == SIZE-1)
                    maze[y][x] = WALL;
                else
                    maze[y][x] = PATH;
            }
        }
    }

    // Loading map file to maze
    public static void loadMap(String map) {
        try {
            AtomicInteger row = new AtomicInteger(1);
            Files.lines(Paths.get(map))
                .map(line -> Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray())
                .forEach(columns -> {
                    int currRow = row.getAndIncrement();
                    for (int column : columns) {
                        maze[currRow][column] = WALL;
                    }
                });
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    // Print maze board
    public static void printMaze() {
        for (int y = 0; y < SIZE; y++) {
            for (int x=0; x < SIZE; x++) {
                System.out.print(maze[y][x] + " ");
            }
            System.out.println();
        }
    }

    // Set start & end point
    public static void setStartEnd() {
        System.out.print("Enter start point (x y): ");
        int startX = sc.nextInt();
        int startY = sc.nextInt();
        maze[startY][startX] = START;
        start = new Point(startX, startY);

        System.out.print("Enter end point (x y): ");
        int endX = sc.nextInt();
        int endY = sc.nextInt();
        maze[endY][endX] = END;
        end = new Point(endX, endY);
    }

    static Stack<Point> paths = new Stack<Point>();
    static Point currPoint;

    // Start from start point (up/down/left/right)
    public static void mazeSolver() {
        currPoint = start;
        while (true) {
            if (maze[currPoint.y-1][currPoint.x] == END ||
                maze[currPoint.y+1][currPoint.x] == END ||
                maze[currPoint.y][currPoint.x-1] == END ||
                maze[currPoint.y][currPoint.x+1] == END) {
                paths.push(currPoint);
                System.out.println("You win!");
                break;
            }
            if (maze[currPoint.y-1][currPoint.x] == PATH) {
                System.out.println("Go up");
                maze[currPoint.y-1][currPoint.x] = VISITED;
                paths.push(currPoint);
                currPoint = new Point(currPoint.x, currPoint.y-1);
            } else if (maze[currPoint.y+1][currPoint.x] == PATH) {
                System.out.println("Go down");
                maze[currPoint.y+1][currPoint.x] = VISITED;
                paths.push(currPoint);
                currPoint = new Point(currPoint.x, currPoint.y+1);
            } else if (maze[currPoint.y][currPoint.x-1] == PATH) {
                System.out.println("Go left");
                maze[currPoint.y][currPoint.x-1] = VISITED;
                paths.push(currPoint);
                currPoint = new Point(currPoint.x-1, currPoint.y);
            } else if (maze[currPoint.y][currPoint.x+1] == PATH) {
                System.out.println("Go right");
                maze[currPoint.y][currPoint.x+1] = VISITED;
                paths.push(currPoint);
                currPoint = new Point(currPoint.x+1, currPoint.y);
            } else {
                maze[currPoint.y][currPoint.x] = VISITED;
                currPoint = paths.pop();
                System.out.println("Back to (" + currPoint.x + ", " + currPoint.y + ")");
            }
        }
    }

    // Print answer
    public static void printAnswer() {
        while (!paths.empty()) {
            Point p = paths.pop();
            maze[p.y][p.x] = ANSWER;
        }
        maze[start.y][start.x] = START;
        printMaze();
    }

}
