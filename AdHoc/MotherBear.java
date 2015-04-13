import java.io.*;
import java.util.*;

public class MotherBear
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.hasNextLine())
		{
			String input = sc.nextLine();
			if(input.equals("DONE"))
				break;
			HashSet<Character> ignoreSet = new HashSet<Character>();
			ignoreSet.add('.');ignoreSet.add('!');ignoreSet.add(',');ignoreSet.add('?');ignoreSet.add(' ');
			int i , j;
			for(i =0, j = input.length()-1; i < j; )
			{
				char c1 = Character.toLowerCase(input.charAt(i));
				char c2 = Character.toLowerCase(input.charAt(j));
				if(ignoreSet.contains(c1) || ignoreSet.contains(c2))
				{
					if(ignoreSet.contains(c1))
					i++;
					if(ignoreSet.contains(c2))
					j--;
					continue;
				}
				if(c1 != c2)
					break;
				i++;
				j--;
			}

			if(i<j) 
			{
				out.println("Uh oh..");
			}
			else
			{
				out.println("You won't be eaten!");
			}
		}

		sc.close();
		out.close();
	}
}	