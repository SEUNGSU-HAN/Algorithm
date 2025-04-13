import java.io.*;

public class Main {
	static String encoded;
	
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		encoded = br.readLine().trim();

		/* 로직 */
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < encoded.length(); i++) {
			int ci = encoded.charAt(i)-3;
			if(ci < 'A') ci = 'Z' + (ci-'A')+1;
			sb.append(Character.toString((char)ci));
		}
		
		/* 출력 */
		System.out.print(sb);
	}

}
