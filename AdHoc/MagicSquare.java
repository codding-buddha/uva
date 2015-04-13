import java.io.*;
import java.util.*;

public class MagicSquare
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		for(int i =0; i < testCaseCount; i++)
		{
			String s = sc.nextLine();
			s = s.replaceAll("[.,?!\\s()\"\']", "");
			out.println(String.format("Case #%d:", i+1));
			double l = Math.sqrt(s.length());
			if(isPalindrome(s) && l % 1 == 0.0)
			{
				boolean isMagic = true;
				int x = (int) l;
				
				for(int j = x, k =0, indx = 0; indx < s.length(); indx++, k = (k+1)%x)
				{
					if(s.charAt(indx) != s.charAt(k*x + (indx/x)) && s.charAt(indx) != s.charAt(j*x - 1 - (indx/x)))
					{
						isMagic = false;
						break;
					}

					j--;
					if(j == 0)
						j = x;
				}

				if(!isMagic)
					out.println("No magic :(");
				else
					out.println((int)l);	
			}
			else
			{
				out.println("No magic :(");
			}
		}

		sc.close();
		out.close();
	}

	static boolean isPalindrome(String s)
	{
		boolean result = true;

		for(int i =0, j = s.length() - 1; i < j; i++, j--)
		{
			if(s.charAt(i) != s.charAt(j))
			{
				result = false;
				break;
			}
		}
		return result;
	}
}