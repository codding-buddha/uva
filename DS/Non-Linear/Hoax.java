import java.util.*;
import java.io.*;

class Hoax {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int count = 0;
		while((count = in.nextInt()) != 0) {
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			long sum = 0;
			while(count-- > 0) {
				int n = in.nextInt();

				while(n-- > 0) {
					 int val = in.nextInt();
					 if(!map.containsKey(val)) {
						map.put(val, 1);
					 } else {
					 	map.put(val, map.get(val) + 1);
					 }
				}

				int low = map.firstKey();
				int high = map.lastKey();
				sum += (high - low);
				int hc = map.get(high);
				int lc = map.get(low);
				
				if(hc > 1) {
					map.put(high, hc - 1);
				} else {
					map.remove(high);
				}

				if(lc > 1) {
					map.put(low, lc - 1);
				} else {
					map.remove(low);
				}
			}

			out.println(sum);
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