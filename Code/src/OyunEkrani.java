
import jaco.mp3.player.MP3Player;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class OyunEkrani extends JFrame {
    public static double totalTime = 0;
    public static int totalBlast = 0;
    public static final String tieFighterTheme = "C:\\Users\\pc\\Desktop\\Umut\\Prog\\Java\\Projeler\\UzayOyunuProjesi\\tie_fighter_theme.wav";
    MP3Player mpp = new MP3Player(new File(tieFighterTheme));
    Level1 level1 = new Level1();
    public static Level2 level2 = new Level2();
    public static Level3 level3 = new Level3();
    public static Level4 level4 = new Level4();
    public static Level5 level5 = new Level5();
    public static gameOver gOver = new gameOver();
    public static JPanel containerPanel = new JPanel();
    JPanel mainMenu = new JPanel();
    public static Oyun oyun = new Oyun();
    public static OyunLevel2 oyun2 = new OyunLevel2();
    public static OyunLevel3 oyun3 = new OyunLevel3();
    public static OyunLevel4 oyun4 = new OyunLevel4();
    public static OyunLevel5 oyun5 = new OyunLevel5();
    JLabel tieFighterAttack = new JLabel("Tie Fighter Attack");
    JButton playButton = new JButton("PLAY");
    JButton exitButton = new JButton("EXIT");
    public static CardLayout c1 = new CardLayout();
    Font font1 = new Font("Georgia", Font.PLAIN, 20);
    Font font2 = new Font("Impact", Font.BOLD, 30);
    JLabel imageLabel = new JLabel();
    JLabel umutKalman = new JLabel("By Umut Kalman");
    BufferedImage starWarsLogo; 
    
    public OyunEkrani(String title) throws HeadlessException {
        super(title);
        try {
            starWarsLogo = ImageIO.read(new FileInputStream(new File("star_wars.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(OyunEkrani.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image newLogo = starWarsLogo.getScaledInstance(starWarsLogo.getWidth()/3, starWarsLogo.getHeight()/3, Image.SCALE_DEFAULT);
        containerPanel.setLayout(c1);
        setSize(800,600);
        setBounds(550, 200, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainMenu.setSize(800,600);
        mainMenu.setBounds(550, 200, 800, 600);
        mainMenu.setBackground(Color.BLACK);
        playButton.setSize(20,20);
        playButton.setBounds(340,400, 100, 50);
        playButton.setFont(font1);
        tieFighterAttack.setForeground(Color.GREEN);
        tieFighterAttack.setFont(font2);
        tieFighterAttack.setSize(30,30);
        tieFighterAttack.setBounds(275, 275, 250, 50);
        exitButton.setSize(20,20);
        exitButton.setBounds(340,475, 100, 50);
        exitButton.setFont(font1);
        umutKalman.setSize(20,20);
        umutKalman.setBounds(600, 500, 200, 20);
        umutKalman.setFont(font1);
        imageLabel.setSize(50,50);
        imageLabel.setBounds(185, -50, 400, 400);
     
        
        imageLabel.setIcon(new ImageIcon(newLogo));
        mainMenu.setLayout(null);
        mainMenu.add(playButton);
        mainMenu.add(exitButton);
        mainMenu.add(imageLabel);
        mainMenu.add(umutKalman);
        mainMenu.add(tieFighterAttack);
        containerPanel.add(mainMenu,"1");
        containerPanel.add(level1,"2");
        containerPanel.add(oyun,"3"); 
        containerPanel.add(level2,"4");
        containerPanel.add(oyun2,"5");
        containerPanel.add(level3,"6");
        containerPanel.add(oyun3,"7");
        containerPanel.add(level4,"8");
        containerPanel.add(oyun4,"9");
        containerPanel.add(level5,"10");
        containerPanel.add(oyun5,"11");
        containerPanel.add(gOver,"12");
        
        c1.show(containerPanel, "1");
        mpp.play();
        
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                c1.show(containerPanel, "2");
                level1.requestFocus();
                level1.addKeyListener(level1);
                /*oyun.timer.start();
                oyun.requestFocus();//focusu panele alacağız.

                oyun.addKeyListener(oyun);
                oyun.setFocusable(true);
                oyun.setFocusTraversalKeysEnabled(false);//decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus.
                */
                //ekran.add(oyun);
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(containerPanel);
        /*BufferedImage img = null;
        try {
            img = ImageIO.read(new File("space.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image dimg = img.getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon imageIcon = new ImageIcon(dimg);
        setContentPane(new JLabel(imageIcon));
        */
    }
    /*public static void retry(){
        oyun.retry();
        
        
    }*/
    
    
    public static void main(String[] args) {
        OyunEkrani ekran = new OyunEkrani("Star Wars");
        

        ekran.setResizable(false);
        ekran.setFocusable(false);//focus frame'de değil, panelde olacak.
        ekran.setVisible(true);
        
        /*ekran.setSize(800,600);
        ekran.setBounds(550, 200, 800, 600);
        ekran.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        */
        
        
        /*JPanel menu = new JPanel();
        JButton playButton = new JButton("PLAY");
        JButton exitButton = new JButton("EXIT");
        menu.add(playButton);
        menu.add(exitButton);*/

    /*   MainMenu mainMenu = new MainMenu();
        mainMenu.requestFocus();
        mainMenu.setFocusable(true);
        ekran.add(mainMenu);
        ekran.setVisible(true);
        System.out.println("auvva");
      */  
        
      
        
            

                /*oyun.requestFocus();//focusu panele alacağız.

                oyun.addKeyListener(oyun);
                oyun.setFocusable(true);
                oyun.setFocusTraversalKeysEnabled(false);//decides whether or not focus traversal keys (TAB key, SHIFT+TAB, etc.) are allowed to be used when the current Component has focus.
                */
                //ekran.add(oyun);
                //oyun.setVisible(true);
                //ekran.setVisible(true);
            
        
        
    }
    }


