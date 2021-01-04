import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Customer is a parent class of subclasses regular and gangster. The class includes methods that allow
 * customers to choose which line they are going to, move, order, get budded in line, receive food and leave the world. 
 * 
 * @author Cliff Xing
 * @version 2020
 */
public abstract class Customer extends Actor
{
    //variables for animation
    protected int frameR;
    protected int frameL;
    protected int speed = 2;

    protected int randomize; //used to randomize customer orders

    //variables for customers
    protected int linenum;
    protected int linePart;
    protected int customerType;
    protected boolean ordering=false;
    protected boolean waiting=false;
    protected int counterTime;
    protected int chosenLine;
    protected boolean atLine=false;
    protected boolean orderDone = false;
    protected boolean facingRight=true; 
    protected boolean beenBudded=false;
    protected int randomizeLine;

    //variables for amount of customers in each line
    protected static int inLine1=0;
    protected static int inLine2=0;
    protected static int inLine3=0;
    protected static int inLine4=0;

    protected static int lane1=0;
    protected static int lane2=0;
    protected static int lane3=0;
    protected static int lane4=0;

    //variable for order of each line 
    private static int line1order=0;
    private static int line2order=0;
    private static int line3order=0;
    private static int line4order=0;

    //variables indicating when customers must move up
    protected static boolean moveUpLine1=false;
    protected static boolean moveUpLine2=false;
    protected static boolean moveUpLine3=false;
    protected static boolean moveUpLine4=false;

    protected static boolean bud1=false;
    protected static boolean bud2=false;
    protected static boolean bud3=false;
    protected static boolean bud4=false;

    //other static variables
    protected static int totalCustomers=0;
    protected static boolean walking=false; //if any customer is walking currently (so that multiple customers don't spawn at once
    protected static boolean isFull=false;

    //sound variables
    private GreenfootSound talkingImpatient;
    private GreenfootSound talkingGangster;
    private GreenfootSound talkingRegular;

    public Customer()
    {
        talkingImpatient = new GreenfootSound("talkingImpatient.wav");
        talkingGangster = new GreenfootSound("talkingGangster.wav");
        talkingRegular = new GreenfootSound("talkingRegular.wav");
    }

    public void act() 
    {

    }    

    /**
     * Resets all static variable values 
     */
    public static void resetScenario()
    {
        inLine1=0;
        inLine2=0;
        inLine3=0;
        inLine4=0;

        line1order=0;
        line2order=0;
        line3order=0;
        line4order=0;

        moveUpLine1=false;
        moveUpLine2=false;
        moveUpLine3=false;
        moveUpLine4=false;

        totalCustomers=0;
        walking=false; 
        boolean isFull=false;

        lane1=0;
        lane2=0;
        lane3=0;
        lane4=0;

    }

    /**
     * Checks to see if gangster has budded the line, and if it has, calls budded() method
     */
    public void checkBudded()
    {
        if (linenum==1 && bud1==true)
        {
            budded();
        }

        if (linenum==2 && bud2==true)
        {
            budded();
        }

        if (linenum==3 && bud3==true)
        {
            budded();
        }

        if (linenum==4 && bud4==true)
        {
            budded();
        }
    }

    /**
     * Causes customer who is budded in line to move back one spot in line
     */
    protected void budded()
    {

        Gangster g = (Gangster)getOneObjectAtOffset((this.getImage().getWidth() / 2)-10 , 0, Gangster.class);
        if (facingRight==true )
        {
            if(g != null )//&& g.getHasBudded() )
            {

                if (linenum==1 && moveUpLine1==false)
                {
                    setLocation(this.getX() - 100, 230);
                    bud1=false;
                }

                if (linenum==2 && moveUpLine2==false)
                {
                    setLocation(this.getX() - 100, 370);
                    bud2=false;
                }

                if (linenum==3 && moveUpLine3==false)
                {
                    setLocation(this.getX() - 100, 510);
                    bud3=false;
                }

                if (linenum==4 && moveUpLine4==false) 
                {
                    setLocation(this.getX() - 100, 650);
                    bud4=false;
                }

            }
        }

    }

    /**
     * Adds a value of 1 to totalCustomers variable 
     */
    public static void addToTotalCustomers()
    {
        totalCustomers=totalCustomers+1;
    }

