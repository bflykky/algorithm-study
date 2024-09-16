package main.java.programmers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Problem258711 {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        // 생성한 vertex를 먼저 찾자.
        // 판단 조건
        // 1. 간선이 해당 vertex로 끝나는 경우가 없는 vertex
        // 2. 해당 vertex로 시작하는 간선이 2개 이상인 vertex
        // {vertex 번호, vertex로 시작하는 간선 개수}
        Map<Integer, List<Integer>> vertexMap = new HashMap<>();
        Map<Integer, Integer> startVertexMap = new HashMap<>();
        Map<Integer, Integer> finishVertexMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            final List<Integer> finishVertexList;
            if (vertexMap.containsKey(edge[0])) {
                finishVertexList = vertexMap.get(edge[0]);
                finishVertexList.add(edge[1]);
                vertexMap.put(edge[0], finishVertexList);
            } else {
                finishVertexList = new ArrayList<>();
                finishVertexList.add(edge[1]);
                vertexMap.put(edge[0], finishVertexList);
            }

            startVertexMap.put(edge[0], startVertexMap.getOrDefault(edge[0], 0) + 1);
            finishVertexMap.put(edge[1], finishVertexMap.getOrDefault(edge[1], 0) + 1);
        }

        int generatorVertex = -1;
        // 생성한 정점에서 가리키는 8자 모양 그래프의 정점 집합
        Set<Integer> eightGraphVertexSet = new HashSet<>();
        for (int vertex : startVertexMap.keySet()) {
            if (startVertexMap.get(vertex) >= 2) {
                if (finishVertexMap.get(vertex) == null) {
                    generatorVertex = vertex;
                } else if (finishVertexMap.get(vertex) >= 2) {
                    eightGraphVertexSet.add(vertex);
                }
            }
        }

        answer[0] = generatorVertex;
        answer[3] = eightGraphVertexSet.size();

        // ====

        List<Integer> startVertexList = new ArrayList<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            if (edge[0] == generatorVertex) {
                startVertexList.add(edge[1]);
            }
        }

        int donutGraphCount = 0;
        int barGraphCount = 0;
        for (int i = 0; i < startVertexList.size(); i++) {
            int startVertex = startVertexList.get(i);
            if (eightGraphVertexSet.contains(startVertex)) {
                // 8자 모양 그래프 개수는 이미 구했으므로 패스
                continue;
            }

            boolean isEight = false;
            boolean isCycle = false;
            int currentVertex = startVertex;
            while (true) {
                List<Integer> nextVertexList = vertexMap.get(currentVertex);
                if (nextVertexList == null || nextVertexList.isEmpty()) {
                    barGraphCount++;
                    break;
                } else {
                    currentVertex = nextVertexList.remove(0);
                    if (eightGraphVertexSet.contains(currentVertex)) {
                        break;
                    }
                    if (currentVertex == startVertex) {
                        donutGraphCount++;
                        break;
                    }
                }
            }

        }

        answer[1] = donutGraphCount;
        answer[2] = barGraphCount;


        // 생성한 vertex의 번호
        // vertex를 생성하기 전 도넛 모양 그래프 개수
        // vertex를 생성하기 전 막대 모양 그래프 개수
        // vertex를 생성하기 전 8자 모양 그래프 개수
        // => answer = [a, b, c, d]
        return answer;
    }
}
