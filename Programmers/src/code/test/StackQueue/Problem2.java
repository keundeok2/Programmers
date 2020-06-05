package code.test.StackQueue;

import java.util.LinkedList;
import java.util.Queue;
// 다리를 지나는 트럭	https://programmers.co.kr/learn/courses/30/lessons/42583
public class Problem2 {

	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = { 7, 4, 5, 6 };

		Problem2 problem2 = new Problem2();
		System.out.println("answer : " + problem2.solution1(bridge_length, weight, truck_weights));

	}

	// 정확도 성공 , 속도 너무 느림
	public int solution1(int bridge_length, int weight, int[] truck_weights) {
		int answer = 0;
		int waiting = truck_weights.length; // 트럭의 갯수
		int index = 1; // truck_weights의 인덱스

		Queue<Integer> queue = new LinkedList<Integer>();
		// 최초 트럭 하나를 큐에 넣음
		queue.add(truck_weights[0]);
		answer++;

		while (sumQueue(queue) != 0) { // 큐안의 모든 값들이 0이면 반복문 종료
			if (queue.size() == bridge_length) { // 큐의 사이즈와 다리의 길이가 같다면 트럭이 도착했으므로 큐에서 빼주기
				queue.poll();
			}

			if (waiting != 1) { // 트럭의 갯수가 1일 때 제외
				if (weight >= sumQueue(queue) + truck_weights[index]) {
					queue.add(truck_weights[index]);
					index++; // 트럭이 하나 들어갔으므로 인덱스 증가시켜서 다음 트럭 넣을 준비
				} else {
					queue.add(0);
				}
				answer++;
			}

			// 마지막 트럭이 다리 위에 올라갔으므로 다리 길이만큼의 시간을 answer에 더해주고 break
			if (index == waiting) {
				answer += bridge_length;
				break;
			}
		}

		return answer;
	}
	
	public int sumQueue(Queue<Integer> queue) {
		int sum = 0;
		for (int i : queue)
			sum += i;

		return sum;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////
	//	다른 사람의 멋진 풀이
	class Truck {
        int weight;
        int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }

    public int solution2(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();

        for (int t : truckWeights) {
            waitQ.offer(new Truck(t));
        }

        int answer = 0;
        int curWeight = 0;

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            answer++;

            if (moveQ.isEmpty()) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            for (Truck t : moveQ) {
                t.moving();
            }

            if (moveQ.peek().move > bridgeLength) {
                Truck t = moveQ.poll();
                curWeight -= t.weight;
            }

            if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
            }
        }

        return answer;
    }
	

}
