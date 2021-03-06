import java.util.*;
import java.io.*;

class FrequentValues {
    static Data[] tree;
    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);
        while(true) {
            int n = in.nextInt();
            if(n == 0) {
                break;
            }
            int q = in.nextInt();
            int[] d = new int[n];
            for(int i = 0; i < n; i++) {
                d[i] = in.nextInt();
            }
            tree = new Data[4*n + 4];
            init(1, d, 0, d.length-1);
            while(q-- > 0) {
                out.println(query(d, in.nextInt()-1, in.nextInt()-1));
            }
        }        
        out.flush();
        out.close();
    }

    static int query(int[] a, int u, int v) {
        return query(1, a, 0, a.length-1, u, v);
    }
    //query range u, v
    static int query(int node, int[] a, int i, int j, int u, int v) {
        if(u > j || v < i)
            return 0;
        if(u == i && v == j) {
            return tree[node].mf;
        }

        int mid = i + ((j-i)>>1);
        if(v <= mid)
            return query(node<<1, a, i, mid, u, v);
        if(u > mid)
            return query((node<<1)+1, a, mid+1, j, u, v);
        int q1 = query(node<<1, a, i, mid, u, mid);
        int q2 = query((node<<1) + 1, a, mid + 1, j, mid+1, v);
        if(a[mid] == a[mid+1]) {
            int temp = min(tree[node<<1].rf, mid-u+1) + min(tree[(node<<1)+1].lf, v-mid);
            return max(temp, max(q1, q2));
        } else {
            return max(q1, q2);
        }
    }

    static void init(int node, int[] a, int i, int j) {
        if(i == j) {
            tree[node] = new Data(1, 1, 1);
            return;
        }
        int mid = i + ((j-i)>>1);
        init(node<<1, a, i, mid);
        init((node<<1)+1, a, mid + 1, j);
        if(a[mid] == a[mid+1]) {
            tree[node] = new Data(0, 0, 0);
            tree[node].lf =  tree[node<<1].lf +  (a[i] == a[mid] ? tree[(node<<1)+1].lf : 0);
            tree[node].rf = tree[(node<<1) + 1].rf + (a[mid+1] == a[j] ? tree[(node<<1)].rf  : 0);
            tree[node].mf = max(tree[node<<1].rf+ tree[(node<<1)+1].lf, max(tree[node<<1].mf, tree[(node<<1)+1].mf));
        } else {
            tree[node] = new Data(tree[node<<1].lf, tree[(node<<1)+1].rf, max(tree[node<<1].mf, tree[(node<<1) + 1].mf));
        }
    }

    static int max(int a, int b) {
        return a > b ? a: b;
    }

    static int min(int a, int b) {
        return a > b ? b : a;
    }

    static class Data {
        public int lf;
        public int rf;
        public int mf;

        public Data(int l , int r, int m) {
            lf = l;
            rf = r;
            mf = m;
        }

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

