import java.io.*;
import java.util.*;

class AncientMessages {
	static int[][] grid;
	static boolean[][] used;
	static int n = 0, m = 0;
	static int cc = 2;
	static int insideCC = -1;
	static int[] dr = new int[] {0, -1, -1, -1, 0, 1, 1, 1};
//	static int[] dr = new int[] {0, -1, 0, 1};
	static int[] dc = new int[] { -1, -1, 0, 1, 1, 1, 0, -1};
//	static int[] dc = new int[] { -1, 0, 1, 0};
	static HashMap<Integer, Integer> whiteCCInsideIcon;
	public static void main(String[] args) {
		InputReader in = new InputReader(System.in);
		OutputWriter out = new OutputWriter(System.out);
		int[] sortedOrder = new int[] {1, 5, 3, 2, 4, 0};
		char[] symbols = new char[] {'A', 'D', 'J', 'K', 'S', 'W'};
		int tc = 1;

		while(true) {
			int h = in.nextInt();
			int w = in.nextInt();
			if(h == 0 || w == 0)
				break;
			n = h;
			m = w*4;
			grid = new int[n][m];
			used = new boolean[n][m];
			for(int r = 0; r < h; r++) {
				String line = in.nextString();
				for(int c = 0; c < w; c++) {
					int v = val(line.charAt(c));
					for(int i = 0; i < 4; i++) {
						grid[r][c*4 + (4-i-1)] = (v & (1<<i)) > 0  ? 1 : 0;
					}
				}
			}

			whiteCCInsideIcon = new HashMap<Integer, Integer>();
			cc = 2;
			

			markBackgroundPixels();
			printGrid();
				
			for(int r = 0; r < n; r++) {
				for(int c = 0; c < m; c++) {
					if(!used[r][c]) {
						int color = grid[r][c];
						insideCC = -1;
						touch(r, c, grid[r][c]);
						if(color == 1) {
							whiteCCInsideIcon.put(cc, 0);
							printGrid();
						} else {
							if(insideCC > 0) {
								if(whiteCCInsideIcon.containsKey(insideCC)) {
									whiteCCInsideIcon.put(insideCC, whiteCCInsideIcon.get(insideCC) + 1);
									printGrid();
								}
							}
						}
						cc++;
					}
				}
			}

			int[] iconCount = new int[6];

			for(Integer key: whiteCCInsideIcon.keySet()) {
				iconCount[whiteCCInsideIcon.get(key)]++;
			}

			out.print(String.format("Case %d: ", tc++));
			for(int i = 0; i < 6; i++) {
				if(iconCount[sortedOrder[i]] > 0) {
					while(iconCount[sortedOrder[i]]-- > 0) {
						out.print(symbols[i]);
					}
				}
			}
			out.println();

		}
		
		out.flush();
		out.close();
	}

