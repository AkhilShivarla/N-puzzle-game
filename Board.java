import java.util.*;
class Board implements Comparable<Board>
{
    String[][] board;
    int height;
    int f;
    //function provide the sorting technique for the priority queue
	@Override
	public int compareTo(Board o) {
		if(this.f==o.f)
		{
			return ((this.findManhattan() - o.findManhattan()));
		}
		return this.f-o.f;
	}
    public Board(String[][] board,int height)
    {
        this.board=board;
        this.height=height;
        this.f=findManhattan()+height;
    }

    public int findManhattan()
    {
        int n=board.length;
        int sum=0;
        int[] index=new int[2];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j].equalsIgnoreCase("B")){
                    continue;
                }
                index=findIndex(Integer.parseInt(board[i][j]));
                sum+=(Math.abs(i-index[0])+Math.abs(j-index[1]));
            }
        }
        return sum;
    }
    public int[] findIndex(int value)
    {
        int[] index=new int[2];
        int n=NumberScramble.goal.length;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(board[i][j].equalsIgnoreCase("B")){
                    continue;
                }
                if(NumberScramble.goal[i][j].equals(String.valueOf(value)))
                {
                    index[0]=i;
                    index[1]=j;
                    return index;
                }
            }
        }
        return index;
    }
    public ArrayList<Board> expand(Board b)
    {
        ArrayList<Board> childs=new ArrayList<Board>();
        int n=this.board.length;
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
            {
                //finding Blank space
                if(b.board[i][j].equalsIgnoreCase("B"))
                {
                    //top
                    if(i-1>=0)
                    {
                        //String[][] temp=b.board;
                        String[][] temp=new String[n][n];
                        for(int a=0;a<n;a++){
                            for(int b1=0;b1<n;b1++){
                                temp[a][b1]=b.board[a][b1];
                            }
                        }
                        temp=swap(temp,i,j,i-1,j);
                        Board b_temp=new Board(temp,b.height+1);
                        childs.add(b_temp); 
                    }
                    //right
                    if(j+1<n)
                    {
                        // String[][] temp=b.board;
                        String[][] temp=new String[n][n];
                        for(int a=0;a<n;a++){
                            for(int b1=0;b1<n;b1++){
                                temp[a][b1]=b.board[a][b1];
                            }
                        }
                        temp=swap(temp,i,j,i,j+1);
                        Board b_temp=new Board(temp,b.height+1);
                        childs.add(b_temp);
                    }
                    //down
                    if(i+1<n)
                    {
                        //String[][] temp=b.board;
                        String[][] temp=new String[n][n];
                        for(int a=0;a<n;a++){
                            for(int b1=0;b1<n;b1++){
                                temp[a][b1]=b.board[a][b1];
                            }
                        }
                        temp=swap(temp,i,j,i+1,j);
                        Board b_temp=new Board(temp,b.height+1);
                        childs.add(b_temp);
                    }
                    //left
                    if(j-1>=0)
                    {
                        // String[][] temp=b.board;
                        String[][] temp=new String[n][n];
                        for(int a=0;a<n;a++){
                            for(int b1=0;b1<n;b1++){
                                temp[a][b1]=b.board[a][b1];
                            }
                        }
                        temp=swap(temp,i,j,i,j-1);
                        Board b_temp=new Board(temp,b.height+1);
                        childs.add(b_temp);
                    }
                }
            }
        }
        return childs;
    }
    //swapping arrays
    public String[][] swap(String[][] arr,int i,int j,int i2,int j2)
    {
        String temp=arr[i][j];
        arr[i][j]=arr[i2][j2];
        arr[i2][j2]=temp;
        return arr;
    }
}