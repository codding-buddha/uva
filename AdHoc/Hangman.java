import java.io.*;
import java.util.*;

public class Hangman
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.hasNextInt())
		{
			int round = sc.nextInt();
			if(round == -1)
				break;

			HashSet<Character> wordLookup = new HashSet<Character>();
			
			char[] word = sc.next().toCharArray();
			
			for(int i =0, len = word.length; i < len; i++) 
				wordLookup.add(word[i]);

			char[] guesses = sc.next().toCharArray();
			ArrayList<Character> chars = new ArrayList<Character>();
			HashSet<Character> guessLookup = new HashSet<Character>();

			for(Character c :  guesses)
			{
				if(!guessLookup.contains(c))
					chars.add(c);
				guessLookup.add(c);
			}

			int movesLeft = 7;
			for(Character c : chars)
			{
				if(wordLookup.contains(c)) 
				{
					wordLookup.remove(c);
				} 
				else
				{
					movesLeft--;
				}

				if(wordLookup.size() == 0 || movesLeft == 0)
					break;
			}

			out.println("Round " + round);
			if(movesLeft < 1)
			{
				out.println("You lose.");
			}
			else if(wordLookup.size() == 0)
			{
				out.println("You win.");
			}
			else
			{
				out.println("You chickened out.");
			}
		}
		out.close();
		sc.close();
	}
}