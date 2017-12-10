import java.awt.Component;
import javax.swing.JFrame;
    public class frame extends JFrame {

    public frame() {
        Component add;
        add = add(new background());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setLocationRelativeTo(null);
        setTitle("CTB in Java");
        setResizable(false);
        setVisible(true);
     
    }

    public static void main(String[] args) {
        frame frame = new frame();
    }
}