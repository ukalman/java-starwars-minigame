
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Umut
 */
public class MainMenu extends JPanel {
    
    private BufferedImage starWarsLogo;
    
    public MainMenu(){
        setSize(800,600);
        setBounds(550, 200, 800, 600);
        setBackground(Color.BLACK);
        try {
            starWarsLogo = ImageIO.read(new FileInputStream(new File("star_wars.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(starWarsLogo, 100, 100, starWarsLogo.getWidth(),starWarsLogo.getHeight(),this);
    }
    
    
}
