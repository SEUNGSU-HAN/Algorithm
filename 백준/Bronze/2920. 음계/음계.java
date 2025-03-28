import java.io.*;

public class Main {
	static String ascending = "1 2 3 4 5 6 7 8";
	static String descending = "8 7 6 5 4 3 2 1";

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine().trim();
		System.out.print(str.equals(ascending) ? "ascending" : (str.equals(descending) ? "descending" : "mixed"));
	}

}
