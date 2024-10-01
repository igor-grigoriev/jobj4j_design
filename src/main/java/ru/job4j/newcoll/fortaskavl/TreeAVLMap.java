package ru.job4j.newcoll.fortaskavl;

import java.util.*;

public class TreeAVLMap<T extends Comparable<T>, V> {

    private Node root;

    public boolean put(T key, V value) {
        boolean result = false;
        if (Objects.nonNull(value)) {
            remove(key);
            root = put(root, key, value);
            result = true;
        }
        return result;
    }

    private Node put(Node node, T key, V value) {
        Node result = new Node(key, value);
        if (Objects.nonNull(node)) {
            int comparisonResult = key.compareTo(node.key);
            if (comparisonResult < 0) {
                node.left = put(node.left, key, value);
            } else {
                node.right = put(node.right, key, value);
            }
            updateHeight(node);
            result = balance(node);
        }
        return result;
    }

    public boolean remove(T key) {
        boolean result = false;
        if (Objects.nonNull(key) && Objects.nonNull(root) && contains(key)) {
            root = remove(root, key);
            result = true;
        }
        return result;
    }

    private Node remove(Node node, T key) {
        if (node == null) {
            return null;
        }
        int comparisonResult = key.compareTo(node.key);
        if (comparisonResult < 0) {
            node.left = remove(node.left, key);
        } else if (comparisonResult > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                if (node.left.height > node.right.height) {
                    T heir = maximum(node.left).key;
                    node.key = heir;
                    node.left = remove(node.left, heir);
                } else {
                    T heir = minimum(node.right).key;
                    node.key = heir;
                    node.right = remove(node.right, heir);
                }
            }
        }
        updateHeight(node);
        return balance(node);
    }

    private Node minimum(Node node) {
        Node parent = node;
        Node current = node;
        while (current != null) {
            parent = current;
            current = current.left;
        }
        return parent;
    }

    private Node maximum(Node node) {
        Node parent = node;
        Node current = node;
        while (current != null) {
            parent = current;
            current = current.right;
        }
        return parent;
    }

    private void updateHeight(Node node) {
        int leftNodeHeight = Objects.isNull(node.left) ? -1 : node.left.height;
        int rightNodeHeight = Objects.isNull(node.right) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private Node balance(Node node) {
        Node result = node;
        if (node.balanceFactor < -1) {
            if (node.left.balanceFactor >= 0) {
                result = leftRightCase(node);
            } else {
                result = rightRotation(node);
            }
        } else if (node.balanceFactor > 1) {
            if (node.right.balanceFactor >= 0) {
                result = leftRotation(node);
            } else {
                result = rightLeftCase(node);
            }
        }
        return result;
    }

    private Node leftRightCase(Node node) {
        node.left = leftRotation(node.left);
        return rightRotation(node);
    }

    private Node rightLeftCase(Node node) {
        node.right = rightRotation(node.right);
        return leftRotation(node);
    }

    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        updateHeight(node);
        updateHeight(newParent);
        return newParent;
    }

    public V get(T key) {
        Node current = root;
        while (current != null) {
            if (key.compareTo(current.key) == 0) {
                return current.value;
            } else if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    public boolean contains(T key) {
        return get(key) != null;
    }

    public Set<T> keySet() {
        Set<T> result = new TreeSet<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private Set<T> inSymmetricalOrder(Node localRoot, Set<T> set) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, set);
            set.add(localRoot.key);
            inSymmetricalOrder(localRoot.right, set);
        }
        return set;
    }

    public Collection<V> values() {
        Collection<V> result = new ArrayList<>();
        Node node = root;
        return inSymmetricalOrder(node, result);
    }

    private Collection<V> inSymmetricalOrder(Node localRoot, Collection<V> collection) {
        if (localRoot != null) {
            inSymmetricalOrder(localRoot.left, collection);
            collection.add(localRoot.value);
            inSymmetricalOrder(localRoot.right, collection);
        }
        return collection;
    }

    private class Node {
        private int balanceFactor;
        private T key;
        private V value;
        private int height;
        private Node left;
        private Node right;

        Node(T key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}