import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class Main extends Application {

    //Resources
    private Image catImg;
    private Image boardImg;
    private Image blankImg;

    //Fields
    Player human = new Player(PlayerType.HUMAN);
    Player cat = new Player(PlayerType.COMPUTER);
    ArrayList<ImageView> gameboard;
    boolean cpuFirst;
    boolean isRunning;

    //Layout Elements
    AnchorPane pane;
    GridPane grid;

    @Override
    public void start(Stage stage) {
        stage.setTitle("TicTacToe");
        loadResources();
        makeLayout();
        openCurtains(stage, pane);
        //Game Loop

    }

    public static void main(String[] args) {
        launch(args);
    }

    //UI design methods
    private void makeLayout() {
        //@format
        VBox container = new VBox();
        container.setPadding(new Insets(10));
        container.setSpacing(10);
        container.setAlignment(Pos.CENTER);

        HBox menu = new HBox();
        Button bnNewGame = new Button("New Game");
        bnNewGame.setOnAction(this::handle_bnNewGame);

        Pane spacer = new Pane();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        Label lbScore = new Label("calcWins()");
        menu.getChildren().addAll(bnNewGame,spacer,lbScore);

        StackPane stack = new StackPane();
        makeGrid();
        ImageView boardView = new ImageView(boardImg);
        boardView.setViewport(new Rectangle2D(0,0,300,300));
        stack.getChildren().addAll(boardView,this.grid);

        container.getChildren().addAll(menu,stack);
        //@done

        pane = new AnchorPane();
        pane.getChildren().add(container);
    }

    private void loadResources() {
        this.boardImg = new Image("board.png");
        this.catImg = new Image("cat.png");
        this.blankImg = new Image("blank.png");
    }

    private void makeGrid() {
        this.gameboard = new ArrayList<>();
        this.grid = new GridPane();
        grid.setPrefSize(300, 300);
        grid.setHgap(9);
        grid.setVgap(9);
        grid.setPadding(new Insets(9, 0, 0, 9));
        int x = 0, y = 0;
        for (int i = 0; i < 9; i++) {
            while (x < 3 && y < 3) {
                ImageView view = new ImageView(catImg);
                view.setViewport(new Rectangle2D(0, 0, 88, 88));
                grid.add(view, x, y, 1, 1);
                gameboard.add(view);
                x++;
            }
            x = 0;
            y++;
        }
    }

    private void openCurtains(Stage stage, AnchorPane root) {
        stage.setTitle("TicTacToe");
        Scene scene = new Scene(root, 320, 375);
        stage.setScene(scene);
        stage.show();
    }


    //Gameboard Methods
    private void clearBoard() {
        for (ImageView view : gameboard) {
            view.setImage(blankImg);
        }
    }


    //Event handling methods
    private void handle_bnNewGame(ActionEvent e) {
        getListeners(gameboard);
        System.out.println("New Game button was clicked.");
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("NEW GAME");
//        alert.setHeaderText("Who will go first?");
//        ButtonType bnYou = new ButtonType("You");
//        ButtonType bnCat = new ButtonType("Cat");
//        ButtonType bnRandom = new ButtonType("Random");
//        ButtonType bnCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
//
//        alert.getButtonTypes().setAll(bnYou, bnCat, bnRandom, bnCancel);
//        Optional<ButtonType> result = alert.showAndWait();
//
//        if(result.isPresent()){
//            if (result.get() == bnYou) {
//                cpuFirst=false;
//            }
//            if (result.get() == bnCat) {
//                cpuFirst=true;
//            }
//            if (result.get() == bnRandom) {
//                Random random = new Random();
//                cpuFirst= random.nextBoolean();
//            }

        clearBoard();
        isRunning = true;
    }

    private void getListeners(ArrayList<ImageView> list) {
        for (ImageView view : list) {
            view.setPickOnBounds(true);
            view.setOnMouseClicked(this::handle_playerMark);
        }
        System.out.println("Listeners added!");
        for (ImageView view : gameboard) {
            System.out.println(view.getImage().toString());
        }
    }

    public void handle_playerMark(MouseEvent e) {
        ImageView view = (ImageView) e.getSource();
        if (view.getImage().equals(blankImg)) {
            view.setImage(human.getMark());
        }
        cat.cpuMove();
    }
}
