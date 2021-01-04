import greenfoot.*;  
/**
 * Button is a greenfoot actor that displays text. The default button is 150x30 and is
 * red with a black border and black text. It includes a public method (setColour) that
 * allows users to change the colour of the bar.
 * 
 * @author Cliff Xing
 * @version 2020
 */
public class Button extends Actor
{
    private GreenfootImage button;
    private Color backingColour;
    private Color borderTextColour;
    private Font font;
    private String label;
    private int textXPosition;
    private int textYPosition;
    
    /**
     * Creates a 150x30 button that displays text with Arial font and has a black and red
     * colour scheme.
     * 
     * @param text The text displayed by the button
     */
    public Button(String text)
    {
        button = new GreenfootImage(150,30);
        label = text;
        
        backingColour = new Color(222,79,79);
        borderTextColour = Color.BLACK;
        
        font = new Font("Arial", true, false, 15);
        button.setFont(font);
        textXPosition = button.getWidth()/3;
        textYPosition = button.getHeight()/2+5;
        
        update();
        setImage(button); 
        
    }  
    
    /** 
     * Sets the colour of the button (excluding the border and text which will
     * remain black)
     * 
     * @param r The R value of the backing and text colour (a number between 0 and 255)
     * @param g The G value of the backing and text colour (a number between 0 and 255)
     * @param b The B value of the backing and text colour (a number between 0 and 255)
     */
    public void setColour(int r, int g, int b)
    {
        backingColour = new Color(r,g,b);
        update(backingColour);
    }
    
    /**
     * Updates the button and draws on the text that is to be displayed
     */
    private void update()
    {
        button.setColor(borderTextColour);
        button.fill();
        button.setColor(backingColour);
        button.fillRect(3,3, button.getWidth()-6, button.getHeight()-6);
        button.setColor(borderTextColour);
        button.drawString(label, textXPosition, textYPosition);
    }
    
    /**
     * Updates the colour of the button
     * 
     * @param backingCol The new colour of the button 
     */
    private void update(Color backingCol)
    {
        backingColour = backingCol;
        button.setColor(borderTextColour);
        button.fill();
        button.setColor(backingColour);
        button.fillRect(3,3, button.getWidth()-6, button.getHeight()-6);
        button.setColor(borderTextColour);
        button.drawString(label, textXPosition, textYPosition);
    
    }
}
