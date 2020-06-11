package code.test.Heap;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

// 라면공장  https://programmers.co.kr/learn/courses/30/lessons/42629
public class Problem2 {
	public static void main(String[] args) {
		int stock = 4;
		int[] dates = { 4, 10, 15};
		int[] supplies = { 20, 5, 10};
		int k = 30;	//	4

		Problem2 problem2 = new Problem2();
		System.out.println("answer : " +  problem2.solution(stock, dates, supplies, k));

	}

	/*
	 * 해당 날짜에 재고가 없으면? 뒤의 날짜들까지 재고가 남아있으면 -> 1 해당 날짜들의 재고중 큰 것 선택 -> 2
	 */

	public int solution(int stock, int[] dates, int[] supplies, int k) {
		int answer = 0;
		int remain = stock;
		int require = k - stock;
		int currDate = 0;
//		System.out.println("remain : " + remain);
		/* amount가 같을 경우 date가 큰 supply 우선 추출
		PriorityQueue<Supply> candidateQ = new PriorityQueue<Supply>(dates.length, new Comparator<Supply>() {
			@Override
			public int compare(Supply o1, Supply o2) {
				if (o1.amount < o2.amount) {
					return 1;
				} else if (o1.amount == o2.amount) {
					return o1.date < o2.date ? 1 : -1; 
				} else return -1;
			}
		});
		*/
		PriorityQueue<Supply> candidateQ = new PriorityQueue<Supply>(dates.length, (o1,o2)-> o1.amount <= o2.amount ? 1 : -1);
		LinkedList<Supply> q = new LinkedList<Supply>();
		for (int i = 0; i < dates.length; i++) {
			q.offer(new Supply(dates[i], supplies[i]));
		}
		
//		System.out.println("Init Q : " + q);

		while (require > 0) {
//			System.out.println("========================");
//			System.out.println("remain : " + remain);
			int idx = 0;
			Supply candidateS = null;
			for (int i = 0; i < q.size(); i++) {
				Supply s = q.get(i);
				if (remain >= s.date-currDate) {
					candidateQ.add(s);
				} else break;
			}
//			System.out.println("candidateQ : " + candidateQ);
			candidateS = candidateQ.poll();
//			System.out.println("candidateS : " + candidateS);
			idx = q.indexOf(candidateS);
//			System.out.println("idx : " + idx);
			for (int i = 0; i <= idx; i++) q.poll();
//			System.out.println("next Q : " + q);
			remain += candidateS.amount - (candidateS.date-currDate);
			currDate = candidateS.date;
			require -= candidateS.amount;
			candidateQ.clear();
			answer++;
//			System.out.println("remain : " + remain);
//			System.out.println("req : " + require);

		}
		return answer;
	}

	class Supply {
		int date;
		int amount;

		public Supply(int date, int amount) {
			this.date = date;
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Supply [date=" + date + ", amount=" + amount + "]";
		}

	}
}
