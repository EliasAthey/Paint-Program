
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias Athey
 */
public class PaintJPanel extends JPanel implements MouseListener{//a JPanel with a mouse listener
    private static PaintJPanel instance;//the one instance of the paint panel
    private int x_start, y_start, x_end, y_end;//starting and ending mouse positions
    private Graphics2D graphic;//the "unseen" canvas that everything is drawn on
    private BufferedImage buffimg;//the image that is displayed in ther JPanel as the canvas
    private boolean sizechanged;//flag for if the JFrame size has been changed
    
    private PaintJPanel(){//creates a PaintJPanel
        setBackground(Color.WHITE);//background is white
        addMouseListener(this);//add a mouse listener to the panel
    }
    
    @Override 
    //when the repaint() method is called on this JPanel, this paintComponent() method is called in order to save the image       
    public void paintComponent(Graphics g){//takes in the current Graphics
        super.paintComponent(g);//call original method 
        Graphics2D g2 = (Graphics2D)g;//convert Graphics to temporary Graphics2D
        g2.setColor(Color.WHITE);
        if(buffimg == null){//if the visible image is empty(at creation)
            int w = getWidth();//width of PaintJPanel
            int h = getHeight();//height of PaintJPanel
            buffimg = (BufferedImage)(this.createImage(w,h));//creates a buffered image of same size
            graphic = buffimg.createGraphics();//makes the 'graphic' match the buffered image
            graphic.setColor(Color.WHITE);//set the graphics color ot white
            graphic.fillRect(0, 0, w, h);//colors a white background for the panel
            sizechanged = false;//makes sure the else-if statement is not called
        }
        else if(sizechanged){//if the JFrame size has been changed -- changes the paint panel accordingly -- allows the new space to be painted on
            Image tempimg = buffimg;//saves previous buffered image
            buffimg = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);//creates a new buffered image with new dimensions and same type
            graphic = (Graphics2D)buffimg.getGraphics();//gets the graphics for the new buffered image
            graphic.drawImage(tempimg, 0, 0, null);//draws the old buffered image(as an image) onto the graphics of the new buffered image
            
            sizechanged = false;//reverts size change flag to false
        }
        g2.drawImage(buffimg, null, 0, 0);//draws the buffered image onto the PaintJPanel
    }
    
    public void changeSize(){//first step in resizing image with JFrame
        sizechanged = true;//flag that size has ben changed
    }
    
    public void saveImage(){//saves the current painted image
        try{
            File savedimg = new File("src", "savedimg.png");//creates a new file in the src folder titled savedimg.png
            ImageIO.write(buffimg, "png", savedimg);//writes the bufferedimage into a png format into the savedimg file
        }
        catch(IOException e){//thrown if file does not exist
            System.out.println("IOException PaintJPanel:75");
        }
    }
    
    public void loadImage(){//loads the single saved image
        try{
            clearJPanel();//clears the canvas - as well as the canvas's buffered image
            buffimg = (BufferedImage)(this.createImage(getWidth(), getHeight()));//recreates a blank buffered image of current panel size
            graphic = buffimg.createGraphics();//makes the 'graphic' match the buffered image
            
            graphic.setColor(Color.WHITE);//colors the background white
            graphic.fillRect(0, 0, this.getWidth(), this.getHeight());
            
            BufferedImage tempimg = ImageIO.read(new File("src/savedimg.png"));//creates bufferedimage from saved file
            graphic.drawImage(tempimg, null, 0, 0);//draws saved image onto the graphic
            
            repaint();//repaints the paint panel's buffered image with current graphic
        }
        catch(IOException e){//thrown if File does not exist
            System.out.println("IOException PaintjPanel:86");
        }
    }
    
    public void clearJPanel(){//clears the canvas panel
        buffimg = null;//emptys the buffered image
        repaint();//repaints the canvas with an empty buffimg
    }
    
    public void drawShape(){//draws a shape on the "invisble" graphic, than calls repaint() to draw graphic onto buffimg
        if(MyPaintChooser.isInstance()){//if there is already an instance of MyJDialog
            graphic.setColor(MyPaintChooser.getInstance().getNewColor());//sets the graphic color(the drawing color) to the chosen color, called from MyJDialog
        }
        else{//if there is NOT already an instance of MyJDialog
            graphic.setColor(Color.BLACK);//then the default color is set to BLACK
        }
        if(x_end > x_start && y_end > y_start){//mouse dragged down and to the right
            switch(ButtonJPanel.getInstance().getIdentifier()){//gets button identifier from ButtonJPanel class
                case "EmptyRec": 
                    graphic.drawRect(x_start, y_start, x_end - x_start, y_end - y_start);//draw an empty rectangle
                    break;
                case "EmptyOval":
                    graphic.drawOval(x_start, y_start, x_end - x_start, y_end - y_start);//draw an empty oval
                    break;
                case "FillRec":
                    graphic.fillRect(x_start, y_start, x_end - x_start, y_end - y_start);//draw a filled rectangle
                    break;
                case "FillOval":
                    graphic.fillOval(x_start, y_start, x_end - x_start, y_end - y_start);//draw a filled oval
                    break;
                case "LineDraw":
                    graphic.drawLine(x_start, y_start, x_end, y_end);//draws a line
                    break;
            }
        }
        else if(x_start > x_end && y_start > y_end){//mouse dragged up and to the left
            switch(ButtonJPanel.getInstance().getIdentifier()){
                case "EmptyRec": 
                    graphic.drawRect(x_end, y_end, x_start - x_end, y_start - y_end);
                    break;
                case "EmptyOval":
                    graphic.drawOval(x_end, y_end, x_start - x_end, y_start - y_end);
                    break;
                case "FillRec":
                    graphic.fillRect(x_end, y_end, x_start - x_end, y_start - y_end);
                    break;
                case "FillOval":
                    graphic.fillOval(x_end, y_end, x_start - x_end, y_start - y_end);
                    break;
                case "LineDraw":
                    graphic.drawLine(x_start, y_start, x_end, y_end);
                    break;
            }
        }
        else if(x_end > x_start && y_start > y_end){//mouse dragged up and to the right
            switch(ButtonJPanel.getInstance().getIdentifier()){
                case "EmptyRec": 
                    graphic.drawRect(x_start, y_end, x_end - x_start, y_start - y_end);
                    break;
                case "EmptyOval":
                    graphic.drawOval(x_start, y_end, x_end - x_start, y_start - y_end);
                    break;
                case "FillRec":
                    graphic.fillRect(x_start, y_end, x_end - x_start, y_start - y_end);
                    break;
                case "FillOval":
                    graphic.fillOval(x_start, y_end, x_end - x_start, y_start - y_end);
                    break;
                case "LineDraw":
                    graphic.drawLine(x_start, y_start, x_end, y_end);
                    break;
            }
        }
        else if(x_start > x_end && y_end > y_start){//mouse dragged down and to the left
            switch(ButtonJPanel.getInstance().getIdentifier()){
                case "EmptyRec": 
                    graphic.drawRect(x_end, y_start, x_start - x_end, y_end - y_start);
                    break;
                case "EmptyOval":
                    graphic.drawOval(x_end, y_start, x_start - x_end, y_end - y_start);
                    break;
                case "FillRec":
                    graphic.fillRect(x_end, y_start, x_start - x_end, y_end - y_start);
                    break;
                case "FillOval":
                    graphic.fillOval(x_end, y_start, x_start - x_end, y_end - y_start);
                    break;
                case "LineDraw":
                    graphic.drawLine(x_start, y_start, x_end, y_end);
                    break;
            }
        }
        repaint();//repaints the 'graphic' onto the 'buffimg'
    }
    
    public static PaintJPanel getInstance(){//returns the one instance of PaintJPanel
        if(instance == null){//if one hasnt been made yet
            instance = new PaintJPanel();//then one is created
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e){}

    @Override
    //records the starting point of mouse when dragged
    public void mousePressed(MouseEvent e){
        x_start = e.getX();
        y_start = e.getY();
    }

    @Override
    //records ending point of mouse when dragged
    public void mouseReleased(MouseEvent e){
        x_end = e.getX();
        y_end = e.getY();
        drawShape();//draws the shape after mouse is released
    }

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}
}
