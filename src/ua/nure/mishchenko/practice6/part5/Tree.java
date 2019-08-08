package ua.nure.mishchenko.practice6.part5;

import java.util.Arrays;

public class Tree<E extends Comparable<E>> {

    private Node<E> root;
    private Node<E> parent;
    private Node<E> current;

    public Tree(){
        /*Default constructor*/
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public void setCurrent(Node<E> current) {
        this.current = current;
    }

    public E get() {
        return root.element;
    }


    public boolean remove(E element) {
        final int left = -1;
        final int right = 1;
        final int noStep = 0;

        setParent(null);
        setCurrent(root);
        int lastStep = noStep;

        lastStep = getLastStep(element, left, right, lastStep);
        if (current == null) {
            return false;
        }
        if (current.lNode == null) {
            if (lastStep == right) {
                parent.rNode = current.rNode;
            } else if (lastStep == left) {
                parent.lNode = current.rNode;
            } else {
                root = current.rNode;
            }
        } else if (current.rNode == null) {
            if (lastStep == right) {
                parent.rNode = current.lNode;
            } else if (lastStep == left) {
                parent.lNode = current.lNode;
            } else {
                root = current.lNode;
            }
        } else {
            Node<E> replacement = current.rNode;
            parent = current;
            while (replacement.lNode != null) {
                parent = replacement;
                replacement = replacement.lNode;
            }
            current.element = replacement.element;
            if (parent.equals(current)) {
                parent.rNode = replacement.rNode;
            } else {
                parent.lNode = replacement.rNode;
            }
        }
        return true;
    }

    private int getLastStep(E element, int left, int right, int lastStep) {
        int comp;
        while (current != null
                && (comp = current.element.compareTo(element)) != 0) {
            parent = current;
            if (comp < 0) {
                lastStep = right;
                current = current.rNode;
            } else {
                lastStep = left;
                current = current.lNode;
            }
        }
        return lastStep;
    }

    public void add(E[] elements) {
        Arrays.stream(elements).forEach(this::add);
    }

    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e, null, null);
            return true;
        }
        return add(root, e);
    }

    public boolean add(Node<E> node, E e) {
        if (e.compareTo(node.element) < 0) {
            if (node.lNode == null) {
                node.lNode = new Node<>(e, null, null);
                return true;
            }
            return add(node.lNode, e);
        }
        if (e.compareTo(node.element) > 0) {
            if (node.rNode == null) {
                node.rNode = new Node<>(e, null, null);
                return true;
            }
            return add(node.rNode, e);
        }
        return false;
    }

    public void print() {
        if (root != null) {
            print("", root);
        }
    }

    private void print(String mock, Node<E> node) {
        if (node == null) {
            return;
        }
        print("  " + mock , node.lNode);
        System.out.println(mock + node.element);
        print("  " + mock, node.rNode);
    }


    public static class Node<E> {

        private E element;
        private Node<E> lNode;
        private Node<E> rNode;

        Node(E element, Node<E> left, Node<E> right) {
            this.element = element;
            this.lNode = left;
            this.rNode = right;

        }
    }
}
