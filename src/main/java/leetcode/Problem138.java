package main.java.leetcode;

import java.util.HashMap;
import java.util.Map;

/*
 * LinkedList 깊은 복사 문제(random 노드 가리키고 있는 노드 포인터 포함)
 *
 * 내 풀이 방식 - copyRandomList() 함수
 * Map<Node, Node>를 만들어 (기존 노드, 복사한 노드) 쌍을 저장함으로써 이후 random 포인터도 복사할 수 있도록 하였다.
 * 시간복잡도: O(2N), 공간복잡도: O(N)
 *
 * 재귀 함수도 있고 한데, 이는 나랑 동일한 Map<Node, Node>를 사용한다.
 *
 * 제일 효율적인 방식 - copyRandomList2() 함수
 * 주어진 연결 리스트에, 복제한 노드를 이어붙여서 weaving한 연결 리스트를 만들어내서 풀이한다.
 * A -> A* -> B -> B* -> C -> C* ... 처럼 기존 노드 next에 복제한 노드를 넣는다.
 * => random 포인터를 복사할 때, 기존 노드.random.next를 복사해 넣으면 된다!
 * (단, 주어진 연결 리스트를 원래 형태로 돌려놔야 하기에, 마지막에 복제한 노드만을 연결 리스트로 분해하면서 함께 되돌려 놓는다.)
 * 시간복잡도: O(3N), 공간복잡도: O(1) (Map<Node, Node>를 사용하지 않음)
 *
 */
public class Problem138 {
    public Node copyRandomList(Node head) {
        Map<Node, Node> nodeMap = new HashMap<>();

        Node copiedHead = new Node(-1); // dummy head

        Node currentOriginalNode = head;
        Node currentCopiedNode = copiedHead; // 복사 과정을 진행하기 위한 임시 노드
        while (currentOriginalNode != null) {
            Node tmpNode = new Node(currentOriginalNode.val);
            currentCopiedNode.next = tmpNode;

            nodeMap.put(currentOriginalNode, tmpNode); // 추후, random 노드를 연결시키기 위한 map 쌓기
            currentOriginalNode = currentOriginalNode.next;
            currentCopiedNode = currentCopiedNode.next;
        }

        currentOriginalNode = head;
        currentCopiedNode = copiedHead.next;
        while (currentOriginalNode != null) {
            Node randomNode = currentOriginalNode.random;
            if (randomNode != null) {
                currentCopiedNode.random = nodeMap.get(randomNode);
            }

            currentOriginalNode = currentOriginalNode.next;
            currentCopiedNode = currentCopiedNode.next;
        }

        return copiedHead.next;
    }

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        Node currentNode = head;
        while (currentNode != null) {
            Node tmpNode = new Node(currentNode.val);
            tmpNode.next = currentNode.next;
            currentNode.next = tmpNode;

            currentNode = currentNode.next.next;
        }

        currentNode = head;
        while (currentNode != null) {
            if (currentNode.random != null) {
                currentNode.next.random = currentNode.random.next;
            }

            currentNode = currentNode.next.next;
        }

        Node copiedHead = head.next;
        currentNode = copiedHead;
        Node currentOriginalNode = head;
        while (currentNode != null) {
            currentOriginalNode.next = currentOriginalNode.next.next;
            currentNode.next = currentNode.next != null ? currentNode.next.next : null;

            currentOriginalNode = currentOriginalNode.next;
            currentNode = currentNode.next;
        }

        return copiedHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
