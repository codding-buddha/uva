import java.util.*;
import java.io.*;

class AnagramByStack {
	static PrintWriter out;
	static String str1, str2;
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		out = new PrintWriter(System.out);
		while((str1 = in.nextLine()) != null) {
			str2 = in.nextLine();
			out.println("[");
			printSequences(str1, str2);
			out.println("]");
		}
		out.flush();
	}

	static void printSequences(String a, String b) {
		if(a.length() != b.length()) {
			return;
		}
		print(a, b, 0, "", "", "");
	}

	static void print(String a, String b, int indx, String stack, String s, String operations) {
		if(s.length() == b.length()) {
			if(s.equals(b)) {
				out.println(operations);
			}
			return;
		}

		if(indx < a.length())
			print(a, b, indx+1, stack+a.charAt(indx), s, operations + (operations.length() == 0 ? "i" : " i"));

		if(stack.length() > 0) {
			s += stack.charAt(stack.length() - 1);

			if(b.charAt(s.length()-1) == s.charAt(s.length()-1)) {
				print(a, b, indx, stack.substring(0, stack.length() - 1), s, operations + " o");
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