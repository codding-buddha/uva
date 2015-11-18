import java.util.*;
import java.io.*;

class LemmingsBattle 
{

	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int b = in.nextInt();
			int gc = in.nextInt();
			int bc = in.nextInt();
			PriorityQueue<Integer> gp = new PriorityQueue<Integer>(gc + 1, Collections.reverseOrder());
			PriorityQueue<Integer> bp = new PriorityQueue<Integer>(bc + 1, Collections.reverseOrder());

			while(gc-- > 0) {
				gp.offer(in.nextInt());
			}

			while(bc-- > 0) {
				bp.offer(in.nextInt());
			}

			while(gp.size() > 0 && bp.size() > 0) {
				int temp = b;
				List<Integer> l1 = new ArrayList<Integer>();
				List<Integer> l2 = new ArrayList<Integer>();
				while(temp-- > 0) {
					if(gp.size() == 0 || bp.size() == 0)
						break;
					int p1 = gp.poll();
					int p2 = bp.poll();
					int diff = p1 - p2;
					if(diff > 0) {
						l1.add(diff);
					} else if(diff < 0) {
						l2.add(-1*diff);
					}
				}

				for(int i = 0; i < l1.size(); i++)
					gp.offer(l1.get(i));

				for(int i = 0; i < l2.size(); i++)
					bp.offer(l2.get(i));
			}

			if(gp.size() > 0) {
				out.println("green wins");
				while(gp.size() > 0)
					out.println(gp.poll());
			} else if(bp.size() > 0) {
				out.println("blue wins");
				while(bp.size() > 0)
					out.println(bp.poll());
			} else {
				out.println("green and blue died");
			}

			if(tc > 0) {
				out.println();
			}
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