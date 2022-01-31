package com.company.trees;

public class ENode<T extends Comparable<? super T>> {
    private T value;
    private ENode<T> leftChild;
    private ENode<T> rightChild;

    public ENode(T value) {
        this.value = value;
    }

    public ENode<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(ENode<T> rightChild) {
        this.rightChild = rightChild;
    }

    public ENode<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(ENode<T> leftChild) {
        this.leftChild = leftChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public boolean isLeftChild(T value) {
        return getValue().compareTo(value) > 0;
    }

    public boolean isLeaf() {
        return (leftChild==null && rightChild==null);
    }

    public boolean hasOnlyOneChild() {
      //  return (getRightChild()==null && getLeftChild()!=null) || (getRightChild()!=null && getLeftChild()==null);
        return getRightChild()!=null ^ getLeftChild()!=null;//XOR
    }
}
