import java.io.*;
import java.util.*;

public class Bowling
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.hasNextLine())
		{
			String input = sc.nextLine();
			if(input.equals("Game Over")) 
				break;
			char[] frames = input.replace(" ", "").toCharArray();
			int frameCount = 0;
			int score = 0;
			boolean frameBeg = true;
			for(int i =0, j= 0; i < frames.length && j < 10; i++)
			{
				if(frames[i] == 'X')
				{
					score += 10 + (frames[i+2] == '/'? 10 : getScore(frames[i+1]) + getScore(frames[i+2]));
					j++;
					frameBeg = true;
				} 
				else if(frames[i] == '/')
				{
					score += -1*getScore(frames[i-1]) + 10 + getScore(frames[i+1]);
					j++;
					frameBeg = true;
				}
				else
				{
						if(!frameBeg)
							j++;
						score += getScore(frames[i]);
						frameBeg = !frameBeg;
				}
			}

			out.println(String.valueOf(score));
		}

		out.close();
		sc.close();
	}

	static int getScore(char c)
	{	
		switch(c)
		{
			case 'X': return 10;
			case '/': return -1;
			default: return c-'0';
		}

	}
}