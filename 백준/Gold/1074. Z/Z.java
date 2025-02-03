import java.util.Scanner;

public class Main {
	static int n;
	static int r, c, cnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		r = sc.nextInt();
		c = sc.nextInt();
		cnt = 0;
		
		//로직
		z(0, 0, 1<<n);
	}

	public static void z(int rr, int cc, int width) {
		if(r == rr & c == cc) {
			System.out.println(cnt);
			return;
		}
		
		if(r >= rr && r < rr+width && c >= cc && c < cc+width) {
			int w = width/2;
			z(rr, cc, w); //11시
			z(rr, cc+w, w); //1시
			z(rr+w, cc, w); //7시
			z(rr+w, cc+w, w); //5시
		}else cnt += width*width;
	}

}
