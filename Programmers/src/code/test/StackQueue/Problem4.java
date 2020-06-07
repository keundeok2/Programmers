package code.test.StackQueue;

import java.util.LinkedList;

// 프린터 https://programmers.co.kr/learn/courses/30/lessons/42587
public class Problem4 {

	public static void main(String[] args) {
		int[] priorities = { 1, 1, 9, 1, 1, 1 };
		int location = 0;

		Problem4 problem4 = new Problem4();
		System.out.println(problem4.solution(priorities, location));
	}

	class Print {
		int priority;
		int location;

		public Print(int priority, int location) {
			this.priority = priority;
			this.location = location;
		}

		@Override
		public String toString() {
			return "Print [priority=" + priority + ", location=" + location + "]";
		}

	}

	public int solution(int[] priorities, int location) {
		int initQlength = priorities.length;
		LinkedList<Print> waitQ = new LinkedList<Print>();

		for (int i = 0; i < priorities.length; i++)
			waitQ.offer(new Print(priorities[i], i));

		while (true) {
			int currPriority = waitQ.peek().priority;
			int temp = 0;

			//	현재 프린트의 우선순위보다 더 높은 우선순위가 있는지 확인
			for (int i = 0; i < waitQ.size(); i++) {
				temp = Math.max(currPriority, waitQ.get(i).priority);
				if (temp > currPriority)
					break;
			}
			
			if (currPriority == temp) { // 현재 프린트의 우선순위가 제일 높은 우선순위일 경우
				if (waitQ.poll().location == location) {
					return initQlength - (waitQ.size());
				}
			} else { // 아니라면 큐에 다시 넣어주기
				waitQ.offer(waitQ.poll());
			}
		}
	}
}
