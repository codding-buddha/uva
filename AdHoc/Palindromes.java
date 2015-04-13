import java.io.*;
import java.util.*;

public class Palindromes
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		HashMap<Character,Character> lookup = new HashMap<Character, Character>();
		lookup.put('A', 'A');
		lookup.put('M', 'M');
		lookup.put('I', 'I');
		lookup.put('Y', 'Y');
		lookup.put('Z', '5');
		lookup.put('1', '1');
		lookup.put('2', 'S');
		lookup.put('S', '2');
		lookup.put('3', 'E');
		lookup.put('E', '3');		
		lookup.put('O', 'O');
		lookup.put('5', 'Z');
		lookup.put('8', '8');
		lookup.put('V', 'V');
		lookup.put('U', 'U');
		lookup.put('T', 'T');
		lookup.put('X', 'X');
		lookup.put('W', 'W');
		lookup.put('H', 'H');
		lookup.put('J', 'L');
		lookup.put('L', 'J');
		while(sc.hasNextLine())
		{
			String input = sc.nextLine().trim();
			boolean isPalindrome = true;
			for(int i = 0, j = input.length() - 1; i < j; i++, j--)
			{
				if(input.charAt(i) != input.charAt(j))
				{
					isPalindrome = false;
					break;
				}
			}

			//String ip = input.replace('0', 'O');
			String ip = input;

			boolean isMirorred = true;
			for(int i =0, len = ip.length(), j = len-1; i <= j; i++, j--)
			{
				if(!lookup.containsKey(ip.charAt(i)) || (ip.charAt(j) != lookup.get(ip.charAt(i))))
				{
					isMirorred = false;
					break;
				}
			}

			if(isMirorred && isPalindrome)
			{
				out.println(String.format("%s -- is a mirrored palindrome.", input));
			} 
			else if(isPalindrome)
			{
				out.println(String.format("%s -- is a regular palindrome.", input));
			}
			else if(isMirorred)
			{
				out.println(String.format("%s -- is a mirrored string.", input));	
			}
			else
			{
				out.println(String.format("%s -- is not a palindrome.", input));
			}
			out.println();
		}

		sc.close();
		out.close();
	}
}