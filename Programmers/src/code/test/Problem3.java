package code.test;

import java.util.Arrays;
import java.util.HashMap;
import static java.util.stream.Collectors.*;

//	https://programmers.co.kr/learn/courses/30/lessons/42578	위장
public class Problem3 {

	public static void main(String[] args) {
		
		String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
		
		Problem3 problem3 = new Problem3();
		problem3.solution1(clothes);
		
	}
	
	
	//	정확성 성공
	 public int solution1(String[][] clothes) {
	        int answer = 0;
	        
	        HashMap<String, Integer> map = new HashMap<String, Integer>();
	        //	HashMap에 key : 의상 종류, value : 갯수 넣어줌
	        for(String[] item : clothes) {
	        	map.put(item[1], map.getOrDefault(item[1], 0) + 1);
	        }
	        
	        /*
	         *	HashMap의 각 value값을 추출해서 계산하기 위해서 Entry를 뽑아옴
	        Set<Entry<String, Integer>> entries = map.entrySet();
	        
	        for(Entry<String, Integer> entry : entries) {
	        	answer += answer*entry.getValue() + entry.getValue();
	        }
	        */
	        
	        //	Entry를 뽑아오는 것 보다 value의 Collection을 뽑아 와서 비교하는 것이 더 간편함
	        for(int val : map.values()) {
	        	answer += answer*val + val;
	        }
	        
	        return answer;
	    }
	 
	 //	다른 사람 풀이 분석해보기
	 public int solution2(String[][] clothes) {
	        return Arrays.stream(clothes)
	                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
	                .values()
	                .stream()
	                .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
	    }
	
	
}
