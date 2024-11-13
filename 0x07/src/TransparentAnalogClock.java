import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class TransparentAnalogClock extends JPanel {
    private int centerX, centerY, clockRadius;

    public TransparentAnalogClock() {
        setPreferredSize(new Dimension(400, 400));
        setBackground(new Color(0, 0, 0, 0));  // 背景透明
        startClock();
    }

    private void startClock() {
        Timer timer = new Timer(true);  // 使用背景執行緒
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1000);  // 每秒重繪一次
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 取得時鐘中心和半徑
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        clockRadius = Math.min(centerX, centerY) - 10;

        // 開啟抗鋸齒
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 畫時鐘圓框
        g2d.setColor(Color.BLACK);
        g2d.drawOval(centerX - clockRadius, centerY - clockRadius, 2 * clockRadius, 2 * clockRadius);

        // 畫刻度和數字
        drawClockMarks(g2d);

        // 畫指針
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        drawHand(g2d, hour * 30 + minute / 2, clockRadius * 0.5, Color.BLACK);  // 時針
        drawHand(g2d, minute * 6, clockRadius * 0.7, Color.BLUE);                // 分針
        drawHand(g2d, second * 6, clockRadius * 0.9, Color.RED);                 // 秒針
    }

    private void drawClockMarks(Graphics2D g) {
        for (int i = 1; i <= 12; i++) {
            double angle = Math.toRadians(i * 30); // 每小時的角度
            int xOuter = (int) (centerX + clockRadius * Math.cos(angle));
            int yOuter = (int) (centerY - clockRadius * Math.sin(angle));
            int xInner = (int) (centerX + (clockRadius - 10) * Math.cos(angle));
            int yInner = (int) (centerY - (clockRadius - 10) * Math.sin(angle));

            // 畫刻度
            g.drawLine(xOuter, yOuter, xInner, yInner);

            // 計算數字位置
            int xNumber = (int) (centerX + (clockRadius - 25) * Math.cos(angle));
            int yNumber = (int) (centerY - (clockRadius - 25) * Math.sin(angle));

            // 畫數字
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString(String.valueOf(i), xNumber - 5, yNumber + 5); // 調整位置使數字居中
        }
    }

    private void drawHand(Graphics2D g, double angleDegrees, double length, Color color) {
        double angle = Math.toRadians(angleDegrees - 90);  // 轉換到 12 點方向
        int x = (int) (centerX + length * Math.cos(angle));
        int y = (int) (centerY + length * Math.sin(angle));
        g.setColor(color);
        g.drawLine(centerX, centerY, x, y);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Transparent Analog Clock");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 400);
            frame.setUndecorated(true);  // 去除邊框
            frame.setBackground(new Color(0, 0, 0, 0));  // 設置背景透明
            frame.setLocationRelativeTo(null);

            TransparentAnalogClock clock = new TransparentAnalogClock();
            frame.add(clock);
            frame.setVisible(true);
        });
    }
}
