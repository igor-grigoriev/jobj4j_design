package ru.job4j.newcoll.tree;

import ru.job4j.collection.SimpleQueue;
import ru.job4j.collection.SimpleStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TreeUtils<T> {
    /**
     * Метод выполняет обход дерева и считает количество узлов
     * @param root корневой узел дерева
     * @return количество узлов
     * @throws IllegalArgumentException если root является null
     */
    public int countNode(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        int result = 0;
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.getInCount() > 0 || queue.getOutCount() > 0) {
            result++;
            Node<T> node = queue.poll();
            node.getChildren().forEach(queue::push);
        }
        return result;
    }

    /**
     * Метод выполняет обход дерева и возвращает коллекцию ключей узлов дерева
     * @param root корневой узел
     * @return коллекция с ключами, реализующая интерфейс Iterable<E>
     * @throws IllegalArgumentException если root является null
     */
    public Iterable<T> findAll(Node<T> root) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        List<T> result = new ArrayList<>();
        SimpleQueue<Node<T>> queue = new SimpleQueue<>();
        queue.push(root);
        while (queue.getInCount() > 0 || queue.getOutCount() > 0) {
            Node<T> node = queue.poll();
            result.add(node.getValue());
            node.getChildren().forEach(queue::push);
        }
        return result;
    }

    /**
     * Метод обходит дерево root и добавляет к узлу с ключом parent
     * новый узел с ключом child, при этом на момент добавления узел с ключом parent
     * уже должен существовать в дереве, а узла с ключом child в дереве быть не должно
     * @param root корень дерева
     * @param parent ключ узла-родителя
     * @param child ключ узла-потомка
     * @return true если добавление произошло успешно и false в обратном случае.
     * @throws IllegalArgumentException если root является null
     */
    public boolean add(Node<T> root, T parent, T child) {
        Node<T> node = findByKey(root, parent).orElse(null);
        if (node != null && node.getChildren().stream().noneMatch(it -> child.equals(it.getValue()))) {
            node.addChildren(new Node<>(child));
            return true;
        }
        return false;
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> findByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            if (key.equals(node.getValue())) {
                return Optional.of(node);
            }
            node.getChildren().forEach(stack::push);
        }
        return Optional.empty();
    }

    /**
     * Метод обходит дерево root и возвращает первый найденный узел с ключом key,
     * при этом из дерева root удаляется все поддерево найденного узла
     * @param root корень дерева
     * @param key ключ поиска
     * @return узел с ключом key, завернутый в объект типа Optional
     * @throws IllegalArgumentException если root является null
     */
    public Optional<Node<T>> divideByKey(Node<T> root, T key) {
        if (root == null) {
            throw new IllegalArgumentException();
        }
        if (key.equals(root.getValue())) {
            return Optional.of(root);
        }
        SimpleStack<Node<T>> stack = new SimpleStack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node<T> node = stack.pop();
            Node<T> test = null;
            for (Node<T> child : node.getChildren()) {
                if (key.equals(child.getValue())) {
                    test = child;
                }
            }
            if (test != null) {
                node.getChildren().removeIf(child -> key.equals(child.getValue()));
                return Optional.of(test);
            }
            node.getChildren().forEach(stack::push);
        }
        return Optional.empty();
    }
}