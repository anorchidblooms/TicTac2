import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Player {
    private int score;
    private Image mark;
    private ArrayList<Integer> positions;
    ArrayList<ImageView> gameboard;

    public Player(PlayerType type) {
        Image blankImg = new Image("blank.png");
        assignMark(type);
        this.score = 0;
        this.positions = new ArrayList<>();
    }

    private void assignMark(PlayerType type) {
        if (type == PlayerType.HUMAN) {
            this.mark = new Image("x.png");
        }
        if (type == PlayerType.COMPUTER) {
            this.mark = new Image("o.png");
        }
    }

    public void cpuMove() {
        if (mark == null) {
            System.out.println("My mark is null.");
        }
        gameboard.get(0).setImage(mark);
    }

    //Getters & Setters
    public Image getMark() {
        return mark;
    }

    public void setMark(Image mark) {
        this.mark = mark;
    }

    public ArrayList<Integer> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Integer> positions) {
        this.positions = positions;
    }
}
