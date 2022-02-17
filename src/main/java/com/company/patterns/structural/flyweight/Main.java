package com.company.patterns.structural.flyweight;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <h1>Легковес (Flyweight)</h1>
 * <font color="#fa8e47">Сложность:⭐⭐⭐</font><br>
 * <font color="#fa8e47">Популярность:⭐</font>⭐⭐<p>
 * структурный паттерн, который экономит память, благодаря разделению общего состояния, вынесенного в один объект, между множеством объектов.
 * Легковес позволяет экономить память, кешируя одинаковые данные, используемые в разных объектах.
 * <p>
 * Его суть заключается в том, что если у разных объектов есть одинаковое состояние, то его можно обобщить и хранить не в каждом объекте, а в одном месте.
 * И тогда каждый объект сможет ссылаться на общу часть, что позволит сократить расходы памяти на хранение.
 * Часто работа данного паттерна связана с предварительным кэшированием или с поддержанием пула объектов.
 * <p>
 * <font color="#fa8e47">Применимость: </font>Весь смысл использования Легковеса — в экономии памяти. Поэтому, если в приложении нет такой проблемы, то вы вряд ли найдёте там примеры Легковеса.
 * <p>
 * <font color="#fa8e47">Примеры Легковеса в стандартных библиотеках Java:</font>
 * java.lang.Integer#valueOf(int)
 * <p>
 * <font color="#fa8e47">Признаки применения паттерна: </font>Легковес можно определить по создающим методам класса, которые возвращают закешированные объекты, вместо создания новых.
 */


/*В этом примере мы создадим и нарисуем лес (1.000.000 деревьев)! Каждому дереву соответствует свой объект, имеющий некоторое состояние (координаты, текстура и прочее).
Такая программа хоть и работает, но ест слишком много памяти.
Много деревьев имеют одинаковые свойства (название, текстуру, цвет). Потому мы можем применить паттерн Легковес и закешировать эти свойства в отдельных объектах TreeType.
Теперь вместо хранения этих данных в миллионах объектов деревьев Tree, мы будем ссылаться на один из нескольких объектов-легковесов.
Клиенту даже необязательно знать обо всём этом. Фабрика легковесов TreeType сама позаботится о создании нового типа дерева,
если будет запрошено дерево с какими-то уникальными параметрами.*/

// Объект, содержащий уникальное состояние дерева
class MyTree {
    private int x;
    private int y;
    private TreeModel type;

    public MyTree(int x, int y, TreeModel type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw(Graphics g) {
        type.draw(g, x, y);
    }
}


// Легковес, имеющий общее состояние нескольких деревьев
class TreeModel {
    private String name;
    private Color color;

    public TreeModel(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.fillRect(x - 1, y, 3, 5);
        g.setColor(color);
        g.fillOval(x - 5, y - 10, 10, 10);
    }
}

// фабрика деревьев
class TreeFactory {
    static Map<String, TreeModel> treeTypes = new HashMap<>();

    public static TreeModel getTreeType(String name, Color color) {
        TreeModel newTreeType = treeTypes.get(name);
        if (newTreeType == null) {
            newTreeType = new TreeModel(name, color);
            treeTypes.put(name, newTreeType);
        }
        return newTreeType;
    }
}

// GUI-лес, который рисует деревья
class Forest extends JFrame {
    private List<MyTree> trees = new ArrayList<>();

    public void plantTree(int x, int y, String name, Color color) {
        TreeModel type = TreeFactory.getTreeType(name, color);
        MyTree tree = new MyTree(x, y, type);
        trees.add(tree);
    }

    @Override
    public void paint(Graphics graphics) {
        for (MyTree tree : trees) {
            tree.draw(graphics);
        }
    }
}

class Main {
    static int CANVAS_SIZE = 1000;
    static int TREES_TO_DRAW = 1000000;
    static int TREE_TYPES = 2;

    public static void main(String[] args) {
        Forest forest = new Forest();
        for (int i = 0; i < Math.floor(TREES_TO_DRAW / TREE_TYPES); i++) {
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Summer Oak", Color.GREEN);
            forest.plantTree(random(0, CANVAS_SIZE), random(0, CANVAS_SIZE),
                    "Autumn Oak", Color.ORANGE);
        }
        forest.setSize(CANVAS_SIZE, CANVAS_SIZE);
        forest.setVisible(true);

        System.out.println(TREES_TO_DRAW + " trees drawn");
        System.out.println("---------------------");
        System.out.println("Memory usage:");
        System.out.println("Tree size (8 bytes) * " + TREES_TO_DRAW);
        System.out.println("+ TreeTypes size (~30 bytes) * " + TREE_TYPES + "");
        System.out.println("---------------------");
        System.out.println("Total: " + ((TREES_TO_DRAW * 8 + TREE_TYPES * 30) / 1024 / 1024) +
                "MB (instead of " + ((TREES_TO_DRAW * 38) / 1024 / 1024) + "MB)");
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}
