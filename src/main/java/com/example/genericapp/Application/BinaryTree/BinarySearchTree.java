package com.example.genericapp.Application.BinaryTree;

public class BinarySearchTree<dataType> {
    private BinaryNode<dataType> root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinaryNode<dataType> find() {
        // TODO: search
        return null;
    }

    public void delete() {
        // TODO: delete
    }

    public boolean insert(dataType data) {
        if (root == null) {
            root = new BinaryNode<dataType>(data);
            return true;
        }
        else {

        }
        return false;
    }

    public void inOrderTransversal() {
        inOrderTransversal(root);
    }

    public void inOrderTransversal(BinaryNode<dataType> node) {
        if(node != null) {
            // TODO: visitor action

            // convention of visiting the left side of the tree first
            inOrderTransversal(node.getLeft());
            inOrderTransversal(node.getRight());
        }
    }

}
