package play;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.ArrayList;
import java.util.Random;


public class GameChoice {

    @FXML
    private TextField name_enter;

    @FXML
    private TextField score;

    @FXML
    private TextField name;

    @FXML
    private Pane GameSpace;

    private Boolean playerTurn = true;

    private int scoreCount = 0;

    private ArrayList<Ball> juggling;

    private Movement clock;

    private class Movement extends AnimationTimer {

        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;

        private long last = 0;
        private ArrayList<Ball> bs;

        public void setBalls(ArrayList<Ball> bs) {
            this.bs = bs;
        }

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                for (Ball b : bs) {
                    b.move();
                    b.draw();
                }
                last = now;
            }
        }
    }


    public void animate() {
        juggling = new ArrayList<Ball>();

        for (int i = 0; i < 15; i++) {
            makeCircle();
        }

        clock = new Movement();
        clock.setBalls(juggling);
        clock.start();
    }

    @FXML
    public void ticCreate(){
        name.setText("Name: Guest");
        GameSpace.getChildren().clear();
        GameSpace.setDisable(false);
        playerTurn = true;
        Tic_Tac_Toe tic = new Tic_Tac_Toe();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                Rectangle space = new Rectangle(150, 100);
                Text move = new Text();
                space.setFill(Color.WHITE);
                space.setStroke(Color.BLACK);
                move.setTranslateX(j * 150 + 70);
                move.setTranslateY(i * 100 + 50);
                move.setScaleX(6);
                move.setScaleY(6);
                space.setTranslateX(j * 150);
                space.setTranslateY(i * 100);
                int finalI = i;
                int finalJ = j;
                space.setOnMouseClicked(event -> {space.setDisable(true);ticPlay(move, tic, finalI, finalJ);});
                GameSpace.getChildren().addAll(space, move);
            }
        }
    }

    @FXML
    public void ticPlay(Text move, Tic_Tac_Toe tic, int i, int j){
        if(playerTurn) {
            move.setText("X");
            tic.play(i,j,"X");
        } else {
            move.setText("O");
            tic.play(i,j,"O");
        }
        playerTurn = !playerTurn;
        Text winner = new Text();
        winner.setTranslateX(200);
        winner.setTranslateY(150);
        winner.setScaleY(4);
        winner.setScaleX(4);
        winner.setTranslateX(175);
        winner.setTranslateY(350);
        if(tic.isOver()){
            if(tic.recentPlay.equals("X")) {
                winner.setText("Winner is: " + name.getText().substring(6));
                scoreCount += 100;
                score.setText("Score: " + scoreCount);
            }
            else
                winner.setText(("Winner is: Player 2"));
            GameSpace.getChildren().addAll(winner);
            GameSpace.setDisable(true);
            animate();
        }
        else if(tic.playNum == 9 && !tic.isOver()){
            GameSpace.getChildren().addAll(winner);
            winner.setText("Winner is: Cat");
            GameSpace.setDisable(true);
        }
    }

    @FXML
    public void roshamboCreate(){
        name.setText("Name: Guest");
        GameSpace.setDisable(false);
        GameSpace.getChildren().clear();
        Text winner = new Text();
        Text state = new Text();
        state.setTranslateY(250);
        state.setTranslateX(200);
        state.setScaleY(2.5);
        state.setScaleX(2.5);
        winner.setTranslateX(200);
        winner.setTranslateY(300);
        winner.setScaleY(3);
        winner.setScaleX(3);
        for(int i = 0; i < 3; i++){
            Rectangle space = new Rectangle(150, 150);
            Text move = new Text();
            if(i == 0)
                move.setText("Rock");
            else if(i == 1)
                move.setText("Paper");
            else
                move.setText("Scissors");
            move.setTranslateY(150);
            move.setTranslateX(i * 150 + 60);
            move.setScaleX(3);
            move.setScaleY(3);
            space.setOnMouseClicked(event -> { roshamboPlay(move.getText(), winner, state);});
            move.setOnMouseClicked(event -> { roshamboPlay(move.getText(), winner, state);});
            space.setFill(Color.WHITE);
            space.setStroke(Color.BLACK);
            space.setTranslateX(i * 150);
            space.setTranslateY(75);
            GameSpace.getChildren().addAll(space, move);
        }
        GameSpace.getChildren().addAll(winner, state);
    }

    @FXML
    public void roshamboPlay(String move, Text winner, Text state){
        Roshambo play;
        if(move.equals("Rock"))
            play = new Roshambo(0);
        else if(move.equals("Paper"))
            play = new Roshambo(1);
        else
            play = new Roshambo(2);
        state.setText(move + " vs. " + play.pMoveStr);
        if(play.isOver()){
            if(play.pMove1 == play.winner()) {
                winner.setText("Winner is: " + name.getText().substring(6));
                scoreCount += 15;
                score.setText("Score: " + scoreCount);
                animate();
            }
            else
                winner.setText("Winner is: AI");
            GameSpace.setDisable(true);
        }
        else{
            winner.setText("Play Again");
        }
    }

    @FXML
    public void connect4(){
        GameSpace.getChildren().clear();

    }

    @FXML
    public void name_change(){
        if(name_enter.getText().equals(""))
            name.setText("Name: Guest");
        else
            name.setText("Name: " + name_enter.getText());
    }


    public void makeCircle(){
        Random rand = new Random();
        Circle c = new Circle();
        c.setFill(Color.rgb(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255)));
        c.setStroke(Color.BLACK);
        Ball ball = new Ball(25, c, GameSpace);
        ball.setX(200);
        ball.setY(200);
        juggling.add(ball);
        GameSpace.getChildren().add(c);
    }
}
