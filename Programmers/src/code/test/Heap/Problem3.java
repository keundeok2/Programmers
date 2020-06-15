package code.test.Heap;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem3 {

	public static void main(String[] args) {
		int[][] jobs = { { 0, 1}, { 3, 4 }, { 9, 10 } };
		Problem3 problem3 = new Problem3();
		System.out.println(problem3.solution(jobs));
	}

	public int solution(int[][] jobs) {
		int answer = 0;
		int end = 0;
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] > o2[0] ? 1 : -1;
			}
		});

		PriorityQueue<int[]> pq2 = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] >= o2[1] ? 1 : -1; // 작업 시간 같은 경우 생각 안해도 됨. 결과는 같음.
			}
		});

		for (int[] iArr : jobs)
			pq.offer(iArr);
		for (int[] iArr : jobs)
			pq2.offer(iArr);

		int[] iarr = pq.poll();
		end = iarr[0] + iarr[1];
		answer += iarr[1];
		pq2.remove(iarr);
//		System.out.println("answer : " + answer);
		
		while (!pq2.isEmpty()) {
			int[] temp = pq2.peek();
			
			if (temp[0] > end) { // 작업 수행하고 있지 않을 경우
				temp = pq.peek();
				end = temp[0]+temp[1];
				answer += temp[1] - temp[0];
			} else {
			end += temp[1];
			answer += end - temp[0];
			}
			pq2.remove(temp);
			pq.remove(temp);
//			System.out.println("answer : " + answer);
//			System.out.println("end : " + end);
//			System.out.println("=========");
		}

		return answer / 3;
	}
}
