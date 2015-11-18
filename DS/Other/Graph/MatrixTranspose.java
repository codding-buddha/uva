import java.io.*;
import java.util.*;

class MatrixTranspose {

	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		try {
			while(true) {
				int m = in.nextInt();
				int n = in.nextInt();
				HashMap<Integer, List<IntPair>> graph = new HashMap<Integer, List<IntPair>>();
				for(int i = 0; i < m; i++) {
					int nonZeroCount = in.nextInt();
					if(nonZeroCount == 0){
						in.nextLine();
						continue;
					}
					int temp = nonZeroCount;
					int vertex = i + 1;
					List<IntPair> neighbours = new ArrayList<IntPair>();
					graph.put(vertex, neighbours);
					while(temp-- > 0) {
						IntPair pair = new IntPair();
						pair.first = in.nextInt();
						neighbours.add(pair);
					}

					for(int k = 0; k < neighbours.size(); k++) {
						neighbours.get(k).second = in.nextInt();
					}
				}

				HashMap<Integer, List<IntPair>> gt = new HashMap<Integer, List<IntPair>>();
				for(int i = 0; i < n; i++) {
					gt.put(i+1, new ArrayList<IntPair>());
				}

				for(Map.Entry<Integer, List<IntPair>> entry: graph.entrySet()) {
					for(IntPair p : entry.getValue()) {
						gt.get(p.first).add(new IntPair(entry.getKey(), p.second));
					}
				}

				//dimension
				out.println(n + " " + m);

				for(Map.Entry<Integer, List<IntPair>> entry: gt.entrySet()) {
					List<IntPair> vertices = entry.getValue();
					int count = vertices.size();
					out.print(count);
					if(count == 0){
						out.println("\n");
						continue;
					}
					for(int i = 0; i < count; i++) {
						out.print(" " + vertices.get(i).first);
					}
					out.println();
					for(int i = 0; i < count; i++) {
						out.print(vertices.get(i).second + (i == count-1 ? "" : " "));
					}
					out.println();
				}
			}
		}catch(Exception e) {
			
		} finally {
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

	static class IntPair {
		public int first;
		public int second;

		public IntPair(int f, int s) {
			this.first = f;
			this.second = s;
		}

		public IntPair() {
			this.first = 0;
			this.second = 0;
		}
	}
}