    /**
     *Returns the integer value of totalCustomers
     */
    public static int getTotalCustomers()
    {
        return totalCustomers;
    }

    /**
     * Plays the sound effects for customers who are ordering, and differentiates
     * the sound played depending on the type of customer
     */
    protected void talkingSound()
    {
        if(linePart == 1 && counterTime < 1 && customerType == 1)
        {
            talkingRegular.play();
            counterTime++;
        }
        else if(linePart == 1 && counterTime < 1 && customerType == 2)
        {
            talkingImpatient.play();
            counterTime++;
        }
        else if (linePart == 1 && counterTime < 1 && customerType == 3)
        {
            talkingGangster.play();
            counterTime++;
        }
    }

    /**
     * Checks if each line has 5 customers in it, and will return true for isFull if 
     * all lines have 5 customers in it, and false if it doesn't
     */
    protected static boolean checkFull()
    {
        if (inLine1==5 && inLine1==inLine2 && inLine2==inLine3 && inLine3==inLine4)
        {
            isFull=true;

        }

        else
        {
            isFull=false;

        }
        return isFull;
    }

    /**
     * Finds the line with the least amount of people and will set variable chosenLine to that line.
     * 
     */
    public void checkLine()
    {
        if (inLine1==5 && inLine1==inLine2 && inLine2==inLine3 && inLine3==inLine4)
        {
            isFull=true;
        }

        else if (lane1<=lane2 && lane1<=lane3 && lane1<=lane4)
        {
            chosenLine=1;

        }

        else if (lane2<=lane1 && lane2<=lane3 && lane2<=lane4)
        {
            chosenLine=2;
        }

        else if (lane3<=lane1 && lane3<=lane2 && lane3<=lane4)
        {
            chosenLine=3;
        }

        else if (lane4<=lane1 && lane4<=lane2 && lane4<=lane3)
        {
            chosenLine=4;
        }
        moveToLine(chosenLine);
    }

    /**
     * Moves customer to the last spot in the line with the least amount of people in it 
     * 
     * @param line The line number that the customer will head towards
     * 
     */
    protected void moveToLine(int line)
    {
        orderDone = false;
        walking=true;
        if (line==1)
        {
            if (atLine==false)
            {
                turnTowards(580-(100*inLine1),230);
                animateR();
                move(2);

            }

            if (this.getX()>=580-(100*inLine1) )
            {
                atLine=true;
                linenum=1;
                inLine1=inLine1+1;
                linePart=inLine1;
                walking=false;
                lane1=lane1+1;

                setRotation(0);
                if (inLine1==1)
                {
                    ordering=true;
                }

            }
        }

        if (line==2)
        {
            if (atLine==false)
            {
                turnTowards(580-(100*inLine2) ,370);
                animateR();
                move(2);

            }

            if (this.getX()>=580-(100*inLine2))
            {
                atLine=true;
                linenum=2;
                inLine2=inLine2+1;
                walking=false;
                linePart=inLine2;
                setRotation(0);
                lane2=lane2+1;

                if (inLine2==1)
                {
                    ordering=true;
                }

            }
        }

        if (line==3)
        {
            if (atLine==false)
            {
                turnTowards(580-(100*inLine3) ,510);
                animateR();
                move(2);

            }

            if (this.getX()>=580-(100*inLine3))
            {
                atLine=true;
                linenum=3;
                inLine3=inLine3+1;
                walking=false;
                linePart=inLine3;
                setRotation(0);                                
                lane3=lane3+1;

                if (inLine3==1)
                {
                    ordering=true;
                }
            }

        }

        if (line==4)
        {
            if (atLine==false)
            {
                turnTowards(580-(100*inLine4) ,650);
                animateR();
                move(2);

            }

            if (this.getX()>=580-(100*inLine4))
            {
                atLine=true;
                inLine4=inLine4+1;
                linenum=4;
                walking=false;
                linePart=inLine4;
                setRotation(0);
                lane4=lane4+1;

                if (inLine4==1)
                {
                    ordering=true;
                }

            }

        }

    }

