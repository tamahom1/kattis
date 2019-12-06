import java.util.*;
import java.io.*;
public class aboveAverage{
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
  public static void reverse(int[] t) {
      for(int i = 0; i < t.length / 2; i++) {
          int tmp = t[i];
          t[i] = t[t.length - i - 1];
          t[t.length - i - 1] = tmp;
      }
  }

  public static void transpose(int[][] t) {
      for(int i = 0; i < t.length; i++) {
          for(int j = i+1; j < t.length; j++) {
              int tmp = t[i][j];
              t[i][j] = t[j][i];
              t[j][i] = tmp;
          }
      }
  }
  public static int[][] board=new int[4][4];
  public static int boardSize = 4;
  public static int LEFT = 0;
  public static int RIGHT = 2;
  public static int UP = 1;
  public static int DOWN = 3;

   public static int[] newEmptyRow(){
     int[] row=new int[boardSize];
     return row;
   }
   public static int[] slideLeftNoMerge(int[] g){
     int[] nrow=newEmptyRow();
     int ind=0;
     for(int i:g){
       if(i!=0){
         nrow[ind]=i;
         ind++;
       }
     }
     return nrow;
   }
   public static int[] slideLeftAndMerge(int[] g){
     g=slideLeftNoMerge(g);
     int[] newrow=newEmptyRow();
     int i=0,j=0;
     while(i<boardSize-1){
       if(g[i]==g[i+1]){
         newrow[j]=2*g[i];
         g[i+1]=0;
         g=slideLeftNoMerge(g);
         i++;
         j++;
       }
       else{
         newrow[j]=g[i];
         j++;
         i++;
       }
     }
     newrow[3]=g[3];
     return newrow;
   }
  public static void slideBoardLeft(){
    for(int i=0;i<boardSize;i++){
      board[i]=slideLeftAndMerge(board[i]);
    }
  }
  public static void slideBoardRight(){
    for(int i=0;i<boardSize;i++){
      reverse(board[i]);
      board[i]=slideLeftAndMerge(board[i]);
      reverse(board[i]);
    }
  }
  public static void slideBoardUp(){
    transpose(board);
    slideBoardLeft();
    transpose(board);
    }
  public static void slideBoardDown(){
    transpose(board);
    slideBoardRight();
    transpose(board);
  }
  public static void slideBoard(int direction) {
      if(direction==LEFT){
        slideBoardLeft();
      }
      else if(direction==UP){
        slideBoardUp();
      }
      else if(direction==DOWN){
        slideBoardDown();
      }
      else{
        slideBoardRight();
      }
      return;
  }
  public static void main(String[] args) throws IOException{
    Reader s= new Reader();
    for(int i=0;i<4;i++){
      for(int j=0;j<4;j++){
        board[i][j]=s.nextInt();
      }
    }
    int dir=s.nextInt();
    slideBoard(dir);
    for(int[] T:board){
      for(int i:T){
        System.out.print(i+" ");
      }
      System.out.println();
    }
    }
  }
