import java.util.*;
import java.io.*;

public class Newspaper
{
	public static void main(String[] args) 
	{
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			int testCaseCount = Integer.parseInt(in.readLine());
			for(int i =0; i < testCaseCount; i++) {
				int tableSize = Integer.parseInt(in.readLine());
				int[] lookup = new int[256];
				long value = 0L;
				for(int j =0; j < tableSize; j++) {
					String[] input = in.readLine().split(" ");
					int c = (int)input[0].charAt(0);
					int v = Integer.parseInt(input[1]);
					lookup[c] = v;
				}

				int lineCount = Integer.parseInt(in.readLine());
				for(int k =0; k < lineCount; k++) {
					String str = in.readLine();
					for(int l =0, len = str.length(); l < len; l++) {
						value += lookup[str.charAt(l)];
					}
				}

				double valInDollar = value/100.0;
				out.println(String.format("%.2f%s", valInDollar, "$"));
			}
			in.close();
		}catch(IOException e) {
		}
		finally{
			out.close();
		}
	}
}