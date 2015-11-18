import java.util.*;
import java.io.*;

public class WakingUpBrain {
	static HashMap<Character, Area> _connections;
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		try{
			while(true) {
				_connections = new HashMap<Character, Area>();
				int c = in.nextInt();
				int con = in.nextInt();
				String direct = in.nextLine();
				for(int i = 0; i < direct.length(); i++) {
					_connections.put(direct.charAt(i), new Area(direct.charAt(i), true));
				}
				for(int i = 0; i < con; i++) {
					String connection = in.nextLine();
					if(!_connections.containsKey(connection.charAt(0))) {
						_connections.put(connection.charAt(0), new Area(connection.charAt(0), false));
					}

					if(!_connections.containsKey(connection.charAt(1))) {
						_connections.put(connection.charAt(1), new Area(connection.charAt(1), false));
					}

					_connections.get(connection.charAt(0)).connect(connection.charAt(1));
					_connections.get(connection.charAt(1)).connect(connection.charAt(0));
				}

				Queue<Area> q1 = new LinkedList<Area>();
				for(int i = 0; i < direct.length(); i++) {
					Area da = _connections.get(direct.charAt(i));
					for(Character an : da.connectedTo) {
						Area a = _connections.get(an);
						if(!a.isAwake && a.connectedCount >= 3) {
							a.isAwake = true;
							q1.add(a);
						}
					}      
				}

				Queue<Area> q2 = new LinkedList<Area>();
				int year = q1.size() == 0 ? 0 : 1;
				while(q1.size() > 0) {
					Area a = q1.poll();
					for(Character an : a.connectedTo) {
						Area na = _connections.get(an);
						if(!na.isAwake) {
							na.connectedCount++;
							if(na.connectedCount >= 3) {
								na.isAwake = true;
								q2.add(na);
							}
						}
					}   

					if(q1.size() == 0) {
						if(q2.size() > 0)
							year++;
						q1 = q2;
						q2= new LinkedList<Area>();
					}
				}

				int awakenedCount = 0;
				for(Area a: _connections.values()) {
					if(!a.isAwake){
						break;
					}
					awakenedCount++;
				}

				if(awakenedCount != c)
					out.println("THIS BRAIN NEVER WAKES UP");
				else
					out.println(String.format("WAKE UP IN, %d, YEARS", year));
			}
		}catch(Exception e) {
		}
		finally{
			out.close();	
		}		
	}

	static class Area {
		public int connectedCount;
		public HashSet<Character> connectedTo;
		public boolean isAwake;
		public char name;

		public Area(char nm, boolean awake) {
			name = nm;
			isAwake = awake;
			connectedTo = new HashSet<Character>();
		}

		public void connect(char a) {
			if(_connections.containsKey(a)) {
				Area ar = _connections.get(a);
				if(ar.isAwake) {
					connectedCount++;
				} else {
					ar.connectedTo.add(this.name);
					connectedTo.add(a);
				}

				/*if(connectedCount >= 3) {
					isAwake = true;
				}

				awaken(this);*/
			}
		}

		public void awaken(Area	ar) {
			if(ar.isAwake) {
				for(Character a : ar.connectedTo) {
					Area an = _connections.get(a);
					if(!an.isAwake) {
						an.connectedCount++;
						if(an.connectedCount >= 3) {
							an.isAwake = true;
							awaken(an);
						}
					}
				}	
			}			
		}
	}

	static class FastScanner
	{
		BufferedReader br;
		StringTokenizer st;
		public FastScanner() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		boolean hasNext() {
			return st != null && st.hasMoreElements();
		}

		String next() {
			while(st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				}catch(IOException e) {
				}
			}
			return st.nextToken();
		}

		int nextInt() {
			return Integer.parseInt(next());
		}

		long nextLong() {
			return Long.parseLong(next());
		}

		double nextDouble() {
			return Double.parseDouble(next());
		}

		String nextLine() {
			String str = "";
			try {
				str = br.readLine();
			}catch(IOException e) {
			}

			return str;
		}
	}
}