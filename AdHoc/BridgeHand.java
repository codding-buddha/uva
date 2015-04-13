import java.io.*;
import java.util.*;

public class BridgeHand
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().trim().split(" ");
			Hand hand = new Hand();
			for(int i =0; i < input.length; i++)
			{
				//KS QS TH 8H 4H AC QC TC 5C KD QD JD 8D
				hand.add(input[i].charAt(1), input[i].charAt(0));
			}

			int value = hand.value();
			if(value < 14)
			{
				output.add("PASS");
			}
			else
			{
				int noTrumpValue = hand.noTrumpValue();
				if(noTrumpValue >= 16 && hand.isStopped('S') && hand.isStopped('C') && hand.isStopped('H') && hand.isStopped('D')) 
				{
					output.add("BID NO-TRUMP");
				}
				else
				{
					output.add("BID "+ hand.maxSuit());
				}

			}
		}

		for(int i =0;i < output.size(); i++)
			System.out.println(output.get(i));
	}
}

class Hand
{
	boolean[] _diamond = new boolean[13];
	boolean[] _spade = new boolean[13];
	boolean[] _club = new boolean[13];
	boolean[] _heart = new boolean[13];
	HashMap<Character, Integer> indexMap = new HashMap<Character,Integer>();
	char[] suits = new char[]{'S', 'H', 'D', 'C'};
	Hand()
	{
		indexMap.put('A', 0);	indexMap.put('K', 1);	indexMap.put('Q', 2); indexMap.put('J', 3);
		indexMap.put('2', 4);	indexMap.put('3', 5);	indexMap.put('4', 6);	indexMap.put('5', 7);
		indexMap.put('6', 8);	indexMap.put('7', 9);	indexMap.put('8', 10);	indexMap.put('9', 11);
		indexMap.put('T', 12);
	}

	public void add(char s, char c)
	{
		boolean[] store = getArrayFor(s);
		store[indexMap.get(c)] = true;
	}

	public char maxSuit()
	{
		char msuit = ' ';
		int maxCount = 0;
		int c = 0;
		for(int i =0; i < suits.length; i++)
		{
			c = count(suits[i]);
			if(c > maxCount)
			{
				msuit = suits[i];
				maxCount = c;
			}
		}

		return msuit;
	}

	public int value()
	{
		int point = 0;
		for(int i =0; i < suits.length; i++)
		{
			int c = count(suits[i]);
			point += value(suits[i], c);
		}
		return point;
	}

	public int noTrumpValue()
	{
		int point = 0;
		for(int i =0; i < suits.length; i++)
		{
			int c = count(suits[i]);
			point += noTrumpValue(suits[i], c);
		}
		return point;	
	}

	private int noTrumpValue(char s, int count)
	{
		boolean[] store = getArrayFor(s);
		int point = 0;

		for(int i = 0; i < 4; i++)
			point += (store[i] ? 4 - i : 0);

		if(store[1] && count == 1)
			point -= 1;

		if(store[2] && count <= 2)
			point -= 1;

		if(store[3] && count <= 3)
			point -= 1;

		return point;
	}

	private int value(char s, int count)
	{
		boolean[] store = getArrayFor(s);
		int point = noTrumpValue(s, count);

		if(count == 2)
			point += 1;
		if(count <= 1)
			point += 2;
		return point;
	}

	public int count(char s)
	{
		boolean[] store = getArrayFor(s);
		int count = 0;
		for(int i =0; i < store.length; i++)
			count = count + (store[i] ? 1 : 0);
		return count;
	}

	public boolean isStopped(char s)
	{
		boolean[] store = getArrayFor(s);
		if(store[0])
			return true;
		int c = count(s);
		if((store[1] && c > 1) || (store[2] && c > 2))
			return true;
		return false;
	}

	private boolean[] getArrayFor(char s)
	{
		boolean[] store = null;
		switch(s)
		{
			case 'S': store = _spade;
			break;
			case 'C' : store = _club;
			break;
			case 'D' : store = _diamond;
			break;
			default : store = _heart;
			break;
		}
		return store;
	}

}