import java.io.*;
import java.util.*;

public class WhatCard
{
	public static void main(String[] args) 
	{
		Stack<String> pile = null;
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		int testCaseCount = Integer.parseInt(sc.nextLine());
		for(int t = 0; t < testCaseCount; t++)
		{
			pile = new Stack<String>();
			String[] input = sc.nextLine().split(" ");
			for(int i = 0; i < input.length - 25; i++)
				pile.push(input[i]);
			int y =0;
			for(int i =0; i < 3; i++)
			{
				String c = pile.pop();
				int x = getValue(c);
				y+=x;
				for(int k =0; k < (10 - x); k++)
					pile.pop();
			}
			for(int i = input.length - 25; i< input.length; i++)
				pile.push(input[i]);

			for(int i =0, len = pile.size() - y; i < len; i++)
				pile.pop();
			//yth card from bottom
			output.add(String.format("Case %d: %s", t + 1, pile.pop()));
		}

		for(int i =0; i< output.size(); i++)
			System.out.println(output.get(i));
		
	}

	static char getSuit(String card)
	{
		return card.charAt(1);
	}

	static int getValue(String card)
	{
		char val = card.charAt(0);
		return val >= '2' && val <= '9'? (val - '0') : 10;
	}
}