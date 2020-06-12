package code.test.Heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

// 라면공장  https://programmers.co.kr/learn/courses/30/lessons/42629
public class Problem2 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = { 4,10,15 };
		int[] supplies = { 20, 5, 10};
		int k = 30; // req : 26

		Problem2 problem2 = new Problem2();
		System.out.println("answer : " + problem2.solution(stock, dates, supplies, k));

	}

	public int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		int idx = 0;
		int require = k - stock; // k일 이후 까지 필요한 재고량
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 <= o2 ? 1 : -1;
			}
		});

		for (int i = 0; i < k; i++) {
			stock--; // 해당 날짜 재고 소진

			// 해당 날짜에 재고가 남아 있더라도 공급일이라면 공급량을 pq에 넣어줌 
			if (idx < dates.length && dates[idx] == i+1) {
				pq.offer(supplies[idx]);
				idx++;
			}
			
			// 재고가 0일 때 pq에서 가장 큰 값을 가져옴
			if (stock == 0) {
				stock += pq.peek();
				require -= pq.poll();
				answer++;
			}
			
			if (require <= 0) {
				break;
			}
		}

		return answer;
	}

}
