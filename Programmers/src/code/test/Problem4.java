package code.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
		List<String> gp = new ArrayList<String>();
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		// key : 장르, value : play의 합
		for (int i = 0; i < genres.length; i++) {
			map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
		}
		System.out.println(map);
		// value 값으로 map을 정렬해서 list에 저장
		List<String> gRank = new ArrayList<String>(map.keySet());
		Collections.sort(gRank, (o1, o2) -> map.get(o2).compareTo(map.get(o1))); // https://jobc.tistory.com/176 참고

		System.out.println("gRank : " + gRank);

		/*
		 * for(int i=0; i<genres.length; i++) { gp.add(genres[i]+";"+plays[i]); }
		 */
		// int[] answer 초기화
		int arrLeng = 0;
		for (String str : gRank) {
			arrLeng += count(str, genres);
		}
		answer = new int[arrLeng];
		/*
		 * System.out.println("gp : " + gp); Object[] ob = gp.toArray();
		 * Arrays.sort(ob);
		 */

		// answer에 넣을 시작 index;
		int startIndex = 0;
		int endIndex = 0;
		int index = 0;
		for (int j = 0; j < gRank.size(); j++) { // 배열에 넣을 장르 순서대로 반복
			for (int i = 0; i < genres.length; i++) { // 장르 목록에서 장르 순위와 일치하는 장르를 가져오는 반복문
				if (genres[i].equals(gRank.get(j))) {
					System.out.println("g : " + genres[i] + " p : " + plays[i]);
					if (answer[startIndex] > 0) {

					}
				} // if
			} // for i
		} // for j

		System.out.println(answer[0]);
		System.out.println(answer[1]);
		System.out.println(answer[2]);
		System.out.println(answer[3]);
		System.out.println(answer[4]);
		return answer;
	}

	// Array에 각 장르가 들어갈 개수를 구하는 메서드
	public int count(String genre, String[] genres) {
		int count = 0;
		for (String str : genres) {
			if (str.equals(genre))
				count++;
		}

		if (count > 2) {
			return 2;
		}

		return count;
	}

}
