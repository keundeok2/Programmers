package code.test.StackQueue;

import java.util.Arrays;

// 주식가격 https://programmers.co.kr/learn/courses/30/lessons/42584
public class Problem6 {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };

		Problem6 problem6 = new Problem6();
		System.out.println(problem6.solution(prices));
	}

	public int[] solution(int[] prices) {
		int[] answer = new int[prices.length];

		for (int i = 0; i < prices.length; i++) {
			int sec = 0;

			for (int j = i + 1; j < prices.length; j++) {
				sec++;
				if (prices[i] > prices[j]) {
					break;
				}
			}
			answer[i] = sec;
		}

		System.out.println(Arrays.toString(answer));
		
		return answer;
	}

}
