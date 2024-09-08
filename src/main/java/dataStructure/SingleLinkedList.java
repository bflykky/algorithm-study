package main.java.dataStructure;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SingleLinkedList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public SingleLinkedList() {
        this.head = null;
        this.tail = null;
        size = 0;
    }

    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) { // head와 tail이 null을 가리키고 있음
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        // 리스트에 요소가 이미 존재하는 경우
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if (size == 0) {
            // addFirst()와 같은 과정
            addFirst(e);
        }

        // 기존의 마지막 요소가 newNode를 가리키도록 한다
        tail.next = newNode;
        // tail이 newNode를 가리키도록 한다
        tail = newNode;
        size++;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("유효하지 않은 인덱스입니다.");
        }

        if (index == 0) {
            addFirst(e);
        } else {
            Node<E> newNode = new Node<>(e);
            Node<E> prevNode = head; // 첫 번째 노드를 가리키는 임시 포인터의 역할
            // 의사코드

            int tmp = index - 1;
            while (tmp-- > 0) {
                prevNode = prevNode.next;
            }

            if (tail == prevNode.next) {
                tail = newNode;
            }
            newNode.next = prevNode.next;
            prevNode.next = newNode;
            size++;
        }
    }

    public E get(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("유효하지 않은 인덱스입니다.");
        }

        Node<E> tmpNode = head;

        int tmp = index;
        while (tmp-- > 0) {
            tmpNode = tmpNode.next;
        }

        return tmpNode.value;
    }

    public Node<E> getNode(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("유효하지 않은 인덱스입니다.");
        }

        Node<E> tmpNode = head;

        int tmp = index;
        while (tmp-- > 0) {
            tmpNode = tmpNode.next;
        }

        return tmpNode;
    }

    public E set(int index, E e) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("유효하지 않은 인덱스입니다.");
        }

        Node<E> tmpNode = head;

        int tmp = index;
        while (tmp-- > 0) {
            tmpNode = tmpNode.next;
        }

        tmpNode.value = e;
        return tmpNode.value;
    }

    public E remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new RuntimeException("유효하지 않은 인덱스입니다.");
        }

        if (index == 0) {
            E tmpValue = head.value;
            head = head.next;
            size--;
            if (size == 0) {
                tail = null;
            }
            return tmpValue;
        } else {
            Node<E> prevNode = head;

            int tmp = index - 1;
            while (tmp-- > 0) {
                prevNode = prevNode.next;
            }

            E tmpValue = prevNode.next.value;
            prevNode.next = prevNode.next.next;
            size--;
            if (index == size - 1) {
                tail = prevNode;
            }

            return tmpValue;
        }
    }

    public void print() {
        Node<E> currentNode = head;
        if (currentNode == null) {
            System.out.println("[ ]");
        }

        System.out.printf("[ ");
        while (currentNode != null) {
            System.out.printf("%s -> ", currentNode.value);
            currentNode = currentNode.next;
        }

        System.out.printf(" 끝 ]\n");
    }

    static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
            next = null;
        }

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }
}
