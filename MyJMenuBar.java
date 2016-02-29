
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class MyJMenuBar extends JMenuBar implements ActionListener{//the menubar uses Action Listener
    private static MyJMenuBar instance;//the instance of the menubar
    
    private MyJMenuBar(){//private constructor
        super();
        
        JMenu file = new JMenu("File");//the menu selections
        JMenu view = new JMenu("View");
        JMenu help = new JMenu("Help");
        
        JMenuItem save = new JMenuItem("Save");//the menu item selections
        JMenuItem load = new JMenuItem("Load");
        JMenuItem exit = new JMenuItem("Exit");
        JMenuItem about = new JMenuItem("About");
        
        exit.addActionListener(this);//action listeners for each sub-menu selection
        save.addActionListener(this);
        about.addActionListener(this);
        load.addActionListener(this);
        
        add(file);//adds the menus to the menu bar
        add(view);
        add(help);
        
        file.add(save);//adds the menu items to the appropriate menu
        file.add(load);
        file.add(exit);
        help.add(about);
    }
    
    public static MyJMenuBar getInstance(){//creates and returns the instance of MyJMenuBar
        if(instance == null){
            instance = new MyJMenuBar();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {//when a menu item is pressed
        switch(e.getActionCommand()){
            case "Save": 
                PaintJPanel.getInstance().saveImage();//calls saveImage method of paint panel
                break;
            case "Load":
                PaintJPanel.getInstance().loadImage();//calls loadImage method of paint panel
                break;
            case "Exit": 
                MyJFrame.getInstance().dispose();//same as pressing the exit button on the JFrame
                break;
            case "About": 
                AboutDialog.getInstance().pack();//repacks the JDialog so its created in the same manner every time
                AboutDialog.getInstance().setVisible(true);//makes the JDialog visible
                break;
        }
    }
}
