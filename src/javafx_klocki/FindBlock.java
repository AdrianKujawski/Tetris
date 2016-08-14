package javafx_klocki;

public class FindBlock 
{
    private MyRectangle[] block;
    
    FindBlock(MyRectangle[] block)
    {
        this.block = block;
    }
    
    public MyRectangle MostLeft()
    {
        double maxLeft = block[0].getX();
        MyRectangle maxRectangle = block[0];
        
        for(int i = 0; i < block.length; i++)
        {
            if(block[i].getX() <= maxLeft)
            {
                maxLeft = block[i].getX();
                maxRectangle = block[i];
            }
        }
        
        return maxRectangle;
    }
    
    public MyRectangle MostLeftDown()
    {
        double maxLeft = block[0].getX();
        double maxDown = block[0].getY();
        MyRectangle maxRectangle = block[0];
        
        for(int i = 0; i < block.length; i++)
        {
            if(block[i].getX() <= maxLeft && block[i].getY() >= maxDown)
            {
                maxLeft = block[i].getX();
                maxDown = block[i].getY();
                maxRectangle = block[i];
            }
        }
        return maxRectangle;
    }
    
    public MyRectangle MostRightDown()
    {
        double maxRight = block[0].getX();
        double maxDown = block[0].getY();
        MyRectangle maxRectangle = block[0];
        
        for(int i = 0; i < block.length; i++)
        {
            if(block[i].getX() >= maxRight && block[i].getY() >= maxDown)
            {
                maxRight = block[i].getX();
                maxDown = block[i].getY();
                maxRectangle = block[i];
            }
        }
        
        return maxRectangle;
    }
    
    public MyRectangle MostRight()
    {
        double maxRight = block[0].getX();
        MyRectangle maxRectangle = block[0];
        
        for(int i = 0; i < block.length; i++)
        {
            if(block[i].getX() >= maxRight)
            {
                maxRight = block[i].getX();
                maxRectangle = block[i];
            }
        }
        
        return maxRectangle;
    }
    
    
}
