import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = "";
		while(!(str = br.readLine()).equals("0")) {
			boolean result = true;			
			char[] cl = str.toCharArray();
			int s = 0, e = cl.length-1;
			while(s < e) {
				if(cl[s++] != cl[e--]) {
					result = false;
				}
			}
			sb.append(result ? "yes" : "no").append("\n");
		}
		System.out.print(sb);
	}

}
