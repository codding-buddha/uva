import java.io.*;
import java.util.*;

public class Snail
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().trim().split(" ");
			int h = Integer.parseInt(input[0]);
			if(h <= 0)
				break;
			float u = Float.parseFloat(input[1]);
			int d = Integer.parseInt(input[2]);
			float f = u * (Float.parseFloat(input[3])/100);
			long day = 1;
			float climb = 0.0f;
			while(climb <= h && climb >= 0.0)
			{
				climb += u;
				if(climb > h)
					break;
				u = u - f;
				u = u < 0 ? 0 : u;
				climb -= d;
				if(climb < 0)
					break;
				day++;
			}
			output.add(String.format("%s on day %d", climb < 0.0 ? "failure" : "success", day));
		}

		for(int i =0;i < output.size(); i++)
			System.out.println(output.get(i));
	}
}

