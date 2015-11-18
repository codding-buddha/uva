import java.util.*;
import java.io.*;

class ForestOfTrees {
	public static void main(String[] args) {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String ip = null;
		int tc = in.nextInt();
		while(tc-- > 0) {
			Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();
			while((ip = in.nextLine().trim()).charAt(0) != '*') {
				addEdge(graph, ip.charAt(1), ip.charAt(3));
			}

			ip = in.nextLine();
			String[] vertexes = ip.split(",");
			for(int i = 0; i < vertexes.length; i++) {
				if(!graph.containsKey(vertexes[i].charAt(0)))
					graph.put(vertexes[i].charAt(0), new ArrayList<Character>());
			}

			int acorns = 0;
			int trees = 0;
			boolean[] visited = new boolean[26];
			for(int i = 0; i < visited.length; i++) {
				if(graph.containsKey((char)('A' + i)) && !visited[i]) {
					if(graph.get((char)('A' + i)).size() > 0) {
						dfs(graph, (char)('A' + i), visited);
						trees++;
					} else {
						acorns++;
					}					
				}
			}

			out.println(String.format("There are %d tree(s) and %d acorn(s).",trees, acorns));
		}

		out.flush();
	}	

	static void dfs(Map<Character, List<Character>> graph, char v, boolean[] visited) {
		if(graph.containsKey(v) && !visited[v - 'A']) {
			visited[v- 'A'] = true;
			List<Character> vertexes = graph.get(v);
			for(int i = 0; i < vertexes.size(); i++) {
				dfs(graph, vertexes.get(i), visited);
			}
		}
	}

	static void addEdge(Map<Character, List<Character>> graph, char v1, char v2) {
		if(!graph.containsKey(v1)) {
			graph.put(v1, new ArrayList<Character>());
		}

		graph.get(v1).add(v2);

		if(!graph.containsKey(v2)) {
			graph.put(v2, new ArrayList<Character>());
		}

		graph.get(v2).add(v1);
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