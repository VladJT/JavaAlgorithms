package com.company.graphs;

import java.util.ArrayList;
import java.util.List;

public class DGraph {
    public class DVertex {
        private String label;
        private boolean isInTree;

        public DVertex(String label) {
            this.label = label;
            this.isInTree = false;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public boolean isInTree() {
            return isInTree;
        }

        public void setInTree(boolean inTree) {
            isInTree = inTree;
        }
    }

    public class Path { // объект данного класса содержащий расстояние и предыдущие и пройденные вершины
        private int distance; // текущая дистанция от начальной вершины
        private List<Integer> parentVertices; // текущий родитель вершины

        public Path(int distance) {
            this.distance = distance;
            this.parentVertices = new ArrayList<>();
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public List<Integer> getParentVertices() {
            return parentVertices;
        }

        public void setParentVertices(List<Integer> parentVertices) {
            this.parentVertices = parentVertices;
        }
    }


    private final int MAX_VERTS = 10;// максимальное количество вершин
    private final int INFINITY = 100000000; // это число у нас будет служить в качестве "бесконечности"
    private DVertex vertexList[]; // список вершин
    private int relationMatrix[][]; // матрица связей вершин
    private int countOfVertices; // текущее количество вершин
    private int countOfVertexInTree; // количество рассмотренных вершин в дереве
    private List<Path> shortestPaths; // список данных кратчайших путей
    private int currentVertex; // текущая вершина
    private int startToCurrent; //расстояние до currentVertex

    public DGraph() {
        vertexList = new DVertex[MAX_VERTS]; // матрица смежности
        relationMatrix = new int[MAX_VERTS][MAX_VERTS];
        countOfVertices = 0;
        countOfVertexInTree = 0;
        for (int i = 0; i < MAX_VERTS; i++) {// матрица смежности заполняется
            for (int k = 0; k < MAX_VERTS; k++) { // бесконечными расстояниями
                relationMatrix[i][k] = INFINITY; // задания значений по умолчанию
                shortestPaths = new ArrayList<>();// задается пустым
            }
        }
    }

    public void addVertex(String lab) {// задание новых вершин
        vertexList[countOfVertices++] = new DVertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        relationMatrix[start][end] = weight; // задание ребер между вершинами, с весом между ними
        relationMatrix[end][start] = weight; // задание ребер между вершинами, с весом между ними
    }

    public void addEdge(String start, String end, int weight) {
        relationMatrix[indexOf(start)][indexOf(end)] = weight; // задание ребер между вершинами, с весом между ними
        relationMatrix[indexOf(end)][indexOf(start)] = weight; // задание ребер между вершинами, с весом между ними
    }

    public void path(String startVertex, String endVertex) { // выбор кратчайшего пути
        //  задание данных для стартовой вершины
        int startTree = indexOf(startVertex); // стартуем с вершины 0
        vertexList[startTree].setInTree(true); // включение в состав дерева первого элемента
        countOfVertexInTree = 1;

        // заполнение коротких путей для вершин смежных с стартовой
        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[startTree][i];
            Path path = new Path(tempDist);
            path.getParentVertices().add(0);// первым родительским элементом, будет всегда стартовая вершина
            shortestPaths.add(path);
        }
        // пока все вершины не окажутся в дереве
        while (countOfVertexInTree < countOfVertices) { // выполняем, пока количество вершин в дереве не сравняется с общим количеством вершин
            int indexMin = getMin();//получаем индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
            int minDist = shortestPaths.get(indexMin).getDistance();// минимальная дистанция вершины, из тек которые ещё не в дереве

            if (minDist == INFINITY) {
                System.out.println("В графе пристувствуют недостижимые вершины");
                break;// в случае если остались непройденными только недостижимые вершины, мы выходим из цикла
            } else {
                currentVertex = indexMin; // переводим указатель currentVert к текущей вершине
                startToCurrent = shortestPaths.get(indexMin).getDistance();// задаем дистанцию к текущей вершине
            }

            vertexList[currentVertex].setInTree(true);  //включение текущей вершины в дерево
            countOfVertexInTree++; // увеличиваем счетчик вершин в дереве
            updateShortestPaths(); // обновление списка кратчайших путей
        }

        displayPaths(endVertex); // выводим в консоль результаты
    }

