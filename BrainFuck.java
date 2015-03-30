import java.io.*;
import java.util.*;

public class BrainFuck
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int testCaseCount = Integer.parseInt(sc.nextLine());
		List<String> output = new ArrayList<String>();
		for(int i =0; i < testCaseCount; i++)
		{
			short[] mem = new short[100];
			int pointer = 0;
			String commands = sc.nextLine();
			for(int k =0, len = commands.length(); k < len; k++)
			{
				switch(commands.charAt(k))
				{
					case '>': pointer = (pointer + 1) % 100;
					break;
					case '<': pointer = (pointer - 1 + 100) % 100;
					break;
					case '+': mem[pointer] =(short)((mem[pointer] + 1) % 256);
					break;
					case '-': mem[pointer] = (short)((mem[pointer] - 1 + 256) % 256);
					break;
					default:
					break;
				}
			}

			String dump = String.format("Case %d:", i+1);
			for(int k = 0; k < mem.length; k++)
			{
				dump += String.format(" %02X",mem[k]);
			}
			output.add(dump);
		}

		for(int i  =0; i < output.size(); i++)
			System.out.println(output.get(i));
	}
}	
//..++<><<+++>>++++++++++++++++++++++++++>>>+++<+...++<><<+++>>++++++++++++++++++++++++++>>>+++<+...++<><<+++>>++++++++++++++++++++++++++>>>+++<+.
