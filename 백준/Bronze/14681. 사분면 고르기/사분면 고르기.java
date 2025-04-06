import java.io.*;

public class Main {
	static int x, y;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		x = Integer.parseInt(br.readLine().trim());
		y = Integer.parseInt(br.readLine().trim());
		if(x > 0 && y > 0) System.out.print(1);
		else if(x < 0 && y > 0) System.out.print(2);
		else if(x < 0 && y < 0) System.out.print(3);
		else System.out.print(4);
	}

}
