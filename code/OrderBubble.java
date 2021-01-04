import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * OrderBubble is a greenfoot actor displaying restaurant orders (apple, egg, fries 
 * and burger).
 * 
 * @author Cliff Xing
 * @version November 2020
 */
public class OrderBubble extends Actor
{

    GreenfootImage image = new GreenfootImage("apple speech bubble.png");
    private String order1;
    private String order2;

    /**
     * Creates a new OrderBubble. The images displayed are based on the time of day for
     * MyWorld. 
     */
    public OrderBubble()
    {
        image.scale(image.getWidth() , image.getHeight() );
        setImage(image);
        if(MyWorld.isBreakfast)
        {
            order1 = "apple speech bubble.png";
            order2 = "egg speech bubble.png";
        }
        else
        {
            order1 = "fries speech bubble.png";
            order2 = "burger speech bubble.png";
        }

    }

    /**
     * Sets the image based on what is ordered by the customer 
     * 
     * @param x The number representing what the customer ordered (1 or 2)
     */
    public void setImage(int x)
    {
        if (x==1)
        {
            GreenfootImage image = new GreenfootImage(order1);
            setImage(image);
        }

        else if (x==2)
        {
            GreenfootImage image = new GreenfootImage(order2);
            setImage(image);
        }

    }

}
