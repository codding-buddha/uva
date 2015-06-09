import java.util.*;
import java.io.*;

public class JustFinishItUp {
	public static void main(String[] args) throws IOException, NumberFormatException {
		Reader.init(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int testCaseCount = Reader.nextInt();
		for(int i =0; i < testCaseCount; i++) {
			int stationCount = Reader.nextInt();
			int[] p = new int[stationCount];
			int[] q = new int[stationCount];
			long sp = 0;
			long sq = 0;
			for(int k =0; k < stationCount; k++) {
				p[k] = Reader.nextInt();
				sp += p[k];
			}
			for(int k =0; k < stationCount; k++) {
				q[k] = Reader.nextInt();
				sq += q[k];
			}
			if(sp < sq) {
				out.write(String.format("Case %d: Not possible\n" , i+1));
			} else {
				out.write(String.format("Case %d: Possible from station %d\n", i+1, getStartingStation(p, q)));
			}
		}

		out.close();
	}

	static int getStartingStation(int[] p, int[] q) {
		int sS = 0;
		int i = 0;
		int sum = 0;
		int len = p.length;
		i = 0;
		int covered = 1;
		while(covered < len) {
			sum += (p[i] - q[i]);
			i = (i+1) % len;
			if(sum < 0) {
				sS = i;
				sum = 0;
				covered = 0;
			} else {
				covered++;
			}
		}

		return sS + 1;
	}
}

	class Reader {
	    static BufferedReader reader;
	    static StringTokenizer tokenizer;

	    static void init(InputStream input) {
	        reader = new BufferedReader(
	                     new InputStreamReader(input) );
	        tokenizer = new StringTokenizer("");
	    }

	    static String next() throws IOException {
	        while ( ! tokenizer.hasMoreTokens() ) {
	            tokenizer = new StringTokenizer(
	                   reader.readLine() );
	        }
	        return tokenizer.nextToken();
	    }

	    static int nextInt() throws IOException {
	        return Integer.parseInt( next() );
	    }
		
	    static double nextDouble() throws IOException {
	        return Double.parseDouble( next() );
	    }

	    static String nextLine() throws IOException {
	    	return reader.readLine();
	    }
	}


