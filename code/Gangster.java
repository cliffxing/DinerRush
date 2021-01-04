import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The Gangster class is a sub class of Customer, and is programs to act as a customer with 
 * one special feature. The gangster will "bud" the line instead of just waiting patiently
 * behind the rest of the customers. 
 * 
 * @author Clifford Xing
 * @version 2020
 */
public class Gangster extends Customer
{
    GreenfootImage image = new GreenfootImage("gangRight1.png");
    protected boolean hasBudded=false;
    AngrySign a = new AngrySign();

    /**
     * Sets the initial image of the Gangster. Sets the customer type.
     */
    public Gangster()
    {
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
        customerType = 3;

    }

    public void act() 
    {      
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

        if(linePart>2 && hasBudded == false && facingRight==true)
        {
            budLine();
        }

    }   

    /**
     * Sets the gangester to move into the line part in front of him and bud the line.
     */
    public void budLine() 
    {
        getWorld().addObject(a, this.getX() + 150, this.getY() - 60);
        if (linenum==1 )
        {
            setLocation(this.getX() + 100, 230);
            bud1=true;
        }

        if (linenum==2 )
        {
            setLocation(this.getX() + 100, 370);
            bud2=true;

        }

        if (linenum==3 )
        {
            setLocation(this.getX() + 100, 510);
            bud3=true;

        }

        
        if (linenum==4 )
        {
            setLocation(this.getX() + 100, 650);
            bud4=true;

        }
        
        hasBudded = true;
    }

    /**
     * Returns if they budded.
     * 
     * @return      true if hasBudded is equal to true, otherwise false.
     */
    public boolean getHasBudded()
    {
        return hasBudded;
    }
}
