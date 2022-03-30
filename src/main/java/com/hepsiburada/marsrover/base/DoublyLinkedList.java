package com.hepsiburada.marsrover.base;

import java.util.Objects;

public class DoublyLinkedList<T> {
    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = Node.<T>builder().data(data).build();

        if (Objects.isNull(head)) {
            newNode.setNext(newNode);
            newNode.setPrevious(newNode);
            head = newNode;
            return;
        }

        Node<T> last = head.getPrevious();
        newNode.setNext(head);
        head.setPrevious(newNode);
        newNode.setPrevious(last);
        last.setNext(newNode);
    }

    public Node<T> traverseForward(Node<T> start) {
        Node<T> search = search(Objects.nonNull(start) ? start.getData() : null);
        return Objects.requireNonNull(search)
                .getNext();
    }

    public Node<T> traverseBackward(Node<T> start) {
        Node<T> search = search(Objects.nonNull(start) ? start.getData() : null);
        return Objects.requireNonNull(search)
                .getPrevious();
    }

    private Node<T> search(T data) {
        Node<T> current = head;
        while (Objects.nonNull(current)) {
            if (current.getData().equals(data)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }
}
