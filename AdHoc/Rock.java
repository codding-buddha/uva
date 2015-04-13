import java.io.*;
import java.util.*;

public class Rock
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int testCaseCount = Integer.parseInt(sc.nextLine());
		for(int i =0; i < testCaseCount; i++)
		{
			String[] input = sc.nextLine().split(" ");
			int r = Integer.parseInt(input[0]);
			int c = Integer.parseInt(input[1]);
			int n = Integer.parseInt(input[2]);
			char[][] a = new char[r][c];
			char[][] b = new char[r][c];
			for(int j =0; j < r; j++)
			{
				a[j] = sc.nextLine().toCharArray();
			}

			for(int j =0; j < n; j++)
			{
				for(int k =0; k < r; k++)
				{
					for(int l =0; l < c; l++)
					{
						b[k][l] = getChar(a, k, l);
					}
				}

				char[][] temp = a;
				a = b;
				b = temp;
			}

			for(int k =0; k < r; k++)
			{
				out.println(new String(a[k]));
			}

			if(i != testCaseCount-1)
				out.println();
		}
		sc.close();
		out.close();
	}

	static char getChar(char[][] a, int i, int j)
	{
		char result = a[i][j];
		if((i-1) >= 0 && compare(a[i][j], a[i-1][j]) == -1)
			result = a[i-1][j];
		else if((i+1) < a.length && compare(a[i][j], a[i+1][j]) == -1)
			result = a[i+1][j];
		else if((j-1) >= 0 && compare(a[i][j], a[i][j-1]) == -1)
			result = a[i][j-1];
		else if((j+1) < a[0].length && compare(a[i][j], a[i][j+1]) == -1)
			result = a[i][j+1];

		return result;
	}

	static int compare(char a, char b)
	{
		if(a == b)
			return 0;
		else if((a == 'S' && b == 'R') || (a == 'P' && b == 'S') || (a == 'R' && b == 'P'))
			return -1;

		else
			return 1;
	}
}