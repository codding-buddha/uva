import java.util.*;
import java.io.*;

class Median {
	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String ip = null;
		PriorityQueue<Integer> max = new PriorityQueue<Integer>(50, new Comparator<Integer>() {
			@Override
			public int compare(Integer i1, Integer i2) {
				return i2.compareTo(i1);
			}
		});
		PriorityQueue<Integer> min = new PriorityQueue<Integer>(50);
		while(true) {
			try{
				ip = in.nextLine().trim();
			}catch(Exception e) {
				break;
			}
			 
			 if(ip.equals("")){
			 	break;
		 	}

			int n = Integer.parseInt(ip);
			if(max.size() == 0 || max.peek() > n) {
				max.add(n);
			} else {
				min.add(n);
			}

			int ms = max.size();
			int mins = min.size();

			if(Math.abs(ms- mins) > 1) {
				if(ms > mins) {
					min.add(max.poll());
				} else {
					max.add(min.poll());
				}
			}

			int result = 0;
			
			if((ms + mins)%2 == 0) {
				result = (max.peek() + min.peek())/2;
			} else {
				result = ms > mins ? max.peek() : min.peek();
			}

			out.write(result +  "\n");
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
				//e.printStackTrace();
			}

			return str;
		}
	}
}