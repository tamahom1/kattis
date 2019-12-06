import java.util.*;
import java.io.*;
class Node{
  int x,y,cost;
  Node(int x,int y,int cost){
    this.x=x;
    this.y=y;
    this.cost=cost;
  }
}
public class A{
  public static int[] ligne={-1,0,0,1};
  public static int[] col = {0,-1,1,0};

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
  public static boolean isPath(int[][]  lab,boolean[][] visited,int i,int j,int n){
    return((i>=0)&&(j>=0)&&(i<n)&&(j<n)&&(lab[i][j]==1)&&(!visited[i][j]));
  }
  public static String bfs(int[][] lab,int i,int j,int x,int y,int n){
    int i1,j1,cost;
    boolean[][] visited=new boolean[lab.length][lab[0].length];
    Queue<Node> q=new ArrayDeque<>();
    visited[i][j]=true;
    q.add(new Node(i,j,0));
    int min_cost=Integer.MAX_VALUE;
    while(!q.isEmpty()){
      Node pos=q.poll();
      i=pos.x;
      j=pos.y;
      cost=pos.cost;
      if(i==x && j==y){
              min_cost=cost;
              break;
      }
      for(int o=0;o<4;o++){
        i1=i+ligne[o];
        j1=j+col[o];
        if(isPath(lab,visited,i1,j1,n)){
          visited[i1][j1]=true;
          q.add(new Node(i1,j1,cost+1));
        }
      }
    }
    return(min_cost==Integer.MAX_VALUE)?"NO":"YES";
  }
     public static void main(String[] args) throws IOException{
       Reader s=new Reader();
       int n=s.nextInt();
       int t=s.nextInt();
       int[][] grid=new int[n][n];
       int k;
       for(int i=0;i<n;i++){
         k=s.nextInt();
         k=k%2;
         for(int j=0;j<n;j++){
         grid[i][j]=k;
       }
    }
    for(int i=0;i<n;i++){
      k=s.nextInt();
      k=k%2;
      for(int j=0;j<n;j++){
        if((grid[j][i]+k)%2==1){
          grid[j][i]=0;
        }
        else{
          grid[j][i]=1;
        }
    }
 }
       for(int i=0;i<t;i++){
         int ra=s.nextInt();
         int ca=s.nextInt();
         int rb=s.nextInt();
         int cb=s.nextInt();
         System.out.println(bfs(grid,ra-1,ca-1,rb-1,cb-1,n));
       }
     }

}
