import java.io.*;
import java.util.*;

public class FEN
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		short[][] position = null; 
		while(sc.hasNextLine())
		{
			position = new short[8][8];
			String[] board = sc.nextLine().split("/");
			for(int row = 0; row < board.length; row++)
			{
				for(int i = 0, len = board[row].length(), k = 0; i < len; i++)
				{
					if(Character.isDigit(board[row].charAt(i)))
					{
						k += board[row].charAt(i) - '0';
						continue;
					}
					position[row][k] = 1;
					k++;
				}
			}

			for(int row = 0; row < 8; row++)
			{
				for(int i = 0, len = board[row].length(), k = 0; i < len; i++)
				{
					if(Character.isDigit(board[row].charAt(i)))
					{
						k += board[row].charAt(i) - '0';
						continue;
					}
					if(position[row][k] == 1)
						setAttackingPosition(board[row].charAt(i), position, row, k);
					k++;
				}
			}

			int result = 0;
			for(int i =0; i < 8; i++)
			{
				for(int j =0; j < 8; j++)
				{
					if(position[i][j] == 0)
						result++;
				}
			}

			System.out.println(String.valueOf(result));
		}
	}

	static void setAttackingPosition(char piece, short[][] position, int row, int i)
	{
		char p = Character.toLowerCase(piece);
		//1 denotes cell occupied by piece
		position[row][i] = 1;
		switch(p)
		{
			case 'p': if(piece == 'P')
						{
							if((row - 1) >= 0 && (i-1) >= 0 && position[row-1][i-1] != 1)
								position[row-1][i-1] = 2;
							if((row - 1) >= 0 && (i+1) < 8 && position[row-1][i+1] != 1)
								position[row-1][i+1] = 2;
						}
						else
						{
							if((row + 1) < 8 && (i+1) < 8 && position[row+1][i+1] != 1)
								position[row+1][i+1] = 2;
							if((row + 1) < 8 && (i-1) >= 0 && position[row+1][i-1] != 1)
								position[row+1][i-1] = 2;
						}

			break;
			case 'b': setDiagonal(position, row, i);
			break;
			case 'r': setRowAndColumn(position, row, i);
			break;
			case 'k' : setAdjacent(position, row, i);
			break;
			case 'q': setDiagonal(position, row, i);
			setRowAndColumn(position, row, i);
			break;
			default:
			if((row + 1) < 8){
				if((i + 2) < 8 && position[row+1][i+2] != 1)
					position[row+1][i+2] = 2;
				if((i-2) >= 0 && position[row+1][i-2] != 1)
					position[row+1][i-2] = 2;
			}

			if((row + 2) < 8){
				if((i+1) < 8 && position[row+2][i+1] != 1)
					position[row+2][i+1] = 2;
				if((i-1) >= 0 && position[row+2][i-1] != 1)
					position[row+2][i-1] = 2;
			}

			if((row - 1) >= 0){
				if((i + 2) < 8 && position[row-1][i+2] != 1)
					position[row-1][i+2] = 2;
				if((i-2) >= 0 && position[row-1][i-2] != 1)
					position[row-1][i-2] = 2;
			}
			
			if((row - 2) >= 0){
				if((i+1) < 8 && position[row-2][i+1] != 1)
					position[row-2][i+1] = 2;
				if((i-1) >= 0 && position[row-2][i-1] != 1)
					position[row-2][i-1] = 2;
			}

			break;
		}

	}

	static void setAdjacent(short[][] position, int x, int y)
	{
		if((x+1) < 8 && (y + 1)< 8 && position[x+1][y+1] != 1)
			position[x+1][y+1] = 2;
		if((x-1) >= 0 && (y + 1)< 8 && position[x-1][y+1] != 1)
			position[x-1][y+1] = 2;
		if((x-1) >= 0 && (y - 1)>= 0 && position[x-1][y-1] != 1)
			position[x-1][y-1] = 2;
		if((x+1) < 8 && (y - 1)>= 0 && position[x+1][y-1] != 1)
			position[x+1][y-1] = 2;
		if((y + 1)< 8 && position[x][y+1] != 1)
			position[x][y+1] = 2;
		if((y-1) >= 0 && position[x][y-1] != 1)
			position[x][y-1] = 2;
		if((x + 1)< 8 && position[x+1][y] != 1)
			position[x+1][y] = 2;
		if((x-1) >= 0 && position[x-1][y] != 1)
			position[x-1][y] = 2;
	}

	static void setRowAndColumn(short[][] pos, int x, int y)
	{
		int tempx = x + 1;
		int tempy = y + 1;
		while(tempx < 8)
		{
			if(pos[tempx][y] == 1)
				break;
			pos[tempx++][y] = 2;
		}
		while(tempy < 8)
		{
			if(pos[x][tempy] == 1)
				break;
			pos[x][tempy++] = 2;
		}

		tempx = x -1;
		tempy = y -1;
		while(tempx >= 0)
		{
			if(pos[tempx][y] == 1)
				break;
			pos[tempx--][y] = 2;
		}

		while(tempy >= 0)
		{
			if(pos[x][tempy] == 1)
				break;
			pos[x][tempy--] = 2;
		}
	}

	static void setDiagonal(short[][] position, int x, int y)
	{
		int tempx = x + 1;
		int tempy = y + 1;
		while(tempx <8 && tempy < 8)
		{
			if(position[tempx][tempy] == 1)
				break;
			position[tempx++][tempy++] = 2;
		}	
		tempx = x - 1;
		tempy = y + 1;
		while(tempx >= 0 && tempy < 8)
		{
			if(position[tempx][tempy] == 1)
				break;
			position[tempx--][tempy++] = 2;
		}

		tempx = x + 1;
		tempy = y - 1;
		while(tempx < 8 && tempy >= 0)
		{
			if(position[tempx][tempy] == 1)
				break;
			position[tempx++][tempy--] = 2;
		}

		tempx = x - 1;
		tempy = y - 1;
		while(tempx >= 0  && tempy >= 0)
		{
			if(position[tempx][tempy] == 1)
				break;
			position[tempx--][tempy--] = 2;
		}
	}

	/*
	static void print(short[][] pos)
	{
		for(int i =0; i < 8; i++)
		{
			for(int j =0; j < 8; j++)
				System.out.print(pos[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	*/
}