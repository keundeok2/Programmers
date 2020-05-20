package code.test;

import java.util.Arrays;

//	https://programmers.co.kr/learn/courses/30/lessons/42577	전화번호 목록
public class Problem2 {
	public static void main(String[] args) {
		
		String[] phone_book = {"119", "97674223", "1195524421"};
		
		Problem2 problem2 = new Problem2();
		System.out.println(problem2.solution1(phone_book));
		System.out.println(problem2.solution2(phone_book));
	
		
	}
	
	//	정확성 성공, 효율성 성공
	public boolean solution1(String[] phone_book) {
        boolean answer = true;
        
       Arrays.sort(phone_book);	//	phone_book 정렬
       //	첫 번째 번호부터 그 다음 번호와 접두어 관계가 있는지 확인
       for(int i=0; i<phone_book.length-1; i++) {
    	   for (int j = i+1; j<phone_book.length; j++) {
    		   if (phone_book[j].indexOf(phone_book[i]) == 0) {	//	i번째 번호가 j번째 번호의 접두어일 때 false 반환
    			   return false;
			}
		}
    	   
       }
       
        return answer;
    }
	//	정확성 성공, 효율성 성공
	public boolean solution2(String[] phone_book) {
		boolean answer = true;
		
		Arrays.sort(phone_book);//	phone_book 정렬
		//	첫 번째 번호부터 그 다음 번호와 접두어 관계가 있는지 확인
		for (int i = 0; i < phone_book.length-1; i++) {
			if (phone_book[i+1].startsWith(phone_book[i])) {
				return false;
			}
		}
		
		return answer;
	}
	
	
}
