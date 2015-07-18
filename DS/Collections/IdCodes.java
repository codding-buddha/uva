import java.util.*;
import java.io.*;

class IdCodes {
	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		String ip = null;
		while(true) {
			ip = in.nextLine();
			if(ip.equals("#")) {
				break;
			}

			String result = nextPermutation(ip);
			out.write((result == null ? "No Successor" : result) + "\n");
		}

		out.close();
	}

	static String nextPermutation(String s) {
		if(s == null || s.equals("")) {
			return s;
		}

		char[] a = s.toCharArray();
		int i = 0;
		for(i = a.length-1; i >0; i--) {
			if(a[i-1] < a[i]) {
				break;
			}
		}

		if(i == 0) {
			return null;
		}

		for(int j = a.length - 1; j >= i; j--) {
			if(a[i-1] < a[j]) {
				char temp = a[j];
				a[j] = a[i-1];
				a[i-1] = temp;
				break;
			}
		}

		for(int j = a.length - 1; j > i; j--, i++) {
			char temp = a[j];
			a[j] = a[i];
			a[i] = temp;
		}

		return String.valueOf(a);
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