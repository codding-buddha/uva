import java.io.*;
import java.util.*;

class TransportationSystem {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = in.nextInt();
		int cs = 1;
		while(tc-- > 0) {
			int n = in.nextInt();
			int r = in.nextInt();
			UnionFind uf = new UnionFind(n);
			Point[] cities = new Point[n];

			for(int i = 0; i < n; i++)
				cities[i] = new Point(in.nextInt(), in.nextInt());

			Edge[] edges = new Edge[((n)*(n-1))/2];
			int index = 0;
			for(int i = 0; i < n; i++) {
				for(int j = i+1; j < n; j++) {
					if(i == j)
						continue;
					edges[index++] = new Edge(i, j, cities[i].distance(cities[j]));
				}
			}

			Arrays.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge e1, Edge e2) {
					double df = e1.w - e2.w;
					return  df < 0 ? -1 : (df > 0 ? 1 : 0);
				}
			});

			int stateCount = 1;
			double roadLength = 0;
			double railLength = 0;
			int edgeCount = 0;
			index = 0;
			while(edgeCount != n-1) {
				Edge e = edges[index++];
				if(uf.find(e.v1) == uf.find(e.v2))
					continue;

				edgeCount++;
				uf.merge(e.v1, e.v2);

				double w = e.w;
				if(w <= r) {
					roadLength += w;
				} else {
					stateCount++;
					railLength += w;
				}
			}


			out.println(String.format("Case #%d: %d %d %d", cs++, stateCount, (int)(roadLength+0.5), (int)(railLength+0.5)));
		}
		
		out.flush();
		out.close();
	}

	static class Edge {
		int v1;
		int v2;
		double w;

		public Edge() {

		}

		public Edge(int v1, int v2, double w) {
			this.v1 = v1;
			this.v2 = v2;
			this.w = w;
		}
	}

	static class UnionFind {
		int[] _parent;
		int[] _rank;

		public UnionFind(int size) {
			_parent = new int[size];
			_rank = new int[size];
			for (int i = 0; i < size; i++) {
				_parent[i] = i;
			}
		}


		public void merge(int x, int y) {
			int px = find(x);
			int py = find(y);

			if(px == py)
				return;
			else if(_rank[px] >= _rank[py])
				_parent[y] = px;
			else
				_parent[x] = py;

			if(_rank[px] == _rank[py])
				_rank[px] += 1;

		}

		public int find(int x) {
			if(_parent[x] != x)
				_parent[x] = find(_parent[x]);

			return _parent[x];
		}
	}

	static class Point {
		public int x;
		public int y;

		public Point() {

		}

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double distance(Point p) {
			return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
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