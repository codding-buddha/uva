import java.util.*;
import java.io.*;

class Argus {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		List<Query> queries = new ArrayList<Query>();
		String input = null;

		while(!(input = in.next().trim()).equals("#")) {
			queries.add(new Query(in.nextInt(), in.nextInt()));
		}

		int num = in.nextInt();
		PriorityQueue<Query> pq = new PriorityQueue<Query>(num+1, new QueryComparator());
		for(int i = 0; i < queries.size(); i++) {
			Query ori = queries.get(i);
			for(int k = 1; k <=num; k++) {
				if(pq.size() < num) {
					Query q = new Query(ori.id, ori.period*k);
					pq.add(q);
					continue;
				}	

				Query q = pq.peek();
				if(q.period >= ori.period*k) {
					q = new Query(ori.id, ori.period*k);
					pq.add(q);
					pq.poll();
				} else {
					break;
				}
			}			
		}

		StringBuilder sb = new StringBuilder(10000);
		int[] ids = new int[pq.size()];

		while(pq.size() > 0) {
			int indx = pq.size() - 1;
			ids[indx] = pq.poll().id;
		}
		for(int i =0; i < ids.length; i++) {
			out.println(ids[i]);
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

	static class Query {
		public int id;
		public int period;

		public Query(int i, int p) {
			id = i;
			period = p;
		}
	}

	public static class QueryComparator implements Comparator<Query> {
		@Override
		public int compare(Query q1, Query q2) {
			int p = q2.period - q1.period;

			if(p != 0) {
				return p;
			}

			return q2.id - q1.id;
		}
	}
}

