import greenfoot.*;  

/**
 * MyWorld is a 1280x720 greenfoot world that creates a restaurant simulation. It creates
 * instances of employees and customers as well as counters that keep track of revenue
 * and the amount of angry customers. It includes methods such as generateRandomCustomer()
 * which generates instances of the customer class, as well as methods that check the
 * order of the first customer in every line (eg. checkLine1Order(), etc.)
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class MyWorld extends World
{
    private GreenfootSound jazzBackground;

    public static boolean isBreakfast = true;
    public static int revGoal = 2000;
    public static int cookingSpeed = 2;
    

    Counter revCounter;

    private int price1;
    private int price2;

    private int slow1 = 3000;
    private int normal1 = 1500;
    private int fast1 = 700;
    private int slow2 = 4000;
    private int normal2 = 2000;
    private int fast2 = 1000;

    Chef chef1 = new Chef();
    Chef chef2 = new Chef();
    Chef chef3 = new Chef();
    Chef chef4 = new Chef();

    Cashier cashier1 = new Cashier();
    Cashier cashier2 = new Cashier();
    Cashier cashier3 = new Cashier();
    Cashier cashier4 = new Cashier();

    OrderBubble bubble1= new OrderBubble();
    OrderBubble bubble2= new OrderBubble();
    OrderBubble bubble3= new OrderBubble();
    OrderBubble bubble4= new OrderBubble();

    private int timer;
    private int spawnRate=300;
    private int randomize;
    private boolean customerAtCounter1 = false;

    public static int numGangster=0; //amount of gangsters in the world at once

    /**
     * Creates a 1280x720 world with two counters (one for revenue count, the other
     * to keep track of angry customers), and four instances of the cashier subclass and
     * chef subclass.
     */
    public MyWorld()
    {    
        super(1280, 720, 1); 
        if(isBreakfast)
        {
            setBackground(new GreenfootImage("Breakfast.png"));
            price1 = 10;
            price2 = 15;
        }
        else
        {
            setBackground(new GreenfootImage("Lunch.png"));
            price1 = 15;
            price2 = 20;
        }
        Customer.resetScenario();
        Cashier.resetScenario();

        revCounter = new Counter(250, 50,0,0,0,0,255,0);
        revCounter.setLabel("Revenue: $");
        revCounter.setFontInfo(15, true, false, 80, 30);
        addObject(revCounter, 900, 65);

        addObject(chef1, 950, 235);
        addObject(chef2, 950, 385);
        addObject(chef3, 950, 536);
        addObject(chef4, 950, 665);

        addObject(cashier1, 750, 235);
        addObject(cashier2, 750, 385);
        addObject(cashier3, 750, 536);
        addObject(cashier4, 750, 665);

        jazzBackground = new GreenfootSound("jazzBackground.mp3");
        numGangster=0;
    }

    public void act()
    {
        if(revCounter.getPoints() >= revGoal)
        {
            Greenfoot.setWorld(new EndScreen());
        }
        //jazzBackground.play();

        chef1.cook();
        chef2.cook();
        chef3.cook();
        chef4.cook();

        generateRandomCustomer();

        checkLine1Order();
        checkLine2Order();
        checkLine3Order();
        checkLine4Order();

        if (Cashier.getOrderDone(1)==true)
        {
            removeObject(bubble1);
            if(cashier1.getCustomerOrder() == 1)
            {
                revCounter.addPoints(price1);
            }
            if(cashier1.getCustomerOrder() == 2)
            {
                revCounter.addPoints(price2);
            }

            cashier1.reset();
        }

        if (Cashier.getOrderDone(2)==true)
        {
            removeObject(bubble2);
            if(cashier2.getCustomerOrder() == 1)
            {
                revCounter.addPoints(price1);
            }
            if(cashier1.getCustomerOrder() == 2)
            {
                revCounter.addPoints(price2);
            }
            cashier2.reset();
        }

        if (Cashier.getOrderDone(3)==true)
        {
            removeObject(bubble3);
            if(cashier3.getCustomerOrder() == 1)
            {
                revCounter.addPoints(price1);
            }
            if(cashier3.getCustomerOrder() == 2)
            {
                revCounter.addPoints(price2);
            }
            cashier3.reset();
        }

        if (Cashier.getOrderDone(4)==true)
        {
            removeObject(bubble4);
            if(cashier4.getCustomerOrder() == 1)
            {
                revCounter.addPoints(price1);
            }
            if(cashier4.getCustomerOrder() == 2)
            {
                revCounter.addPoints(price2);
            }
            cashier4.reset();
        }

    }

    /**
     * Generates instances of the customer subclasses (Gangster, Impatient, Regular).
     */
    public void generateRandomCustomer()
    {
        if (Customer.getWalking()==false ) 
        {
            randomize = Greenfoot.getRandomNumber(30);

            if (randomize==1)
            {
                if (Customer.getTotalCustomers() <12)
                {
                    addObject(new Regular(), 5,450);
                    Customer.addToTotalCustomers();
                }

            }
            if (randomize == 2)
            {
                if(Customer.getTotalCustomers() < 12)
                {
                    if (numGangster<4)
                    {
                        addObject(new Gangster(), 5, 450);
                        Customer.addToTotalCustomers();
                        numGangster++;
                    }

                    else
                    {
                        addObject(new Regular(), 5,450);
                        Customer.addToTotalCustomers();
                    }

                }
            }
        }

    }
    
    public static void decreaseNumGangster()
    {
        numGangster--;
    }

    
    /** 
     * Checks the order of first customer in the first line, generates a speech bubble
     * that displays the order, and makes the cashier check the customer's
     * order and move to the chef 
     */
    public void checkLine1Order()
    {
        if (Customer.getLine1()==1)
        {
            bubble1.setImage(1);
            addObject(bubble1, 620, 150);
            Customer.setLine1(0);
            cashier1.move();
            cashier1.checkCustomerOrder(1);
            if(cookingSpeed == 1)
            {
                chef1.setCookTime(slow1);
            }
            if(cookingSpeed == 2)
            {
                chef1.setCookTime(normal1);
            }
            if(cookingSpeed == 3)
            {
                chef1.setCookTime(fast1);
            }

        }

        else if(Customer.getLine1()==2)
        {
            bubble1.setImage(2);
            addObject(bubble1, 620, 150);
            Customer.setLine1(0);
            cashier1.move();
            cashier1.checkCustomerOrder(2);
            if(cookingSpeed == 1)
            {
                chef1.setCookTime(slow2);
            }
            if(cookingSpeed == 2)
            {
                chef1.setCookTime(normal2);
            }
            if(cookingSpeed == 3)
            {
                chef1.setCookTime(fast2);
            }
        }
    }

    /** 
     * Checks the order of first customer in the second line, generates a speech bubble
     * that displays the order, and makes the cashier check the customer's
     * order and move to the chef 
     */
    public void checkLine2Order()
    {
        if (Customer.getLine2()==1)
        {
            bubble2.setImage(1);
            addObject(bubble2, 620, 290);
            Customer.setLine2(0);
            cashier2.move();
            cashier2.checkCustomerOrder(1);
            if(cookingSpeed == 1)
            {
                chef2.setCookTime(slow1);
            }
            if(cookingSpeed == 2)
            {
                chef2.setCookTime(normal1);
            }
            if(cookingSpeed == 3)
            {
                chef2.setCookTime(fast1);
            }
        }

        else if(Customer.getLine2()==2)
        {
            bubble2.setImage(2);
            addObject(bubble2, 620, 290);
            Customer.setLine2(0);
            cashier2.move();
            cashier2.checkCustomerOrder(2);
            if(cookingSpeed == 1)
            {
                chef2.setCookTime(slow2);
            }
            if(cookingSpeed == 2)
            {
                chef2.setCookTime(normal2);
            }
            if(cookingSpeed == 3)
            {
                chef2.setCookTime(fast2);
            }
        }
    }

    /** 
     * Checks the order of first customer in the third line, generates a speech bubble
     * that displays the order, and makes the cashier check the customer's
     * order and move to the chef 
     */
    public void checkLine3Order()
    {
        if (Customer.getLine3()==1)
        {
            bubble3.setImage(1);
            addObject(bubble3, 620, 430);
            Customer.setLine3(0);
            cashier3.move();
            cashier3.checkCustomerOrder(1);
            if(cookingSpeed == 1)
            {
                chef3.setCookTime(slow1);
            }
            if(cookingSpeed == 2)
            {
                chef3.setCookTime(normal1);
            }
            if(cookingSpeed == 3)
            {
                chef3.setCookTime(fast1);
            }
        }

        else if(Customer.getLine3()==2)
        {
            bubble3.setImage(2);
            addObject(bubble3, 620, 430);
            Customer.setLine3(0);
            cashier3.move();
            cashier3.checkCustomerOrder(2);
            chef3.setCookTime(1000);
            if(cookingSpeed == 1)
            {
                chef3.setCookTime(slow2);
            }
            if(cookingSpeed == 2)
            {
                chef3.setCookTime(normal2);
            }
            if(cookingSpeed == 3)
            {
                chef3.setCookTime(fast2);
            }
        }
    }

    /** 
     * Checks the order of first customer in the fourth line, generates a speech bubble
     * that displays the order, and makes the cashier check the customer's
     * order and move to the chef 
     */
    public void checkLine4Order()
    {
        if (Customer.getLine4()==1)
        {
            bubble4.setImage(1);
            addObject(bubble4, 620, 570);
            Customer.setLine4(0);
            cashier4.move();
            cashier4.checkCustomerOrder(1);
            if(cookingSpeed == 1)
            {
                chef4.setCookTime(slow1);
            }
            if(cookingSpeed == 2)
            {
                chef4.setCookTime(normal1);
            }
            if(cookingSpeed == 3)
            {
                chef4.setCookTime(fast1);
            }
        }

        else if(Customer.getLine4()==2)
        {
            bubble4.setImage(2);
            addObject(bubble4, 620, 570);
            Customer.setLine4(0);
            cashier4.move();
            cashier4.checkCustomerOrder(2);
            if(cookingSpeed == 1)
            {
                chef4.setCookTime(slow2);
            }
            if(cookingSpeed == 2)
            {
                chef4.setCookTime(normal2);
            }
            if(cookingSpeed == 3)
            {
                chef4.setCookTime(fast2);
            }
        }
    }

}

