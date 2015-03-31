import java.io.*;
import java.util.*;

public class Jollo
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(true)
		{

			int[] input = parse(sc.nextLine().trim().split(" "));
			if(isEnd(input))
				break;
			boolean[] lookup = new boolean[53];
			for(int i =0; i < input.length; i++)
				lookup[input[i]] = true;

			int min = input[0] < input[1] ? (input[0] < input[2] ? input[0] : input[2]) : (input[1] < input[2] ?  input[1] : input[2]);
			int max = input[0] > input[1] ? (input[0] > input[2] ? input[0] : input[2]) : (input[1] > input[2] ?  input[1] : input[2]);
			input[1] = input[0]+input[1]+input[2] - max - min;
			input[0] = min;
			input[2] = max;
			max = input[3] > input[4] ? input[3] : input[4];
			min = input[3] + input[4] - max;
			input[3] = min;
			input[4] = max;
			if(input[1] > input[4])
				output.add("-1");
			else
			{
				if(input[3] > input[2])
				{
					for(int i =1;i < lookup.length; i++)
					{
						if(!lookup[i])
						{
							output.add(String.valueOf(i));
							break;
						}
					}
				}
				else
				{
					int result = input[3] > input[1] ? (input[1] + 1) : (input[2] + 1);
					while(result <= 52)
					{
						if(!lookup[result])
						{
							break;
						}
						result++;
					}
					if(result == 53)
						output.add("-1");
					else
					{
						if(input[3] > input[1] || (input[2] < input[4]))
							output.add(String.valueOf(result));
						else
							output.add("-1");
					}
				}
				
			}

		}

		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}

	static boolean isEnd(int[] input)
	{
		for(int i =0; i< input.length; i++)
		{
			if(input[i] > 0)
				return false;
		}
		return true;
	}
	static int[] parse(String[] input)
	{
		int[] ip = new int[input.length];
		for(int i =0;i < input.length; i++)
		{
			ip[i] = Integer.parseInt(input[i]);
		}
		return ip;
	}
}