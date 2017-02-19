import java.io.*;
import java.util.*;

class ArticNetwork {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);

		int tc = in.nextInt();
		while(tc-- > 0) {
			int c = in.nextInt() - 1;
			int t = in.nextInt();
			Point[] points = new Point[t];
			for(int  i = 0; i < t; i++) {
				points[i] = new Point(in.nextInt(), in.nextInt());
			}

			List<Edge> edges = new ArrayList<Edge>();

			for(int i = 0; i < t; i++) {
				for(int j = i+1; j < t; j++) {
					edges.add(new Edge(i, j, points[i].distance(points[j])));
				}
			}

			Collections.sort(edges, new Comparator<Edge>() {
				@Override
				public int compare(Edge e1, Edge e2) {
					return e1.w < e2.w ? -1 : (e1.w > e2.w ? 1 : 0);
				}
			});

			UnionFind uf = new UnionFind(t);

			double max = 0.0;
			for(int i = 0, len = edges.size(); i < len && c < t-1; i++) {
				Edge e = edges.get(i);
				if(uf.merge(e.x, e.y)) {
					max = e.w;
					c++;
				}
			}

			out.println(String.format("%.2f", c == t-1  ? max :  0.0));

		}
		
		out.flush();
		out.close();
	}

	static class UnionFind {

		private int[] _parent;
		private int[] _rank;

		public UnionFind(int n) {
			_parent = new int[n];
			_rank = new int[n];

			for(int i = 0; i < n; i++) {
				_parent[i] = i;
				_rank[i] = 0;
			}
		}

		public boolean merge(int x, int y) {
			int px = find(x), py = find(y);
			if(px == py)
				return false;

			if(_rank[px] >= _rank[py]) {
				_parent[px] = py;
			} else {
				_parent[py] = px;
			}

			if(_rank[px] == _rank[py])
				_rank[px] += 1;

			return true;
		}

		public int find(int x) {
			if(_parent[x] != x) {
				_parent[x] = find(_parent[x]);
			}

			return _parent[x];
		}

	}

	static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public double distance(Point p)  {
			if(p == null)
				return 0;

			return Math.sqrt(Math.pow(this.x - p.x, 2) + Math.pow(this.y - p.y, 2));
		}
	}

	static class Edge {
		public int x;
		public int y;
		public double w;

		public Edge(int x, int y, double w) {
			this.x = x;
			this.y = y;
			this.w = w;
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