	static void printGrid() {
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++)
				System.out.print(String.format("%12d ", grid[i][j]));
			System.out.println();				
		}
		System.out.println("\n\n");
	}

	static void markBackgroundPixels() {
		// for(int i = 0; i < n; i++) {
		// 	boolean leftStop = false;
		// 	boolean rightStop = false;
		// 	for(int j = 0, l = m-1; j <= l;) {
		// 		if(!leftStop) {
		// 			if(grid[i][j] == 0){
		// 				used[i][j] = true;
		// 				grid[i][j] = Integer.MAX_VALUE;
		// 			} else {
		// 				leftStop = true;
		// 			}	
		// 			j++;
		// 		}
				
		// 		if(!rightStop) {
		// 			if(grid[i][l] == 0){
		// 				used[i][l] = true;
		// 				grid[i][l] = Integer.MAX_VALUE;
		// 			} else {
		// 				rightStop = true;
		// 			}	
		// 			l--;
		// 		}
		// 		if(rightStop && leftStop) {
		// 			if(grid[i][j] == 0) {
		// 				for(int k = 0; k < dr.length; k++) {
		// 					int x = i + dr[k];
		// 					int y = j + dc[k];
		// 					if(x < 0 || x >= n || y < 0 || y >= m) {
		// 						continue;
		// 					}
		// 					if(grid[x][y] == Integer.MAX_VALUE) {
		// 						grid[i][j] = Integer.MAX_VALUE;
		// 						used[i][j] = true;
		// 					}
								
		// 				}
		// 			}
		// 			j++;
		// 		}
		// 	}
		// }

		for(int i = 0; i < n; i++) {
			if(grid[i][0] == 0) {
				used[i][0] = true;
				grid[i][0] = Integer.MAX_VALUE;
			}
			if(grid[i][m-1] == 0) {
				used[i][m-1] = true;
				grid[i][m-1] = Integer.MAX_VALUE;
			}
		}

		for(int j = 0; j < m; j++) {
			if(grid[0][j] == 0) {
				used[0][j] = true;
				grid[0][j] = Integer.MAX_VALUE;
			}

			if(grid[n-1][j] == 0) {
				used[n-1][j] = true;
				grid[n-1][j] = Integer.MAX_VALUE;
			}
		}

		int i, k = 0, l = 0;
		int er = n, ec = m;
	 
	    while (k < er && l < ec)
	    {
	        /* Print the first row from the remaining rows */
	        for (i = l; i < ec; ++i)
	        {
	    		if(grid[k][i] == 0) {
	    			adjustValues(k, i);
	    		}
	        }
	        k++;
	 
	        /* Print the last column from the remaining columns */
	        for (i = k; i < er; ++i)
	        {
	            if(grid[i][ec-1] == 0) {
	    			adjustValues(i, ec-1);
	    		}
	        }
	        ec--;
	 
	        /* Print the last row from the remaining rows */
	        if ( k < er)
	        {
	            for (i = n-1; i >= l; --i)
	            {
	            	if(grid[er-1][i] == 0) {
		    			adjustValues(er-1, i);
		    		}
	            }
	            er--;
	        }
	 
	        /* Print the first column from the remaining columns */
	        if (l < ec)
	        {
	            for (i = er-1; i >= k; --i)
	            {
	            	if(grid[i][l] == 0) {
		    			adjustValues(i, l);
		    		}
	            }
	            l++;    
	        }        
	    }

	}

	static void adjustValues(int r, int c) {
		for(int k = 0; k < dr.length; k++) {
			int x = r + dr[k];
			int y = c + dc[k];
			if(x < 0 || x >= n || y < 0 || y >= m) {
				continue;
			}
			if(grid[x][y] == Integer.MAX_VALUE) {
				grid[r][c] = Integer.MAX_VALUE;
				used[r][c] = true;
			}
				
		}
	}

	static void touch(int i, int j, int c) {
		if(i < 0 || i >= n || j < 0 || j >= m) {
			//out of boundary, surely not contained any icon
			insideCC = -2;
			return;
		}

		if(grid[i][j] != c) {
			if(insideCC == -2)
				return;
			insideCC = grid[i][j];
			return;
		}

		if(used[i][j])
			return;

		grid[i][j] = cc;
		used[i][j] = true;
		for(int k = 0; k < dr.length; k++) {
			if(k == 1 || k == 3 || k == 5 || k == 7) {
				if(k == 1 && isValidPosition(i+dr[k], j+dc[k]) && (grid[i][j-1] != Integer.MAX_VALUE || grid[i-1][j] != Integer.MAX_VALUE))
					touch(i + dr[k], j + dc[k], c);
				else if(k == 3 && isValidPosition(i+dr[k], j+dc[k]) && (grid[i-1][j] != Integer.MAX_VALUE || grid[i][j+1] != Integer.MAX_VALUE))
					touch(i + dr[k], j + dc[k], c);
				else if(k == 5 && isValidPosition(i+dr[k], j+dc[k]) && (grid[i][j+1] != Integer.MAX_VALUE || grid[i+1][j] != Integer.MAX_VALUE))
					touch(i + dr[k], j + dc[k], c);
				else if(k == 7 && isValidPosition(i+dr[k], j+dc[k]) && (grid[i][j-1] != Integer.MAX_VALUE || grid[i+1][j] != Integer.MAX_VALUE))
					touch(i + dr[k], j + dc[k], c);

			} else {
				touch(i + dr[k], j + dc[k], c);
			}

		}
	}

	static boolean isValidPosition(int i, int j) {
		return !(i < 0 || i >= n || j < 0 || j >= m);
	}

	static int val(char c) {
		if(c >= 'a' && c <= 'f')
			return 10 + (c - 'a');
		else
			return c - '0';
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
			System.out.print(items[i] + " ");
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