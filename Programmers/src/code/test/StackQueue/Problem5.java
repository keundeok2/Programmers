package code.test.StackQueue;

import java.util.Stack;

// 쇠막대기 https://programmers.co.kr/learn/courses/30/lessons/42585
public class Problem5 {
	public static void main(String[] args) {
		String arrangement = "()(((()())(())()))(())";

		Problem5 problem5 = new Problem5();
		System.out.println(problem5.solution(arrangement));

	}

	public int solution(String arrangement) {
		int answer = 0;
		int countOfStick = 0;
		String dot = arrangement.replace("()", "."); // 레이저를 '.'로

		for (int i = 0; i < dot.length(); i++) {
			if (dot.charAt(i) == '(') {
				countOfStick++;
			} else if (dot.charAt(i) == ')') {
				countOfStick--;
				answer++;
			} else if (dot.charAt(i) == '.') {
				answer += countOfStick;
			}
		}

		return answer;
	}

	public int solution1(String arrangement) {
		int answer = 0;
		Stack<Integer> stack = new Stack<Integer>();
		
		for (int i = 0; i < arrangement.length(); i++) {
			if (arrangement.charAt(i) == '(') {
				stack.push(i);
			} else if (arrangement.charAt(i) == ')') {
				if (stack.peek() + 1 == i) { // 레이저인 경우
					stack.pop();
					answer += stack.size(); // stack의 size : 막대기의 갯수
				} else { // 레이저가 아닌 경우
					stack.pop();
					answer++;
				}
			}
		}
		return answer;
	}

}