import java.util.*;
import java.io.*;

class HardwoodSpecies {
	public static void main(String[] args)
	{
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		in.nextLine();
		Map<String, Integer> map = new TreeMap<String, Integer>();
		for(int i = 0; i < tc; i++) {
			String t;
			long total = 0;
			while(true) {
				t = in.nextLine();
				if(t == null || t.equals(""))
					break;
				
				if(!map.containsKey(t)) {
					map.put(t, 1);
				} else {
					map.put(t, map.get(t) + 1);
				}
				total++;
			}
			
			float factor = 100.0F/total;
			for(Map.Entry<String, Integer> entry: map.entrySet()) {
				String name = entry.getKey();
				float value = entry.getValue()*factor;
				String output = String.format("%s %.4f", name, value);
				out.println(output);
			}
			if(i != tc-1) {
				out.println();
			}
			map.clear();
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