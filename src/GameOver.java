import java.awt.*;
import java.awt.FontMetrics;

public class GameOver {
    private String msg = "Game Over";
    private Font small = new Font("Helvetica", Font.BOLD, 14);

    public void draw(Graphics g) {
        FontMetrics fm = g.getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (Constants.B_WIDTH - fm.stringWidth(msg)) / 2,
                Constants.B_HEIGHT / 2);
    }
}
