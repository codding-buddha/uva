import java.util.*;
import java.io.*;

class Redudancy {
	public static void main(String[] args){
		PrintWriter out = new PrintWriter(System.out);
		FastScanner in = new FastScanner();
		Map<Integer, Integer> lookup= new TreeMap<Integer, Integer>();
		List<Pair> list = new ArrayList<Pair>();
		String line;
		while(true) {
			try {
				 int n = in.nextInt();
				 if(lookup.containsKey(n)) {
				 	Pair p = list.get(lookup.get(n));
				 	p.val++;
				 } else {
				 	lookup.put(n, list.size());
				 	list.add(new Pair(n, 1L));
				 }
			}catch(Exception e) {
				break;
			}
		}
				
		for(int i = 0; i < list.size(); i++) {
			Pair p = list.get(i);
			out.println(p.key + " " + p.val);
		}

		out.flush();
		out.close();
	}

	static class Pair {
		public int key;
		public long val;

		public Pair(int k, long v) {
			this.key = k;
			this.val = v;
		}

		@Override
		public boolean equals(Object o) {
			if(!(o instanceof Pair))
				return false;
			if(o == this)
				return true;
			Pair p = (Pair) o;
			return p.key == this.key;
		}
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