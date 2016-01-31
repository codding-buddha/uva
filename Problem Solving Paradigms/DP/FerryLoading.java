import java.io.*;
import java.util.*;

class FerryLoading {
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int tc = in.nextInt();
		while(tc-- > 0) {
			int L = in.nextInt()*100;
			int v = 0;
			boolean[][] lookup = new boolean[205][L+5];
			int[][] selection = new int[205][L+5];
			int[] a = new int[205];
			int[] sum = new int[205];
			int indx = 1;
			int  n = 1;
			while((v= in.nextInt()) != 0){
				sum[indx] = v;
				sum[indx] += indx > 0 ? sum[indx-1] : 0;
				if(sum[indx] > 2*L){
					indx++;
					continue;
				}

				indx++;
				a[n++] = v;
			}

			int len = 0;
			int laslen = 0;
			lookup[0][0] = true;

			for(int i = 1; i < n; i++) {
				boolean canload = false;
				for(int l = 0; l <= L; l++) {
					if(!lookup[i-1][l])
						continue;

					if(l+a[i] <= L) {
						canload = true;
						len = i;
						lookup[i][l+a[i]] = true;
						selection[i][l+a[i]] = 1;
						laslen = l+a[i];
					}

					if((sum[i-1] - l) + a[i] <= L) {
						canload = true;
						len = i;
						lookup[i][l] = true;
						selection[i][l] = 2;
						laslen = l;
					}
				}

				if(!canload)
					break;
			}

			out.println(len);
			String[] answers = new String[len];
			indx = len-1;
			for(int i= len; i >= 0; i--) {
				if(selection[i][laslen] == 1) {
					laslen = laslen - a[i];
					answers[indx--] = "port";
				}else if(selection[i][laslen] == 2){
					 answers[indx--] =  "starboard";
				}
			}

			for(int i = 0; i < answers.length; i++)
				out.println(answers[i]);
			if(tc > 0)
				out.println();

		}	
		
		out.flush();
		out.close();
	}

	static int[] toArray(List<Integer> lst) {
		int[] arr = new int[lst.size()+1];
		for(int i = 1; i < arr.length; i++)
			arr[i] = lst.get(i-1);

		return arr;
	}

	// static int load(int c, int l) {
	// 	if(c >= n)
	// 		return 0;

	// 	if(lookup[c][l] != 0) {
	// 		return lookup[c][l] < 0 ? 0 : lookup[c][l];
	// 	}

	// 	int r = L - (sum[c] - (L-l));
	// 	if(c == n-1) {
	// 		if(a[c] <= l) {
	// 			lookup[c][l] = 1;
	// 			return 1;
	// 		} else if(a[c] <= r) {
	// 			lookup[c][r] = 1;
	// 			selection[c][l] = 1;
	// 			return 1;
	// 		} else {
	// 			return 0;
	// 		}
	// 	}

	// 	int opt1 = 0, opt2 = 0, opt3 = 0;
	// 	if(a[c] <= l){
	// 		opt1 = 1 + load(c+1, l-a[c]);
	// 	}
	// 	if(a[c] <= r){
	// 		opt2 = 1 + load(c+1, l);
	// 	}

	// 	if(opt2 > opt1) {
	// 		selection[c][l] = 1;
	// 	}

	// 	lookup[c][l] = Math.max(opt1, opt2);

	// 	if(lookup[c][l] == 0) {
	// 		lookup[c][l] = -1;
	// 	}

	// 	return lookup[c][l] < 0 ? 0 : lookup[c][l];
	// }

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