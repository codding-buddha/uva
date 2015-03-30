import java.io.*;
import java.util.*;

public class TrainTrack
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		List<String> output = new ArrayList<String>();
		for(int i =0; i < testCaseCount; i++)
		{
			String[] input = sc.nextLine().split(" ");
			if(isLoopPossible(input))
			{
				output.add("LOOP");
			}
			else
			{
				output.add("NO LOOP");
			}
		}

		for(int i  =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}

	static boolean isLoopPossible(String[] input)
	{
		if(input.length == 1)
			return false;
		int mm = 0;
		int fm = 0;
		int mf = 0;
		int ff = 0;
		for(int i  =0; i < input.length; i++)
		{
			switch(input[i])
			{
				case "MF": mf++;
				break;
				case "FM" : fm++;
				break;
				case "FF" : ff++;
				break;
				default: mm++;
				break;
			}
		}
		if(mm != ff)
			return false;
		if(mf > 0 && fm > 0 && mm == 0)
			return false;
		
		return true;
	}
}