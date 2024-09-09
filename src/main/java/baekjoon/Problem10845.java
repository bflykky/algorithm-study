package main.java.baekjoon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Problem10845 {
    /*
     *
     * push X: 정수 X를 큐에 넣는 연산이다.
     * pop: 큐에서 가장 앞에 있는 정수를 빼고, 그 수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
     * size: 큐에 들어있는 정수의 개수를 출력한다.
     * empty: 큐가 비어있으면 1, 아니면 0을 출력한다.
     * front: 큐의 가장 앞에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
     * back: 큐의 가장 뒤에 있는 정수를 출력한다. 만약 큐에 들어있는 정수가 없는 경우에는 -1을 출력한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue queue = new Queue(10_000);

        String commandCountInput = br.readLine();
        int commandCount = Integer.parseInt(commandCountInput);
        while (commandCount-- > 0) {
            String commandInput = br.readLine();
            String[] splitCommandInput = commandInput.split(" ");

            final int element;
            switch (splitCommandInput[0]) {
                case "push":
                    queue.push(Integer.parseInt(splitCommandInput[1]));
                    break;
                case "pop":
                    element = queue.pop();
                    bw.write(element + "\n");
                    break;
                case "size":
                    element = queue.size();
                    bw.write(element + "\n");
                    break;
                case "empty":
                    element = queue.empty() ? 1 : 0;
                    bw.write(element + "\n");
                    break;
                case "front":
                    element = queue.front();
                    bw.write(element + "\n");
                    break;
                case "back":
                    element = queue.back();
                    bw.write(element + "\n");
                    break;
                default:
                    throw new RuntimeException("지원하지 않는 명령어입니다.");
            }
        }

        bw.flush();

        bw.close();
        br.close();
    }
}

class Queue {
    int[] data;
    int front;
    int rear;
    int size;
    int capacity;

    public Queue() {
        this(100);
    }

    public Queue(int capacity) {
        data = new int[capacity];
        front = 0;
        rear = 0;
        size = 0;
        this.capacity = capacity;
    }

    public int push(int element) {
        if (size >= capacity) {
            throw new RuntimeException("큐가 가득 차있습니다.");
        }

        if (size == 0) {
            data[rear] = element;
        } else {
            data[++rear] = element;
        }

        size++;
        return element;
    }

    public int pop() {
        final int result;
        if (size == 0) {
            result = -1;
        } else {
            size--;
            result = data[front++];
        }

        // 혹시 front가 rear를 넘어섰으면 둘 다 0으로 초기화
        if (front > rear) {
            front = rear = 0;
        }
        return result;
    }

    public int size() {
        return size;
    }

    public boolean empty() {
        return size == 0;
    }

    public int front() {
        if (size == 0) {
            return -1;
        } else {
            return data[front];
        }
    }

    public int back() {
        if (size == 0) {
            return -1;
        } else {
            return data[rear];
        }
    }
}