package code.test.hash;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/*
 * 
https://programmers.co.kr/learn/courses/30/lessons/42579	베스트 앨범
1.속한 노래가 많이 재생된 장르를 먼저 수록합니다.
2.장르 내에서 많이 재생된 노래를 먼저 수록합니다.
3.장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.

제한사항

genres[i]는 고유번호가 i인 노래의 장르입니다.
plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
장르 종류는 100개 미만입니다.
장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
모든 장르는 재생된 횟수가 다릅니다.
*
*/
public class Problem4 {

	public static void main(String[] args) {

		String[] genres = { "classic", "pop", "classic", "classic", "pop", "rock" };
		int[] plays = { 500, 600, 150, 800, 2500, 1000 };

		new Problem4().solution1(genres, plays);

	}

	public int[] solution1(String[] genres, int[] plays) {
		int[] answer = {};
		Map<String, Integer> rankMap = new HashMap<String, Integer>();
		Map<String, Integer> hm = new HashMap<String, Integer>();

		// HashMap rankMap => key : 장르, value : 장르별 재생횟수의 합
		for (int i = 0; i < genres.length; i++) {
			rankMap.put(genres[i], rankMap.getOrDefault(genres[i], 0) + plays[i]);
		}
		System.out.println(rankMap);

		//	HashMap hm => key :  장르:인덱스, value : plays
		for (int i = 0; i < genres.length; i++) {
			hm.put(genres[i] + ":" + i, plays[i]);
		}
		System.out.println(hm);

		List<String> gRank = new ArrayList<String>(rankMap.keySet());	//reankMap의 keySet으로 ArrayList생성
		Collections.sort(gRank, (o1, o2) -> rankMap.get(o2).compareTo(rankMap.get(o1))); //  value 값으로 key를 내림차순정렬해서 list에 저장 https://jobc.tistory.com/176 참고
		System.out.println("gRank : " + gRank);

		Set<Entry<String, Integer>> set = hm.entrySet();
		List<Integer> answerList = new ArrayList<Integer>(); // 베스트앨범 곡 수록을 위한 list 추후에 배열로 변환
		
		for (String rank : gRank) {	//	각 순위별 장르로 반복문 실행한다.
			Map<String, Integer> tempMap = new HashMap<String, Integer>();
			//	hm에서 해당 장르를 key에 포함하고있는 Entry를 찾아서 tempMap에 key,value로 저장한다.
			for (Entry<String, Integer> entry : set) {
				if (entry.getKey().contains(rank)) {
					tempMap.put(entry.getKey(), entry.getValue());
				}
			}
			System.out.println("solveMap : " + tempMap);
			//	tempMap에 저장된 데이터를 value로 key를 내림차순으로 정렬해 tempList에 저장한다.
			List<String> tempList = new ArrayList<String>(tempMap.keySet());
			Collections.sort(tempList, (o1, o2) -> tempMap.get(o2).compareTo(tempMap.get(o1)));
			System.out.println("solveList : " + tempList);
			
			//	tempList를 ':'로 split하여 1번째 인덱스를 answerList에 저장한다.
			for (int i = 0; i < (tempList.size() >= 3 ? 2 : tempList.size()); i++) {
				answerList.add(Integer.parseInt(tempList.get(i).split(":")[1]));
			}

		}
		System.out.println("answerList : " + answerList);
		//	answerList의 size와 동일한 크기의 배열 answer를 만들어 각 인덱스를 순서대로 저장한다.
		answer = new int[answerList.size()];
		for (int i = 0; i < answerList.size(); i++) {
			answer[i] = answerList.get(i);
		}

		return answer;
	}

}
