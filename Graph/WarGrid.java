import java.io.*;
import java.util.*;

class WarGrid {
	static int[] rd = new int[] {1, 1, -1, -1};
	static int[] cd = new int[] {1, -1, 1, -1};
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = in.nextInt();

		for(int t = 1; t <= tc; t++) {
			int r = in.nextInt(), c = in.nextInt(), m = in.nextInt(), n = in.nextInt();
			int w = in.nextInt();
			int[][] grid = new int[r][c];
			while(w-- > 0) {
				int i = in.nextInt(), j = in.nextInt();
				grid[i][j] = -1;
			}

			int oddCount = 0; 
			int evenCount = 0;
			Queue<Pair> q = new LinkedList<Pair>();
			HashSet<Pair> set = new HashSet<Pair>();

			Pair fp = new Pair();
			q.add(fp);
			set.add(fp);	
			
			while(q.size() > 0) {
				HashSet<Pair> used = new HashSet<Pair>();
				Pair p = q.remove();
				set.remove(p);
				int i = p.i;
				int j = p.j;
				int len = 4;
				for(int k = 0; k < len; k++) {
					int rm = i + n*rd[k];
					int cm = j + m*cd[k];
					if(rm >= 0 && rm < r && cm >= 0 && cm < c && grid[rm][cm] != -1) {
						Pair pr = new Pair(rm, cm);
						if(used.contains(pr))
							continue;

						used.add(pr);
						grid[i][j]++;
						if(grid[rm][cm] == 0){
							if(set.add(pr))
								q.add(pr);
						}
					}
				}

				for(int k = 0; k < len; k++) {
					int rm = i + m*rd[k];
					int cm = j + n*cd[k];
					if(rm >= 0 && rm < r && cm >= 0 && cm < c && grid[rm][cm] != -1) {
						Pair pr = new Pair(rm, cm);
						//already considered this cell
						if(used.contains(pr))
							continue;

						used.add(pr);
						grid[i][j]++;
						if(grid[rm][cm] == 0) {
							if(set.add(pr))
								q.add(pr);
						}
					}
				}

				if(grid[i][j] == 0)
					continue;

				if(grid[i][j] % 2 == 0)
					evenCount++;
				else
					oddCount++;
			}

			if(evenCount == 0 && oddCount == 0)
				evenCount++;

			out.println(String.format("Case %d: %d %d", t, evenCount, oddCount));
		}
		
		out.flush();
		out.close();
	}

	static class Pair{
		public int i;
		public int j;

		public Pair(int v1, int v2) {
			i = v1; j = v2;
		}

		public Pair() {
			i = 0; j = 0;
		}
		
	   @Override
	    public String toString() {
	        return "(" + i + ", " + j + ")";
	    }

	    @Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
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