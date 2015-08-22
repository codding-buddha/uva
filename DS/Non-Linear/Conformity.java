import java.util.*;
import java.io.*;

class Conformity {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		while(true) {
			int tc = in.nextInt();
			if(tc == 0) 
				break;
			Map<String, Integer> map = new HashMap<String, Integer>();
			int max = 0;
			int maxCount = 0;
			while(tc-->0) {
				int[] a = new int[5];
				for(int i = 0; i < 5; i++) {
					a[i] = in.nextInt();
				}
				Arrays.sort(a);
				String key = String.valueOf(a[0]) + String.valueOf(a[1]) + String.valueOf(a[2]) + String.valueOf(a[3]) + String.valueOf(a[4]);
				int val = !map.containsKey(key) ? 1 : map.get(key) + 1;
				map.put(key, val);
				if(val > max) {
					max = val;
					maxCount = 1;
				} else if(val == max) {
					maxCount++;
				}
			}
			
			out.println(maxCount*max);
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