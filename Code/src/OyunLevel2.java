
import jaco.mp3.player.MP3Player;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;


public class OyunLevel2 extends JPanel implements KeyListener,ActionListener{
    public Timer timer = new Timer(5, this);
    public static final String tieBlast = "C:\\Users\\pc\\Desktop\\Umut\\Prog\\Java\\Projeler\\UzayOyunuProjesi\\TIEBlastSound.wav";
    public static final String blast = "C:\\Users\\pc\\Desktop\\Umut\\Prog\\Java\\Projeler\\UzayOyunuProjesi\\blast.wav";
    public static final String lightsaber = "C:\\Users\\pc\\Desktop\\Umut\\Prog\\Java\\Projeler\\UzayOyunuProjesi\\lightsaber.wav";
    MP3Player mP3Player1 = new MP3Player(new File(tieBlast));
    MP3Player mP3Player2 = new MP3Player(new File(blast));
    MP3Player mP3Player3 = new MP3Player(new File(lightsaber));
    public int gecen_sure = 0;
    public int harcanan_ates = 0;
    private BufferedImage image;
    private BufferedImage background;
    private BufferedImage asteroid;
    private BufferedImage droid;
    private BufferedImage explosion;
    private BufferedImage gameOver;
    private ArrayList<Ates> atesler = new ArrayList<>();
    private int atesdirY = 5;
    private int topX = 0;
    private int topdirX = 2;
    private int uzayGemisiX = 0;
    private int dirUzayX = 20;

    public int getGecen_sure() {
        return gecen_sure;
    }

    public int getHarcanan_ates() {
        return harcanan_ates;
    }
    
    
    public boolean kontrolEt(){
        for(Ates ates : atesler){
            if(new Rectangle(ates.getX(), ates.getY(),5,20).intersects(new Rectangle(topX,0,droid.getWidth()/15,droid.getHeight()/15))){
                return true;
            }  
        }
        return false;
    }
    
    public OyunLevel2(){        
        try {            
            image = ImageIO.read(new FileInputStream(new File("tie_fighter.png")));
            background = ImageIO.read(new FileInputStream(new File("space.jpg")));
            asteroid = ImageIO.read(new FileInputStream(new File("asteroid.png")));
            droid = ImageIO.read(new FileInputStream(new File("droid.png")));
            explosion = ImageIO.read(new FileInputStream(new File("explosion.png")));
            gameOver = ImageIO.read(new FileInputStream(new File("game_over.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        //timer.start();
        
    }
    
        @Override
    public void paint(Graphics g) {
        super.paint(g);
        gecen_sure += 5;
        Color brown = new Color(102,51,0);//brown
        g.drawImage(background, 0, 0, null);
        g.setColor(brown);
        //g.fillOval(topX, 0, 20, 20);
        //g.drawImage(asteroid, 0, 0,asteroid.getWidth()/25,image.getHeight()/10, this);
        g.drawImage(droid, topX, 0,droid.getWidth()/15,droid.getHeight()/10, this);
        g.drawImage(image, uzayGemisiX, 490, image.getWidth() / 7, image.getHeight() / 7, this);
        
        for(Ates ates : atesler){
            if(ates.getY() < 0){
                atesler.remove(ates);
            }
        }
        
        g.setColor(Color.GREEN);
        for(Ates ates : atesler){
            g.fillRect(ates.getX(), ates.getY(), 5, 20);
        }
        
        if(kontrolEt()){
            g.drawImage(explosion, topX - 50, -10, explosion.getWidth() / 7, explosion.getHeight() / 7, this);
            mP3Player3.play();
            mP3Player2.play();   
            timer.stop();
            OyunEkrani.totalTime += gecen_sure;
            OyunEkrani.totalBlast += harcanan_ates;
            //g.drawImage(gameOver, 140, 100, gameOver.getWidth()/7,gameOver.getHeight()/7, this);
            String message = "GAME OVER" + "\n" +
                             "Total Fire: " + harcanan_ates + "\n" +
                             "Total Time: " + gecen_sure / 1000.0 + " seconds";
            Level2Completed lc2 = new Level2Completed();
            lc2.setVisible(true);
            //AraFrame af = new AraFrame();
            //af.setVisible(true);
            //JOptionPane.showMessageDialog(this, message);
            //System.exit(0);
        }
        //validate();
    }
    
    @Override
    public void repaint() {
        super.repaint(); 
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int c = e.getKeyCode();
        
        if(c == KeyEvent.VK_LEFT){
            if(uzayGemisiX <= 0){
                uzayGemisiX = 0;
            }
            else{
                uzayGemisiX -= dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_RIGHT){
            if(uzayGemisiX >= 725){
                uzayGemisiX = 725;
            }
            else{
                uzayGemisiX += dirUzayX;
            }
        }
        else if(c == KeyEvent.VK_SPACE){
            atesler.add(new Ates(uzayGemisiX +32, 490));
            harcanan_ates++;
            mP3Player1.play();
      
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Ates ates : atesler){
            ates.setY(ates.getY() - atesdirY);
        }
        
        topX += topdirX;
        if(topX >= 750){
            topdirX = -topdirX;
        }
        if(topX <= 0){
            topdirX = -topdirX;
        }
        repaint();
    }
    
}
