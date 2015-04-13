import java.io.*;
import java.util.*;

public class Bingo
{
	public static void main(String[] args)
	{

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int testCaseCount = Integer.parseInt(sc.nextLine().trim());
		for(int i =0; i < testCaseCount; i++)
		{
			boolean[][] mark = new boolean[5][5];
			mark[2][2] = true;
			int card[][] = new int[5][5];
			for(int k =0; k < 5; k++)
			{
				for(int l =0; l < 5; l++)
				{
					if(k == 2 && l == 2)
						continue;
					card[k][l] = sc.nextInt();
				}
			}

			boolean bingo = false;
			for(int r = 0; r < 75; r++)
			{
				int num = sc.nextInt();
				if(bingo)
					continue;

				int col = (num-1) / 15;

				for(int l =0; l < 5; l++)
				{
					if(card[l][col] == num)
					{
						mark[l][col] = true;
						bingo = checkForBingo(mark, l, col);
						if(bingo)
						{
							out.println(String.format("BINGO after %d numbers announced", r+1));
						}
						break;
					}
				}
			}
		}
		out.close();
		sc.close();
	}

	static boolean checkForBingo(boolean[][] mark, int r, int c)
	{
		boolean isBingo = true;
		for(int i =0; i < 5; i++)
		{
			if(!mark[r][i]){
				isBingo = false;
				break;
			}
		}

		if(isBingo){
			return isBingo;
		}
		isBingo = true;

		for(int i =0; i < 5; i++)
		{
			if(!mark[i][c]){
				isBingo = false;
				break;
			}
		}

		if(isBingo){
			return isBingo;
		}
		isBingo = true;
		if(r == c)
		{
			for(int i = 0; i < 5; i++)
			{
				if(!mark[i][i])
				{
					isBingo = false;
					break;
				}
			}

			if(isBingo){
				return true;
			}
		}

		isBingo = true;

		if((r+c) == 4)
		{
			for(int i =4, j= 0; i >=0 && j < 5; i--, j++)
			{
				if(!mark[i][j])
				{
					isBingo = false;
					break;
				}
			}
		}
		else
		{
			isBingo = false;
		}

		return isBingo;
	}
}