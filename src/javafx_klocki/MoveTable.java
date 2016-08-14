package javafx_klocki;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class MoveTable 
{
    private int tableHeight;
    private int tableWidth;
    private MyRectangle blockArray[][];

    public MoveTable(int tableHeight, int tableWidth, MyRectangle[][] blockArray) 
    {
        this.tableHeight = tableHeight;
        this.tableWidth = tableWidth;
        this.blockArray = blockArray;
    }
    
    public MoveTable(MyRectangle[][] blockArray) 
    {
        this.blockArray = blockArray;
    }
    
    public void moveAllBlocksDown(int level)
    {
        for (int i = tableHeight - 1 - level; i >= 0; i--) 
        {
            for (int j = tableWidth - 1; j >= 0; j--) 
            {
                if (blockArray[j][i].playing) 
                {
                    int currentTabX = blockArray[j][i].getTabX();
                    int currentTabY = blockArray[j][i].getTabY();
                    int newTabY = currentTabY + 1;

                    MyRectangle temp = blockArray[currentTabX][newTabY];

                    temp.setFill(blockArray[j][i].getFill());
                    temp.setStroke(blockArray[j][i].getStroke());
                    temp.setWidth(blockArray[j][i].getWidth());
                    temp.setHeight(blockArray[j][i].getHeight());
                    temp.playing = true;
                    
                    blockArray[j][i].reset();                  
                }
            }
            
        }
    }
    
    public void moveDown(boolean isTableOver, MyRectangle r[])
    {
        
        if (isTableOver)
        {
            
            int[] currentTabX = new int[4];
            int[] currentTabY = new int[4];
            MyRectangle[] tempRectangle = new MyRectangle[4];
            tempRectangle[0] = new MyRectangle(tableWidth, tableWidth, 0, 0);
            tempRectangle[1] = new MyRectangle(tableWidth, tableWidth, 0, 0);
            tempRectangle[2] = new MyRectangle(tableWidth, tableWidth, 0, 0);
            tempRectangle[3] = new MyRectangle(tableWidth, tableWidth, 0, 0);
            
            Position position = new Position(r, blockArray);
            
            if (position.IsNewPositionVerticalFree()) 
            {
                for (int i = r.length - 1; i >= 0; i--) 
                {
                    currentTabX[i] = r[i].getTabX();
                    currentTabY[i] = r[i].getTabY();

                    tempRectangle[i].setFill(r[i].getFill());
                    tempRectangle[i].setStroke(r[i].getStroke());
                    tempRectangle[i].setWidth(r[i].getWidth());
                    tempRectangle[i].setHeight(r[i].getHeight());

                    r[i].reset();
                    
                }

                for (int i = r.length - 1; i >= 0; i--) 
                {
                    MyRectangle temp = blockArray[currentTabX[i]][currentTabY[i] + 1];

                    temp.setFill(tempRectangle[i].getFill());
                    temp.setStroke(tempRectangle[i].getStroke());
                    temp.setWidth(tempRectangle[i].getWidth());
                    temp.setHeight(tempRectangle[i].getHeight());

                    r[i] = temp;
                }
            }  
        }
    
    }
    
    public void moveRight(MyRectangle[] r) 
    {
        Position position = new Position(r, blockArray);
        
        if (position.IsNewPositionHorizontalRightFree()) 
        {
            int[] currentTabX = new int[4];
            int[] currentTabY = new int[4];
            int[] newTabX = new int[4];
            MyRectangle[] temp = new MyRectangle[4];
            Paint color = r[0].getFill();
            
            for (int i = r.length - 1; i >= 0; i--) 
            {
                currentTabX[i] = r[i].getTabX();
                currentTabY[i] = r[i].getTabY();
                newTabX[i] = currentTabX[i] + 1;

                blockArray[currentTabX[i]][currentTabY[i]].reset();

            }

            for (int i = r.length - 1; i >= 0; i--) 
            {
                temp[0] = blockArray[newTabX[i]][currentTabY[i]];
                temp[0].setFill(color);
                temp[0].setStroke(Color.BLACK);
                temp[0].setWidth(20);
                temp[0].setHeight(20);

                r[i] = temp[0];
            }

        }

    }
    
    public void moveLeft(MyRectangle[] r) 
    {
        Position position = new Position(r, blockArray);
        
        if (position.IsNewPositionHorizontalLeftFree()) 
        {
            int[] currentTabX = new int[4];
            int[] currentTabY = new int[4];
            int[] newTabX = new int[4];
            MyRectangle[] temp = new MyRectangle[4];
            Paint color = r[0].getFill();
            
            for (int i = r.length - 1; i >= 0; i--) 
            {
                currentTabX[i] = r[i].getTabX();
                currentTabY[i] = r[i].getTabY();
                newTabX[i] = currentTabX[i] - 1;

                blockArray[currentTabX[i]][currentTabY[i]].reset();

            }

            for (int i = r.length - 1; i >= 0; i--) 
            {
                temp[0] = blockArray[newTabX[i]][currentTabY[i]];
                temp[0].setFill(color);
                temp[0].setStroke(Color.BLACK);
                temp[0].setWidth(20);
                temp[0].setHeight(20);
                r[i] = temp[0];
            }

        }
    }
    
    public void rotateShape(MyRectangle[] r, boolean IsSquare) {
        if (!IsSquare) 
        {
            int currentTabX[] = new int[4];
            int currentTabY[] = new int[4];
            int newTabX[] = new int[4];
            int newTabY[] = new int[4];
            int newTabX0[] = new int[4];
            int newTabY0[] = new int[4];
            Paint color = r[0].getFill();

            int originX = r[0].getTabX();
            int originY = r[0].getTabY();

            try {
                for (int i = 0; i < r.length; i++) {
                    //zapisanie aktualnego położenia bloków
                    currentTabX[i] = r[i].getTabX();
                    currentTabY[i] = r[i].getTabY();

                    //przelozenie punktow do punktu (0,0)
                    newTabX0[i] = currentTabX[i] - originX;
                    newTabY0[i] = currentTabY[i] - originY;
                    newTabY0[i] *= (-1);

                    //zapisanie nowych położen bloków 
                    newTabX[i] = (int) Math.round((newTabX0[i] * cos(PI / 2) - newTabY0[i] * sin(PI / 2)));
                    newTabY[i] = (int) Math.round(newTabX0[i] * sin(PI / 2) + newTabY0[i] * cos(PI / 2));
                    newTabY[i] *= (-1);

                    //przelozenie punktow do starego polozenia
                    newTabX[i] = newTabX[i] + originX;
                    newTabY[i] = newTabY[i] + originY;

                }

                Position position = new Position(r, blockArray);

                if (position.isBlockFree(newTabX, newTabY)) {
                    for (int i = 0; i < r.length; i++) {
                        blockArray[currentTabX[i]][currentTabY[i]].reset();
                    }

                    for (int i = 0; i < r.length; i++) {
                        MyRectangle temp = blockArray[newTabX[i]][newTabY[i]];

                        temp.setFill(color);
                        temp.setStroke(Color.BLACK);
                        temp.setWidth(20);
                        temp.setHeight(20);

                        r[i] = temp;
                    }
                }
            } catch (Exception e) {
                //
            }
        }
    }

}
