package com.example.genericapp.Application.BinaryTree;

public class BinaryNode<dataType> {
    private dataType data;
    private BinaryNode left;
    private BinaryNode right;

    public BinaryNode(dataType data) {
        this(data, null, null);
    }
    public BinaryNode(dataType data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public dataType getData() {
        return data;
    }

    public void setData(dataType data) {
        this.data = data;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
