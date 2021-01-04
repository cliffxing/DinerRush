import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * DollarSign is a greenfoot actor that moves towards a specified area of the screen 
 * and removes itself from the world once it gets there. 
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class DollarSign extends Actor
{
    /**
     * Creates a new actor with the image of a dollar sign
     */
    public DollarSign()
    {
        setImage("dollar sign.png");
    }
    
    public void act() 
    {
        turnTowards(900,65);
        move(2);
        if(this.getX() >= 900 && this.getY() >= 65)
        {
            getWorld().removeObject(this);
        }
    }    
}
