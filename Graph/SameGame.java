import java.io.*;
import java.util.*;

class SameGame {
	static int[][] g;
	static int[] dc = new int[] {-1, 0, 1, 0};
	static int[] dr = new int[] {0, -1, 0, 1};
	static int n;
	static int m = 15;
	static int sx = -1;
	static int sy = -1;
	static long points = 0;
	static int cc = 0;
	static int maxArea = -1;
	static HashMap<Integer, Integer> cl;
	static BufferedReader in;
	static OutputWriter out;
	public static void main(String[] args) throws Exception{
		in = new BufferedReader(new InputStreamReader(System.in));
		out = new OutputWriter(System.out);
		int tc = Integer.parseInt(in.readLine().trim());
		in.readLine();
		for(int t = 1; t <= tc; t++) {
			
			if(t > 1)
				out.println();
			
			out.println(String.format("Game %d:", t));
			out.println();

			cl = new HashMap<Integer, Integer>();
			String line = null;
			boolean endOfGrid = false;
			List<String> rows = new ArrayList<String>();
			while(true) {
				line = in.readLine();
				line = line != null ? line.trim() : line;
				//end of testcase
				if(line == null || line.equals("")) {
					endOfGrid = true;
				}

				if(endOfGrid) {
					n = rows.size();
					g = new int[n][m];
					for(int i = n-1, k = 0; i >= 0; i--, k++) {
						String r = rows.get(k);
						for(int j = 0; j < m; j++) {
							g[i][j] = r.charAt(j) == 'R' ? -1 :  r.charAt(j) == 'G' ? -2  : -3;
						}
					}

					//till game ends
					int move = 1;
					points = 0;
					sx = -1; sy = -1;
					while(true) {
						cluster();
						if(sx == -1 || sy == -1) {
							int rb = remaingingBalls();
							points += rb == 0 ? 1000 : 0; 
							out.println(String.format("Final score: %d, with %d balls remaining.", points, rb));
							break;
						}

						remove(move++);
					}
					
					break;

				} else {
					rows.add(line);
				}
			}
		}
		
		
		out.flush();
		out.close();
	}

	static void cluster() {
		cc = 0;
		int max = 0;
		int mcc = 0;
		for(int j = 0; j < m; j++) {
			for(int i = 0; i < n; i++) {
				if(g[i][j] >= 0 || g[i][j] == Integer.MIN_VALUE){
					continue;
				}
				else {
					int color = g[i][j];
					int area = mark(i, j, color);
					cl.put(cc, color);
					cc++;
					if(area >= 2 && area > max) {
						max = area;
						sx = i;
						sy = j;
						maxArea = max;
						mcc = cc-1;
					}
				}
			}
		}

		if(max == 0) {
			sx = -1; sy = -1;
		}
	}

	static void remove(int mc) throws Exception{
		if(sx == -1 || sy == -1)
			return;
		long p = (maxArea-2)*(maxArea-2);
		points += p;
		out.println(String.format("Move %d at (%d,%d): removed %d balls of color %c, got %d points.",
									mc, sx+1, sy+1, maxArea, color(sx, sy), p));
		remove(sx, sy, g[sx][sy]);
		//shift down
		for(int j = 0; j < m; j++) {
			int k = 0;
			for(int i = 0; i < n; i++) {
				if(g[i][j] != Integer.MIN_VALUE) {
					//reset color
					g[i][j] = cl.containsKey(g[i][j]) ? cl.get(g[i][j]) : g[i][j];
					g[k++][j] = g[i][j];
				}
			}
			while(k < n)
				g[k++][j] = Integer.MIN_VALUE;
		}

		//shift left
		int k = 0;
		for(int j = 0, i = 0; j < m; j++) {
			for(i = 0; i < n; i++) {
				if(g[i][j] != Integer.MIN_VALUE)
					break;
			}
			
			if(i != 10) {
				for(i = 0; i < n; i++)
					g[i][k] = g[i][j];
				k++;
			}
		}
		
		while(k < m) {
			for(int i = 0; i < n; i++)
				g[i][k] = Integer.MIN_VALUE;
			k++;
		}

		cl.clear();
	}

	static boolean isEmptyCol(int col) {
		for(int i = 0; i < n; i++) {
			if(g[i][col] != Integer.MIN_VALUE)
				return false;
		}

		return true;
	}

	static void remove(int i, int j, int c) {
		if(i >= n || i < 0 || j >= m || j < 0 || g[i][j] != c)
			return;
		g[i][j] = Integer.MIN_VALUE;
		for(int k = 0; k < 4; k++) {
			remove(dr[k] + i, dc[k] + j, c);
		}
	}

	static int mark(int i, int j, int c) {
		if(i >= n || i < 0 || j >= m || j < 0 || g[i][j] != c)
			return 0;
		int m = 1;

		g[i][j] = cc;
		for(int k = 0; k < 4; k++) {
			m += mark(dr[k] + i, dc[k] + j, c);
		}

		return m;
	}

	static char color(int x, int y) throws Exception{
		int c = g[x][y] < 0 ? g[x][y] : cl.get(g[x][y]);
		switch(c) {
			case -1: return 'R';
			case -2:  return 'G';
			case -3: return 'B';
		}

		throw new Exception("invlaid color " + c);
	}

	static int remaingingBalls() {
		int b = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(g[i][j] != Integer.MIN_VALUE)
					b++;
			}
		}

		return b;
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