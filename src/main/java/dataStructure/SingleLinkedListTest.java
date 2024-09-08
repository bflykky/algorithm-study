package main.java.dataStructure;

import main.java.dataStructure.SingleLinkedList.Node;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SingleLinkedListTest {
    public static void main(String[] args) {

        int[] score = {1, 2, 3};
        List<Integer> scoreList = IntStream.of(score).boxed().collect(Collectors.toList());
        Collections.sort(scoreList);
//        LinkedList
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.addFirst("A");
        linkedList.addFirst("A-");
        linkedList.addLast("A+");
        linkedList.print(); // (0) A-, (1) A, (2) A+

        Node<String> node = linkedList.getNode(2);
        System.out.println("node = " + node.value);

        linkedList.add(0, "N"); // (0) N, (1) A-, (2) A, (3) A+
        linkedList.print();
        linkedList.add(2, "P"); // (0) N, (1) A-, (2) P, (3) A, (4) A+
        linkedList.print();
        linkedList.add(4, "Q"); // (0) N, (1) A-, (2) P, (3) A, (4) A+, (5) Q
        linkedList.print();

        System.out.println(" ===========set() 과정============= ");
        linkedList.set(5, "New Q");
        linkedList.set(0, "New N");
        linkedList.print();

        System.out.println(" ===========삭제 과정============= ");
        String remove1 = linkedList.remove(5);
        linkedList.print();
        String remove2 = linkedList.remove(3);
        linkedList.print();
        String remove3 = linkedList.remove(0);
        linkedList.print();
    }
}
