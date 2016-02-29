
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias Athey
 */
public class MyJFrame extends JFrame implements ComponentListener{//subclass of JFrame, implements ComponentListener
    private static MyJFrame instance;//variable represents the one instance of MyJFrame
    
    private MyJFrame(){
        super("J-Paint");//calls the parent constructor and passes the string for the title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//makes the exit button stop the program
        setLayout(new BorderLayout());//set the layout to a border layout manager
        
        getContentPane().add(ButtonJPanel.getInstance(), BorderLayout.WEST);//adds the panel with buttons
        getContentPane().add(PaintJPanel.getInstance(), BorderLayout.CENTER);//adds the panel that is painted on
        setJMenuBar(MyJMenuBar.getInstance());//adds the menubar
        
        addComponentListener(this);//adds a component listener to teel when the size is changed
        
        setMinimumSize(new Dimension(300, 345));//the minimum size of the window -- prevents the button icons from clipping over the background
        setLocation(0,0);//places the frame away from the corner
        setSize(900, 900);//sets the size
        setVisible(true);//final thing to do is set the frame to visible
    }
    
    public static MyJFrame getInstance(){//can be called to retrieve the only existing instance of the JFrame
        if(instance == null){//if there isnt one already available, one is made
            instance = new MyJFrame();
        }
        return instance;//returns the one instance of MyJFrame
    }
    
    @Override
    public void componentResized(ComponentEvent e){//when JFrame is resized, calls the changeSize method of the paint panel
        PaintJPanel.getInstance().changeSize();
    }

    @Override
    public void componentMoved(ComponentEvent e){}

    @Override
    public void componentShown(ComponentEvent e){}

    @Override
    public void componentHidden(ComponentEvent e){}
    
}
