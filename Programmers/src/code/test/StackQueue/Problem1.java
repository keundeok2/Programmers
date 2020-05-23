package code.test.StackQueue;

import java.util.LinkedList;
import java.util.Stack;

//	https://programmers.co.kr/learn/courses/30/lessons/42588	탑
public class Problem1 {

	public static void main(String[] args) {
//		int[] heights = { 6, 9, 5, 7, 4 }; // return [0,0,2,2,4]
//		int[] heights = {3,9,9,3,5,7,2};	//	return [0,0,0,3,3,3,6]	
		int[] heights = {5,1,3,6,7,6,5};	//	return	[0,1,1,0,0,5,6]

		Problem1 problem1 = new Problem1();
		problem1.solution(heights);
	}

	//	정확성 성공
	public int[] solution(int[] heights) {
		int[] answer = new int[heights.length];
		Stack<Integer> stack = new Stack<Integer>();
		LinkedList<Integer> answerQueue = new LinkedList<Integer>();

		for (int i : heights) stack.push(i);	//	Stack에 순서대로 push

		while (stack.size() > 0) {
			int comp = stack.pop();	//	stack의 head를 pop. 즉 제일 마지막 인덱스의 값을 가져옴
			for (int i = stack.size(); i > 0; i--) {	//	comp의 왼쪽 인덱스의 값들을 가져와 비교하는 반복문
				if (stack.get(i-1) > comp) { // comp보다 더 높은 값이라면 해당 인덱스를 queue에 push후 break
					answerQueue.push(i);
					break;
				} else if(i==1) answerQueue.push(0); // i=1일 때 위의 if문의 조건이 충족되지 않았다면 0을 queue에 push
			}
		}
		answerQueue.push(0); //	0번째 인덱스의 값은 0.
		for(int i=0; i<answer.length; i++) answer[i] = answerQueue.get(i); 
		
		
		return answer;
	}
	
	
	
}
