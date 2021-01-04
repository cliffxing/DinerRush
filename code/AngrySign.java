import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * AngrySign is a greenfoot actor displaying an angry emoticon in a speech bubble. It
 * removes itself from the world after a specified amount of time.
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class AngrySign extends Actor
{
    GreenfootImage image = new GreenfootImage("Angry.png");
    private int time = 300;
    public AngrySign()
    {
        setImage(image);
    }
    public void act() 
    {
        time--;
        if(time == 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
