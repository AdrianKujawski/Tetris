package javafx_klocki;

import static java.lang.Math.PI;
import static java.lang.Math.abs;
import static java.lang.Math.ceil;
import static java.lang.Math.cos;
import static java.lang.Math.floor;
import static java.lang.Math.sin;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class GameTable 
{
    private MyRectangle blockArray[][];
    private final int tableWidth = 12;
    private final int tableHeight = 22;
    private Timeline animation;
    boolean end;
    
    public void setAnimation(Timeline animation) {
        this.animation = animation;
    }

    private Pane gamePane;

    public MyRectangle[][] getBlockArray() {
        return blockArray;
    }

    public int getTableWidth() {
        return tableWidth;
    }

    public int getTableHeight() {
        return tableHeight;
    }
    
    public GameTable(Pane gamePane)
    {
        this.end = false;
        this.gamePane = gamePane;
        blockArray = new MyRectangle[tableWidth][tableHeight];
        ArrayInitialization(gamePane);
    }
    
    private void ArrayInitialization(Pane gamePane)
    {
        for(int i = 0; i < tableWidth; i++)
            for(int j = 0; j < tableHeight; j++)
            {
                blockArray[i][j] = new MyRectangle(i,j,i*20, j*20);
                gamePane.getChildren().add(blockArray[i][j]);
            }
    }
     
    public MyRectangle putBlocks(int n, int m, int rectangleSize)
    {
        if(!blockArray[n][m].playing){
        blockArray[n][m].setFill(Color.CADETBLUE);
        blockArray[n][m].setStroke(Color.BLACK);
        blockArray[n][m].setWidth(rectangleSize);
        blockArray[n][m].setHeight(rectangleSize);
        blockArray[n][m].playing = true;
        blockArray[n][m].mySelf = true;
        return blockArray[n][m]; 
        }
        else
        {
           
           
            animation.stop(); 
            if(!end)
            {
            Alert alertNiePoprawny = new Alert(AlertType.INFORMATION);
            alertNiePoprawny.setTitle("KONIEC");
            alertNiePoprawny.setHeaderText("Przegrałeś Naciśnij przycisk RESTART. ");
            alertNiePoprawny.show();
            }
            end = true;
            return new MyRectangle();
        }

    }
    
    public void Falling(MyRectangle[] r) 
    {
        if (IsTableOver(r))
        {
            
            int[] currentTabX = new int[4];
            int[] currentTabY = new int[4];
            MyRectangle[] tempRectangle = new MyRectangle[4];
            
            for(int i = 0; i < 4 ; i++)
                tempRectangle[i] = new MyRectangle();
 
            Position position = new Position(r, blockArray);
            
            
            if (position.IsNewPositionVerticalFree()) 
            {
                for (int i = r.length - 1; i >= 0; i--) 
                {
                    currentTabX[i] = r[i].getTabX();
                    currentTabY[i] = r[i].getTabY();

                    tempRectangle[i].setProperties(r[i]);

                    r[i].reset();
                    
                }

                for (int i = r.length - 1; i >= 0; i--) 
                {
                    MyRectangle temp = blockArray[currentTabX[i]][currentTabY[i] + 1];

                    temp.setProperties(tempRectangle[i]);

                    r[i] = temp;
                }
            } 
            else 
            {
                stopMove(r);
            }
        }
        else
        {
            stopMove(r);
        }
    }
    
    public boolean IsTableOver(MyRectangle[] r)
    {
        FindBlock findBlock = new FindBlock(r);
        double min = findBlock.MostRightDown().getY();
        double max = findBlock.MostLeftDown().getY();
        
        if(min < gamePane.getHeight()-20 && max < gamePane.getHeight()-20)
            return true;
        else
            return false;
    }
    
    public void stopMove(MyRectangle[] r)
    {
        animation.stop();
        
        for (int i = 0; i < r.length; i++) 
        {
            int currentTabX = r[i].getTabX();
            int currentTabY = r[i].getTabY();
            
            blockArray[currentTabX][currentTabY].playing = true;
        }
        
        for (int i = 0; i < r.length; i++) {
            r[i] = null;
        }
        
        IsFullStep();
        if(!end)
        {
        Blocks b = new Blocks(gamePane, this);
        b.randomShape();
        b.start();
    }}
       
    public void IsFullStep()
    {
        int level = 0;
        int countBlocks;
        for(int i = tableHeight-1; i >= 0; i--)
        {
            countBlocks = 0;
            for(int j = tableWidth-1; j >= 0; j--)
            {
                if(blockArray[j][i].playing)
                {
                    countBlocks++;
                }
                
                if(countBlocks == tableWidth)
                {
                    level = tableHeight - i - 1;
                    
                    for(int x = j; x <= 11; x++)
                    {
                        blockArray[x][i].reset();
                    }
                    
                   MoveTable moveTable = new MoveTable(tableHeight, tableWidth, blockArray);
                   moveTable.moveAllBlocksDown(level);
                   i++;
                }
                    
            }
        }  
    } 
    
}

