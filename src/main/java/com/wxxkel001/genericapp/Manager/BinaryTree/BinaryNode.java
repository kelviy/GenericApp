package com.wxxkel001.genericapp.Manager.BinaryTree;

import com.wxxkel001.genericapp.Manager.GenericData;

/**
 * The Node of the Binary Search Tree. Accepts GenericData
 * @param <dataType>
 */
public class BinaryNode<dataType extends GenericData> {
    private dataType data;
    private BinaryNode<dataType> left;
    private BinaryNode<dataType> right;

    /**
     * Creates a node with data
     * @param data
     */
    public BinaryNode(dataType data) {
        this(data, null, null);
    }

    /**
     * Creates a node with data and reference to left and right
     * @param data
     * @param left
     * @param right
     */
    public BinaryNode(dataType data, BinaryNode left, BinaryNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    // getters and setters
    public dataType getData() {
        return data;
    }

    public void setData(dataType data) {
        this.data = data;
    }

    public BinaryNode<dataType> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<dataType> left) {
        this.left = left;
    }

    public BinaryNode<dataType> getRight() {
        return right;
    }

    public void setRight(BinaryNode<dataType> right) {
        this.right = right;
    }
}
