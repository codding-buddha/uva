import java.io.*;
import java.util.*;

public class EventPlanning
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String[] line = sc.nextLine().trim().split(" ");
			int pCount = Integer.parseInt(line[0]);
			int budget = Integer.parseInt(line[1]);
			int hotels = Integer.parseInt(line[2]);
			int weeks = Integer.parseInt(line[3]);
			int min = -1;
			for(int i =0; i < hotels; i++)
			{
				int p = Integer.parseInt(sc.nextLine());
				String[] v = sc.nextLine().trim().split(" ");
				for(int k  =0; k < v.length; k++)
				{
					if(Integer.parseInt(v[k]) >= pCount) 
					{
						int cost = p * pCount;
						if(cost <= budget)
						{
							min = min == -1 ? cost : (min < cost ? min : cost) ;
						}
					}
				}
			}
			output.add(min == -1 ? "stay home" : String.valueOf(min));
		}

		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}
}