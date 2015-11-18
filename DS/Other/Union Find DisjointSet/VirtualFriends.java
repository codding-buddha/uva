import java.io.*;
import java.util.*;

class VirtualFriends
{
	static HashMap<String, String> parent;
	static HashMap<String, Integer> size;
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int fc = in.nextInt();
			parent = new HashMap<String, String>();
			size = new HashMap<String, Integer>();
			while(fc-- > 0) {
				String f1 = in.next();
				String f2 = in.next();
				out.println(setFriend(f1, f2));
			}
		}
		out.close();
	}

	static int setFriend(String f1, String f2) {
		String p1 = find(f1);
		String p2 = find(f2);
		if(p1.equals(p2))
			return size.get(p1);

		int size1 = size.get(p1);
		int size2 = size.get(p2);
		if(size1 > size2) {
			parent.put(p2, p1);
			size.put(p1, size1 + size2);
		} else {
			parent.put(p1, p2);
			size.put(p2, size1 + size2);
		}
		return size1 + size2;
	}

	static String find(String f) {
		if(!parent.containsKey(f)){
			parent.put(f, f);
			size.put(f, 1);
		}

		String p = parent.get(f);
		while(!parent.get(p).equals(p))
			p = parent.get(p);

		parent.put(f, p);
		return parent.get(f);
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