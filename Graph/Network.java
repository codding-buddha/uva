import java.io.*;
import java.util.*;

class Network {
	static List<Integer>[] graph;
	static int n;
	static boolean[] articulationPoint;
	static int dfsCount = 0;
	static int[] dfsL;
	static int[] dfsN;
	static boolean[] visited;
	static int[] parent;
	static int dfsRoot;
	static int rootChildren;
	public static void main(String[] args) throws IOException {
		// InputReader in = new InputReader(System.in);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		OutputWriter out = new OutputWriter(System.out);
		while(true) {
			n = Integer.parseInt(br.readLine().trim());

			if(n == 0)
				break;

			graph = (ArrayList<Integer>[])new ArrayList[n+1];

			for(int i = 1; i <= n; i++)
				graph[i] = new ArrayList<Integer>();

			String line = null;
			articulationPoint = new boolean[n+1];
			visited = new boolean[n+1];
			parent = new int[n+1];
			dfsN = new int[n+1];
			dfsL = new int[n+1];

			while(!(line = br.readLine().trim()).equals("0")) {
				String[] num = line.split(" ");
				int[] nodes = new int[num.length];

				for(int i = 0; i < nodes.length; i++)
					nodes[i] = Integer.parseInt(num[i]);

				for(int j = 1, i = 0; j < nodes.length; j++) {
					graph[nodes[i]].add(nodes[j]);
					graph[nodes[j]].add(nodes[i]);
				}
			}

			for(int i = 1; i <= n; i++) {
				dfsRoot = i;
				rootChildren = 0;
				if(!visited[i]) {
					findArticulation(i);
					articulationPoint[i] = rootChildren > 1 ? articulationPoint[i] : false;
				}
			}

			int count = 0;
			for(int i = 1; i <= n; i++) {
				count += articulationPoint[i] ? 1 : 0;
			}

			out.println(count);
		}
		
		out.flush();
		out.close();
	}

	static void findArticulation(int u) {
		if(visited[u])
			return;

		visited[u] = true;
		dfsL[u] = dfsCount++;
		dfsN[u] = dfsL[u];

		for(int i = 0; i < graph[u].size(); i++) {
			int v = graph[u].get(i);

			if(!visited[v]) {
				parent[v] = u;

				if(u == dfsRoot)
					rootChildren++;

				findArticulation(v);

				if(dfsL[v] >= dfsN[u])
					articulationPoint[u] = true;

				dfsL[u] = Math.min(dfsL[v], dfsL[u]);

			} else if(parent[u] != v){
				dfsL[u] = Math.min(dfsL[u], dfsN[v]);
			}
		}
	}

	static int log(int x, int base) {
		return (int)(Math.log(x)/Math.log(base));
	}

	static int min(Integer... numbers) {
		int min = numbers[0];
		for(int i = 1; i < numbers.length; i++) {
			if(numbers[i] < min)
				min = numbers[i];
		}
		return min;
	}

	static <T> void print(T[] items) {
		if(items == null)
			return;

		for(int i = 0; i < items.length; i++) {
			System.out.println(items[i]);
		}
	}

	static <T extends Comparable<? super T>> T[] nextPermutation(T[] items) {
		int first = -1;
		for(int i = items.length - 2; i >= 0; i--) {
			if(items[i].compareTo(items[i+1]) < 0) {
				first = i;
				break;
			}
		}

		if(first < 0)
		return null;

		for(int j = items.length - 1; j > first; j--) {
			if(items[j].compareTo(items[first]) > 0) {
				swap(items, first, j);
				break;
			}
		}

		for(int i = first + 1, j = items.length - 1; i <= j; i++, j--) {
			swap(items, i, j);
		}

		return items;
	}

