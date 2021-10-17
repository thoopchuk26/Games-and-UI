package play;

public class Tic_Tac_Toe {

    public String[][] board;
    public String recentPlay;
    public int playNum = 0;

    public Tic_Tac_Toe(){
        board = new String[3][3];
        for(int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                board[x][y] = "empty " + x + "," + y;
            }
        }
    }

    public void play(int i, int j, String move){
        playNum++;
        board[i][j] = move;
        recentPlay = move;
    }

    public Boolean isOver(){
        for(int i = 0; i < 3; i++){
            if(board[i][0].equals(board[i][1]) && board[i][1].equals(board[i][2]))
                return true;
        }
        for(int i = 0; i < 3; i++){
            if(board[0][i].equals(board[1][i]) && board[1][i].equals(board[2][i]))
                return true;
        }
        if(board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2]))
            return true;

        if(board[0][2].equals(board[1][1]) && board[1][1].equals(board[2][0]))
            return true;

        return false;
    }



}
