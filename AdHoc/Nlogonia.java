import java.io.*;
import java.util.*;

public class Main
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			int queryCount = Integer.parseInt(sc.nextLine());
			if(queryCount == 0)
				break;
			int[] dcord = GetCoordinates(sc);
			for(int i =0; i < queryCount; i++)
			{
				int[] coord = GetCoordinates(sc);
				if(coord[0] == dcord[0] || coord[1] == dcord[1])
					output.add("divisa");
				else if(coord[0] > dcord[0])
				{
					if(coord[1] > dcord[1])
					{
						output.add("NE");
					}
					else
					{
						output.add("SE");
					}
				}
				else
				{
					if(coord[1] < dcord[1])
					{
						output.add("SO");
					}
					else
					{
						output.add("NO");
					}
				}

			}

		}

		for(int i =0; i < output.size(); i++)
			System.out.println(output.get(i));

	}

	private static int[] GetCoordinates(Scanner sc)
	{
		String[] pointStr = sc.nextLine().trim().split(" ");
		int[] coord = new int[2];
		coord[0] = Integer.parseInt(pointStr[0]);
		coord[1] = Integer.parseInt(pointStr[1]);
		return coord;
	}
}