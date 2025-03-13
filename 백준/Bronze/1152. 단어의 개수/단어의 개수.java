import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		String[] sl = br.readLine().trim().split(" ");
		System.out.print(sl[0].equals("") ? 0 : sl.length);
	}
}
