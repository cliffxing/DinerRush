import greenfoot.*;  

/**
 * EndScreen is a 1280x720 greenfoot world that creates the ending screen
 * (which appears after the revenue goal has been met, effectively ending the 
 * simulation). It gives users the option to press the space button and return to 
 * the title screen so they can run the simulation again. 
 * 
 * @author Cliff Xing
 * 
 * @version 2020
 */
public class EndScreen extends World
{

    /**
     * Creates a 1280x720 world displaying the ending screen for the simulation.
     */
    public EndScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1280, 720, 1);  
        setBackground(new GreenfootImage("end screen.jpg"));
    }
    
    public void act()
    {
       if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new TitleScreen());
        } 
    }
}
