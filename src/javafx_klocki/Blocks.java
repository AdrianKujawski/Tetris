package javafx_klocki;

import java.util.Random;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Blocks {

    Timeline timeline1;
    private MyRectangle[] boxes;
    private final int startLocationX = 5;
    private final int rectangleSize;
    private final Pane gamePane;
    private final GameTable gameTable;
    private MyRectangle r;
    private boolean isSquare;
    
    public Blocks(Pane gamePane, GameTable gt) 
    {
        gameTable = gt;
        this.gamePane = gamePane;
        this.rectangleSize = 20;
        boxes = new MyRectangle[4];
        initializationKeyEvent();
    }

    public boolean getIsSquare() {
        return isSquare;
    }

    public void setIsSquare(boolean isSquare) {
        this.isSquare = isSquare;
    }
    
    
    public void randomShape() {
        Random rand = new Random();
        int i = rand.nextInt(6);

        switch (i) {
            case 0:
                createIShape();
                break;
            case 1:
                createOShape();
                break;
            case 2:
                createJShape();
                break;
            case 3:
                createLShape();
                break;
            case 4:
                createSShape();
                break;
            case 5:
                createTShape();
                break;
            case 6:
                createZShape();
                break;
        }
    }

    public void createIShape() {
        Color rColor = Color.AQUA;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX + 2, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 3, 0, rectangleSize);

        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void createOShape() {
        Color rColor = Color.FUCHSIA;

        boxes[0] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX, 1, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 1, 1, rectangleSize);

        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(true);
    }

    public void createJShape() {
        Color rColor = Color.LIME;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX + 2, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 2, 1, rectangleSize);

        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void createLShape() {
        Color rColor = Color.LIGHTSKYBLUE;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX + 2, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX, 1, rectangleSize);
        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void createSShape() {
        Color rColor = Color.PINK;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 1, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX, 1, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 2, 0, rectangleSize);
        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void createTShape() {
        Color rColor = Color.WHEAT;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX + 2, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 1, 1, rectangleSize);
        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void createZShape() {
        Color rColor = Color.GHOSTWHITE;

        boxes[0] = gameTable.putBlocks(startLocationX + 1, 1, rectangleSize);
        boxes[1] = gameTable.putBlocks(startLocationX, 0, rectangleSize);
        boxes[2] = gameTable.putBlocks(startLocationX + 1, 0, rectangleSize);
        boxes[3] = gameTable.putBlocks(startLocationX + 2, 1, rectangleSize);
        for (int i = 0; i <= boxes.length - 1; i++) {
            boxes[i].setFill(rColor);
        }

        setIsSquare(false);
    }

    public void start() {
        
        
        timeline1 = new Timeline(new KeyFrame(
                Duration.millis(500),
                ae -> gameTable.Falling(boxes)));

        timeline1.setCycleCount(Animation.INDEFINITE);
        timeline1.play();
        gameTable.setAnimation(timeline1);

    }

    private void initializationKeyEvent() {
        gamePane.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.RIGHT && !gameTable.end) 
                {
                    FindBlock findBlock = new FindBlock(boxes);
                    double min = findBlock.MostRight().getX();
                    
                    if (min + 2 * rectangleSize <= gamePane.getWidth()) {
                        MoveTable moveTable = new MoveTable(gameTable.getBlockArray());
                        moveTable.moveRight(boxes);
                    }

                }

                if (event.getCode() == KeyCode.LEFT && !gameTable.end) 
                {
                    FindBlock findBlock = new FindBlock(boxes);
                    double max = findBlock.MostLeft().getX();
                    
                    if (max - rectangleSize >= gamePane.getMinWidth()) {
                        MoveTable moveTable = new MoveTable(gameTable.getBlockArray());
                        moveTable.moveLeft(boxes);
                    }
                }
                if (event.getCode() == KeyCode.DOWN && !gameTable.end) 
                {
                    MoveTable moveTable = new MoveTable(gameTable.getBlockArray());
                    moveTable.moveDown(gameTable.IsTableOver(boxes),boxes);
                }

                if (event.getCode() == KeyCode.UP && !gameTable.end) 
                {
                    MoveTable moveTable = new MoveTable(gameTable.getBlockArray());
                    moveTable.rotateShape(boxes,getIsSquare());
                }
            }
        });
    }
}
