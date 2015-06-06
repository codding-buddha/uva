import java.io.*;
import java.util.*;

public class ArmyBuddies 
{
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
//		PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		StringBuilder stringBuilder = new StringBuilder(25000);

		while((line = br.readLine()) != null) {
			String[] input = line.split(" ");
			int sCount = Integer.parseInt(input[0]);
			int lReport = Integer.parseInt(input[1]);
			if(sCount == 0 && lReport == 0) {
				break;
			}

			int[] ls = new int[sCount + 1];
			int[] rs = new int[sCount + 1];
			for(int i =1; i < ls.length; i++) {
				ls[i] = i-1;
				rs[i] = i + 1;
			}

			rs[sCount] = 0;

			for(int i =0; i < lReport; i++) {
				StringTokenizer range = new StringTokenizer(br.readLine());
				int lb = Integer.parseInt(range.nextToken());
				int ub = Integer.parseInt(range.nextToken());

				if(ls[lb] < 1) {
					//out.print("* ");
					stringBuilder.append("*");
				} else {
					//out.print(ls[lb] + " ");
					stringBuilder.append(ls[lb]);
					if(ls[lb] > 0){
						rs[ls[lb]] = rs[ub];
					}
				}

				stringBuilder.append(" ");

				if(rs[ub] < 1) {
					//out.println("*");
					stringBuilder.append("*");
				} else {
					//out.println(rs[ub]);
					stringBuilder.append(rs[ub]);
					if(rs[ub] > 0) {
						ls[rs[ub]] = ls[lb];
					}
				}
				stringBuilder.append("\n");
			}

			if(stringBuilder.length() > 10000) {
				out.write(stringBuilder.toString());
				stringBuilder = new StringBuilder(25000);
			}
			stringBuilder.append("-\n");
			//out.println("-");
		}

		out.write(stringBuilder.toString());
		out.flush();
		out.close();
		br.close();
	}
}