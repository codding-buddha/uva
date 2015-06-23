import java.io.*;
import java.util.*; 

class SpiralTrap 
{
	public static void main(String[] args) throws IOException {
		FastScanner sc = new FastScanner();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		while(true) {
			int sz = sc.nextInt();
			long p = sc.nextLong();
			if(sz + p  == 0)
				break;

			int inc = (int)Math.floor(Math.sqrt(p));
			inc = (inc%2) == 0 ? inc - 1 : inc;
			long c = inc*inc;
			long x = sz - (sz/2 - inc/2), y = sz/2 + inc/2 + 1;
			if(p != c) {
				//first line
				if(c+(inc+1) >= p) {
					x++;
					y = y - (p - c - 1);
				} 
				//second line
				else if((c + (2*(inc + 1))) >= p) {
					y = y - inc;
					x = x - (p - (c + inc + 1) - 1);
				} 
				///third line, ignore the corner element
				else if((c + (3*(inc + 1)) - 1) >= p) {
					x = x - inc;
					y = y - ((c + (inc+1)*3) - 1 - p);
				} 
				//fourth line
				else {
					y++;
					x = x - ((c + (inc + 1)*4) - 1 - p);
				}
			}
			out.write("Line = " + x + ", column = "+ y + ".\n");
		}

		out.close();
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

