public class EightQueenPuzzle{
/**********************t620**********************************/
    public static void t620(){
        //Eight Queens Puzzle
        int[] pos = new int[8];
        calPos(0, pos, 92);
    }

    public static void calPos(int index, int[] pos, int resultNum){
        int result=0;              //resultNum, how much result you want to print
        int[] status = new int[8]; //status, store the column number next time to be searched
        int columNum = 0;          //columNum, indicate the current column number
        while(index<8){            //index, which indicate the current row number
            columNum = status[index];
            if(status[index]==8){  //sometimes the number stored in "status" casually be 8, when
                status[index] = 0; //the matching column was 7. When back to this index, should 
                index--;            //go back to last row oncemore.
                continue;
            }
            while(columNum<8){
                pos[index] = columNum;
                if(!canAttackOrNot(index, pos)){
                    status[index] = columNum + 1;  
                    index++;
                    break;
                }
                else
                    columNum++;
            }
            if(columNum==8){       //when all the column had been searched and no one matched,
                status[index] = 0; //go back to last row.
                index--;
            }

            //when get a result, print it and back to the last index
            if(index==8){
                for(int n:pos){
                      System.out.print(n + " ");
                  }
                System.out.println(" ");
                displayPos(pos);
            result++;
            index--;
            if(result==resultNum||result==92)
                break;
            }
        }
    }

    public static boolean canAttackOrNot(int index, int[] pos){
        for(int n=0;n<index;n++){
            if ((pos[index]==pos[n]) 
            || (n+pos[n]==index+pos[index]) 
            || (n-pos[n]==index-pos[index]))
                return true;
        }
        return false;
    }

    public static void displayPos(int[] p){
        for(int n=0;n<p.length;n++){
            for(int s=0;s<8;s++){
                if(p[n]==s){
                    System.out.print("|Q");
                }
                else{
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }

    }


    public static void main(String[] args){
        long startTime=System.nanoTime();
        t620();
        long endTime=System.nanoTime();
        System.out.println("Process running time: " 
                   + (endTime-startTime) + "ns");
    }
}
