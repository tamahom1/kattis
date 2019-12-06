import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.HashMap;
import java.util.Arrays;
import java.util.Map;
class Main{
  static class Reader{
      final private int BUFFER_SIZE = 1 << 16;
      private DataInputStream din;
      private byte[] buffer;
      private int bufferPointer, bytesRead;

      public Reader()
      {
          din = new DataInputStream(System.in);
          buffer = new byte[BUFFER_SIZE];
          bufferPointer = bytesRead = 0;
      }

      public Reader(String file_name) throws IOException
      {
          din = new DataInputStream(new FileInputStream(file_name));
          buffer = new byte[BUFFER_SIZE];
          bufferPointer = bytesRead = 0;
      }

      public String readLine() throws IOException
      {
          byte[] buf = new byte[64]; // line length
          int cnt = 0, c;
          while ((c = read()) != -1)
          {
              if (c == '\n')
                  break;
              buf[cnt++] = (byte) c;
          }
          return new String(buf, 0, cnt);
      }

      public int nextInt() throws IOException
      {
          int ret = 0;
          byte c = read();
          while (c <= ' ')
              c = read();
          boolean neg = (c == '-');
          if (neg)
              c = read();
          do
          {
              ret = ret * 10 + c - '0';
          }  while ((c = read()) >= '0' && c <= '9');

          if (neg)
              return -ret;
          return ret;
      }

      public long nextLong() throws IOException
      {
          long ret = 0;
          byte c = read();
          while (c <= ' ')
              c = read();
          boolean neg = (c == '-');
          if (neg)
              c = read();
          do {
              ret = ret * 10 + c - '0';
          }
          while ((c = read()) >= '0' && c <= '9');
          if (neg)
              return -ret;
          return ret;
      }

      public double nextDouble() throws IOException
      {
          double ret = 0, div = 1;
          byte c = read();
          while (c <= ' ')
              c = read();
          boolean neg = (c == '-');
          if (neg)
              c = read();

          do {
              ret = ret * 10 + c - '0';
          }
          while ((c = read()) >= '0' && c <= '9');

          if (c == '.')
          {
              while ((c = read()) >= '0' && c <= '9')
              {
                  ret += (c - '0') / (div *= 10);
              }
          }

          if (neg)
              return -ret;
          return ret;
      }

      private void fillBuffer() throws IOException
      {
          bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
          if (bytesRead == -1)
              buffer[0] = -1;
      }

      private byte read() throws IOException
      {
          if (bufferPointer == bytesRead)
              fillBuffer();
          return buffer[bufferPointer++];
      }

      public void close() throws IOException
      {
          if (din == null)
              return;
          din.close();
      }
  }
  public static int balancedString(String s,int n) {
        Map<Character, Integer> idxMap = new HashMap<>();
        idxMap.put('M', 0); idxMap.put('O', 1);idxMap.put('I', 2);
        int[] cnt = new int[3];
        Arrays.fill(cnt, -n / 3);

        for(int i = 0; i < n; i++) {
            cnt[idxMap.get(s.charAt(i))]++;
        }

        if(cnt[0] == 0 && cnt[1] == 0 && cnt[2] == 0) {
            return 0;
        }

        int l = 0, r = 0, res = n;
        while(r < n) {
            while(r < n && (cnt[0] > 0 || cnt[1] > 0 || cnt[2] > 0 )) {
                cnt[idxMap.get(s.charAt(r))]--;
                r++;
            }

            while(l <= r && cnt[0] <= 0 && cnt[1] <= 0 && cnt[2] <= 0 ) {
                cnt[idxMap.get(s.charAt(l))]++;
                l++;
            }
            res = Math.min(res, r - l + 1);
        }
        return res;
    }
  public static void main(String[] args) throws IOException{
    Reader s=new Reader();
    int t=s.nextInt();
    for(int i=0;i<t;i++){
      int n=s.nextInt();
      String p=System.console().readLine();
      if(n%3==0){
        System.out.println(balancedString(p,n));
      }
      else{
        System.out.println("Impossible");
      }
    }
    System.exit(0);
  }
}
