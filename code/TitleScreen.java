import greenfoot.*;  

/**
 * TitleScreen is a 1280x720 greenfoot world that creates a starting screen and menu of options 
 * for the simulation. It allows users to click buttons, customizing variables such as
 * time of day, speed of chefs and revenue goal. In addition, users can press their space 
 * button to progress to the actual simulation.
 * 
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class TitleScreen extends World
{

    Button breakfast = new Button("Breakfast");
    Button lunch = new Button("Lunch");
    Button revGoal1 = new Button("$1000");
    Button revGoal2 = new Button("$2000");
    Button revGoal3 = new Button("$3000");
    Button slow = new Button("Slow");
    Button normal = new Button("Normal");
    Button fast = new Button("Fast");
    
    /**
     * Creates a 1280x720 world with nine buttons displaying customizable options
     * for the simulation. 
     */
    public TitleScreen()
    {    
        super(1280, 720, 1);  
        setBackground(new GreenfootImage("title screen 1.jpg"));
        addObject(breakfast, 688, 272);
        addObject(lunch, 891, 272);
        addObject(revGoal1, 619, 381);
        addObject(revGoal2, 782,381);
        addObject(revGoal3, 945, 381);
        addObject(slow, 619, 499);
        addObject(normal, 782, 499);
        addObject(fast, 945, 499);

    }

    public void act()
    {
        if(Greenfoot.isKeyDown("space"))
        {
            Greenfoot.setWorld(new MyWorld());
        }

        if(Greenfoot.mouseClicked(breakfast))
        {
            breakfast.setColour(188,237,52);
            lunch.setColour(222,79,79);
            MyWorld.isBreakfast = true;
        }
        
        if(Greenfoot.mouseClicked(lunch))
        {
            lunch.setColour(188,237,52);
            breakfast.setColour(222,79,79);
            MyWorld.isBreakfast = false;
        }
        
        if(Greenfoot.mouseClicked(revGoal1))
        {
            revGoal1.setColour(188,237,52);
            revGoal2.setColour(222,79,79);
            revGoal3.setColour(222,79,79);
            MyWorld.revGoal = 1000;
        }
        
        if(Greenfoot.mouseClicked(revGoal2))
        {
            revGoal2.setColour(188,237,52);
            revGoal1.setColour(222,79,79);
            revGoal3.setColour(222,79,79);
            MyWorld.revGoal = 2000;
        }
        
        if(Greenfoot.mouseClicked(revGoal3))
        {
            revGoal3.setColour(188,237,52);
            revGoal2.setColour(222,79,79);
            revGoal1.setColour(222,79,79);
            MyWorld.revGoal = 3000;
        }
        
        if(Greenfoot.mouseClicked(slow))
        {
            slow.setColour(188,237,52);
            normal.setColour(222,79,79);
            fast.setColour(222,79,79);
            MyWorld.cookingSpeed = 1;
        }
        
        if(Greenfoot.mouseClicked(normal))
        {
            normal.setColour(188,237,52);
            slow.setColour(222,79,79);
            fast.setColour(222,79,79);
            MyWorld.cookingSpeed = 2;
        }
        
        if(Greenfoot.mouseClicked(fast))
        {
            fast.setColour(188,237,52);
            slow.setColour(222,79,79);
            normal.setColour(222,79,79);
            MyWorld.cookingSpeed = 3;
        }
    }
}
