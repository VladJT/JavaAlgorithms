package com.company.trees;

import com.sun.jdi.InterfaceType;

import java.util.Stack;

public class ETree<E extends Comparable<? super E>> implements ITree<E> {

    private ENode<E> root;
    private int size;


    class NodeAndParent {
        private ENode<E> current;
        private ENode<E> parent;

        public NodeAndParent(ENode<E> current, ENode<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    @Override
    public boolean add(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        if (nodeAndParent.current != null) return false;
        ENode<E> parent = nodeAndParent.parent;
        ENode<E> newNode = new ENode<>(value);

        if (isEmpty()) {
            root = newNode;
        } else {
            if (parent.isLeftChild(value))
                parent.setLeftChild(newNode);
            else parent.setRightChild(newNode);
        }
        size++;
        return true;
    }

    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        return (nodeAndParent != null);
    }

    private NodeAndParent doFind(E value) {
        ENode<E> current = root;
        ENode<E> parent = null;
        while (current != null) {
            if (current.getValue().equals(value)) {
                return new NodeAndParent(current, parent);
            }
            parent = current;
            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
            } else {
                current = current.getRightChild();
            }
        }
        return new NodeAndParent(null, parent);
    }


    @Override
    public boolean remove(E value) {
        NodeAndParent nodeAndParent = doFind(value);
        ENode<E> removed = nodeAndParent.current;
        ENode<E> parent = nodeAndParent.parent;

        if (removed == null) {
            return false;
        }
        size--;

        if (removed.isLeaf()) {
            removeLeafNode(removed, parent);
        } else if (removed.hasOnlyOneChild()) {
            removeNodeWithOneChild(removed, parent);
        } else {
            removeNodeWithAllChild(removed, parent);
        }


        return false;
    }

    private void removeNodeWithAllChild(ENode<E> removed, ENode<E> parent) {
        ENode<E> succesor = getSuccecor(removed);

        if (removed == root) {
            root = succesor;
        } else {
            if (parent.isLeftChild(removed.getValue())) {
                parent.setLeftChild(succesor);
            } else parent.setRightChild(succesor);
        }
    }

    private ENode<E> getSuccecor(ENode<E> removed) {
        ENode<E> succesor = removed;
        ENode<E> parent = null;
        ENode<E> current = removed.getRightChild();
        while (current != null) {
            parent = succesor;
            succesor = current;
            current = current.getLeftChild();
        }

        if (succesor != removed.getRightChild() && parent != null) {
            parent.setLeftChild(succesor.getRightChild());
            succesor.setRightChild(removed.getRightChild());
        }
        succesor.setLeftChild(removed.getLeftChild());
        return succesor;
    }

    private void removeNodeWithOneChild(ENode<E> removed, ENode<E> parent) {
        ENode<E> child = removed.getLeftChild() != null ? removed.getLeftChild() : removed.getRightChild();

        if (removed == root) {
            root = null;
        } else {
            if (parent.isLeftChild(removed.getValue())) {
                parent.setLeftChild(child);
            } else parent.setRightChild(child);
        }

    }


    private void removeLeafNode(ENode<E> removed, ENode<E> parent) {
        if (removed == root) {
            root = null;
        } else {
            if (parent.isLeftChild(removed.getValue())) {
                parent.setLeftChild(null);
            } else parent.setRightChild(null);
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void display() {
        Stack<ENode<E>> globalStack = new Stack();
        globalStack.push(root);
        int nBlanks = 64;
        boolean isRowEmpty = false;
        System.out.println("........................................................");

        while (!isRowEmpty) {
            Stack<ENode<E>> localStack = new Stack();
            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }
            while (!globalStack.isEmpty()) {
                ENode<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++)
                    System.out.print(' ');
            }
            System.out.println(" ");
            nBlanks = nBlanks / 2;
            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }
            //     System.out.println("........................................................");
        }
    }


    @Override
    public void traverse(TraversMode mode) {
        switch (mode) {
            case PRE_ORDER -> {// прямой обход
                preOrder(root);
                break;

            }
            case IN_ORDER -> { // центрированный обход
                inOrder(root);
                break;

            }
            case POST_ORDER -> {// обратный обход
                postOrder(root);
                break;
            }
        }
        System.out.println();
    }

    private void postOrder(ENode<E> current) {
        if (current == null) {
            return;
        }
        postOrder(current.getLeftChild());
        postOrder(current.getRightChild());
        System.out.print(current.getValue() + " ");
    }

    private void inOrder(ENode<E> current) {
        if (current == null) {
            return;
        }
        inOrder(current.getLeftChild());
        System.out.print(current.getValue() + " ");
        inOrder(current.getRightChild());
    }

    private void preOrder(ENode<E> current) {
        if (current == null) {
            return;
        }
        System.out.print(current.getValue() + " ");
        preOrder(current.getLeftChild());
        preOrder(current.getRightChild());
    }


    public static void main(String[] args) {
        ETree<Integer> t = new ETree<>();
        t.add(5);
        t.add(6);
        t.add(8);
        t.add(1);
        t.add(2);
        t.add(4);
        t.add(0);
        t.add(-4);
        t.add(-1);
        t.add(-5);
        t.display();

        t.traverse(TraversMode.PRE_ORDER);
        t.traverse(TraversMode.IN_ORDER);
        t.traverse(TraversMode.POST_ORDER);
    }
}
