package play;

public class Roshambo {

    public int pMove1;
    public int pMove2;
    public String pMoveStr;

    public Roshambo(int x){
        pMove1 = x;
        pMove2 = (int) (Math.random()*3);
        if(pMove2 == 0)
            pMoveStr = "Rock";
        if(pMove2 == 1)
            pMoveStr = "Paper";
        if(pMove2 == 2)
            pMoveStr = "Scissors";
    }

    public Boolean isOver(){
        return pMove2 != pMove1;
    }

    public int winner(){
        if(pMove2 == (pMove1+2)%3)
            return pMove1;
        return pMove2;
    }
}
