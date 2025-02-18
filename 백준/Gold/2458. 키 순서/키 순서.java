
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
    static int T, N, M;
    static int cnt;
    static int sum;
    static boolean[] visited;
    static ArrayList<Integer>[] heightArr;
    static ArrayList<Integer>[] heightArrRev;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            cnt = 0;
            heightArr = new ArrayList[N+1];
            heightArrRev = new ArrayList[N+1];
            for (int i = 0; i < heightArr.length; i++) {
                heightArr[i] = new ArrayList<Integer>();
                heightArrRev[i] = new ArrayList<Integer>();
            }
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int small = Integer.parseInt(st.nextToken());
                int tall = Integer.parseInt(st.nextToken());
                heightArr[tall].add(small);
                heightArrRev[small].add(tall);
            }
            for (int i = 1; i <= N; i++) {
                getAns(i);
            }
        System.out.println(cnt);
    }
    private static void getAns(int i) {
        visited = new boolean[N+1];
        visited[i] = true;
        getSmallCnt(i);
        getTallerCnt(i);
        for (int j = 1; j < visited.length; j++) {
			if(!visited[j]) return;
		}
        cnt++;
        return;
    }
    private static void getTallerCnt(int n) {
    	Queue<Integer> que = new ArrayDeque<>();
    	for (int x : heightArrRev[n]) {
    		visited[x] = true;
    		que.offer(x);
    	}
    	while(!que.isEmpty()) {
    		int b = que.poll();
    		for (int x : heightArrRev[b]) {
    			if(visited[x]) continue;
        		visited[x] = true;
        		que.offer(x);
        	}
    	}
    }
    private static void getSmallCnt(int n) {
    	Queue<Integer> que = new ArrayDeque<>();
    	visited[n] = true;
    	for (int x : heightArr[n]) {
    		visited[x] = true;
    		que.offer(x);
    	}
    	while(!que.isEmpty()) {
    		int b = que.poll();
    		for (int x : heightArr[b]) {
    			if(visited[x]) continue;
        		visited[x] = true;
        		que.offer(x);
        	}
    	}
    }
}
