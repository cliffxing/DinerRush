import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Cashier is a sub class of Employees and takes the order from the customers. They then 
 * deliver the order to the chefs for them to cook. Once the order is completed, the cashiers
 * take the completed order to the customers and serves them.
 * 
 * 
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class Cashier extends Employee
{
    private int frameR;
    private int frameL;

    private int xPos = 750;

    private int speed = 2;

    private boolean checkOrder = true;

    private boolean orderComplete = false;

    public boolean isMeetChef = false;

    private boolean receivedOrder = false;

    public static int customerOrder;
    private boolean go1 = false;
    private int meetingWithChef = 0;
    private int testing;

    private boolean readyForOrder=true;

    private static boolean readyForOrder1=true;
    private static boolean readyForOrder2=true;
    private static boolean readyForOrder3=true;
    private static boolean readyForOrder4=true;

    private static boolean orderDone1=false;
    private static boolean orderDone2=false;
    private static boolean orderDone3=false;
    private static boolean orderDone4=false;

    private GreenfootSound makeMoney;

    /**
     * Sets the starting image of the cashiers. Sets the sound that will play
     * when the cashiers deliver an order to the customers.
     */
    public Cashier()
    {
        makeMoney = new GreenfootSound("makeMoney.wav");
        GreenfootImage image = new GreenfootImage("cashierLeft1.png");
        image.scale(image.getWidth() / 2, image.getHeight() / 2);
        setImage(image);
    }

    /**
     * Checks to see if the cashier is ready to take an order from the customers.
     */
    public void updateLanes()
    {
        if(this.getY()==235)
        {
            readyForOrder1=readyForOrder;
        }

        else if (this.getY()==385)
        {
            readyForOrder2=readyForOrder;
        }

        else if (this.getY()==536)
        {
            readyForOrder3=readyForOrder;
        }

        else if (this.getY()==665)
        {
            readyForOrder4=readyForOrder;
        }
    }

    /**
     * Checks to see if the cashier in each lane are ready to receive an order
     * from the customers
     * 
     * @param lane      which lane the cashier is in.
     * @return boolean  true if readyForOrder1 is equal to true, otherwise
     *                  false.
     * @return boolean  true if readyForOrder2 is equal to true, otherwise
     *                  false.
     * @return boolean  true if readyForOrder3 is equal to true, otherwise
     *                  false.
     * @return boolean  true if readyForOrder4 is equal to true, otherwise
     *                  false.
     */
    public static boolean getReadyOrder(int lane)
    {
        if (lane==1)
        {
            return readyForOrder1;
        }

        else if (lane==2)
        {
            return readyForOrder2;
        }

        else if (lane==3)
        {
            return readyForOrder3;
        }

        else
        {
            return readyForOrder4;
        }
    }

    /**
     * Resets the variables so cashiers can take the next customer's order.
     */
    public static void resetScenario()
    {
        orderDone1=false;
        orderDone2=false;
        orderDone3=false;
        orderDone4=false;

    }

    public void act() 
    {  
        updateLanes();
        if(go1 == true)
        {
            moveToChef();
            moveToRegister();
        }
    }    

    /**
     * Resets the variables to the values the simulation started with.
     */
    public void reset()
    {
        xPos = 750;
        speed = 2;
        checkOrder = true;
        orderComplete = false;
        isMeetChef = false;
        receivedOrder = false;
        customerOrder = 0;
        go1 = false;
        meetingWithChef = 0;
    }

    /**
     * Checks for which food item the customer orders.
     * 
     * @param x             food item that the customer ordered.
     */
    public void checkCustomerOrder(int x)
    {
        if(x != 0)
        {
            checkOrder = true;
        }
        customerOrder = x;
    }

    /**
     * Returns the order of the customer.
     * 
     * @return int          1 if the customer ordered an apple during breakfast,
     *                      2 if the customer ordered an egg during breakfast,
     *                      1 if the customer ordered fries during lunch, and
     *                      2 if the customer ordered a hamburger during lunch.
     */
    public int getCustomerOrder()
    {
        return customerOrder;
    }

    /**
     * Checks to see if the cashier is elligible to move.
     */
    public void move()
    {
        go1 = true;
    }

    /**
     * Checks to see if the cashier does not have a completed order and is at
     * the register which will tell the cashier to move towards the chef.
     */
    public void moveToChef()
    {
        if(xPos < 950 && orderComplete == false && meetingWithChef < 100)
        {
            readyForOrder=false;
            move(speed);
            xPos += speed;
            animateRight("cashierRight1.png", "cashierRight2.png", "cashierRight3.png", "cashierRight4.png");
        }
        checkOrder=false;
    }

    /**
     * Checks to see if the chef has completed the order and has given it to
     * the cashier. Checks the positon of the cashier so that once the cashier
     * reaches the register and delivers the food to the customer, the 
     * restaurant will make money.
     */
    public void moveToRegister()
    {
        checkOrderComplete();   
        if(receivedOrder == true && xPos > 750)
        {
            move(-speed);
            xPos -= speed;
            animateLeft("cashierLeft1.png", "cashierLeft2.png", "cashierLeft3.png", "cashierLeft4.png");
            if (xPos==750 && orderComplete==true )
            {
                makeMoney.play();
                readyForOrder=true;
                orderDone();
                DollarSign dollar = new DollarSign();
                getWorld().addObject(dollar, getX() - 70 , getY()- 43);
            }
        }    

    }

    /**
     * Sets the the order to complete within each respective line.
     */
    public void orderDone()
    {
        if (this.getY()==235)
        {
            orderDone1=true;
        }

        else if (this.getY()==385)
        {
            orderDone2=true;
        }

        else if (this.getY()==536)
        {
            orderDone3=true;
        }

        else if (this.getY()==665)
        {
            orderDone4=true;
        }
        orderComplete=false;
    }

    /**
     * Returns the boolean that sets if the order is completed within each 
     * respective line.
     * 
     * @param linenum       which line the cashier is in.
     * @return boolean      true if orderDone1 is equal to true, otherwise
     *                      false.
     * @return boolean      true if orderDone2 is equal to true, otherwise
     *                      false.
     * @return boolean      true if orderDone3 is equal to true, otherwise
     *                      false.
     * @return boolean      true if orderDone4 is equal to true, otherwise
     *                      false.
     */
    public static boolean getOrderDone(int linenum)
    {
        if (linenum==1)
        {
            return orderDone1;

        }
        else if (linenum==2)
        {
            return orderDone2;

        }

        else if (linenum==3)
        {
            return orderDone3;
        }

        else
        {
            return orderDone4;
        }

    }
    
    /**
     * Resets the status of the order in line 1.
     */
    public static void setOrderDone1()
    {
        orderDone1=false;
    }
    
    /**
     * Resets the status of the order in line 2.
     */
    public static void setOrderDone2()
    {
        orderDone2 = false;
    }
    
    /**
     * Resets the status of the order in line 3.
     */
    public static void setOrderDone3()
    {
        orderDone3 = false;
    }
    
    /**
     * Resets the status of the order in line 4.
     */
    public static void setOrderDone4()
    {
        orderDone4 = false;
    }
    
    /**
     * Checks to see if the chef returns to the cashier after receiving the 
     * order, which tells the cashier that the chef has delivered the competed
     * order and tells the cashier to move back to register.
     */
    public void checkOrderComplete()
    {
        Chef c = (Chef)getOneObjectAtOffset((this.getImage().getWidth() / 2) - 100, 0, Chef.class); //code taken from the Mr Cohen's Bug Simulation
        if(c != null && xPos == 950)
        {
            meetingWithChef++;
            orderComplete = c.getIsOrderComplete();
        }
        if(orderComplete == true && meetingWithChef == 50)
        {
            receivedOrder = true;
        }
    }
}

