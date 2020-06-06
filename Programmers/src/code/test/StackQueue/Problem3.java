package code.test.StackQueue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 기능개발	https://programmers.co.kr/learn/courses/30/lessons/42586
public class Problem3 {

	public static void main(String[] args) {

		int[] progresses = { 93, 30, 55 };
		int[] speeds = { 1, 30, 5 };

		Problem3 problem3 = new Problem3();
		int[] result = problem3.solution(progresses, speeds);
		for (int i : result)
			System.out.print(i + " ");

	}

	class Development {
		int progress;
		int devSpeed;

		public Development() {
		}

		public Development(int progress, int devSpeed) {
			this.progress = progress;
			this.devSpeed = devSpeed;
		}

		public void next() {
			progress += devSpeed;
		}

		@Override
		public String toString() {
			return "Development [progress=" + progress + ", devSpeed=" + devSpeed + "]";
		}

	}

	// 성공. 그런데 반복문이 너무 많아
	public int[] solution(int[] progresses, int[] speeds) {
		Queue<Development> devQueue = new LinkedList<Development>();
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < progresses.length; i++)
			devQueue.add(new Development(progresses[i], speeds[i]));

		while (!devQueue.isEmpty()) {
			int count = 0;
			for (Development d : devQueue)
				d.next();

			while (!devQueue.isEmpty() && devQueue.peek().progress >= 100) {
				devQueue.poll();
				count++;
			}
			if (count != 0)
				list.add(count);
		}

		int[] answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

	// 다른 형님 풀이 => 개발 완료까지 남은 날짜 미리 계산
	public int[] solution1(int[] progresses, int[] speeds) {
		Queue<Integer> q = new LinkedList<>();
		List<Integer> answerList = new ArrayList<>();

		for (int i = 0; i < speeds.length; i++) {
			double remain = (100 - progresses[i]) / (double) speeds[i];
			int date = (int) Math.ceil(remain);

			if (!q.isEmpty() && q.peek() < date) {
				answerList.add(q.size());
				q.clear();
			}

			q.offer(date);
		}

		answerList.add(q.size());

		int[] answer = new int[answerList.size()];

		for (int i = 0; i < answer.length; i++) {
			answer[i] = answerList.get(i);
		}

		return answer;
	}
}
