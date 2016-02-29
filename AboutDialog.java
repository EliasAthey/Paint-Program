
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Elias
 */
public class AboutDialog extends JDialog{//The JDialog window that holds my logo
    
    private static AboutDialog instance;//the one instance of the JDialog
    private ImageIcon icon;//The icon used for the JDialog
    
    private AboutDialog(){//constructor
        super(MyJFrame.getInstance(), "About");//attaches the JDialog to JFrame with the Title: About
        
        try{
            icon = new ImageIcon(ImageIO.read(new File("src/JPaintLogo.png")));//converts file to image, image to icon
            add(new JLabel(icon));//adds a label to hold the image
            setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);//never recreates an instance, just hides this one
            getContentPane().setSize(icon.getIconWidth(), icon.getIconHeight());//sets the size of the JDialog to the size of the icon
            setLocationRelativeTo(MyJFrame.getInstance());//places the window in the center of the JFrame
            setVisible(true);//makes it visible
        }
        catch(IOException e){//thrown if fil does not exist
            System.out.println("IOException AboutDialog:33 File not found");
        }
    }
    
    public static AboutDialog getInstance(){//returns the one instance of AboutJDialog
        if(instance == null){
            instance = new AboutDialog();
        }
        return instance;
    }
}
