import java.util.*;
import java.io.*;

class MultiTasking {
	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String line = null;
		while(true) {
			line = in.nextLine();
			if(line.equals("0 0"))
				break;
			StringTokenizer tokens = new StringTokenizer(line);
			int N = Integer.parseInt(tokens.nextToken());
			int M = Integer.parseInt(tokens.nextToken());
			BitSet bitset = new BitSet();
			boolean isConflict = false;
			for(int i =0; i < N; i++) {
				String[] range = in.nextLine().split(" ");
				if(!isConflict){
					int[] rangeInt = new int[] {Integer.parseInt(range[0]), Integer.parseInt(range[1]) };
					if(bitset.get(rangeInt[0], rangeInt[1]).cardinality() > 0) {
						isConflict = true;
						continue;
					}
					bitset.set(rangeInt[0], rangeInt[1]);
				}
			}

			for(int i = 0; i < M; i++) {
				String[] input = in.nextLine().split(" ");
				if(!isConflict) {
					int[] rangeInt = new int[] {Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]) };
					if(rangeInt[2] < (rangeInt[1] - rangeInt[0])) {	
						isConflict = true;
						continue;
					}
					
					while(rangeInt[0] <= 1000000) {
						int setCount = 0;
						if(bitset.get(rangeInt[0], rangeInt[1]).cardinality() > 0) {
							isConflict = true;
							break;
						}

						bitset.set(rangeInt[0], rangeInt[1]);
						rangeInt[0] += rangeInt[2];
						rangeInt[1] += rangeInt[2];	
					}
				}
			}

			out.write(isConflict ?  "CONFLICT" : "NO CONFLICT");
			out.write("\n");
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