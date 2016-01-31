import java.io.*;
import java.util.*;
 
 
public class FerryLoadingII {
	
	public static void main(String args[]) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		//BufferedReader in = new BufferedReader(new FileReader("input"));	// for local testing from input file
		
		int caseNum = Integer.parseInt(in.readLine());	// get the test case
		
		
		for(int caseId=0; caseId<caseNum; caseId++){
			in.readLine();	// read the empty line before each test case
			
			int L = Integer.parseInt(in.readLine());	
			L *= 100;								// get the ferry length, and convert from meters to centimeters
			
			boolean [][] dp = new boolean [2][L+1];	// allocate dp table with space saving trick, note the off-by-one, as length can be [0, L] including L+1 values
			Arrays.fill(dp[0], false);				// initialize dp table to "no solution" state
			dp[0][0] = true;						// initially, 0 length at both sides is feasible
			
			int [][] pre = new int [205][L+1];		// allocate solution memo, note the off-by-one also
			
			int [] carlen = new int [205];			// the length of each car, used to recover our solution from pre[][] matrix
			
			boolean done = false;					// are we done with this case?
			int t=0, pt;							// space saving trick
			int i = 0;								// current car id
			int N = 0;								// how many cars can we load
			
			int sumlen = 0;				// sum of car length we've considered so far
			int lastlen = 0;			// record feasible length at port side for the last loaded car
			
			while(true){
				int curlen = Integer.parseInt(in.readLine());	// get the current car length
				if(curlen==0) break;	// get a 0-length, terminate the case
				if(done==true) continue;// a previous car cannot be loaded already, so we directly ignore the following car
				
				pt = t; t ^= 1;			// swap dp table (space saving trick)
				carlen[++i] = curlen;	// increment car id, store the car length
				sumlen += curlen;		// add the length to the length sum

				Arrays.fill(dp[t], false);				// initialize a new row of dp table

				boolean canload = false;				// can we load this car?

				for(int len=0; len<=L; len++){					// enumerate all the possible lengths at port side
					if(dp[pt][len]==false) continue;			// there is no solution for this state, ignore
					
					// is it ok if we put the car on the port side?
					if(len+curlen<=L && sumlen-(len+curlen)<=L){  // port side ok && starboard side ok
						dp[t][len+curlen] = true;
						pre[i][len+curlen] = 0;	// record out decision, recall: 0 means we put the i-th car at port side
						lastlen = len+curlen;
						canload = true;
					}

					// is it ok if we put the car on the port side?
					if(sumlen-len<=L){			// starboard side ok (len<=L is trivial so we do not write it explicitly)
						dp[t][len] = true;
						pre[i][len] = 1;		// record out decision, recall: 1 means we put the i-th car at starboard side
						lastlen = len;
						canload = true;
					}
				}
				if(!canload) done = true;		// if we cannot load this car, we are done
				else N = i;						// store how many cars we have successfully loaded
			}
			
			System.out.println(N);				// print the number of cars we load
			
			int [] answer = new int [N+1];		// allocate the answer (decision) array, note the off-by-one, cars are numbered from 1 to N
			for(i=N; i>=0; i--){				// iterate reversely from the last car to the first, and write to the answer table
				if(pre[i][lastlen]==0){			// this car is loaded at port
					lastlen -= carlen[i];		// decrease the port side length
					answer[i] = 0;
				}else if(pre[i][lastlen]==1){	// this car is loaded at starboard
					answer[i] = 1;	
				}
			}

			for(i=1; i<=N; i++){									// print the solutions
				if(answer[i]==0) System.out.println("port");
				else if(answer[i]==1) System.out.println("starboard");
			}
			if(caseId<caseNum-1) System.out.println("");			// print an empty line between consecutive cases
		}
		
	}
}