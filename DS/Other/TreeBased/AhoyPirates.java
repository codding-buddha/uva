import java.util.*;
import java.io.*;

class AhoyPirates {
	static Data[] tree;
	public static void main(String[] args) throws Exception {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = in.nextInt();
		int cs = 1;
		while(tc-- > 0) {
			int m = in.nextInt();
			StringBuilder sb = new StringBuilder(100000);
			while(m-- > 0) {
				int c = in.nextInt();
				String desc = in.nextString();
				while(c-- > 0) {
					sb.append(desc);
				}
			}
			String desc = sb.toString();
			int[] a = new int[desc.length()];
			tree = new Data[a.length*4];
			for(int i = 0, len = desc.length(); i < len; i++) {
				a[i] = desc.charAt(i) == '0' ? 0 : 1;
			}

			init(a);
			int q = in.nextInt();
			//System.out.println(q);
			int qc = 1;
			out.println("Case " + cs++ + ":");
			while(q-- > 0) {
				String t = in.nextString();
				int i = in.nextInt();
				int j = in.nextInt();
				if(t.equals("I")) {
					flip(a, i, j);
				} else if(t.equals("F")) {
					set(a, i, j);
				} else if(t.equals("E")) {
					clear(a, i, j);
				} else {
					out.println("Q" + qc + ": " + query(a, i, j));
					qc++;
				}
			}
		}

		out.flush();
		out.close();
	}

	static class Data {
		public int v;
		public char state;

		public Data(int v, char state) {
			this.v = v;
			this.state = state;
		}

		@Override
		public String toString() {
			return "Value : " + v + ", State : " + state;
		}	
	}

	static void flip(int[] a, int i, int j) {
		update(1, a, 0, a.length-1, i, j, 'F');
	}

	static void set(int[] a, int i, int j) {
		update(1, a, 0, a.length-1, i, j, 'S');
	}

	static void clear(int[] a, int i, int j) {
		update(1, a, 0, a.length-1, i, j, 'C');
	}

	static void init(int[] a) {
		init(1, a, 0, a.length-1);
	}

	static void init(int node, int[] a, int i, int j) {
		if(i == j) {
			tree[node] = new Data(a[i], 'N');
			return;
		}

		int m = i + ((j-i)>>1);
		init(node<<1, a, i, m);
		init((node<<1)+1, a, m+1, j);
		tree[node] = new Data(tree[node<<1].v + tree[(node<<1)+1].v, 'N');
	}

	static void update(int node, int[] a, int i, int j, int u, int v, char state) {
		if(tree[node].state != 'N') {
			updateNode(node, i, j, u, v, tree[node].state);
		}

		if(i > j || i > v || j < u)
			return;

		if(i >= u && j <= v) {
			updateNode(node, i, j, u, v, state);	
			return;
		}

		int m = i + ((j-i)>>1);
		update(node<<1, a, i, m, u, v, state);
		update((node<<1)+1, a, m+1, j, u, v, state);
		tree[node].v = tree[node<<1].v + tree[(node<<1)+1].v;
		//System.out.println("Updated i : " + i  + ", j : " + j + " ->  node " + node + " value -> " + tree[node]);
		tree[node].state = 'N';
	}

	static int query(int[] a, int i, int j) {
		//System.out.println("Querying -> " + i + " : " + j);
		return query(1, a, 0, a.length-1, i, j);
	}

	static int query(int node, int[] a, int i, int j, int u, int v) {
		if(i > j || i > v || j < u)
			return 0;

		if(tree[node].state != 'N') {
			updateNode(node, i, j, u, v, tree[node].state);
		}

		if(i >= u && j <= v) {
			//System.out.println("Query : i -> " + i + " j -> " + j + " Value : " + tree[node]);
			return tree[node].v;
		}

		int m = i + ((j-i)>>1);
		int lv = query(node<<1, a, i, m, u, v);
		int rv = query((node<<1)+1, a, m+1, j, u, v);
		//System.out.println("Query : i -> " + i + " j -> " + j + " Value : " + (lv+rv));
		return lv+rv;
	}

	static void updateNode(int node, int i , int j, int u, int v, char state) {
			if(state == 'C') {
				tree[node].v = 0;
				if(i != j) {
					tree[node<<1].state = 'C';
					tree[(node<<1) + 1].state = 'C';
				}
			} else if(state == 'S') {
				tree[node].v = j-i+1;
				if(i != j) {
					tree[node<<1].state = 'S';
					tree[(node<<1) + 1].state = 'S';	
				}				
			} else if(state == 'F') {
				tree[node].v = (j-i+1) - tree[node].v;
				if(i != j) {
					tree[node<<1].state = flipState(tree[node<<1].state);
					tree[(node<<1)+1].state = flipState(tree[(node<<1)+1].state);
				}
			}

			tree[node].state = 'N';
	}

	static char flipState(char c) {
		switch(c) {
			case 'S':
				return 'C';
			case 'C':
				return 'S';
			case 'F':
				return 'N';
			case 'N':
				return 'F';
		}

		return 'N';
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