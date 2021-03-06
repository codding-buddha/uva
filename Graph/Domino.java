import java.io.*;
import java.util.*;

class Domino {
	static int[][] dis;
	static int[] visited;
	static long MAX = Long.MAX_VALUE;

	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = 1;
		while(true){
			int n = in.nextInt();
			int m = in.nextInt();
			if(n == 0 && m == 0)
				break;

			out.println(String.format("System #%d", tc++));
			if(n == 0) {
				for(int i = 0; i < m; i++) {
					in.nextInt(); in.nextInt(); in.nextInt();
				}

				out.println(String.format("The last domino falls after %.1f seconds, at key domino %d.", -1.0F, -1));
			}

			dis = new int[n+1][n+1];
			for(int i = 0; i <= n; i++){
				for(int j = 0; j <= n; j++) {
					dis[i][j] = -1;
				}
			}
			
			visited = new int[n+1];
			for(int i = 0; i < m; i++) {
				int s = in.nextInt();
				int e = in.nextInt();
				int w = in.nextInt();
				dis[s][e] = w;
				dis[e][s] = w;
			}

			long[] l = new long[n+1];

			for(int i = 2; i <= n; i++)
				l[i] = MAX;

		   while (true) {
		      int a = -1;
		      long min = MAX;

		      for (int i = 1; i <= n; i++) {
		        if (visited[i] == 0 && l[i] < min) {
		          min = l[i];
		          a = i;
		        }
		      }

		      if (a == -1) {
		        break;
		      }

		      visited[a] = 1;
		      for (int i = 1; i <= n; i++) {
		      	if(i == a || dis[a][i] == -1)
		      		continue;

		        if (l[i] > l[a] + dis[a][i]) {
		          l[i] = l[a] + dis[a][i];
		        }
		      }
		    }

			double time = 0.0F;
			int left = 1 , right = -1;
			for (int i = 1; i <= n; i++) {
				if(l[i] != MAX && time < l[i]) {
					time = l[i];
					left = i;
				}
			}

			for(int i = 1; i <=n; i++) {
				for(int j = 1; j <=n; j++) {
					if(i == j || dis[i][j] == -1)
						continue;

					long t1 = l[i], t2 = l[j];
					double t = Math.max(t1, t2) + (dis[i][j] - Math.abs(t1 - t2))/2.0;
					if(t1 != MAX && t2 != MAX && t > time) {
						time = t;
						left = i < j ? i : j;
						right = i > j ? i : j;
					}
				}
			}

			if(right == -1)
				out.println(String.format("The last domino falls after %.1f seconds, at key domino %d.", time, left));
			else
				out.println(String.format("The last domino falls after %.1f seconds, between key dominoes %d and %d.", time, left, right));

			out.println();
		}
		
		out.flush();
		out.close();
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