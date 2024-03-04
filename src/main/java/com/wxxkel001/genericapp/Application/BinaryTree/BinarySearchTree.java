package com.wxxkel001.genericapp.Application.BinaryTree;

import com.wxxkel001.genericapp.Application.GenericData;

import java.util.ArrayList;


public class BinarySearchTree<dataType extends GenericData> {
    private BinaryNode<dataType> root;

    /**
     * Constructor that creates an empty Binary Search Tree
     */
    public BinarySearchTree() {
        this.root = null;
    }

    /**
     * returns a Node that matches the dataType
     * @param d
     * @return
     */
    public BinaryNode<dataType> find(dataType d) {
        if (root == null) {
            return null;
        }
        else {
            return find(d, root);
        }
    }

    /**
     * Recursive method of the find method()
     * @param d
     * @param node
     * @return
     */
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

    /**
     * Uses the search algorithm of the Binary Tree, However desired node is found it would check if original node has a confidence score lower than node being searched. If true then update and return tree, else do not update and return true. Boolean return type tells the caller if the node has been found or not.
     * @param d The dataType would be GenericData
     * @return returns true if node is found. False otherwise
     */
    public boolean searchUpdate(dataType d) {
        if (root == null) {
            return false;
        }
        else {
            return searchUpdate(d, root);
        }
    }

    /**
     * Recursive method of searchUpdate()
     * @param d
     * @param node
     * @return
     */
    public boolean searchUpdate(dataType d, BinaryNode<dataType> node) {
        int cmp = d.compareTo(node.getData());

        if (cmp == 0) {
            if (node.getData().getScore() < d.getScore()) {
                node.setData(d);
            }
            return true;
        }
        else if (cmp < 0) {
            return (node.getLeft() == null) ? false : searchUpdate(d, node.getLeft());
        }
        else {
            return (node.getRight() == null) ? false : searchUpdate(d, node.getRight());
        }
    }

    /**
     * Inserts a new object.
     * @param data The object being inserted
     */
    public void insert(dataType data) {
        if (root == null) {
            root = new BinaryNode<dataType>(data);
        }
        else {
            insert(data, root);
        }
    }


    /**
     * Recursive method for insert()
     * @param d
     * @param node
     */
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

    /** Uses the searchUpdate to update inserted object that match the correct conditions for confidence score. If object is not found then the object is inserted into the Binary Search Tree. Would interact with the GUI to insert a new Item.
     * @param d
     */
    public void insertItem(dataType d) {
        boolean flag = searchUpdate(d);
        if (!flag) {
            insert(d);
        }
    }

    /**
     * Returns an ArrayList of all the data in the data structure in ascending order
     * @return ArrayList of (Type: GenericData)
     */
    public ArrayList<GenericData> getAllData() {
        int size = getSize();
        ArrayList<GenericData> data = new ArrayList<>();
        getAllData(root, data);
        return data;
    }

    /**
     * recursive method of getAllData(). Uses the InOrderTraversal Algorithm
     * @param node
     * @param data
     */
    private void getAllData(BinaryNode<dataType> node, ArrayList<GenericData> data) {
        if(node != null) {
            // convention of visiting the left side of the tree first
            getAllData(node.getLeft(), data);
            data.add(node.getData());
            getAllData(node.getRight(), data);
        }
    }

    /**
     * Gets the size of the data structure
     * @return
     */
    public int getSize() {
        return getSize(root);
    }

    /**
     * recursive algorithm of size()
     * @param node
     * @return
     */
    public int getSize(BinaryNode<dataType> node) {
        if (node == null) {
            return 0;
        }
        else {
            return 1 + getSize(node.getLeft()) + getSize(node.getRight());
        }
    }

}
