package com.wxxkel001.genericapp.Application.BinaryTree;

import com.wxxkel001.genericapp.Application.GenericData;

import java.util.ArrayList;

public class BinarySearchTree<dataType extends GenericData> {
    private BinaryNode<dataType> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinaryNode<dataType> find(dataType d) {
        if (root == null) {
            return null;
        }
        else {
            return find(d, root);
        }
    }

    public BinaryNode<dataType> find(dataType d, BinaryNode<dataType> node) {
        int cmp = d.compareTo(node.getData());

        if (cmp == 0) {
            return node;
        }
        else if (cmp < 0) {
            return (node.getLeft() == null) ? null : find(d, node.getLeft());
        }
        else {
            return (node.getRight() == null) ? null : find(d, node.getRight());
        }
    }

    public void insert(dataType data) {
        if (root == null) {
            root = new BinaryNode<dataType>(data);
        }
        else {
            insert(data, root);
        }
    }


    // TODO: How to handle duplicates
    public void insert(dataType d, BinaryNode<dataType> node) {
        if (d.compareTo(node.getData()) <= 0) {
            if (node.getLeft() == null) {
                node.setLeft(new BinaryNode<dataType>(d, null, null));
            } else {
                insert(d, node.getLeft());
            }
        }
        else {
            if (node.getRight() == null) {
                node.setRight(new BinaryNode<dataType>(d, null, null));
            } else {
                insert(d, node.getRight());
            }
        }
    }

    public ArrayList<GenericData> getAllData() {
        int size = getSize();
        ArrayList<GenericData> data = new ArrayList<>();
        getAllData(root, data);
        return data;
    }

    private void getAllData(BinaryNode<dataType> node, ArrayList<GenericData> data) {
        if(node != null) {
            // convention of visiting the left side of the tree first
            getAllData(node.getLeft(), data);
            data.add(node.getData());
            getAllData(node.getRight(), data);
        }
    }

    public int getSize() {
        return getSize(root);
    }

    public int getSize(BinaryNode<dataType> node) {
        if (node == null) {
            return 0;
        }
        else {
            return 1 + getSize(node.getLeft()) + getSize(node.getRight());
        }
    }

}
