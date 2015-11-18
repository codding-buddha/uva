import java.util.*;
import java.io.*;
class War
{
	static int[] parent = null;
	static int[] rank = null;
	static int pCount  = 0;

	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		pCount = in.nextInt();
		parent = new int[pCount*2];
		rank = new int[pCount*2];
		for(int i = 0; i < parent.length; i++) {
			createSet(i);
		}

		while(true) {
			int action = in.nextInt();
			int c1 = in.nextInt();
			int c2 = in.nextInt();
			if(action+c1+c2 == 0) {
				break;
			}

			switch(action) {
				case 1:
				if(!setFriends(c1, c2))
					out.println("-1");
				break;
				case 2:
				if(!setEnemies(c1, c2))
					out.println("-1");
				break;
				case 3:
				out.println(areFriends(c1, c2) ? "1" : "0");
				break;
				case 4:
				out.println(areEnemies(c1, c2) ? "1": "0");
				break;
			}
		}

		out.close();
	}

	static boolean setEnemies(int x, int y) {
		if(areFriends(x, y))
			return false;
		if(areEnemies(x, y))
			return true;
		int x_ = x + pCount;
		int y_ = y + pCount;
		merge(x, y_);
		merge(y, x_);
		return true;
	}

	static boolean setFriends(int x, int y) {
		if(areFriends(x, y))
			return true;
		if(areEnemies(x, y)) {
			return false;
		}else {
			int x_ = x + pCount;
			int y_ = y + pCount;
			merge(x, y);
			merge(x_, y_);
			return true;
		}
	}

	static boolean areFriends(int x, int y) {
		return find(x) == find(y);
	}

	static boolean areEnemies(int x, int y) {
		int px = find(x);
		int py = find(y);
		int px_ = find(x + pCount);
		int py_ = find(y + pCount);
		return px == py_ || py == px_;
	}

	static void createSet(int x) {
		parent[x] = x;
		rank[x] = 0;
	}

	static int find(int x) {
		if(parent[x] != x)
			parent[x] = find(parent[x]);

		return parent[x];
	}

	static void merge(int x, int y) {
		int px = find(x);
		int py = find(y);

		if(px != py) {
			if(rank[py] > rank[px]) {
				parent[px] = py;
			} else {
				parent[py] = px;
			}

			if(rank[px] == rank[py]) {
				rank[px] += 1;
			}
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