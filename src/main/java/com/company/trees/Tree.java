package com.company.trees;

import java.util.Stack;

public class Tree {


    /**
     * Чтобы создать узлы дерева, добавим класс Node,
     * который будет содержать данные о сотруднике предприятия: порядковый номер, ФИО и возраст.
     * В узле дерева будет храниться объект типа Person.
     * Так как дерево двоичное, необходимо завести два поля для хранения левого и правого потомка.
     * Также в классе надо реализовать метод display, который выводит информацию об узле.
     */
    class Node {
        public Person person;
        public Node leftChild;
        public Node rightChild;

        public void display() {
            System.out.println("[" + person.id + "] " + person.name + ", " + person.age);
        }

    }

    // В поле root будет храниться корневой элемент дерева, у которого есть ссылки на его потомков.
    private Node root;

    // Метод find возвращает найденный по идентификатору человека узел.
    public Node find(int key) {
        Node current = root;
        while (current.person.id != key) {
            if (current.person.id < key) current = current.leftChild;
            else current = current.rightChild;
            if (current == null) return null;
        }
        return current;
    }

    //Метод insert осуществляет вставку нового узла в дерево.
    public void insert(Person person) {
        Node newNode = new Node();
        newNode.person = person;

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent = null;
            while (true) {
                parent = current;
                if (current.person.id > person.id) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }


    }

    // Один из самых сложных методов в деревьях — delete, который удаляет узел из дерева по указанному идентификатору.
    public boolean delete(int id) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;
        while (current.person.id != id) {
            parent = current;
            if (id < current.person.id) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) return false;
        }

        // Когда удаляемый узел найден, проверяем, что он действительно не имеет потомков, и удаляем его.
        //Если он является корневым, изменяем значение поля root на null.
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) root = null;
            else if (isLeftChild) parent.leftChild = null;
            else parent.rightChild = null;
        }
        //Если удаляемый узел имеет одного потомка, необходимо соединить родителя удаляемого узла с его потомком.
        // Если нет правого потомка
        else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        }
        // Если нет левого потомка
        else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else {// есть 2 потомка!!
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }
            successor.leftChild = current.leftChild;
        }
        return true;
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

        return successor;
    }


    public void inOrder(Node rootNode) {
        if (rootNode != null) {
            inOrder(rootNode.leftChild);
            rootNode.display();
            inOrder(rootNode.rightChild);
        }
    }

    public Node min() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.leftChild;
        }
        return last;
    }

    public Node max() {
        Node current, last = null;
        current = root;
        while (current != null) {
            last = current;
            current = current.rightChild;
        }
        return last;
    }

    public void printTree() { // метод для вывода дерева в консоль
        Stack globalStack = new Stack(); // общий стек для значений дерева
        globalStack.push(root);
        int gaps = 50; // начальное значение расстояния между элементами
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
                    temp.display(); // выводим его значение в консоли
                    localStack.push(temp.leftChild); // соохраняем в локальный стек, наследники текущего элемента
                    localStack.push(temp.rightChild);
                    if (temp.leftChild != null ||
                            temp.rightChild != null)
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

    public static void main(String[] args) {
        Tree t = new Tree();
        t.insert(new Person("Ivan", 10, 40));
        t.insert(new Person("Petr", 5, 34));
        t.insert(new Person("Stas", 11, 42));
        t.insert(new Person("Vasya", 6, 27));
        t.printTree();

    }
}
