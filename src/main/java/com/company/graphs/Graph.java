package com.company.graphs;


import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Graph {

    private class Vertex {
        public char label;

        public boolean wasVisited;

        public Vertex(char label) {
            this.label = label;
            this.wasVisited = false;
        }
    }

    /**
     * При вызове конструктора инициализируется массив размером MAX_VERTS, матрица смежности размером MAX_VERTS x MAX_VERTS,
     * значение текущего количества вершин.
     * Также конструктор заполняет матрицу смежности нулями.
     */
    private final int MAX_VERTS = 32;//определяет максимальное количество вершин, которое может быть представлено в графе.
    private Vertex[] vertexList;//это массив, который хранит вершины
    private int[][] adjMat;// матрица смежности
    private int size; // текущее количество вершин

    private Stack stack = new Stack(); // для обхода в глубину
    private Queue queue = new LinkedList();// для обхода в ширину

    private String minPath = "";

    public Graph() {
        vertexList = new Vertex[MAX_VERTS];
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        size = 0;
        for (int i = 0; i < MAX_VERTS; i++) {
            for (int j = 0; j < MAX_VERTS; j++) {
                adjMat[i][j] = 0;
            }

        }
    }

    public void addVertex(char label) {
        vertexList[size++] = new Vertex(label);
    }

    //добавление ребра между вершинами;
    public void addEdge(int start, int end) {
        adjMat[start][end] = 1;
        adjMat[end][start] = 1;
    }

    public void displayVertex(int vertex) {
        System.out.println(vertexList[vertex].label);
    }

    /**
     * Обход — одна из основных операций, которая проводится над графами.
     * Она состоит в посещении из заданной вершины всех других, которые связаны ребрами.
     * Если представить граф железнодорожного сообщения, можно поставить задачу найти маршрут со всеми остановками между Москвой и Воронежем.
     * Существует два основных способа обхода деревьев — в глубину и в ширину.
     * Оба подхода обеспечивают перебор всех связанных вершин, но обход в глубину использует в качестве структуры данных стек,
     * а обход в ширину — очередь.
     */
    // поиск непосещенных вершин
    private int getAdjUnvisitedVertex(int ver) {
        for (int i = 0; i < size; i++) {
            if (adjMat[ver][i] == 1 && !vertexList[i].wasVisited) {
                return i;
            }
        }
        return -1;
    }

    // обход в глубину
    // Depth-First Search, DFS
    public void dfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        stack.push(0);
        while (!stack.isEmpty()) {
            int v = getAdjUnvisitedVertex((int) stack.peek());
            if (v == -1) {
                stack.pop();
            } else {
                vertexList[v].wasVisited = true;
                displayVertex(v);
                stack.push(v);
            }
        }

        clearVisits();
    }

    private void clearVisits() {
        for (int i = 0; i < size; i++) {
            vertexList[i].wasVisited = false;
        }
    }

    /**
     * В отличие от обхода в глубину, где алгоритм стремится как можно быстрее удалиться от вершины, обход в ширину, напротив,
     * стремится быть как можно ближе к исходной вершине. Сначала он обходит все смежные вершины и только после этого отходит дальше.
     * Такой алгоритм может пригодиться для задач нахождения кратчайшего пути.
     */
    // поиск в ширину (Breadth-First Search, BFS)
    public void bfs() {
        vertexList[0].wasVisited = true;
        displayVertex(0);
        queue.add(0); // Вставка в конец очереди
        int v2;
        while (!queue.isEmpty()) {
            int v1 = (int) queue.remove();
            while ((v2 = getAdjUnvisitedVertex(v1)) != -1) {
                vertexList[v2].wasVisited = true; // Пометка
                displayVertex(v2); // Вывод
                queue.add(v2);
            }
        }
        for (int i = 0; i < size; i++) // Сброс флагов
            vertexList[i].wasVisited = false;
    }


    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addVertex('A');//0
        graph.addVertex('B');
        graph.addVertex('C');
        graph.addVertex('D');
        graph.addVertex('E');
        graph.addVertex('F');//5

        graph.addEdge(0, 1); //AB
        graph.addEdge(0, 2); //AC
        graph.addEdge(0, 3); //AD
        graph.addEdge(1, 4); //BE
        graph.addEdge(1, 5); //BF
        graph.addEdge(3, 5); //DF
        graph.addEdge(4, 5); //EF
        graph.addEdge(3, 4); //DE

        graph.searchPath(0, 5); // A - F
    }

    private void searchPath(int startV, int lastV) {
        System.out.println("ВАРИАНТЫ МАРШРУТОВ:");
        vertexList[startV].wasVisited = true;
        stack.push(startV);
        while (!stack.isEmpty()) {
            int v = getAdjUnvisitedVertex((int) stack.peek());
            if (v == -1) {// тупик, ищем другой путь
                stack.pop();
            } else {
                if (v == lastV) {//найден маршрут до конечной точки
                    String currentPath = "" + vertexList[v].label;
                    boolean previousVertex = true;
                    while (!stack.isEmpty()) {
                        int vv = (int) stack.pop();
                        if (previousVertex == false) {
                            vertexList[vv].wasVisited = false;//сбрасываем посещения у всего пути, кроме предпоследней вершины до LastV
                        }
                        previousVertex = false;
                        currentPath += " <- " + vertexList[vv].label;
                    }
                    stack.push(startV);
                    vertexList[startV].wasVisited = true;
                    System.out.println(currentPath);
                    if (minPath == "") minPath = currentPath;
                    else if (minPath.length() > currentPath.length()) minPath = currentPath;
                } else { // идем по ветке далее
                    vertexList[v].wasVisited = true;
                    stack.push(v);
                }

            }// else v==-1
        }

        System.out.println("Оптимальный маршрут: " + minPath);
        clearVisits();

    }


}
