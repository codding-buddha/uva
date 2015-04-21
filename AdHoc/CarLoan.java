import java.io.*;
import java.util.*;

public class CarLoan
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		List<String> output = new ArrayList<String>();
		while(sc.hasNextLine())
		{
			String[] input = sc.nextLine().trim().split(" ");
			int duration = Integer.parseInt(input[0]);
			if(duration < 0)
				break;
			float downPayment = Float.parseFloat(input[1]);
			float loan = Float.parseFloat(input[2]);
			int depreciationCount = Integer.parseInt(input[3]);
			float worth = loan + downPayment;
			float amountOwed = loan;
			float[] depreciation = new float[duration+1];
			float loanPayment = loan / duration;
			depreciation[0] = Float.parseFloat(sc.nextLine().split(" ")[1]);
			int k = 1;
			for(int i = 1; i < depreciationCount; i++)
			{
				String[] ip = sc.nextLine().split(" ");
				int month = Integer.parseInt(ip[0]);
				float dep = Float.parseFloat(ip[1]);
				if(month > k) 
				{
					while(k < month)
					{
						depreciation[k] = depreciation[k - 1];
						k++; 
					}
				}

				depreciation[k++] = dep;
			}
			
			while(k < depreciation.length) 
			{
				depreciation[k] = depreciation[k-1];
				k++;
			}

			String result  = "0 months";
			for(int i =0; i <= duration; i++)
			{
				amountOwed = loan - i*loanPayment;
				worth = worth - depreciation[i]*worth;
				if(worth > amountOwed) 
				{
					result = String.format("%d %s", i, i > 1 ? "months" : (i == 0 ? "months" : "month"));
					break;
				}
			}

			output.add(result);
		}

		for(int i =0;i < output.size(); i++)
			System.out.println(output.get(i));
	}
}