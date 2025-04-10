import java.io.*;
import java.util.*;

public class Main {
	static char[] text, pattern;
	static int[] pi;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		text = br.readLine().toCharArray();
		pattern = br.readLine().toCharArray();

		/* 초기화 */
		pi = new int[pattern.length];
		
		/* 로직 */
		for (int i = 1, j = 0; i < pattern.length; i++) {
			while(j > 0 && pattern[i] != pattern[j]) {
				j = pi[j-1];
			}
			
			if(pattern[i] == pattern[j]) pi[i] = ++j;
		}
		
		int count = 0;
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0, j = 0; i < text.length; i++) {
			while(j > 0 && text[i] != pattern[j]) {
				j = pi[j-1];
			}
			if(text[i] == pattern[j]) {
				if(j == pattern.length-1) {
					++count;
					list.add(i-pattern.length+2);
					j = pi[j];
				}else ++j;
			}
		}
		
		/* 출력 */
		StringBuilder sb = new StringBuilder();
		sb.append(count).append("\n");
		if(count > 0) {
			for (int i : list) {
				sb.append(i).append("\n");
			}
		}
		System.out.print(sb);
	}

}
