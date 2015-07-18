import java.io.*;
import java.util.*;

class PotentCorner {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		while(true) {
			int n = in.nextInt();
			int a = 0;
			int b = 0;

			if(n == 0)
				break;
			int indx = 1;
			for (int i = 0; i < 32; i++) {
				int v = 1<<i;
				if((n&v) > 0) {
					if((indx&1) == 1) {
						a = a | v;
					}else {
						b = b | v;
					}
					indx++;
				}
			}

			out.println(a + " " + b);
		}
		out.flush();
	}


	static class FastScanner
	{
		BufferedReader br;
		StringTokenizer st;
		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			}catch(IOException e) {
				e.printStackTrace();
			}

			return str;
		}
	}
}