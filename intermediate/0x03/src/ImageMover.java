import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ImageMover extends JFrame {

    private final JLabel imageLabel;
    private int mouseX, mouseY;

    public ImageMover() {
        setTitle("Image Mover");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 

        ImageIcon icon = new ImageIcon("public/image.jpg"); 
        Image scaledImage = icon.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        imageLabel = new JLabel(scaledIcon);

        imageLabel.setBounds(0, 0, scaledIcon.getIconWidth(), scaledIcon.getIconHeight());

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getXOnScreen();
                mouseY = e.getYOnScreen();
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen() - mouseX;
                int y = e.getYOnScreen() - mouseY;
                imageLabel.setLocation(x, y);
            }
        };

        imageLabel.addMouseListener(mouseAdapter);
        imageLabel.addMouseMotionListener(mouseAdapter);

        add(imageLabel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageMover());
    }
}
