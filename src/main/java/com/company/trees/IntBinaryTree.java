package com.company.trees;

import java.util.Random;
import java.util.Stack;

class IntBinaryTree {
    class Node {
        private int value; // ключ узла
        private Node leftChild; // Левый узел потомок
        private Node rightChild; // Правый узел потомок

        public void printNode() { // Вывод значения узла в консоль
            System.out.println(" Выбранный узел имеет значение :" + value);
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(final int value) {
            this.value = value;
        }

        public Node getLeftChild() {
            return this.leftChild;
        }

        public void setLeftChild(final Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return this.rightChild;
        }

        public void setRightChild(final Node rightChild) {
            this.rightChild = rightChild;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", leftChild=" + leftChild +
                    ", rightChild=" + rightChild +
                    '}';
        }
    }

    private Node rootNode; // корневой узел

    public IntBinaryTree() { // Пустое дерево
        rootNode = null;
    }


    public Node findNodeByValue(int value) { // поиск узла по значению
        Node currentNode = rootNode; // начинаем поиск с корневого узла
        while (currentNode.getValue() != value) { // поиск покуда не будет найден элемент или не будут перебраны все
            if (value < currentNode.getValue()) { // движение влево?
                currentNode = currentNode.getLeftChild();
            } else { //движение вправо
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null) { // если потомка нет,
                return null; // возвращаем null
            }
        }
        return currentNode; // возвращаем найденный элемент
    }

    public void insertNode(int value) { // метод вставки нового элемента
        Node newNode = new Node(); // создание нового узла
        newNode.setValue(value); // вставка данных
        if (rootNode == null) { // если корневой узел не существует
            rootNode = newNode;// то новый элемент и есть корневой узел
        } else { // корневой узел занят
            Node currentNode = rootNode; // начинаем с корневого узла
            Node parentNode;
            while (true) // мы имеем внутренний выход из цикла
            {
                parentNode = currentNode;
                if (value == currentNode.getValue()) {   // если такой элемент в дереве уже есть, не сохраняем его
                    return;    // просто выходим из метода
                } else if (value < currentNode.getValue()) {   // движение влево?
                    currentNode = currentNode.getLeftChild();
                    if (currentNode == null) { // если был достигнут конец цепочки,
                        parentNode.setLeftChild(newNode); //  то вставить слева и выйти из методы
                        return;
                    }
                } else { // Или направо?
                    currentNode = currentNode.getRightChild();
                    if (currentNode == null) { // если был достигнут конец цепочки,
                        parentNode.setRightChild(newNode);  //то вставить справа
                        return; // и выйти
                    }
                }
            }
        }
    }

    public boolean deleteNode(int value) // Удаление узла с заданным ключом
    {
        Node currentNode = rootNode;
        Node parentNode = rootNode;
        boolean isLeftChild = true;
        while (currentNode.getValue() != value) { // начинаем поиск узла
            parentNode = currentNode;
            if (value < currentNode.getValue()) { // Определяем, нужно ли движение влево?
                isLeftChild = true;
                currentNode = currentNode.getLeftChild();
            } else { // или движение вправо?
                isLeftChild = false;
                currentNode = currentNode.getRightChild();
            }
            if (currentNode == null)
                return false; // yзел не найден
        }

        if (currentNode.getLeftChild() == null && currentNode.getRightChild() == null) { // узел просто удаляется, если не имеет потомков
            if (currentNode == rootNode) // если узел - корень, то дерево очищается
                rootNode = null;
            else if (isLeftChild)
                parentNode.setLeftChild(null); // если нет - узел отсоединяется, от родителя
            else
                parentNode.setRightChild(null);
        } else if (currentNode.getRightChild() == null) { // узел заменяется левым поддеревом, если правого потомка нет
            if (currentNode == rootNode)
                rootNode = currentNode.getLeftChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getLeftChild());
            else
                parentNode.setRightChild(currentNode.getLeftChild());
        } else if (currentNode.getLeftChild() == null) { // узел заменяется правым поддеревом, если левого потомка нет
            if (currentNode == rootNode)
                rootNode = currentNode.getRightChild();
            else if (isLeftChild)
                parentNode.setLeftChild(currentNode.getRightChild());
            else
                parentNode.setRightChild(currentNode.getRightChild());
        } else { // если есть два потомка, узел заменяется преемником
            Node heir = getSuccessor(currentNode);// поиск преемника для удаляемого узла
            if (currentNode == rootNode)
                rootNode = heir;
            else if (isLeftChild)
                parentNode.setLeftChild(heir);
            else
                parentNode.setRightChild(heir);
        }
        return true; // элемент успешно удалён
    }

