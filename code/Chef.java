import greenfoot.*;  
/**
 * Chef is a sub class of the super class Employees. The Chefs will receive an order from the
 * cashiers and then proceed to prepare/cook the order. Once the order is complete, the Chefs
 * will bring the order to the cashier. 
 * 
 * 
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class Chef extends Employee
{
    private GreenfootSound sizzle1;
    private int speed = 2;
    private int xPos = 950;
    private StatBar cookingTimer;
    private boolean isAtKitchen = false;
    private boolean isMoveToKitchen = false;
    private int time;
    GreenfootImage blank = new GreenfootImage(1,1);
    private boolean isOrderComplete = false;
    private int customerOrder;
    
    /**
     * Sets the image that the chef begins the simulation with. Initializes
     * the sounds as well as the cooking timer.
     */
    public Chef()
    {
        sizzle1 = new GreenfootSound("sizzle1.wav");
        GreenfootImage image = new GreenfootImage("chefLeft1.png");
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
        cookingTimer = new StatBar (time, this);
    }
    
    /**
     * Returns the status of the order.
     * 
     * @return boolean      true if isOrderComplete is equal to true, otherwise
     *                      false.
     */
    public boolean getIsOrderComplete()
    {
        return isOrderComplete;
    }
    
    public void act() 
    {      
 
    } 
    
    /**
     * Checks for the customer's order.
     * 
     * @param x             food item that the customer ordered.     
     */
    public void checkCustomerOrder(int x)
    {
       x = customerOrder;
    }
    
    /**
     * Adds the cooking timer stat bar into the world.
     */
    public void addedToWorld (World w)
    {
        w.addObject (cookingTimer, getX(), getY());
        cookingTimer.update(time);
    }
    
    /**
     * Sets the amount of time it takes the chef to cook and prepare the food
     * 
     * @param cookTime          the amount of time it takes to prepare the 
     *                          customer's order.
     */
    public void setCookTime (int cookTime)
    {
        time = cookTime;
        int x[] = new int[cookTime];
        for(int i = 0; i<cookTime; i++)
        {
            x[i]=cookTime - i;
        }
        
        cookingTimer.setMaxVal(x);
    }
    
    /**
     * Checks to see if the chef is at the kitchen.
     */
    public void checkKitchen()
    {
        if(getX() == 1150)
        {
            isAtKitchen = true;
        }
        else
        {
            isAtKitchen = false;
        }
    }
    
    /**
     * Checks to see if the chef is at the kitchen and starts the cooking
     * timer. Once the chef has finished cooking, they will move back towards
     * the cashier.
     */
    public void cook()
    {
      checkMeetCashier();
      if(isMoveToKitchen == true)
      {
          moveToKitchen();
      }
      if(isAtKitchen == true)
        {
           time -= 1;
           cookingTimer.update(time);
           sizzle1.play();
        }

      if(time == 0)
      {
          isOrderComplete = true;
          moveToCashier();
          cookingTimer.setImage(blank);
          sizzle1.stop();
      }

    }
    
    /**
     * Checks to see if the chef is at the kitchen. If not, then move the 
     * chef to the kitchen.
     */
    public void moveToKitchen()
    {
      checkKitchen();
      if(isAtKitchen == false)
        {
          move(speed);
          animateRight("chefRight1.png", "chefRight2.png", "chefRight3.png", "chefRight4.png");
          xPos += speed;
        }
      
    }
    
    /**
     * Checks to see if the chef is at the position of the cashier. If not, 
     * move towards the cashier.
     */
    public void moveToCashier()
    {
      if(xPos > 950)
      {
          move(-speed);
          animateLeft("chefLeft1.png", "chefLeft2.png", "chefLeft3.png", "chefLeft4.png");
          xPos -= speed;
      }
      isAtKitchen = false;
      isMoveToKitchen = false;
      
    }

    /**
     * Checks to see if the cashier has met with the chef. If so, the chef
     * gets told to move towards the kitchen.
     */
    public void checkMeetCashier()
    {
        Cashier c = (Cashier)getOneObjectAtOffset((this.getImage().getWidth() / 2) - 10, 0, Cashier.class); //code taken from Mr Cohen's Bug Simulation
        if(c != null)
        {
            isMoveToKitchen = true;
        }
    }
    
}
