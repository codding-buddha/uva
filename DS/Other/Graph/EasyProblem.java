import java.io.*;
import java.util.*;

class EasyProblem {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		try{
			while(true) {
				int n = in.nextInt();
				int q = in.nextInt();
				HashMap<Integer, List<Integer>> graph = new HashMap<Integer, List<Integer>>();
				for(int i = 0; i < n; i++) {
					int num = in.nextInt();
					if(!graph.containsKey(num)) {
						List<Integer> pos = new ArrayList<Integer>();
						pos.add(i+1);
						graph.put(num, pos);
					} else {
						graph.get(num).add(i+1);
					}
				}

				while(q-- > 0) {
					int k = in.nextInt();
					int v = in.nextInt();
					if(graph.containsKey(v) && graph.get(v).size() >= k) {
						out.println(graph.get(v).get(k-1));
					} else {
						out.println(0);
					}
				}				
			}
		}catch(Exception e) {
			
		}finally {
			out.close();
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