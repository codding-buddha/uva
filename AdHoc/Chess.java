import java.io.*;
import java.util.*;

public class Chess
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		List<String> output = new ArrayList<String>();
		for(int i =0; i < testCaseCount; i++)
		{
			String[] input = sc.nextLine().trim().split(" ");
			char chessPiece = input[0].charAt(0);
			int n = Integer.parseInt(input[1]);
			int m = Integer.parseInt(input[2]);
			switch(chessPiece)
			{
				case 'r': output.add(String.valueOf(n < m ? n : m));
				break;
				case 'K': output.add(String.valueOf(((n+1)/2) * ((m+1)/2)));
				break;
				case 'k': output.add(String.valueOf((n*m + 1)/2));
				break;
				default:  output.add(String.valueOf(n < m ? n : m));
				break;
			}
		}

		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}
}