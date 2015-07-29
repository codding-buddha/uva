import java.io.*;
import java.util.*;

class FerryLoadingIV {
	enum Side {
		Left,
		Right
	}
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();

		while(tc-- > 0) {
			long blen = in.nextLong()*100;
			int arrivals = in.nextInt();
			Queue<Integer> left = new ArrayDeque<Integer>();
			Queue<Integer> right = new ArrayDeque<Integer>();

			while(arrivals-- > 0) {
				int len = in.nextInt();
				String side = in.next();
				
				if(len > blen) {
					continue;
				}

				if(side.equals("left")) {
					left.add(len);
				} else {
					right.add(len);
				}
			}

			int crossCount = 0;
			//0 for left
			Side currentSide = Side.Left;

			while(left.size() > 0 || right.size() > 0) {
				if(currentSide == Side.Left) {
					//empty queue
					if(left.size() == 0) {
						crossCount++;
						currentSide = Side.Right;
						continue;
					}

					long loaded = 0;
					while(left.size() > 0 && (loaded + left.peek()) <= blen) {
						loaded += left.remove();
					}

					crossCount++;
					currentSide = Side.Right;

				} else {
					//empty queue
					if(right.size() == 0) {
						crossCount++;
						currentSide = Side.Left;
						continue;
					}

					long loaded = 0;
					while(right.size() > 0 && (loaded + right.peek()) <= blen) {
						loaded += right.remove();
					}
					
					crossCount++;
					currentSide = Side.Left;
				}
			}

			out.println(crossCount);
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