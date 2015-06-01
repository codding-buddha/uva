import java.io.*;
import java.util.*;

public class Jollyjumper
{
	public static void main(String[] args) {
		PrintWriter out = new PrintWriter(System.out);
		Scanner in = new Scanner(System.in);
		String input = null;
		while(in.hasNextLine()) {
			input = in.nextLine();
			String[] nStr = input.split(" ");
			int count = Integer.parseInt(nStr[0]);
			if(count == 1) {
				out.println("Jolly");
				continue;
			}

			boolean[] map = new boolean[count];
			map[0] = true;
			int prev = Integer.parseInt(nStr[1]);
			boolean invalid = false;
			for (int i = 2; i < nStr.length ; i++) {
				int curr = Integer.parseInt(nStr[i]);
				int abs = Math.abs(curr - prev);
				prev = curr;
				if(abs > (count - 1) || abs == 0) {
					out.println("Not jolly");
					invalid = true;
					break;
				}
				map[abs] = true;
			}

			if(!invalid) {
				boolean isJolly = true;
				for(int i =0; i < map.length; i++) {
					isJolly = isJolly && map[i];
					if(!isJolly){
						break;
					}
				}
				if(isJolly) {
					out.println("Jolly");
				} else {
					out.println("Not jolly");
				}
			}

		}
		out.close();
		in.close();
	}
}
