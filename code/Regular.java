import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Regular here.
 * 
 * @author Clifford Xing
 * @version Novemeber 2020
 */
public class Regular extends Customer
{
    GreenfootImage image = new GreenfootImage("manRight1.png");

    public Regular()
    {
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
        customerType = 1;
    }
    
    public void act() 
    {      
        checkBudded();
        talkingSound();
        checkFull();
        if (linePart>1)
        {
            moveUpLine();
        }

        
        orderFood(); 

        if (atLine==false)
        {
            checkLine();
        }

        if (getWaiting()==true)
        {
            checkDone();
        }

        
    }   
}
