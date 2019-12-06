import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedOutputStream;
import java.util.Scanner;
import java.util.StringTokenizer;
public class Balanced{
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
  public static int count(String s,char C){
    int cnt=0;
    for(int i=0;i<s.length();i++){
      if(s.charAt(i)==C){
       cnt++;
      }
    }
    return cnt;
  }
  public static int min(int x,int y){
    return x<y?x:y;
  }
  public static int Solve(String s,int n){
    int[] counts=new int[128];
    counts['M']=count(s,'M');
    counts['O']=count(s,'O');
    counts['I']=count(s,'I');
    if(counts['M']==counts['I'] && counts['I']==counts['O']){
      return 0;
    }
    int r=0,l=0,res=s.length();
    while(r<s.length()){
      counts[s.charAt(r)]--;
      r++;
      while(l<=r && counts['M']<=n/3 && counts['O']<=n/3 && counts['I']<=n/3){
        counts[s.charAt(l)]++;
        l++;
        res=min(res,r-l+1);
      }

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
        System.out.println(Solve(p,n));
      }
      else{
        System.out.println("Impossible");
      }
    }
    System.exit(0);
  }

}
