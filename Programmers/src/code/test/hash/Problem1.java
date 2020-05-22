package code.test.hash;

import java.util.Arrays;
import java.util.HashMap;

//	https://programmers.co.kr/learn/courses/30/lessons/42576	완주하지 못한 선수
public class Problem1 {
	public static void main(String[] args) {
		String[] part = {"c", "a", "b"};
		String[] comp = {"c", "b"};
		
		
		
		Problem1 problem1 = new Problem1();
		problem1.solution2(part, comp);
		
		
		
		
	}
	
	//	정확성 성공, 효율성 탈락
	public String solution1(String[] participant, String[] completion) {
        String answer = "";
        
        
        for (int i = 0; i < participant.length; i++) {	//	참가자 목록 가져오기
        	int count = 0;	// 참가자의 이름이 완주자 목록에 있는지 검사하는 카운트
        	answer = participant[i];
        	for (int j = 0; j < completion.length; j++) {	//	완주자 목록 가져오기
				if (answer.equals(completion[j])) {
					count++;
					completion[j]="";	//	중복되는 참가자의 이름을 검사할 때 카운트를 증가시키지 않도록
					break;	//	해당 참가자가 완주자 목록에 있을 때 for loop break
				}
				
			}//for
        	//	count가 0이라면 참가자가 완주자 목록에 없으므로 완주하지 못한 선수
        	if (count == 0) {
        		return answer;
        	}
		}//for
        
        
        return answer;
    }
	
	//	정확성 성공, 효율성 성공
	public String solution2(String[] participant, String[] completion) {
		String answer = "";
		
		//	participant, completion 정렬
		Arrays.sort(participant);
		Arrays.sort(completion);
		
		int i = 0; // 완주자의 길이만큼 비교시 참가자와 완주자가 모두 같을 경우를 대비해서 지역변수로 선언 ex) [a, b, c] [a, b] a와 b가 같은 경우
		for(i=0; i<completion.length; i++) {
			if (!participant[i].equals(completion[i])) {
				return answer=participant[i]; // 참가자의 인덱스와 완주자의 인덱스의 값이 다를 경우 해당 인덱스의 참가자는 완주하지 못한 선수
			}
		}
		
		return answer=participant[i];	// 모든 인덱스의 값이 같을 경우 마지막에 남은 참가자가 완주하지 못한 선수
	}
	
	
	
	//	정확성 성공, 효율성 성공
	public String solution3(String[] participant, String[] completion) {
		String answer = "";
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		//	참가자의 이름이 map에 없다면 0, 있다면 value + 1
		for(String player : participant) {
			map.put(player, map.getOrDefault(player, 0) + 1);
		}
		
		//	참가자의 이름이 map에 있다면 value - 1
		for(String player : completion) {
			map.put(player, map.get(player) - 1);
		}
		
		//	참가자 중 value가 0이 아닌 참가자 => 완주하지 못한 참가자
		for(String key : map.keySet()) {
			if (map.get(key) != 0) {
				answer = key;
			}
		}
		
		return answer;
	}
	
}
