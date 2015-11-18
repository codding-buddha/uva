import java.util.*;
import java.io.*;

class IntervalProduct {

	static int[] tree;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		try {
			while(true) {
				int n = in.nextInt();
				int q = in.nextInt();
				int[] a = new int[n];
	
				for(int i = 0; i < n; i++) {
					a[i] = in.nextInt();
				}
				
				tree = new int[4*n];
				init(a);
				while(q-- > 0) {
					String action = in.nextString();
					int i = in.nextInt();
					int j = in.nextInt();
					if(action.equals("C")) {						
						update(a, i-1, j);
					} else {
						int result = query(a, i-1, j-1);
						out.print((result == 0 ? "0" : (result < 0 ? "-" : "+")));
					}
				}
				out.println();
			}
		}catch(Exception e) {

		}
		finally {
			out.flush();
			out.close();
		}
	}

	static void init(int[] a) {
		init(1, a, 0, a.length-1);
	}

	static void init(int node, int[] a, int i , int j) {
		if(i > j)
			return;
		if(i == j) {
			tree[node] = a[i] == 0 ? 0 : (a[i] < 0 ? -1 : 1);
			return;
		}
		int m = i + (j-i)/2;
		init(node<<1, a, i, m);
		init((node<<1)+1, a, m+1, j);
		tree[node] = tree[node<<1]*tree[(node<<1)+1];
	}

	static void update(int[] a, int i, int v) {
		update(1, a, 0, a.length - 1, i, i, v);
	}

	static void update(int node, int[] a, int i, int j, int u, int v, int x) {
		if(j < u || i > v)
			return;
		if(i == j) {
			tree[node] = (x == 0 ? 0 : (x < 0 ? -1 : 1));
			return;
		}

		int m = i + (j-i)/2;
		update(node<<1, a, i, m, u, v, x);
		update((node<<1)+1, a, m+1, j, u, v, x);		
		tree[node] = tree[node<<1]*tree[(node<<1)+1];
	}

	static int query(int[] a, int i , int j) {
		return query(1, a, 0, a.length - 1, i, j);
	}

	static int query(int node, int[] a, int i, int j, int u, int v) {
		if(j < u || i > v){
			return 1;
		}
		if(i >= u && j <= v) {
			return tree[node];
		}

		int m = i + (j-i)/2;
		return query(node<<1, a, i, m, u, v)*query((node<<1)+1, a, m+1, j, u, v);
	}

	private static class InputReader
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
 
    private static class OutputWriter
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