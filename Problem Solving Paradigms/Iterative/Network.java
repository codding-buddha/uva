import java.io.*;
import java.util.*;

class Network {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int cs = 1;
		while(true) {
			int n = in.nextInt();
			int m = in.nextInt();
			if(n == 0 && m == 0)
				break;
			List<PriorityQueue<Pair>> buffer = new ArrayList<PriorityQueue<Pair>>(n+1);
			int[] expected = new int[n+1];
			int[] ms = new int[n+1];
			Integer[] p = new Integer[n];
			int[] msg = new int[m];
			int[] s = new int[m];
			int[] e = new int[m];
			int result = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++){
				ms[i+1] = in.nextInt();
				p[i] = i+1;
			}

			int indx = 0;
			int temp = m;
			while(temp-- > 0) {

				msg[indx] = in.nextInt();
				s[indx] = in.nextInt();
				e[indx] = in.nextInt();
				indx++;
			}

			do {
				indx = 0;
				int k = 0;
				buffer.clear();
				int size = 0;
				int max = 0;
				for(int i = 0; i < n; i++){
					expected[i+1] = 1;
					buffer.add(new PriorityQueue<Pair>());
				}

				while(indx < p.length && k < m) {
					PriorityQueue<Pair> pq = buffer.get(msg[k]-1);
					if(msg[k] == p[indx]) {
						if(expected[msg[k]] == s[k]) {
							expected[msg[k]] = e[k]+1;
							while(pq.size() > 0 && pq.peek().first == expected[msg[k]]) {
								Pair pr = pq.poll();
								size -= (pr.second-pr.first+1);
								expected[msg[k]] = pr.second+1;
							}
						} else {
							pq.add(new Pair(s[k], e[k]));
							size += (e[k]-s[k])+1;
							if(size > max)
								max = size;
						}
						if(expected[msg[k]] >= ms[msg[k]]){
							indx++;
							while(true) {
								if(indx >= p.length)
									break;
								if(buffer.get(p[indx]-1).size() < 1){
									indx++;
									continue;
								}

								if(expected[p[indx]] == buffer.get(p[indx]-1).peek().first) {
									Pair pr = buffer.get(p[indx]-1).poll();
									size -= (pr.second-pr.first+1);
									expected[p[indx]] = pr.second + 1;
									if(expected[p[indx]] >= ms[p[indx]])
										indx++;
								} else {
									break;
								}
							}
						}
					} else {
						pq.add(new Pair(s[k], e[k]));
						size += (e[k]-s[k])+1;
						if(size > max)
							max = size;
					}

					k++;
			  }

			  if(max < result)
			  	result = max;

			}while(nextPermutation(p) != null);

			out.println(String.format("Case %d: %d", cs++, result));
			out.println();

		}
		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair>{
		public int first;
		public int second;

		public Pair(int f, int s) {
			first = f;
			second = s;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.first == p.first)
				return this.second - p.second;
			return this.first - p.first;
		}

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