	static void swap(Comparable[] a, int i, int j) {
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	static int setBitCount(int n) {
		int count = 0;
		while(n != 0) {
			n = n & (n-1);
			count++;
		}

		return count;
	}

	static class InputReader
	{
	    private InputStream stream;
	    private byte[] buf = new byte[1024];
	    private int curChar;
	    private int numChars;
	    private SpaceCharFilter filter;

	    public InputReader(InputStream stream)
	    {
	        this.stream = stream;
	    }

	    public int read()
	    {
	        if (numChars == -1)
	            throw new InputMismatchException();
	        if (curChar >= numChars)
	        {
	            curChar = 0;
	            try
	            {
	                numChars = stream.read(buf);
	            } catch (IOException e)
	            {
	                throw new InputMismatchException();
	            }
	            if (numChars <= 0)
	                return -1;
	        }
	        return buf[curChar++];
	    }

	    public int nextInt()
	    {
	        int c = read();
	        while (isSpaceChar(c))
	            c = read();
	        int sgn = 1;
	        if (c == '-')
	        {
	            sgn = -1;
	            c = read();
	        }
	        int res = 0;
	        do
	        {
	            if (c < '0' || c > '9')
	                throw new InputMismatchException();
	            res *= 10;
	            res += c - '0';
	            c = read();
	        } while (!isSpaceChar(c));
	        return res * sgn;
	    }

	    public String nextString()
	    {
	        int c = read();
	        while (isSpaceChar(c))
	            c = read();
	        StringBuilder res = new StringBuilder();
	        do
	        {
	            res.appendCodePoint(c);
	            c = read();
	        } while (!isSpaceChar(c));
	        return res.toString();
	    }
	    public double nextDouble() {
	        int c = read();
	        while (isSpaceChar(c))
	            c = read();
	        int sgn = 1;
	        if (c == '-') {
	            sgn = -1;
	            c = read();
	        }
	        double res = 0;
	        while (!isSpaceChar(c) && c != '.') {
	            if (c == 'e' || c == 'E')
	                return res * Math.pow(10, nextInt());
	            if (c < '0' || c > '9')
	                throw new InputMismatchException();
	            res *= 10;
	            res += c - '0';
	            c = read();
	        }
	        if (c == '.') {
	            c = read();
	            double m = 1;
	            while (!isSpaceChar(c)) {
	                if (c == 'e' || c == 'E')
	                    return res * Math.pow(10, nextInt());
	                if (c < '0' || c > '9')
	                    throw new InputMismatchException();
	                m /= 10;
	                res += (c - '0') * m;
	                c = read();
	            }
	        }
	        return res * sgn;
	    }
	    public long nextLong() {
	        int c = read();
	        while (isSpaceChar(c))
	            c = read();
	        int sgn = 1;
	        if (c == '-') {
	            sgn = -1;
	            c = read();
	        }
	        long res = 0;
	        do {
	            if (c < '0' || c > '9')
	                throw new InputMismatchException();
	            res *= 10;
	            res += c - '0';
	            c = read();
	        } while (!isSpaceChar(c));
	        return res * sgn;
	    }
	    public boolean isSpaceChar(int c)
	    {
	        if (filter != null)
	            return filter.isSpaceChar(c);
	        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
	    }

	    public String next()
	    {
	        return nextString();
	    }

	    public interface SpaceCharFilter
	    {
	        public boolean isSpaceChar(int ch);
	    }
	}

	static class OutputWriter
	{
	    private final PrintWriter writer;

	    public OutputWriter(OutputStream outputStream)
	    {
	        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
	    }

	    public OutputWriter(Writer writer)
	    {
	        this.writer = new PrintWriter(writer);
	    }

	    public void print(Object... objects)
	    {
	        for (int i = 0; i < objects.length; i++)
	        {
	            if (i != 0)
	                writer.print(' ');
	            writer.print(objects[i]);
	        }
	    }

	    public void println(Object... objects)
	    {
	        print(objects);
	        writer.println();
	    }

	    public void close()
	    {
	        writer.close();
	    }

	    public void flush()
	    {
	        writer.flush();
	    }
	}
}