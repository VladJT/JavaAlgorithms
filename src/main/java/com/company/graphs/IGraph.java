package com.company.graphs;

public interface IGraph {

    void addVertex(String label);

    boolean addEdge(String startLabel, String SecondLabel, String... others);

    boolean addEdge(String startLabel, String SecondLabel);

    int getSize();

    void display();

    // Depth-first search
    void dfs(String startLabel);

    // Breadth-first search
    void bfs(String startLabel);
}
