import java.io.*;
import java.util.*;

public class Bender
{
	public static void main(String[] args)
	{
		Direction d = new Direction();
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			d.Set("+x");
			int l = Integer.parseInt(sc.nextLine());
			if(l == 0)
				break;
			String[] action = sc.nextLine().trim().split(" ");
			for(int i =0; i < action.length; i++)
			{
				if(!action[i].equals("No"))
				{
					d.Change(action[i]);
				}
			}
			output.add(d.Get());
		}
		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}
}

class Direction
{
	String current;
	
	Direction()
	{
		current = null;
	}

	void Change(String d)
	{
		if(current == null || current.equals("+x"))
		{
			current = d;
			return;
		}
		int currDir = current.charAt(0) == '+' ?  1 : -1;
		char curraxis = current.charAt(1);
		int changeDir = d.charAt(0) == '+' ? 1 : -1;
		char changeaxis = d.charAt(1);
		if(curraxis == 'y')
		{
			if(changeaxis == 'y')
			{
				current = (currDir*changeDir) < 0 ? "+x" : "-x";
			}
		} 
		else if(curraxis == 'x')
		{
			current = (changeDir*-1 < 0 ? "-" : "+") + changeaxis;
		} 
		else
		{
			if(changeaxis == 'z')
			{
				current = (currDir*changeDir) < 0 ? "+x" : "-x";	
			}

		}
	}

	void Set(String d)
	{
		current = d;
	}

	String Get()
	{
		return current;
	}
}