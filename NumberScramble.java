/*
-Compilation
    javac NumberScramble.java
-Run
    java NumberScramble
-Input requirements: a 2D array represents initial grid
    -input can be given in two ways
        -1.Program defaults Array
        -2.Input through KeyBoard
        

Program flow
	-Takes input from keyboard(initial state of grid)
    -Tthen sort array according to the user requirement(ascending or descending)
    -Initialize the grid with initial values through Constructor and find Manhattan distance for each
    -Main class Constructor is called for expansion and finding of Solution
    -declaring PriorityQueue for storing objects/state, and sort based on low Manhattan distance
    -Arraylist to hold all the expanded state/objects of Grid
    -------------------
    -Adding Initial Grid to PriorityQueue for expansion
    -calls expand method of Board class for generating child states.(left,right,down,top)
    -adding those child states to priorityQueue if it is not visited
    -recurring through the entire priorityQueue until its free;
*/
import java.util.*;
public class NumberScramble
{
     public static String[][] goal;
    public static String[][] ascending={{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","15","B"}};
    public static String[][] descending={{"B","15","14","13"},{"12","11","10","9"},{"8","7","6","5"},{"4","3","2","1"}};
                                        //{{"13","15","14","B"},{"12","11","10","9"},{"8","7","6","5"},{"4","3","2","1"}};
    //priority queue is created for holding all the state objects created.
    public static PriorityQueue<Board> pq=new PriorityQueue<Board>();
    // Array List 'expanded' is created for holding all expanded states information.(final results)
    public static ArrayList<Board> expanded=new ArrayList<Board>();

    public NumberScramble(Board fBoard)
    {
        pq.add(fBoard);
        ArrayList<Board> list=new ArrayList<Board>();
        while(!pq.isEmpty())
        {
            int seen;
            //returns and deletes the first node of the priority queue and store it in 'current' variable.
            Board current=pq.poll();
            //Adds current object to the 'end' list<State> which holds all the expanded nodes
            expanded.add(current);
            if(Arrays.deepEquals(current.board,goal))
            {
                break;
            }
            list=current.expand(current);
            //check whether the state expanded is already visited by verifying in the 'end' array list
            for(Board l:list)
            {
                seen=0;
                for(Board b1:expanded)
                {
                    if(Arrays.deepEquals(l.board,b1.board))
                    {
                        seen=1;
                    }
                }
                if(seen==1)
                    continue;
                pq.add(l);
            }
            //System.out.println("work in process");
        }//while
    }
    public static void main(String ar[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Select your choice of initial Grid:\n1.System default generated\n2.Input through Keyboard");
        try{
            int choice=sc.nextInt();sc.nextLine();
            if(choice==1)
            {                 
                String[][] boardInput={{"1","2","3","4"},{"5","6","7","8"},{"B","10","11","12"},{"13","14","9","15"}};
                                    //{{"1","2","3","4"},{"5","6","7","8"},{"B","10","11","12"},{"13","14","9","15"}};//ascending
                                    //{{"15","9","14","13"},{"12","11","10","B"},{"8","7","6","5"},{"4","3","2","1"}};//descending
                                    //{{"1","2","3","4"},{"5","6","7","8"},{"9","10","11","12"},{"13","14","B","15"}};
                System.out.println("Select choice of ordering in grid\n1.Ascending\t2.Descending");
                choice=sc.nextInt();sc.nextLine();
                if(choice==1){
                    NumberScramble.goal=ascending;
                }
                else if(choice==2){
                    NumberScramble.goal=descending;
                }
                else{
                    System.out.println("Enter either 1 or 2 for ordering");
                return;
                }
                Board b=new Board(boardInput,0);
                new NumberScramble(b);
                for(Board states:expanded) {
                    for (int l = 0;l<boardInput.length;l++)
                    {
                        for (int m=0;m<boardInput.length;m++)
                        {
                            System.out.print(states.board[l][m]+" ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }
            }
            else if(choice==2)
            {
                String[][] boardInput=new String[4][4];
                System.out.println("Enter initial Grid\n");
                for (int i=0;i<boardInput.length;i++)
                {
                    for(int j=0;j<boardInput.length;j++)
                    {
                        boardInput[i][j] = sc.next();
                        if(boardInput[i][j].equalsIgnoreCase("B")){
                            continue;
                        }
                        else{
                            if(boardInput[i][j].length()==0||boardInput[i][j].length()>=3 || (boardInput[i][j].charAt(0)<'1' && boardInput[i][j].charAt(0)!='B') || Integer.parseInt(boardInput[i][j])>15)
                            {
                                System.out.println("Error: Input should be any number between 1 to 15 or a 'B'\nProgram Terminated");
                                return;
                            }
                        }
                    }
                }

                System.out.println("Select choice of ordering in grid\n1.Ascending\t2.Descending");
                choice=sc.nextInt();sc.nextLine();
                if(choice==1){
                    NumberScramble.goal=ascending;
                }
                else if(choice==2){
                    NumberScramble.goal=descending;
                }
                else{
                    System.out.println("Enter either 1 or 2 for ordering");
                return;
                }

                Board b=new Board(boardInput,0);
                new NumberScramble(b);
                for(Board states:expanded) {
                    for (int l = 0;l<boardInput.length;l++)
                    {
                        for (int m=0;m<boardInput.length;m++)
                        {
                            System.out.print(states.board[l][m]+" ");
                        }
                        System.out.print(" ");
                    }
                    System.out.println();
                }

            }
            else{
                System.out.println("Enter either 1 or 2");
                return;
            }
        }
        catch(Exception e){
            System.out.println("Accepts Integers either 1 or 2 only\nProgram Terminated");
        }
    }
}