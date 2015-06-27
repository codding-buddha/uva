import java.util.*;
import java.io.*;

class GridSuccessor {
	public static void main(String[] args) throws IOException {
		FastScanner in = new FastScanner();
		BufferedWriter out  = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCaseCount = in.nextInt();
		for(int i =0; i < testCaseCount; i++) {
			in.nextLine();
			int[][] g = new int[3][3];
			for(int k = 0; k < 3; k++) {
				char[] ip = in.nextLine().toCharArray();
				g[k][0] = ip[0] - '0';
				g[k][1] = ip[1] - '0';
				g[k][2] = ip[2] - '0';
			}
			int indx = 0;
			int[][] transformed = new int[3][3];
			if(equal(transformed, g)) {
				out.write("-1\n");
				continue;
			}
			int[][] temp;

			while(true) {
				transform(g, transformed);
				if(equal(transformed, g)) {
					break;
				}
				temp = g;
				g = transformed;
				transformed = temp;
				indx++;
			}
			out.write(indx-1 + "\n");
		}

		out.close();
	}

	static void print(int[][] a) {
		for(int i =0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(a[i][j] +  " ");
			}
			System.out.println();
		}
	}

	static void transform(int[][] g, int[][] h) {
		for(int i =0; i < 3; i++) {
			for(int j = 0; j <3; j++) {
				h[i][j] = 0;
				if(i-1 >= 0) {
					h[i][j] += g[i-1][j];
				}

				if((j+1) < 3) {
					h[i][j] += g[i][j+1];
				}

				if(j-1 >= 0) {
					h[i][j] += g[i][j-1];
				}


				if((i+1) < 3) {
					h[i][j] += g[i+1][j];
				}

				h[i][j] %= 2;
			}
		}
	}

	static boolean equal(int[][] g1, int[][] g2) {
		boolean equal = true;
		for(int i =0; i < 3; i++) {
			equal = equal && (g1[i][0] == g2[i][0]);
			if(!equal)
				break;
			equal = equal && (g1[i][1] == g2[i][1]);
			if(!equal)
				break;
			equal = equal && (g1[i][2] == g2[i][2]);
			if(!equal)
				break;
		}

		return equal;
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