    // Для реализации алгоритма поиска преемника создадим метод getSuccessor, который будет возвращать узел, являющийся приемником.
    public Node getSuccessor(Node node) {
        Node successorParent = node;
        Node successor = node;
        Node current = node.rightChild;

        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //Если преемник является правым потомком удаляемого элемента, переносим все поддерево на один уровень вверх, где преемник становится на освободившееся место.
        if (successor != node.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild;
        }
        // TODO
        successor.leftChild = node.leftChild;

        return successor;
    }

    // метод возвращает узел со следующим значением после передаваемого аргументом.
    // для этого он сначала переходим к правому потомку, а затем
    // отслеживаем цепочку левых потомков этого узла.
    private Node receiveHeir(Node node) {
        Node parentNode = node;
        Node heirNode = node;
        Node currentNode = node.getRightChild(); // Переход к правому потомку
        while (currentNode != null) // Пока остаются левые потомки
        {
            parentNode = heirNode;// потомка задаём как текущий узел
            heirNode = currentNode;
            currentNode = currentNode.getLeftChild(); // переход к левому потомку
        }
        // Если преемник не является
        if (heirNode != node.getRightChild()) // правым потомком,
        { // создать связи между узлами
            parentNode.setLeftChild(heirNode.getRightChild());
            heirNode.setRightChild(node.getRightChild());
        }
        return heirNode;// возвращаем приемника
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(rootNode);
        int gaps = 64; // начальное значение расстояния между элементами
        boolean isRowEmpty = false;
        String separator = "-----------------------------------------------------------------";
        System.out.println(separator);// черта для указания начала нового дерева
        while (isRowEmpty == false) {
            Stack localStack = new Stack(); // локальный стек для задания потомков элемента
            isRowEmpty = true;

            for (int j = 0; j < gaps; j++)
                System.out.print(' ');
            while (globalStack.isEmpty() == false) { // покуда в общем стеке есть элементы
                Node temp = (Node) globalStack.pop(); // берем следующий, при этом удаляя его из стека
                if (temp != null) {
                    System.out.print(temp.getValue()); // выводим его значение в консоли
                    localStack.push(temp.getLeftChild()); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.getRightChild());
                    if (temp.getLeftChild() != null ||
                            temp.getRightChild() != null)
                        isRowEmpty = false;
                } else {
                    System.out.print("__");// - если элемент пустой
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < gaps * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println();
            gaps /= 2;// при переходе на следующий уровень расстояние между элементами каждый раз уменьшается
            while (localStack.isEmpty() == false)
                globalStack.push(localStack.pop()); // перемещаем все элементы из локального стека в глобальный
        }
        System.out.println(separator);// подводим черту
    }


    public int getDepthHelper(Node node) {
        int treeHeightLeft;
        int treeHeightRight;
        //get height of left subtree
        if (node.getLeftChild() == null)
            treeHeightLeft = 1;
        else {
            treeHeightLeft = getDepthHelper(node.getLeftChild()) + 1;
        }

        //get height of right subtree
        if (node.getRightChild() == null)
            treeHeightRight = 1;
        else {
            treeHeightRight = getDepthHelper(node.getRightChild()) + 1;
        }
        return Math.max(treeHeightLeft, treeHeightRight);
    }

    public static void main(String[] args) {
        IntBinaryTree tree = new IntBinaryTree();
        Random random = new Random();
        tree.insertNode(random.nextInt(26) - 25);
        while (true) {
            int newValue = random.nextInt(26) - 25;
            if (tree.getDepthHelper(tree.rootNode) >= 4) break;
            else
                tree.insertNode(newValue);
        }
//        tree.insertNode(8);
//        tree.insertNode(9);
//        tree.insertNode(1);
//        tree.insertNode(0);
//        tree.insertNode(3);
//        tree.insertNode(10);
//        tree.insertNode(15);
//        tree.insertNode(7);
//        tree.insertNode(5);
//        tree.insertNode(6);
//        tree.insertNode(4);
//        tree.insertNode(2);
//        tree.deleteNode(3);
        tree.printTree();
    }
}
