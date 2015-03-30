import java.io.*;
import java.util.*;

public class RFP
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		int index = 1;
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().split(" ");
			int requirement = Integer.parseInt(input[0]);
			int proposals = Integer.parseInt(input[1]);
			if(requirement == 0)
				break;
			int i =0;
			int maxr = Integer.MIN_VALUE;
			float minp = Float.MAX_VALUE;
			String pname = "";
			while(i++ < requirement)
				sc.nextLine();

			for(i =0; i < proposals; i++)
			{
				String nm = sc.nextLine();
				input = sc.nextLine().split(" ");
				float p = Float.parseFloat(input[0]);
				int r = Integer.parseInt(input[1]);
				if(maxr < r)
				{
					maxr = r;
					minp = p;
					pname = nm;
				} 
				else if(maxr == r)
				{
					if(minp > p)
					{
						minp = p;
						pname = nm;
					}
				}
				int k =0;
				while(k++ < r)
					sc.nextLine();
			}
			output.add("RFP #"+index);
			output.add(pname);
			output.add("");
			index++;
		}

		for(int i =0; i < output.size() - 1; i++)
			System.out.println(output.get(i));
	}
}