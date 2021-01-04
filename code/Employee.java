
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Employee is a greenfoot actor that branches off into two child classes, Cashier and Chef, 
 * that interact with each other in order to prepare food for the customers.
 * 
 * @author Cliff Xing
 * @version 2020
 */
public abstract class Employee extends Actor
{
    private int frameR;
    private int frameL;
    private int speed = 2;
    
    public void act() 
    {
    }    
    
    /**
     * Checks for customer order
     * 
     * @param x         which order the customer ordered
     */
    public abstract void checkCustomerOrder(int x);

    /**
     * Animates the Employees to the left
     * 
     * @param img1      image 1
     * @param img2      image 2
     * @param img3      image 3
     * @param img4      image 4
     */
    public void animateLeft(String img1, String img2, String img3, String img4)
    { 
        if (frameL == 0)
        {
            GreenfootImage image = new GreenfootImage(img1);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameL++;
        }
        else if (frameL == 1)
        {
            GreenfootImage image = new GreenfootImage(img2);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameL++;
        }
        else if (frameL == 2)
        {
            GreenfootImage image = new GreenfootImage(img3);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameL++;
        }
        else if (frameL == 3)
        {
            GreenfootImage image = new GreenfootImage(img4);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameL = 1;
        }

    }

    /**
     * Animates the Employees to the right
     * 
     * @param img1      image 1
     * @param img2      image 2
     * @param img3      image 3
     * @param img4      image 4
     */
    public void animateRight(String img1, String img2, String img3, String img4)
    {
        if (frameR == 0)
        {
            GreenfootImage image = new GreenfootImage(img1);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameR++;
        }
        if (frameR == 1)
        {
            GreenfootImage image = new GreenfootImage(img2);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameR++;
        }
        else if (frameR == 2)
        {
            GreenfootImage image = new GreenfootImage(img3);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameR++;
        }
        else if (frameR == 3)
        {
            GreenfootImage image = new GreenfootImage(img4);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameR=0;
        }

    }

}
