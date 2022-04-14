package com.company;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

class CodeWars2 {


    public static List<Character> escape(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }

        int x = -1, y = -1;
        char currentPos = '?';
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'v' || maze[i][j] == '<' || maze[i][j] == '>' || maze[i][j] == '^') {
                    x = i;
                    y = j;
                    currentPos = maze[i][j];
                    break;
                }
            }
        }

        bestWay = "";
        findWay(maze, x, y, "");

        System.out.println("RESULT : " + bestWay);

        List<Character> resultWay = new ArrayList<>();
        if (bestWay.equals("")) return resultWay;

        String[] st = bestWay.split(";\\s");
        int oldX, oldY;
        for (int i = 1; i < st.length; i++) {
            oldX = Integer.parseInt(st[i-1].split(":")[0]);
            oldY = Integer.parseInt(st[i-1].split(":")[1]);
            x = Integer.parseInt(st[i].split(":")[0]);
            y = Integer.parseInt(st[i].split(":")[1]);

            // move down
            if(x-oldX == 1){
                if(currentPos=='<') resultWay.add('L');
                if(currentPos=='>') resultWay.add('R');
                if(currentPos=='^') resultWay.add('B');
                currentPos = 'v';
            }// move up
            if(x-oldX == -1){
                if(currentPos=='<') resultWay.add('R');
                if(currentPos=='>') resultWay.add('L');
                if(currentPos=='v') resultWay.add('B');
                currentPos = '^';
            }// move right
            if(y-oldY == 1){
                if(currentPos=='<') resultWay.add('B');
                if(currentPos=='^') resultWay.add('R');
                if(currentPos=='v') resultWay.add('L');
                currentPos = '>';
            }// move left
            if(y-oldY == -1){
                if(currentPos=='>') resultWay.add('B');
                if(currentPos=='^') resultWay.add('L');
                if(currentPos=='v') resultWay.add('R');
                currentPos = '<';
            }
            resultWay.add('F');
        }

        return resultWay;
    }

    static String bestWay = "";

    private static void findWay(char[][] maze, int x, int y, String way) {
        if (!bestWay.equals("")) return;// has best way!

        maze[x][y] = '.';
        way += x + ":" + y + "; ";

        if (x == 0 || x == maze.length - 1 || y == 0 || y == maze[0].length - 1) {
            // find exit!!
            System.out.println("find!!! " + way);
            bestWay = way;
            return;
        }

        if (existsWay(x - 1, y, maze)) {
            findWay(maze, x - 1, y, way);
        }
        if (existsWay(x + 1, y, maze)) {
            findWay(maze, x + 1, y, way);
        }
        if (existsWay(x, y - 1, maze)) {
            findWay(maze, x, y - 1, way);
        }
        if (existsWay(x, y + 1, maze)) {
            findWay(maze, x, y + 1, way);
        }
        //      System.out.println("bad way!! " + way);
    }

    private static String findWay2(char[][] maze, int x, int y) {
        String way = "";
        maze[x][y] = '.';
        way = x + ":" + y + "; ";


        if (existsWay(x - 1, y, maze)) {
            way += findWay2(maze, x - 1, y);
        }
        if (existsWay(x + 1, y, maze)) {
            way += findWay2(maze, x + 1, y);
        }
        if (existsWay(x, y - 1, maze)) {
            way += findWay2(maze, x, y - 1);
        }
        if (existsWay(x, y + 1, maze)) {
            way += findWay2(maze, x, y + 1);
        }

        if (x == 0 || x == maze.length - 1 || y == 0 || y == maze[0].length - 1) {
            // find exit!!
            System.out.println("find!!! " + way);
            return way;
        }
        return way;
    }

    private static boolean existsWay(int x, int y, char[][] maze) {
        if (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == ' ') {
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        //-------------------------

        List<char[][]> basicMazes = new ArrayList<>();
        basicMazes.add(new char[][] {
                "# #".toCharArray(),
                " > ".toCharArray(),
                "# #".toCharArray()
        });
        basicMazes.add(new char[][]{
                "####### #".toCharArray(),
                "#>#   # #".toCharArray(),
                "#   #   #".toCharArray(),
                "#########".toCharArray()
        });
        basicMazes.add(new char[][]{
                "###########".toCharArray(),
                "#        >#".toCharArray(),
                "###########".toCharArray()
        });
        basicMazes.add(new char[][]{
                "##########".toCharArray(),
                "#        #".toCharArray(),
                "#  ##### #".toCharArray(),
                "#  #   # #".toCharArray(),
                "#  #^# # #".toCharArray(),
                "#  ### # #".toCharArray(),
                "#      # #".toCharArray(),
                "######## #".toCharArray()
        });

        System.out.println(escape(basicMazes.get(0)));
        System.out.println(escape(basicMazes.get(1)));
        System.out.println(escape(basicMazes.get(2)));
        System.out.println(escape(basicMazes.get(3)));

        //-------------------------
        System.out.println("Время выполнения (милисек.): " + (System.currentTimeMillis() - startTime));
    }

}