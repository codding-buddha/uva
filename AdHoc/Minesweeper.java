import java.util.*;
import java.io.*;

class Minesweeper
{

	public static void main(String[] args)
	{
		PrintWriter out = new PrintWriter(System.out);
		Scanner sc = new Scanner(System.in);
		int index = 1;
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			if((n+m) == 0)
				break;
			char[][] result = new char[n][m];
			if(index != 1)
				out.println();
			for(int i =0; i < n; i++)
			{
				String line = sc.nextLine();
				for(int k=0; k < m; k++)
				{
					char c = line.charAt(k);
					if(c == '*')
					{
						if((i-1) >= 0)
						{
							result[i-1][k] = result[i-1][k] == '\0' ? '1' : (result[i-1][k] == '*' ? '*' : (++result[i-1][k]));

							if((k-1) >= 0)
							{
								result[i-1][k-1] = result[i-1][k-1] == '\0'? '1': (result[i-1][k-1] == '*' ? '*' : ++result[i-1][k-1]);
							}

							if((k+1) < m)
							{
								result[i-1][k+1] = result[i-1][k+1] == '\0'? '1' : (result[i-1][k+1] == '*' ? '*' : ++result[i-1][k+1]);
							}
						}

						if((i+1) < n)
						{
							result[i+1][k] = result[i+1][k] == '\0' ? '1' : (result[i+1][k] == '*' ? '*' : (++result[i+1][k]));

							if(k-1 >= 0)
							{
								result[i+1][k-1] = result[i+1][k-1] == '\0' ? '1' : (result[i+1][k-1] == '*' ? '*' : (++result[i+1][k-1]));
							}
							if((k+1) < m)
							{
								result[i+1][k+1] = result[i+1][k+1] == '\0' ? '1' : (result[i+1][k+1] == '*' ? '*': (++result[i+1][k+1]));
							}
						}

						if(k-1 >= 0)
						{
							result[i][k-1] = result[i][k-1] == '\0' ? '1' : (result[i][k-1] == '*' ? '*' : (++result[i][k-1]));
						}

						if(k+1 < m)
							result[i][k+1] = result[i][k+1] == '\0' ? '1' : (result[i][k+1] == '*' ? '*' : ++result[i][k+1]);

						result[i][k] = c;
					}
				}
			}

			out.println(String.format("Field #%d:", index++));
			for(int i =0; i < n; i++)
			{
				for(int j =0; j < m; j++)
				{

					out.print(result[i][j] == '\0' ? '0' : result[i][j]);
				}
				out.println();
			}
		}
		sc.close();
		out.close();
	}
	
}