    /**
     * Checks if the customer's order is done, and if it is, will make customer exit the restaurant
     * and get removed from the world
     */
    protected void checkDone()
    {
        if (Cashier.getOrderDone(linenum)==true)
        {
            orderDone = true;
            if (this.getX()>0)
            {
                move(-1);
                animateL();
                facingRight=false;
            }

            else
            {
                if (customerType==3)
                {
                    MyWorld.decreaseNumGangster();

                }
                getWorld().removeObject(this);
                totalCustomers=totalCustomers-1;
                if (linenum==1)
                {
                    moveUpLine1=true;
                    inLine1=inLine1-1;
                    Cashier.setOrderDone1();
                    line1order=0;
                }

                else if (linenum==2)
                {
                    moveUpLine2=true;
                    inLine2=inLine2-1;
                    Cashier.setOrderDone2();
                    line2order=0;
                }

                else if (linenum==3)
                {
                    moveUpLine3=true;
                    inLine3=inLine3-1;
                    Cashier.setOrderDone3();
                    line3order=0;
                }

                else
                {
                    moveUpLine4=true;
                    inLine4=inLine4-1;
                    Cashier.setOrderDone4();
                    line4order=0;
                }
            }
        }
    }

    /**
     * Makes customers move up one spot within the line when
     * a customer leaves the line 
     */
    protected void moveUpLine() 
    {
        if (linenum==1 && moveUpLine1==true)
        {
            if (linePart>1) 
            {

                if (this.getX()<580-(100*(linePart-2)))
                {
                    turnTowards(580-(100*(linePart-2)), 230);
                    move(2);
                    animateR();
                }

                else
                {
                    linePart=linePart-1;

                    if (linePart==1)
                    {
                        ordering=true;
                        moveUpLine1=false;
                    }

                }
            }
        }

        if (linenum==2 && moveUpLine2==true)
        {
            if (linePart>1)
            {
                if (this.getX()<580-(100*(linePart-2)))
                {
                    turnTowards(580-(100*(linePart-2)), 370);
                    move(2);
                    animateR();
                } 
                else
                {
                    linePart=linePart-1;

                    if (linePart==1)
                    {
                        ordering=true;
                        moveUpLine2=false;
                    }
                }
            }
        }

        if (linenum==3 && moveUpLine3==true)
        {
            if (linePart>1)
            {
                if (this.getX()<580-(100*(linePart-2)))
                {
                    turnTowards(580-(100*(linePart-2)), 510);
                    move(2);
                    animateR();
                }

                else
                {
                    linePart=linePart-1;

                    if (linePart==1)
                    {
                        ordering=true;
                        moveUpLine3=false;
                    }
                }
            }
        }

        if (linenum==4 && moveUpLine4==true)
        {
            if (linePart>1)
            {
                if (this.getX()<580-(100*(linePart-2)))
                {
                    turnTowards(580-(100*(linePart-2)), 650);
                    move(2);
                    animateR();
                }

                else
                {
                    linePart=linePart-1;

                    if (linePart==1)
                    {
                        ordering=true;
                        moveUpLine4=false;
                    }
                }
            }
        }
    }

    /**
     * Differentiates the randomizeOrder methods depending on what line customer is in
     * 
     */
    protected void orderFood()
    {
        if (ordering==true)
        {

            if (linenum==1)
            {
                randomizeOrder1();
            }

            else if (linenum==2)
            {
                randomizeOrder2();
            }

            else if (linenum==3)
            {
                randomizeOrder3();
            }

            else if (linenum==4)
            {
                randomizeOrder4();
            }

        }
    }

    /**
     * Differentiates what type of customer is being animated, then calls animate method for customers
     * moving to the right
     */
    protected void animateR()
    {
        if(customerType == 1)
        {
            animateRight("manRight1.png", "manRight2.png", "manRight3.png", "manRight4.png");
        }
        else if(customerType == 2)
        {
            animateRight("girlRight1.png", "girlRight2.png", "girlRight3.png", "girlRight4.png");
        }
        else if (customerType == 3)
        {
            animateRight("gangRight1.png", "gangRight2.png", "gangRight3.png", "gangRight4.png");
        }
    }

    /**
     * Differentiates what type of customer is being animated, then calls animate method for customers
     * moving to the left
     */
    protected void animateL()
    {
        if(customerType == 1)
        {
            animateRight("manLeft1.png", "manLeft2.png", "manLeft3.png", "manLeft4.png");
        }
        else if(customerType == 2)
        {
            animateRight("girlLeft1.png", "girlLeft2.png", "girlLeft3.png", "girlLeft4.png");
        }
        else if (customerType == 3)
        {
            animateRight("gangLeft1.png", "gangLeft2.png", "gangLeft3.png", "gangLeft4.png");
        }
    }

