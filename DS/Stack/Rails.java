import java.util.*;
import java.io.*;

class Rails {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();	
		PrintWriter out = new PrintWriter(System.out);
		while(true) {
			int N = in.nextInt();
			if(N == 0)
				break;

			boolean end = false;
			while(!end) {
				int c = 0;
				int j = 0;
				Stack<Integer> stack = new Stack<Integer>();
				for(int i = 0; i < N; i++) {
					c = in.nextInt();
					if(c == 0) {
						end = true;
						break;
					}
					while(j < N && j != c) {
						if(stack.size() > 0 && stack.peek() == c)
							break;
						j++;
						stack.push(j);
					}

					if(stack.peek() == c) {
						stack.pop();
					}
				}

				if(end) {
					out.println();
				} else {
					out.println(stack.size() == 0 ? "Yes" : "No");
				}
			}
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