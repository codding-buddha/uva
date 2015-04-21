import java.io.*;
import java.util.*;

public class RelationalOperator
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
			int testCaseCount = Integer.parseInt(sc.nextLine());
			String[] output = new String[testCaseCount];
			for(int i = 0; i < testCaseCount; i++)
			{
				String[] input = sc.nextLine().split(" ");
				long a = Long.parseLong(input[0]);
				long b = Long.parseLong(input[1]);
				output[i] = a > b ? ">" : (a == b ? "=" : "<");
			}

			for(int i =0; i< testCaseCount; i++)
				System.out.println(output[i]);
		}
	}
}