    public void clean() { // очиска дерева
        countOfVertexInTree = 0;
        for (int i = 0; i < countOfVertices; i++) {
            vertexList[i].setInTree(false);
        }
    }

    private int getMin() {
        int minDist = INFINITY; // за точку старта взята "бесконечная" длина
        int indexMin = 0;
        for (int i = 1; i < countOfVertices; i++) {// для каждой вершины
            if (!vertexList[i].isInTree() && shortestPaths.get(i).getDistance() < minDist) { // если вершина ещё не ве дереве и её растояние меньше старого минимума
                minDist = shortestPaths.get(i).getDistance(); // задаётся новый минимум
                indexMin = i; // обновление индекса вершины содержащую минимаьную дистанцию
            }
        }
        return indexMin; //возвращает индекс вершины с наименшей дистанцией, из вершин еще не входящих в дерево
    }

    private void updateShortestPaths() {
        int vertexIndex = 1; // стартовая вершина пропускается
        while (vertexIndex < countOfVertices) { // перебор столбцов

            if (vertexList[vertexIndex].isInTree()) { // если вершина column уже включена в дерево, она пропускается
                vertexIndex++;
                continue;
            }
            // вычисление расстояния для одного элемента sPath
            // получение ребра от currentVert к column
            int currentToFringe = relationMatrix[currentVertex][vertexIndex];
            // суммирование всех расстояний
            int startToFringe = startToCurrent + currentToFringe;
            // определение расстояния текущего элемента vertexIndex
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();

            // сравнение расстояния через currentVertex с текущим расстоянием в вершине с индексом vertexIndex
            if (startToFringe < shortPathDistance) {// если меньше, то у вершины под индексом vertexIndex будет задан новый кратчайший путь
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());//создаём копию списка родителей вершины currentVert
                newParents.add(currentVertex);// задаём в него и currentVertex как предыдущий
                shortestPaths.get(vertexIndex).setParentVertices(newParents); // соохраняем новый маршут
                shortestPaths.get(vertexIndex).setDistance(startToFringe); // соохраняем новую дистанцию
            }
            vertexIndex++;
        }
    }

    private void displayPaths(String endVertex) { // метод для вывода кратчайших путей на экран
        int lastIndex = indexOf(endVertex);
        System.out.print("Расстояние = ");
            if (shortestPaths.get(lastIndex).getDistance() == INFINITY) {
                System.out.println("0");
            } else {
                String result = shortestPaths.get(lastIndex).getDistance() + " (";
                List<Integer> parents = shortestPaths.get(lastIndex).getParentVertices();
                for (int j = 0; j < parents.size(); j++) {
                    result += vertexList[parents.get(j)].getLabel() + " -> ";
                }
                System.out.println(result + vertexList[lastIndex].getLabel() + ")");
            }

    }

    // поиск индекса вершины по названию
    private int indexOf(String label) {
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i].getLabel().equals(label))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        DGraph graph = new DGraph();
        graph.addVertex("Москва");
        graph.addVertex("Тамбов");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Самара");
        graph.addVertex("Тверь");
        graph.addVertex("Орел");
        graph.addVertex("Воронеж");
        /**
         *              Тамбов------------ 50------- Самара
         *          /100   30 |                          \80
         *   Москва--40-----Рязань---150---Тверь-- 60-----Воронеж
         *          \90             \ 130               /40
         *          Калуга----120--Орел----------------
         */

        graph.addEdge("Москва", "Тамбов", 100);
        graph.addEdge("Москва", "Рязань", 40);
        graph.addEdge("Москва", "Калуга", 80);

        graph.addEdge("Тамбов", "Самара", 80);
        graph.addEdge("Рязань", "Тверь", 150);
        graph.addEdge("Рязань", "Орел", 130);
        graph.addEdge("Калуга", "Орел", 120);

        graph.addEdge("Самара", "Воронеж", 80);
        graph.addEdge("Тверь", "Воронеж", 60);
        graph.addEdge("Орел", "Воронеж", 40);
        graph.addEdge("Тамбов", "Рязань", 30);


        System.out.println("ОПТИМАЛЬНЫЙ МАРШРУТ МОСКВА - ВОРОНЕЖ ");
        graph.path("Москва", "Воронеж");
        graph.clean();
    }
}
