import java.util.*;
import java.io.*;

class BrokenKeyboard {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);

		String ip = null;
		while((ip = in.nextLine()) != null) {
			LinkedList<Character> list = new LinkedList<Character>();
			int insertPosition = 0;
			for(int i =0, len = ip.length(); i < len; i++) {
				char c = ip.charAt(i);
				if(c == '[') {
					insertPosition = 0;
				} else if(c == ']') {
					insertPosition = list.size();
				} else {
					list.add(insertPosition, c);
					insertPosition++;
				}
			}

			Character[] result = list.toArray(new Character[0]);
			StringBuilder builder = new StringBuilder(result.length);
			for(int i = 0; i < result.length; i++) {
				builder.append(result[i].toString());
			}
			out.println(builder.toString());
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