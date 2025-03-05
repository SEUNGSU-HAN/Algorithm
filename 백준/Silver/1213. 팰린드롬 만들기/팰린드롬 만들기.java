import java.util.Arrays;
import java.util.Scanner;

public class Main {
	static String str;
	static char[] cl;
	static int[] counts;

	public static void main(String[] args) {
		/* 입력 */
		Scanner sc = new Scanner(System.in);
		str = sc.nextLine();
		
		/* 초기화 */
		cl = str.toCharArray();
		counts = new int['Z'-'A'+1];
		for (int i = 0; i < cl.length; i++) {
			counts[cl[i]-'A']++;
		}		
		
		/* 로직 */
		StringBuilder sb = new StringBuilder();
		str = "";
		int oddCount = 0;
		String midStr = "";
		for (int i = 0; i < counts.length; i++) {
			if(counts[i] == 0) continue;
			if(counts[i]%2 == 1) {
				oddCount++;
				//문자가 단 1개이면 정 가운데에 와야함
				midStr = String.valueOf((char)(65+i));
			}
			if(oddCount == 2) {
				//팰린드롬 실패
				str = "I'm Sorry Hansoo";
				break;
			}

			char c = (char)(65+i);
			for (int j = 0; j < counts[i]/2; j++) {
				sb.append(c);
			}
		}
		
		if(str.isEmpty()) {
			//팰린드로 가능
			String original = sb.toString();
			String reverse = sb.reverse().toString();
			str = original+midStr+reverse;
		}
		
		/* 출력 */
		System.out.print(str);
	}

}
