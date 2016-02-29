
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
public class ButtonJPanel extends JPanel implements ActionListener{//subclass of JPanel-to create a JPanel, implements ActionListener interface
    private static ButtonJPanel instance;//represent the one instance of ButtonJPanel                     
    
    private JButton clear;//the buttons avaible to user
    private JButton fillrec;
    private JButton filloval;
    private JButton emptyrec;
    private JButton emptyoval;
    private JButton linedraw;
    private JButton opencolor;
    
    private ImageIcon fillrecimg;//the Icons that will go on some of the buttons
    private ImageIcon fillovalimg;
    private ImageIcon emptyrecimg;
    private ImageIcon emptyovalimg;
    private ImageIcon linedrawimg;
    
    private String identifier;//used to identify which button was pressed
    
    private ButtonJPanel(){
        GridLayout layout = new GridLayout(0, 1);//creates a grid layout with 1 column and unlimited rows, for the buttons
        setLayout(layout);//sets the JPanel layout
        layout.setVgap(5);//sets the vertical gap between the buttons
        setBackground(Color.WHITE);//sets the color of the panel to WHITE
        
        fillrecimg = new ImageIcon("src/fillrec.png");//assigns a png image to the ImageIcon
        fillovalimg = new ImageIcon("src/filloval.png");
        emptyrecimg = new ImageIcon("src/emptyrec.png");
        emptyovalimg = new ImageIcon("src/emptyoval.png");
        linedrawimg = new ImageIcon("src/linedraw.png");
        
        clear = new JButton("Clear");//initializes the buttons, either with text or an icon
        (fillrec = new JButton(fillrecimg)).setActionCommand("Filled Rectangle");//icon buttons are assigned a string ActionCommand
        (filloval = new JButton(fillovalimg)).setActionCommand("Filled Oval");
        (emptyrec = new JButton(emptyrecimg)).setActionCommand("Empty Rectangle");
        (emptyoval = new JButton(emptyovalimg)).setActionCommand("Empty Oval");
        (linedraw = new JButton(linedrawimg)).setActionCommand("Line Drawing");
        opencolor = new JButton("Color Chooser");
        
        JButton[] buttons = {clear, fillrec, filloval, emptyrec, emptyoval, linedraw, opencolor};//an array of all the buttons
        
        for(JButton button : buttons){//for each button...
            button.addActionListener(this);//add a listener
            add(button);//add the button to the panel
            button.setOpaque(true);//make the color visable
            button.setBackground(Color.BLACK);//set background to black
            button.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));//sets the font, style, and size
        }
    }
    
    public String getIdentifier(){//public method that returns the identifier: String representing button last pressed
        return identifier;
    }
    
    @Override
    //called when any button is pressed
    public void actionPerformed(ActionEvent ae){
        switch(ae.getActionCommand()){//switch statement for the various buttons
            case "Clear":
                identifier = "Clear";//set identifier respective to button pressed
                PaintJPanel.getInstance().clearJPanel();//clears the Paint canvas
                clear.setBackground(Color.RED);//sets selected button to RED and the rest to BLACK -- Clear button always turns RED
                fillrec.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                linedraw.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Filled Rectangle": 
                identifier = "FillRec";
                fillrec.setBackground(MyPaintChooser.getNewColor());//sets selected button to current chosen color and rest to BLACK
                clear.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                linedraw.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Filled Oval":
                identifier = "FillOval";
                filloval.setBackground(MyPaintChooser.getNewColor());
                clear.setBackground(Color.BLACK);
                fillrec.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                linedraw.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Empty Rectangle": 
                identifier = "EmptyRec";
                emptyrec.setBackground(MyPaintChooser.getNewColor());
                clear.setBackground(Color.BLACK);
                fillrec.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                linedraw.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Empty Oval": 
                identifier = "EmptyOval";
                emptyoval.setBackground(MyPaintChooser.getNewColor());
                clear.setBackground(Color.BLACK);
                fillrec.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                linedraw.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Line Drawing": 
                identifier = "LineDraw";
                linedraw.setBackground(MyPaintChooser.getNewColor());
                clear.setBackground(Color.BLACK);
                fillrec.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                opencolor.setBackground(Color.BLACK);
                break;
            case "Color Chooser"://when the color chooser button is pressed
                identifier = "ColorChooser";
                if(MyPaintChooser.isInstance()){//if there is already an instance of the color chooser dialog
                    MyPaintChooser.getInstance().mySetLocation();//realligns JDialog
                }
                else{//if one already exists
                    MyPaintChooser.getInstance();//otherwise it is called to open for the first time
                }
                opencolor.setBackground(MyPaintChooser.getNewColor());
                linedraw.setBackground(Color.BLACK);
                clear.setBackground(Color.BLACK);
                fillrec.setBackground(Color.BLACK);
                emptyrec.setBackground(Color.BLACK);
                filloval.setBackground(Color.BLACK);
                emptyoval.setBackground(Color.BLACK);
                break;
            default: System.out.println("Error ButtonJPanel:153");
        }
    }
    
    public void changeColor(Color c){//called by MyPaintChooser when it changes the color -- keeps the color of the selected button updated
        switch(identifier){
            case "FillRec":
                fillrec.setBackground(c);
                break;
            case "FillOval":
                filloval.setBackground(c);
                break;
            case "EmptyRec":
                emptyrec.setBackground(c);
                break;
            case "EmptyOval":
                emptyoval.setBackground(c);
                break;
            case "LineDraw":
                linedraw.setBackground(c);
                break;
            case "ColorChooser":
                opencolor.setBackground(c);
                break;
        }
    }
    
    public static ButtonJPanel getInstance(){//returns the one instance of the button panel
        if(instance == null){//if one does not exist, it makes one
            instance = new ButtonJPanel();
        }
        return instance;   
    }
    
    
}
