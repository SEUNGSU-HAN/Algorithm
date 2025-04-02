import java.io.*;

public class Main {
	static int N;
	static String[] pattern;
	static String pStr;

	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		
		/* 초기화 */
		pattern = br.readLine().split("\\*");
		pStr = pattern[0]+pattern[1];

		/* 로직 */
		StringBuilder print_sb = new StringBuilder();
		boolean isEqual;
		for (int i = 0; i < N; i++) {
			StringBuilder input_sb = new StringBuilder();
			input_sb.append(br.readLine().trim());
			if(pStr.length() == input_sb.length()) {
				isEqual = pStr.equals(input_sb.toString()) ? true : false;
			}else if(pStr.length() > input_sb.length()) isEqual = false;
			else {
				if(pattern[0].equals(input_sb.substring(0, pattern[0].length()))
						&& pattern[1].equals(input_sb.substring(input_sb.length()-pattern[1].length())))
					isEqual = true;
				else
					isEqual = false;
			}
			print_sb.append(isEqual ? "DA\n" : "NE\n");
		}
		
		/* 출력 */
		System.out.print(print_sb);
	}

}
