
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class MyPaintChooser extends JDialog implements ChangeListener{//Dialog box with a change listener for the JColorChooser
    private static MyPaintChooser instance;//the instance of the color chooser JDialog box
    private JColorChooser cc;//the java swing color chooser
    private static Color newcolor;//the color picked by the JColorChooser
    
    private MyPaintChooser(){
        super(MyJFrame.getInstance(), "Color Chooser");//constructs a JDialog owned my MyJFrame, titled "Color Chooser"
        cc = new JColorChooser();//calls a new JColorChooser and assigns it to 'cc'
        cc.getSelectionModel().addChangeListener(this);//adds a changelistener in order to get the color picked
        add(cc);//adds the JColorChooser to the dialog
        
        setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);//the instance of MyJDialog, once opened, never actually goes away, it is only hidden
        setSize(670, 385);//sets the size of MyJDialog
        mySetLocation();//calls my method to set location
        
        setVisible(true);//sets the Dialog box to visible
    }
    
    public void mySetLocation(){//sets the location of the Color Chooser to the top right corner or next to JFrame
        int framexstart = MyJFrame.getInstance().getX();//X location of JFrame
        int framewidth = MyJFrame.getInstance().getWidth();//width of JFrame
        
        if(framexstart + framewidth + getWidth() <= Toolkit.getDefaultToolkit().getScreenSize().width){//if there is at least a space the width of PaintChooser between the right side of the JFrame and the right side of the monitor
            setLocation(MyJFrame.getInstance().getX() + MyJFrame.getInstance().getWidth(), 0);//sets the JDialog box to the right side of the JFrame
        }
        else{
            setLocation(Toolkit.getDefaultToolkit().getScreenSize().width - 500, 0);//otherwise, sets Color Chooser in top right corner
        }
        setVisible(true);//makes the dialog visible
    }
    
    public static Color getNewColor(){//returns the current chosen color
        if(newcolor == null){
            newcolor = Color.BLACK;//default is BLACK
        }
        return newcolor;
    }
    
    public static boolean isInstance(){ //returns true if the color chooser has already been opened(instantiated)
        return instance != null;
    } 
    
    public static MyPaintChooser getInstance(){//returnsthe one instance of MyJDialog
        if(instance == null){
            instance = new MyPaintChooser();//if there isnt one, makes one
        }
        return instance;
    }

    @Override
    public void stateChanged(ChangeEvent e){//is called when the JColorChooser color changes
        newcolor = cc.getColor();//assigns the color to the instance field
        ButtonJPanel.getInstance().changeColor(newcolor);//changes the color of the button when the color is changed
    }
}
