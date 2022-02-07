package com.company.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

    @Override
    public void dfs(String startLabel) {

    }

    @Override
    public void bfs(String startLabel) {

    }

    public static void main(String[] args) {
        GraphImpl graph = new GraphImpl(7);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");

        graph.addEdge("A", "B", "C");
        graph.addEdge("B", "C", "D");
        graph.addEdge("C", "A", "B", "D");
        graph.addEdge("D", "B", "C");

        System.out.println("Graph size: " + graph.getSize());
        graph.display();
    }
}
