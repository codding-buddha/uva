import java.util.*;
import java.io.*;

class AddAll {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		
		int count = 0;
		while((count = in.nextInt()) != 0) {
			int cost = 0;
			PriorityQueue<Integer> pq = new PriorityQueue<Integer>();	
			while(count-- > 0) {
				pq.add(in.nextInt());
			}
			while(pq.size() > 0) {
				int s = pq.poll() + pq.poll();
				cost += s;
				if(pq.size() != 0)
					pq.add(s);
			}
			out.println(cost);
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

		boolean hasNext() {
			return st != null && st.hasMoreElements();
		}

		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
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
			}

			return str;
		}
	}

}