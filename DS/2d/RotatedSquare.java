import java.io.*;
import java.util.*;

public class RotatedSquare {
	public static void main(String[] args) throws IOException{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		FastScanner r = new FastScanner();
		while(true) {
			int N = r.nextInt();
			int n = r.nextInt();
			if(n+N == 0) {
				break;
			}
			char[][] str = new char[N][N];
			char[][] pattr = new char[n][n];

			for(int i =0; i < N; i++) {
				copy(str, i, r.nextLine());
			}

			for(int i =0; i < n; i++) {
				copy(pattr, i, r.nextLine());
			}

			char[][] pattrn1 = rotate90(pattr);
			char[][] pattrn2 = rotate90(pattrn1);
			char[][] pattrn3 = rotate90(pattrn2);

			out.write(matchCount(str, pattr) + " " + matchCount(str, pattrn1) +  " " + matchCount(str, pattrn2) +  " " + matchCount(str, pattrn3) +  "\n");
		}

		out.close();
	}

	static int matchCount(char[][] str, char[][] ptr) {
		int l1 = str.length,
			l2 = ptr.length,
			result = 0;

		for(int i =0; i <= l1 - l2; i++) {
			for(int j = 0; j <= l1-l2; j++) {
				if(isMatch(str, ptr, i, j))
					result++;
			}
		}

		return result;
	}

	static boolean isMatch(char[][] a, char[][] b, int i, int j) {
		for(int r=0; r<b.length; r++) {
			for(int c = 0; c<b.length; c++) {
				if(b[r][c] != a[i+r][c+j]) {
					return false;
				}
			}
		}

		return true;
	}

	static void copy(char[][] arr, int row, String s) {
		arr[row] = s.toCharArray();
	}

	static void print(char[][] a, BufferedWriter out) throws IOException {
		for(int i =0; i < a.length; i++) {
			for(int j = 0;  j < a.length; j++) {
				out.write(a[i][j] +  " ");
			}
			out.write("\n");
		}
	}

	static char[][] rotate90(char[][] a) {
		int n = a.length;
		char[][] result = new char[n][n];

		for(int i =0; i < a.length; i++) {
			if(result[i] == null) {
				result[i] = new char[n];
			}

			for(int j =0;  j < a.length; j++) {
				result[i][j] = a[n-j-1][i];
			}
		}

		return result;
	}
}

class FastScanner
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