import java.io.*;
import java.util.*;

public class Ananagrams
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		HashMap<String, ArrayList<String>> lookup = new HashMap<String, ArrayList<String>>();
		ArrayList<String> result = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String line = sc.nextLine();
			if(line.equals("#"))
				break;
			String[] words = line.split(" ");
			for(int i =0; i < words.length; i++)
			{
				if(words[i].length() == 1)
				{
					result.add(words[i]);
					continue;
				}
				
				char[] key = words[i].toLowerCase().toCharArray();
				Arrays.sort(key);
				String kStr = new String(key);
				if(lookup.containsKey(kStr))
				{
					lookup.get(kStr).add(words[i]);
				}
				else
				{
					ArrayList<String> ar = new ArrayList<String>();
					ar.add(words[i]);
					lookup.put(kStr, ar);
				}
			}
		}


		for(Map.Entry<String, ArrayList<String>> entry : lookup.entrySet())
		{
			if(entry.getValue().size() == 1)
			{
				result.add(entry.getValue().get(0));
			}
		}

		Collections.sort(result);

		for(int i =0; i < result.size(); i++)
		{
			out.println(result.get(i));
		}

		sc.close();
		out.close();
	}
}