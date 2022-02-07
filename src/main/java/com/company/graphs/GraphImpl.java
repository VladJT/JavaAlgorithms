package com.company.graphs;

import java.util.*;


public class GraphImpl implements IGraph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMatrix;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new boolean[maxVertexCount][maxVertexCount];
    }


    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String SecondLabel, String... others) {
        boolean result = addEdge(startLabel, SecondLabel);

        for (String other : others) {
            result &= addEdge(startLabel, other);
        }

        return result;
    }

    @Override
    public boolean addEdge(String startLabel, String SecondLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(SecondLabel);

        if (startIndex == -1 || endIndex == -1)
            return false;
        else
            adjMatrix[startIndex][endIndex] = true;//TODO 0, 1

        return true;
    }

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
                if (adjMatrix[i][j]) {
                    sb.append(" -> ").append(vertexList.get(j));
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
        if (startIndex == -1) throw new IllegalArgumentException("Неверная вершинка " + startLabel);

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


    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }


    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.println(vertex.getLabel() + "");
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
        if (startIndex == -1) throw new IllegalArgumentException("Неверная вершинка " + startLabel);

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

        graph.addEdge("Москва", "Тамбов");
        graph.addEdge("Москва", "Рязань");
        graph.addEdge("Москва", "Калуга");

        graph.addEdge("Тамбов", "Самара");
        graph.addEdge("Рязань", "Тверь");
        graph.addEdge("Рязань", "Орел");
        graph.addEdge("Калуга", "Орел");

        graph.addEdge("Самара", "Воронеж");
        graph.addEdge("Тверь", "Воронеж");
        graph.addEdge("Орел", "Воронеж");


        System.out.println("Graph size: " + graph.getSize());
        graph.display();

        graph.dfs("Москва");
    }
}
