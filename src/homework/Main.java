package homework;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static int SIZE = 5;
    public static int DOTS_TO_WIN = 5;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static char[][] map;
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static void main(String[] args) {
        initMap();
        printMap();
        while (true) {
            humanTurn();
            printMap();
            if (checkWin(DOT_X)) {
                System.out.println("Победил человек");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
            aiTurn();
            printMap();
            if (checkWin(DOT_O)) {
                System.out.println("Победил Искуственный Интеллект");
                break;
            }
            if (isMapFull()) {
                System.out.println("Ничья");
                break;
            }
        }
        System.out.println("Игра закончена");
    }

    //public static boolean checkWin(char symb) {
        //if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) return true;
        //if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) return true;
        //if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) return true;
        //if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) return true;
        //if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) return true;
        //if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) return true;
        //if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) return true;
        //if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) return true;
        //return false;
    //}
    private static boolean checkWin(char symb) {
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < DOTS_TO_WIN; j++) {
                    if (checkLine(i, j, 0, 1,  symb)) return true;   // проверим линию по х
                    if (checkLine(i, j, 1, 1,  symb)) return true;   // проверим по диагонали х у
                    if (checkLine(i, j, 1, 0,  symb)) return true;   // проверим линию по у
                    if (checkLine(i, j, -1, 1, symb)) return true;  // проверим по диагонали х -у
                }
            }
            return false;
        }
        // проверка линии
        private static boolean checkLine(int y, int x, int vy, int vx, char symb) {
            int wayX = x + (SIZE - 1) * vx;
            int wayY = y + (SIZE - 1) * vy;
            if (wayX < 0 || wayY < 0 || wayX > SIZE - 1 || wayY > DOTS_TO_WIN - 1) return false;
            for (int i = 0; i < SIZE; i++) {
                int itemY = y + i * vy;
                int itemX = x + i * vx;
                if (map[itemY][itemX] != symb) return false;
            }
            return true;
        }

    public static boolean isMapFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY) return false;
            }
        }
        return true;
    }

    public static void aiTurn() {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        map[y][x] = DOT_O;
    }

    public static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты в формате X Y");
            x = sc.nextInt() - 1;
            y = sc.nextInt() - 1;
        } while (!isCellValid(x, y)); // while(isCellValid(x, y) == false)        map[y][x] = DOT_X;    }    public static boolean isCellValid(int x, int y) {        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;        if (map[y][x] == DOT_EMPTY) return true;        return false;    }    public static void initMap() {        map = new char[SIZE][SIZE];        for (int i = 0; i < SIZE; i++) {            for (int j = 0; j < SIZE; j++) {                map[i][j] = DOT_EMPTY;            }        }    }    public static void printMap() {        for (int i = 0; i <= SIZE; i++) {            System.out.print(i + " ");        }        System.out.println();        for (int i = 0; i < SIZE; i++) {            System.out.print((i + 1) + " ");            for (int j = 0; j < SIZE; j++) {                System.out.print(map[i][j] + " ");            }            System.out.println();        }        System.out.println();    }}
        map[y][x] = DOT_X;
    }
    public static boolean isCellValid(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE) return false;
        if (map[y][x] == DOT_EMPTY) return true;
        return false;
    }

    public static void initMap() {
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void printMap() {
        for (int i = 0; i <= SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(map[i][j] + " ");
            }            System.out.println();
        }        System.out.println();
    }
}
