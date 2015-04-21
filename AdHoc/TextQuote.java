import java.io.*;
import java.util.*;
public class TextQuote
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> list = new ArrayList<String>();
		String input = null;
		boolean flag = false;
		while(sc.hasNextLine())
		{
			input=sc.nextLine();
			flag = transform(input, list, flag);
		}

		for(int i =0, len = list.size(); i < len; i++)
		{
			System.out.println(list.get(i));
		}
	}

	private static boolean transform(String str, List<String> lst, boolean flag)
	{
		int quotesCount = 0;
		for(int i =0, len = str.length(); i < len; i++)
		{
			if(str.charAt(i) == '"')
			{
				quotesCount++;
			}
		}
		if(quotesCount == 0)
		{
			lst.add(str);
		}
		else
		{
			char[] transformedStr = new char[str.length() + quotesCount];
			for(int i =0, j =0, len = str.length(); i < len; i++)
			{
				if(str.charAt(i) == '"')
				{
					if(!flag){
						transformedStr[j++] = '`';
						transformedStr[j++] = '`';
					}
					else
					{
						transformedStr[j++] = '\'';
						transformedStr[j++] = '\'';
					}
					flag = !flag;
				}
				else
				{
					transformedStr[j++] = str.charAt(i);
				}
			}
			lst.add(new String(transformedStr));
		}

		return flag;
	}
}