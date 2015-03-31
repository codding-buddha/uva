import java.io.*;
import java.util.*;

public class HowManyKnights
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().trim().split(" ");
			int n = Integer.parseInt(input[0]);
			int m = Integer.parseInt(input[1]);
			
			if(n*m == 0)
				break;

			int result;
			if(n == 1 || m == 1)
			{
				result = n*m;
				
			} 
			else 
			{
				if(n == 2 || m == 2)
				{
					result = (n*m)/2;
					int mod = result % 4;
					result = ((result/4) * 4) + (mod == 0 ? 0 : ((mod == 2 || mod == 3) ? 4:2));
				}
				else{
					result = (n*m + 1)/2;
				}
			}
			System.out.println(String.format("%d knights may be placed on a %d row %d column board.",result, n, m));
		}
	}
}