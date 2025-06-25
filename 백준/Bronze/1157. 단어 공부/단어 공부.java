import java.io.*;
import java.util.*;

public class Main {
	static String str;
	static int max, maxCount, maxIndex;
	static int[] alpha;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().toLowerCase();
		
		/* 초기화 */
		alpha = new int[26];

		/* 로직 */
		for (int i = 0; i < str.length(); i++) {
			alpha[str.charAt(i)-'a']++;
		}
		
		for (int i = 0; i < 26; i++) {
			if(alpha[i] > max) {
				max = alpha[i];
				maxCount = 1;
				maxIndex = i;
			}else if(alpha[i] == max) {
				maxCount++;
			}
		}
		
		/* 출력 */
		System.out.println(maxCount > 1 ? "?" : Character.toString((char)('A'+maxIndex)));
	}

}
