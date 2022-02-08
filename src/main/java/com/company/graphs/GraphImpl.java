package com.company.graphs;

import java.util.*;

// взвешенный граф для путешествий от Москвы до Воронежа
public class GraphImpl implements IGraph {

    private final List<Vertex> vertexList;
    private final int[][] adjMatrix;

    String bestPath = "";
    int bestDistance = -1;

    void displayBestPath() {
        System.out.println("ОПТИМАЛЬНЫЙ МАРШРУТ: " + bestPath);
        System.out.println("ОБЩЕЕ РАССТОЯНИЕ: " + bestDistance);
    }

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
        for (int i = 0; i < maxVertexCount; i++) {
            for (int j = 0; j < maxVertexCount; j++) {
                this.adjMatrix[i][j] = -1;
            }
        }
    }


    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String SecondLabel, int distance, String... others) {
        boolean result = addEdge(startLabel, SecondLabel, distance);

        for (String other : others) {
            result &= addEdge(startLabel, other, distance);
        }

        return result;
    }

    @Override
    public boolean addEdge(String startLabel, String SecondLabel, int distance) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(SecondLabel);

        if (startIndex == -1 || endIndex == -1)
            return false;
        else {
            adjMatrix[startIndex][endIndex] = distance;
            adjMatrix[endIndex][startIndex] = distance;
        }
        return true;
    }


    // поиск индекса вершины по названию
    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label))
                return i;
        }
        return -1;
    }


    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] != -1) {
                    sb.append(" [" + adjMatrix[i][j] + "] -> ").append(vertexList.get(j));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    // Обход в глубину
    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) throw new IllegalArgumentException("Неверная вершина " + startLabel);

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null)
                visitVertex(stack, vertex);
            else stack.pop();
        }

    }

    // число ребер для вершины
    private int getEdgeCount(Vertex temp) {
        int currentIndex = indexOf(temp.getLabel());
        int count = 0;
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] != -1)
                count++;
        }
        return count;
    }

    // дистанция между вершинами
    private int getDistance(String prevLabel, String currlabel) {
        int i = indexOf(prevLabel);
        int j = indexOf(currlabel);
        return adjMatrix[i][j];
    }

    // поиск ближайшей непосещенной вершины
    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] != -1 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }


    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        //     System.out.println(vertex.getLabel() + "");
        stack.push(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex> stack, Vertex vertex) {
        System.out.println(vertex.getLabel() + "");
        stack.add(vertex);
        vertex.setVisited(true);
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) throw new IllegalArgumentException("Неверная вершина " + startLabel);

        Queue<Vertex> stack = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null)
                visitVertex(stack, vertex);
            else stack.remove();
        }

    }

    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(20);
        graph.addVertex("Москва");
        graph.addVertex("Тамбов");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Самара");
        graph.addVertex("Тверь");
        graph.addVertex("Орел");
        graph.addVertex("Воронеж");

        graph.addEdge("Москва", "Тамбов", 100);
        graph.addEdge("Москва", "Рязань", 40);
        graph.addEdge("Москва", "Калуга", 80);

        graph.addEdge("Тамбов", "Самара", 80);
        graph.addEdge("Рязань", "Тверь", 150);
        //   graph.addEdge("Рязань", "Орел", 130);
        graph.addEdge("Калуга", "Орел", 120);

        graph.addEdge("Самара", "Воронеж", 80);
        graph.addEdge("Тверь", "Воронеж", 60);
        graph.addEdge("Орел", "Воронеж", 40);
        //    graph.addEdge("Тамбов", "Рязань", 30);

        //System.out.println("Graph size: " + graph.getSize());

        graph.searchPath("Москва", "Воронеж");       // ищем все пути от Москвы до Воронежа
        graph.displayBestPath();// показать лучший маршрут (кратчайший) Москва - Воронеж
    }


    private void searchPath(String startLabel, String lastLabel) {
        int startIndex = indexOf(startLabel);
        int lastIndex = indexOf(lastLabel);
        if (startIndex == -1) throw new IllegalArgumentException("Неверная вершина " + startLabel);
        if (lastIndex == -1) throw new IllegalArgumentException("Неверная вершина " + lastLabel);

        Stack<Vertex> stack = new Stack<>();
        Vertex startVertex = vertexList.get(startIndex);
        Vertex currVertex = startVertex;

        visitVertex(stack, currVertex);
        String currPath = currVertex.getLabel(); // путь в виде строки

        String prevLabel = "";// имя предыдущей вершины в пути
        int distance = 0; // расстояние между городами от начального до конечного

        while (!stack.isEmpty()) {
            if (currVertex != null) prevLabel = currVertex.getLabel();
            currVertex = getNearUnvisitedVertex(stack.peek());
            if (currVertex != null) {
                int d = getDistance(prevLabel, currVertex.getLabel());
                distance += d;
                currPath += "  🚗 " + d + "  " + currVertex.getLabel();
                if (!currVertex.getLabel().equals(lastLabel)) {// если не конец пути - идем дальше
                    visitVertex(stack, currVertex);
                } else {// если дошли до конца пути
                    System.out.println(currPath);
                    System.out.println("Итого расстояние: " + distance + "\n");


                    currVertex.setVisited(false);//сбрасываем посещения у конца пути

                    while (stack.size() > 1) {// чистим стек до первой вершины
                        Vertex temp = stack.pop();
                    }


                    if (bestDistance == -1 || (bestDistance != -1 && bestDistance > distance)) {
                        bestDistance = distance;
                        bestPath = currPath;
                    }

                    currPath = startVertex.getLabel();
                    currVertex = startVertex;
                    distance = 0; // сброс расстояния между нач. и конечн. точками
                }

            }//if (vertex != null)
            else stack.pop();// тупик, ищем другой путь
        }//while (!stack.isEmpty())

    }

}
