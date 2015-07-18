import java.util.*;
import java.io.*;

class ContestBoard {
	public static void main(String[] args) throws IOException {
		Scanner in = new Scanner(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		int tc = Integer.parseInt(in.nextLine());
		in.nextLine();
		for(int i = 0; i < tc; i++) {
			Team[] teams = new Team[100];
			for(int j = 0; j < teams.length; j++) {
				teams[j] = new Team(j+1);
			}

			while(in.hasNextLine()) {
				String ip = in.nextLine().trim();
				if(ip.isEmpty())
					break;

				String[] parts = ip.split(" ");
				int c = Integer.parseInt(parts[0]) - 1;
				int p = Integer.parseInt(parts[1]) - 1;
				int t = Integer.parseInt(parts[2]);
				String r = parts[3];

				//mark as participated
				teams[c].submitted = true;

				if(!teams[c].solved[p]) {
					if(r.equals("C")) {
						teams[c].solved[p] = true;
						teams[c].penalty[p] += t;
					} else if(r.equals("I")) {
						teams[c].penalty[p] += 20;
					}
				}
			}

			Arrays.sort(teams);
			for(int j =0 ;j < teams.length; j++) {
				if(teams[j].submitted) {
					out.write(teams[j].teamIndex + " " + teams[j].totalProblemsSolved() + " " + teams[j].getTotalPenalty() + "\n");
				}
			}			

			if(i < tc-1)
				out.write("\n");
		}

		out.close();
	}

	static class Team implements Comparable<Team> {
		public int teamIndex;
		public boolean[] solved;
		public int[] penalty;
		public boolean submitted;

		public Team(int ti) {
			teamIndex = ti;
			penalty = new int[9];
			solved = new boolean[9];
		}

		public int getTotalPenalty() {
			int p = 0;
			for(int i =0; i < 9; i++) {
				p += (solved[i] ? penalty[i] : 0);
			}

			return p;
		}

		public int totalProblemsSolved() {
			int c = 0;
			for(int i = 0; i < 9; i++) {
				c += solved[i] ? 1 : 0;
			}

			return c;
		}

		public int compareTo(Team t) {
			int thisScore = this.totalProblemsSolved();
			int thatScore = t.totalProblemsSolved();
			int thisPenalty = this.getTotalPenalty();
			int thatPenalty = t.getTotalPenalty();

			if(thisScore != thatScore) {
				return thatScore - thisScore;
			}else if(thisPenalty != thatPenalty) {
					return thisPenalty - thatPenalty;
			} else {
				return this.teamIndex - t.teamIndex;
			}
		}
	}

}