import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class background extends JPanel implements ActionListener {
    private Timer timer;
    private control buah;
    private ArrayList fruits;
    private boolean ingame;
    private int B_WIDTH;
    private int B_HEIGHT;
    AudioClip soundTrack;
    AudioClip sound;
    Image gambar;
    private Image back;
    

    private int[][] pos = {
        {89,-30}, {29,-90}, {59,-120},
        {109,-280}, {259,-390}, {150,-590},
        {170,-490}, {70,-510}, {60,-530}, 
        {90,-540}, {45,-560}, {139,-580},
        {80,-590}, {50,-660}, {239,-680},
        {30,-700}, {180,-740}, {50,-760},
        {109,-780}, {259,-790}, {150,-790},
        {220,-810}, {128,-820}, {20,-860},
        {259,-900}, {200,-920}, {159,-930},  
        {59,-940}, {209,-980},  {30,-990},  
        {89,-1380}, {29,-1680}, {59,-2000} 
     };

    public background() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        ingame = true;

        setSize(400,400);
        buah = new control();

        initFruits();
        soundTrack=Applet.newAudioClip(getClass().getResource("backsound.wav"));
        soundTrack.loop();
        timer = new Timer(20, this);
        timer.start();
        
    }
    @Override
    public void addNotify() {
        super.addNotify();
        B_WIDTH = getWidth();
        B_HEIGHT = getHeight();   
    }

    public final void initFruits() {
        fruits = new ArrayList();
        for (int i=0; i<pos.length; i++ ) {
            fruits.add(new buah(pos[i][0], pos[i][1]));
        }
    }
    
     public static Image loadImage(String fileName) {
        ImageIcon icon = new ImageIcon(fileName);
        return icon.getImage();
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (ingame) {

            Graphics2D g2d = (Graphics2D)g;

            if (buah.isVisible()) {
                g2d.drawImage(buah.getImage(), buah.getX(), buah.getY(),this);
            } 

            for (int i = 0; i < fruits.size(); i++) {
                buah a = (buah)fruits.get(i);
                if (a.isVisible()) {
                    g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
                }
            }
            g2d.setColor(Color.red);
            g2d.drawString("Target Buah: " + fruits.size(), 5, 15);
        } else {
            String msg = "Permainan Selesai";
            Font small = new Font("Helvetica", Font.BOLD, 30);
            FontMetrics metr = this.getFontMetrics(small);

            g.setColor(Color.red);
            g.setFont(small);
            g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) /2, B_HEIGHT /2);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (fruits.isEmpty()) {
            ingame = false;
        }

        for (int i = 0; i < fruits.size(); i++) {
            buah a = (buah) fruits.get(i);
            if (a.isVisible()) { 
                a.move();
            }
            else {
                fruits.remove(i);
            }
        }
        buah.move();
        checkCollisions();
        repaint();  
    }
    public void checkCollisions() {
        Rectangle r3 = buah.getBounds();
        for (int j = 0; j<fruits.size(); j++) {
            buah a = (buah) fruits.get(j);
            Rectangle r2 = a.getBounds();

            if (r3.intersects(r2)) {
                a.setVisible(false);
                sound = Applet.newAudioClip(getClass().getResource("soft-slidertick.wav"));
                sound.play();
            }
        }
    }
    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
           buah.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            buah.keyPressed(e);
        }
    }
}  