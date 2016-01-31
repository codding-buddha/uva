import java.io.*;
import java.util.*;

class BitMap {
	static int charc;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		while(true) {
			String f = in.nextString();
			if(f.equals("#")){
				break;
			}
			
			int s = in.nextInt();
			int e = in.nextInt();
			if(s == 0 || e == 0) {
				out.println(String.format("%s%4d%4d\n", f.equals("B") ? "D" : "B", s, e));
				continue;	
			}
			String input = in.nextString();
			if(f.equals("B")) {
				while(input.length() != s*e) {
					input += in.nextString();
				}
			}
			
			out.println(String.format("%s%4d%4d", f.equals("B") ? "D" : "B", s, e));
			String result = f.equals("B") ? convertToD(input, s, e) : converToB(input, s, e);
			out.print(result.endsWith("\n") ? result : (result + "\n"));
		}
		
		out.flush();
		out.close();
	}
	
	static String converToB(String input, int s, int e) {
		int[][] a = new int[s][e];
		convertB(input, new int[] {-1}, 0, s-1, 0, e-1, a);
		charc = 0;
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s; i++) {
			for(int j = 0; j < e; j++) {
				append(sb, a[i][j] == 0 ? '0' : '1');
			}
		}
		return sb.toString();
	}

	static String convertToD(String input, int s, int e) {
		int[][] a = new int[s][e];
		int k = 0;
		for(int i = 0; i < s; i++) {
			for(int j = 0; j < e; j++) {
				a[i][j] = input.charAt(k++) - '0';
			}
		}
		charc = 0;
		StringBuilder sb = new StringBuilder();
		convertD(a, 0, s-1, 0, e-1, sb);
		return sb.toString();
	}

	static void convertB(String input, int[] indx,  int r0, int r1, int c0, int c1, int[][] a) {
		if(indx[0] >= input.length() || (r0 > r1) || (c0 > c1))
			return;
		indx[0] = indx[0]+1;
		char c = input.charAt(indx[0]);
		if(c != 'D') {
			for(int i = r0; i <= r1; i++) {
				for(int j= c0; j <= c1; j++) {
					a[i][j] = c-'0'; 
				}
			}
		} else {
			int sr = (r1-r0)+1;
			int sc = (c1-c0)+1;
			int tr = r0 + (int)Math.ceil(sr/2);
			int tc = c0 + (int)Math.ceil(sc/2);
			tr -= sr%2==0 ? 1: 0;
			tc -= sc%2==0 ? 1: 0;
			convertB(input, indx, r0, tr, c0, tc, a);
			convertB(input, indx, r0, tr, tc+1, c1, a);
			convertB(input, indx, tr+1, r1, c0, tc, a);
			convertB(input, indx, tr+1, r1, tc+1, c1, a);
		}
	}
	
	static void append(StringBuilder sb, char c) {
		sb.append(c);
		charc++;
		if(charc == 50){
			charc = 0;
			sb.append("\n");
		}
	}

	static void convertD(int[][] a, int r0, int r1, int c0, int c1, StringBuilder sb) {
		if(r0 > r1 || c0 > c1)
			return;

		int cnt0 = 0, cnt1 = 0;
		for(int i = r0; i <= r1; i++) {
			for(int j = c0; j <= c1; j++) {
				if(a[i][j] == 1)
					cnt1++;
				else
					cnt0++;
				if(cnt0 > 0 && cnt1 > 0) {
					int sr = (r1-r0)+1;
					int sc = (c1-c0)+1;
					int tr = r0 + (int)Math.ceil(sr/2);
					int tc = c0 + (int)Math.ceil(sc/2);

					tr -= sr%2==0 ? 1: 0;
					tc -= sc%2==0 ? 1: 0;
					append(sb, 'D'); 
					convertD(a, r0,  tr, c0, tc, sb);
					convertD(a, r0, tr, tc+1, c1, sb);
					convertD(a, tr+1, r1, c0, tc, sb);
					convertD(a, tr+1, r1, tc+1, c1, sb);
					return;
				}
			}
		}

		append(sb, cnt0 > 0 ? '0' : '1');
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