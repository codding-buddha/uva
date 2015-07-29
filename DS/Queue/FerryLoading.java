import java.util.*;
import java.io.*;

class FerryLoading 
{
	public static void main(String[] args) throws Exception{
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int capacity = in.nextInt();
			int time = in.nextInt();
			int arrivals = in.nextInt();
			Queue<Index> qleft = new ArrayDeque<Index>();
			Queue<Index> qright = new ArrayDeque<Index>();
			int startTime = 0;
			String currentBank =  "left";
			int aCount = arrivals;
			int[] output = new int[arrivals];
			int idx = 0;
			while(aCount-- > 0) {
				int t = in.nextInt();
				String bank = in.next();
				Index indx = new Index();
				indx.i = idx++;
				indx.v = t;
				if(bank.equals("left")) {
					qleft.add(indx);
				} else {
					qright.add(indx);
				}
			}

			while(qleft.size() > 0 || qright.size() > 0) {
				int loaded = 0;
				if(currentBank.equals("left")) {
					if(qleft.size() == 0) {
						startTime = (startTime > qright.peek().v ? startTime : qright.peek().v) + time;
						currentBank = "right";
						continue;
					}

					if(startTime < qleft.peek().v) {
						if(qright.size() == 0 || qleft.peek().v < qright.peek().v) {
							startTime = qleft.peek().v;
						} else{

							startTime = (startTime > qright.peek().v ? startTime : qright.peek().v) + time;
							currentBank = "right";
							continue;
						}
					}

					while(qleft.size() >  0 && startTime >= qleft.peek().v && loaded < capacity) {
						output[qleft.peek().i] = startTime + time;
						qleft.remove();
						loaded++;
					}

					startTime += time;
					currentBank = "right";

				} else {
					if(qright.size() == 0) {
						startTime = (startTime > qleft.peek().v ? startTime : qleft.peek().v ) + time;
						currentBank = "left";
						continue;	
					}

					if(startTime < qright.peek().v) {
						if(qleft.size() == 0 || qright.peek().v < qleft.peek().v) {
							startTime = qright.peek().v;
						} else{
							startTime = (startTime > qleft.peek().v ? startTime : qleft.peek().v ) + time;
							currentBank = "left";
							continue;
						}
					}

					while(qright.size() >  0 && startTime >= qright.peek().v && loaded < capacity) {
						output[qright.peek().i] = startTime + time;
						qright.remove();
						loaded++;
					}

					startTime += time;
					currentBank = "left";

				}
			}

			for(int i = 0; i < output.length; i++) {
				out.println(output[i]);
			}

			if(tc > 0)
				out.println();
			
		}

		out.flush();
	}

	static class Index {
		public int i;
		public int v;
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
