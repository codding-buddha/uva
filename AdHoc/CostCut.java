import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		for(int i =0;i < testCaseCount; i++)
		{
			String[] inputStr = sc.nextLine().trim().split(" ");
			int[] salaries = new int[inputStr.length];
			for(int j =0, len = inputStr.length; j < len; j++)
			{
				salaries[j] = Integer.parseInt(inputStr[j]);
			}
			int max = salaries[0] > salaries[1] ? (salaries[0] > salaries[2] ? salaries[0] : salaries[2])
												: (salaries[1] > salaries[2] ? salaries[1] : salaries[2]);
			int min = salaries[0] < salaries[1] ? (salaries[0] < salaries[2] ? salaries[0] : salaries[2])
												: (salaries[1] < salaries[2] ? salaries[1] : salaries[2]);
			System.out.println(String.format("Case %d: %d", i+1, (salaries[0] + salaries[1] + salaries[2] - max - min)));
		}
	}
}