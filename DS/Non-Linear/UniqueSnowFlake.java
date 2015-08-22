import java.util.*;
import java.io.*;

class UniqueSnowFlake {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int c = in.nextInt();
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			int s = 0;
			int max = 0;
			for(int i = 0; i < c; i++) {
				int id = in.nextInt();
				if(map.containsKey(id) && map.get(id) >= s) {
					if((i-s) > max)
						max = i-s;
					s = map.get(id) + 1;
				}
				
				map.put(id, i);
			}

			if(s < c && (c - s) > max) {
				max = c-s;
			}

			out.println(max);
		}
		out.close();
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

