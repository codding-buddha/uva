import java.util.*;
import java.io.*;

class Container {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String str = null;
		int tc = 1;
		while(!(str = in.nextLine()).equals("end")) {
			out.println("Case " + tc++ + ": " + stackCount(str));
		}
		out.flush();
	}

	static int stackCount(String str) {
		List<Stack<Character>> list = new ArrayList<Stack<Character>>();
		Stack<Character> s0 = new Stack<Character>();
		s0.push(str.charAt(0));
		list.add(s0);
		for(int i = 1; i < str.length(); i++) {
			char c = str.charAt(i);
			boolean found = false;
			for(int j = 0, len = list.size();j < len; j++) {
				Stack<Character> s = list.get(j);
				if(c <= s.peek()) {
					s.push(c);
					found = true;
					break;
				}
			}

			if(!found) {
				Stack<Character> s = new Stack<Character>();
				s.push(c);
				list.add(s);
			}
		}

		return list.size();
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