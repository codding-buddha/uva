import java.io.*;
import java.util.*;

public class HorrorDash
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		List<String> output = new ArrayList<String>();
		for(int i =0; i < testCaseCount; i++)
		{
			String[] speed = sc.nextLine().trim().split(" ");
			int maxSpeed = 0;
			for(int k =0; k < speed.length; k++)
			{
				if(Integer.parseInt(speed[k]) > maxSpeed)
					maxSpeed = Integer.parseInt(speed[k]);
			}
			output.add(String.format("Case %d: %d",i+1,maxSpeed));
		}

		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}
}