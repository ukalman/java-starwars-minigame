
import jaco.mp3.player.MP3Player;
import java.awt.Graphics;
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
import java.awt.Color;
import java.awt.Rectangle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Timer;


class Ates{
    private int x;
    private int y;

    public Ates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}

public class Oyun extends JPanel implements KeyListener,ActionListener{
    private final String level = "1";
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
    private int topdirX = 1;
    private int uzayGemisiX = 0;
    private int dirUzayX = 20;
    
    /*public void retry(){
        removeAll();
        revalidate();
        repaint();
        timer.start();
    }*/

    public String getLevel() {
        return level;
    }
        
    public int getGecen_sure() {
        return gecen_sure;
    }

    public void setGecen_sure(int gecen_sure) {
        this.gecen_sure = gecen_sure;
    }

    public int getHarcanan_ates() {
        return harcanan_ates;
    }

    public void setHarcanan_ates(int harcanan_ates) {
        this.harcanan_ates = harcanan_ates;
    }
    
    
    public boolean kontrolEt(){
        for(Ates ates : atesler){
            if(new Rectangle(ates.getX(), ates.getY(),5,20).intersects(new Rectangle(topX,0,droid.getWidth()/15,droid.getHeight()/15))){
                return true;
            }  
        }
        return false;
    }
    public Oyun(){        
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
    public void atesSesi(){
        try {
            AudioInputStream stream = AudioSystem.getAudioInputStream(new File("TIEBlastSound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Oyun.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
            Level1Completed lc1 = new Level1Completed();
            lc1.setVisible(true);
            
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
            //atesSesi();
            
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
