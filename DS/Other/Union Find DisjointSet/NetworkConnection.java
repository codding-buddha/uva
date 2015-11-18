import java.util.*;
import java.io.*;

public class NetworkConnection
{
	public static void main(String[] args)
	{
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		in.nextLine();
		while(tc-- > 0) {
			int size = in.nextInt();
			UnionFind uf = new UnionFind(size + 1);
			for(int i = 1; i <= size; i++)
				uf.createSet(i);
			String input = "";
			int s = 0;
			int e = 0;
			try{
				while(!(input = in.nextLine().trim()).equals("")) {
					String[] tokens = input.split(" ");
					if(tokens[0].equals("q")) {
						int px = uf.find(Integer.parseInt(tokens[1]));
						int py = uf.find(Integer.parseInt(tokens[2]));
						if(px == py) {
							s++;
						} else {
							e++;
						}
					} else {
						uf.merge(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
					}
				}	
			}catch(Exception excep) {

			}finally {
				out.println(s + "," + e);
				if(tc != 0) {
					out.println();
				}
			}
		}

		out.close();	
	}

	static class UnionFind 
	{
		int[] _parent;
		int[] _rank;

		public UnionFind(int size) 
		{
			_parent = new int[size];
			_rank = new int[size];
		}

		public void createSet(int x) 
		{
			_parent[x] = x;
			_rank[x] = 0;
		}

		public int find(int x) {
			if(_parent[x] != x) {
				_parent[x] = find(_parent[x]);
			}
			return _parent[x];
		}

		public void merge(int x, int y) 
		{
			int px = find(x);
			int py = find(y);

			if(px != py) {

				if(_rank[px] >= _rank[py]) {
					_parent[py] = px;
				} else {
					_parent[px] = py;
				}

				if(_rank[px] == _rank[py]) {
					_rank[px] += 1;
				}
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