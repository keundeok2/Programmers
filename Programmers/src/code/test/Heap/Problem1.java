package code.test.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

// 더 맵게 https://programmers.co.kr/learn/courses/30/lessons/42626
public class Problem1 {

	public static void main(String[] args) {

		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;

		Problem1 problem1 = new Problem1();
		System.out.println(problem1.solution(scoville, K));

	}

	public int solution(int[] scoville, int K) {
		int answer = 0;
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(scoville.length); // 작은 값 먼저 나옴
		/* 큰 값 먼저 나옴
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(scoville.length, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? 1 : (o1 == o2) ? 0 : -1;
			}
		});
		*/
		
		for (int sc : scoville)
			q.offer(sc);

		while (true) {
			if (q.peek() >= K) {
				return answer;
			}
			
			if (q.size() == 1) {
				return -1;
			}
			
			answer++;
			int sum = q.poll() + q.poll() * 2;
			
			q.offer(sum);
		}
	}
}
