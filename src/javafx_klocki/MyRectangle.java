/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx_klocki;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Adrian
 */
public class MyRectangle extends Rectangle
{
    private int tabX;
    private int tabY;
    public boolean playing;
    public boolean mySelf;
    
    public MyRectangle()
    {
        playing = true;
        mySelf = true;
    }
    
    public MyRectangle(int tabX, int tabY, double x, double y) 
    {
        setX(x);
        setY(y);
        this.tabX = tabX;
        this.tabY = tabY;
        playing = false;
        mySelf = false;
    }
    
    public void reset()
    {
        setFill(null);
        setStroke(null);
        setWidth(0);
        setHeight(0);
        playing = false;
        mySelf = false;
    }
    
    public int getTabX() {
        return tabX;
    }

    public void setTabX(int tabX) {
        this.tabX = tabX;
    }

    public int getTabY() {
        return tabY;
    }

    public void setTabY(int tabY) {
        this.tabY = tabY;
    }
    
    public void setProperties(MyRectangle r)
    {
        this.setFill(r.getFill());
        this.setStroke(r.getStroke());
        this.setWidth(r.getWidth());
        this.setHeight(r.getHeight());
    }
}
