import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;

//수학숙제
public class Main {
	static int N;
	static char[][] str;
	static ArrayList<BigInteger> nl;
    
	public static void main(String[] args) throws Exception{
		/* 입력 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		StringBuilder sb = new StringBuilder();
		
		/* 초기화 */
		str = new char[N][];
		for (int i = 0; i < N; i++) {
			str[i] = br.readLine().toCharArray();
		}
		nl = new ArrayList<BigInteger>();
		
		/* 로직 */
		//int 안됨 -> Long 안됨 -> 더 커야댐
		//a9999....9999a (9가 연달아 98개) 들어오면 수용할 수 있어야함
		//BigInteger 사용해보자.
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < str[i].length; j++) {
				if(Character.isDigit(str[i][j])) { //숫자일 경우
					sb.append(String.valueOf(str[i][j]));
				}else {
					if(sb.length() != 0) {
						nl.add(new BigInteger(sb.toString()));
					}
					sb.setLength(0);
				}
			}
			if(sb.length() != 0) {
				nl.add(new BigInteger(sb.toString()));
			}
			sb.setLength(0);
		}
		nl.sort(BigInteger::compareTo);
		
		/* 출력 */
		sb.setLength(0);
		for (BigInteger n : nl) {
			sb.append(n+"\n");
		}		
		System.out.print(sb.toString());
	}

}
