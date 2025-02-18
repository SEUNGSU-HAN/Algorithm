class Solution {
    static int ans;
    public int solution(int k, int[][] dungeons) {
		int answer = -1;
		int N = dungeons.length;
		boolean[] visited = new boolean[N];
		ans = 0;
		getAns(k, dungeons, visited, 0);
		return ans;
	}

	private void getAns(int k, int[][] dungeons, boolean[] visited, int cnt) {
		for (int i = 0; i < dungeons.length; i++) {
			if (visited[i]) continue;
			if (k >= dungeons[i][0]) {
				visited[i] = true;
				getAns(k - dungeons[i][1], dungeons, visited, cnt + 1);
				visited[i] = false;
			}
		}
		ans = Math.max(ans, cnt);
	}
}