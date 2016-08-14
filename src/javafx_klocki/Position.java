package javafx_klocki;


public class Position 
{
    private MyRectangle[] block;
    private MyRectangle blockArray[][];
    
    Position(MyRectangle[] block, MyRectangle blockArray[][])
    {
        this.block = block;
        this.blockArray = blockArray;
    }
    
    public boolean IsNewPositionHorizontalLeftFree()
    {
        boolean isFree = true;
         
        for (int i = block.length - 1; i >= 0; i--) 
        {
            int currentTabX = block[i].getTabX();
            int currentTabY = block[i].getTabY();

            MyRectangle temp = blockArray[currentTabX - 1][currentTabY];

            if (temp.mySelf == false && temp.playing == true) {
                isFree = false;
            }
        }
        
        return isFree;
    }
    
    public boolean IsNewPositionHorizontalRightFree()
    {  
    boolean isFree = true;
         
        for (int i = block.length - 1; i >= 0; i--) 
        {
            int currentTabX = block[i].getTabX();
            int currentTabY = block[i].getTabY();

            MyRectangle temp = blockArray[currentTabX + 1][currentTabY];

            if (temp.mySelf == false && temp.playing == true) {
                isFree = false;
            }
        }
        
            return isFree;
    }
    
    public boolean IsNewPositionVerticalFree()
    {
        boolean isFree = true;

        for (int i = block.length - 1; i >= 0; i--) 
        {
            int currentTabX = block[i].getTabX();
            int currentTabY = block[i].getTabY();

            MyRectangle temp = blockArray[currentTabX][currentTabY + 1];

            if (temp.mySelf == false && temp.playing == true) {
                isFree = false;
            }
        }

        return isFree;

    }
    
    public boolean isBlockFree(int[] X, int[] Y)
    {
        for(int i = 0; i <= 3; i++)
        {
            MyRectangle temp = blockArray[X[i]][Y[i]];
            if(temp.playing == true)
                return false;
        }
        return true;
    }  
}
