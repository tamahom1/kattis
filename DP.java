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
public class DP{
  public static int[][]chargeLabyrinthe(String nomFichier){
    int[][]labyrinthe={};
    try{
      Scanner sc=new Scanner(new File(nomFichier)).useDelimiter("\n");
      int c=0;
    //On compte le nombre de lignes
      while(sc.hasNext()){
        c=c+1;
        String tmp=sc.next();
      }
      labyrinthe=new int[c][];
      sc=new Scanner(new File(nomFichier)).useDelimiter("\n");
      int i=0;
      while(sc.hasNext()){
        String ligne=sc.next();
        String[] splitLigne=ligne.split(",");
        labyrinthe[i]=new int[splitLigne.length];
        for(int j=0;j<splitLigne.length;j=j+1){
          labyrinthe[i][j]=Integer.parseInt(splitLigne[j]);
        }
        i=i+1;
      }

    }
    catch(Exception e){
      System.out.println("Probleme dans la lecture du fichier "+nomFichier);
      e.printStackTrace();
    }
    return labyrinthe;
    }
  public static int[] ligne={-1,0,0,1};
  public static int[] col = {0,-1,1,0};
  public static boolean isPath(int[][]  lab,boolean[][] visited,int i,int j){
    return((i>=0)&&(j>=0)&&(i<lab.length)&&(j<lab[0].length)&&(lab[i][j]==1)&&(!visited[i][j]));
  }
  public static int bfs(int[][] lab){
    int i=0,j=0,x=lab.length-1,y=lab[0].length-1,i1,j1,cost;
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
        if(isPath(lab,visited,i1,j1)){
          visited[i1][j1]=true;
          q.add(new Node(i1,j1,cost+1));
        }
      }
    }
    return(min_cost==Integer.MAX_VALUE)?-1:min_cost+2;
  }
  public static void main(String[] args){
     int[][]lab=chargeLabyrinthe("labyrinthe1.csv");
     System.out.print(bfs(lab));
   }
}