    /**
     * Animates the customer when moving towards the left
     * 
     * @param img1 The first image of the sprite being animated
     * @param img2 The second image of the sprite being animated
     * @param img3 The third image of the sprite being animated
     * @param img4 The fourth image of the sprite being animated
     */
    protected void animateLeft(String img1, String img2, String img3, String img4)
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
            frameL=0;
        }

    }

    /**
     * Animates the customer when moving towards the right
     * 
     * @param img1 The first image of the sprite being animated
     * @param img2 The second image of the sprite being animated
     * @param img3 The third image of the sprite being animated
     * @param img4 The fourth image of the sprite being animated
     */
    protected void animateRight(String img1, String img2, String img3, String img4)
    {
        if (frameR == 0)
        {
            GreenfootImage image = new GreenfootImage(img1);
            image.scale(image.getWidth() / 2, image.getHeight() / 2);
            setImage(image);
            frameR++;
        }
        else if (frameR == 1)
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

    /**
     * Determines the order of the customer in the first line (randomized)
     */
    protected void randomizeOrder1()
    {
        randomize = Greenfoot.getRandomNumber(2);
        if (randomize==0)
        {
            line1order=1;
            ordering=false;
            waiting=true;
        }

        else if (randomize==1)
        {
            line1order=2; 
            ordering=false;
            waiting=true;
        }

    }

    /**
     * Determines the order of the customer in the second line (randomized)
     */
    protected void randomizeOrder2()
    {
        randomize = Greenfoot.getRandomNumber(2);
        if (randomize==0)
        {
            line2order=1;
            ordering=false;
            waiting=true;
        }

        else if (randomize==1)
        {
            line2order=2; 
            ordering=false;
            waiting=true;
        }

    }

    /**
     * Determines the order of the customer in the third line (randomized)
     */
    protected void randomizeOrder3()
    {
        randomize = Greenfoot.getRandomNumber(2);
        if (randomize==0)
        {
            line3order=1;
            ordering=false;
            waiting=true;
        }

        else if (randomize==1)
        {
            line3order=2; 
            ordering=false;
            waiting=true;
        }

    }

    /**
     * Determines the order of the customer in the fourth line (randomized)
     */
    protected void randomizeOrder4()
    {
        randomize = Greenfoot.getRandomNumber(2);
        if (randomize==0)
        {
            line4order=1;
            ordering=false;
            waiting=true;
        }

        else if (randomize==1)
        {
            line4order=2; 
            ordering=false;
            waiting=true;
        }

    }

    /**
     * Returns the value of walking variable
     */
    public static boolean getWalking()
    {
        return walking;   
    }

    /**
     * Returns the value of waiting variable
     */
    public boolean getWaiting()
    {
        return waiting;
    }

    /**
     * Returns the value of line1order variable
     */
    public static int getLine1()
    {
        return line1order;   
    }

    /**
     * Returns the value of line2order variable
     */
    public static int getLine2()
    {
        return line2order;   
    }

    /**
     * Returns the value of line3order variable
     */
    public static int getLine3()
    {
        return line3order;   
    }

    /**
     * Returns the value of line4order variable
     */
    public static int getLine4()
    {
        return line4order;   
    }

    /**
     * Returns the value of isFull variable
     */
    public static boolean getIsFull()
    {
        return isFull;   
    }

    /**
     * Sets value of line1order to assigned value in parameter
     * 
     * @param num Value of what line1order is desired to be set to
     */
    public static void setLine1(int num)
    {
        line1order=num;
    }

    /**
     * Sets value of line2order to assigned value in parameter
     * 
     * @param num Value of what line1order is desired to be set to
     */
    public static void setLine2(int num)
    {
        line2order=num;
    }

    /**
     * Sets value of line3order to assigned value in parameter
     * 
     * @param num Value of what line1order is desired to be set to
     */
    public static void setLine3(int num)
    {
        line3order=num;
    }

    /**
     * Sets value of line4order to assigned value in parameter
     * 
     * @param num Value of what line1order is desired to be set to
     */
    public static void setLine4(int num)
    {
        line4order=num;
    }

}
