package CJP.Server.Suporte;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {
    private static int SPACING = 20;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        for (int x = 0; x < getWidth(); x += SPACING) {
            for (int y = 0; y < getHeight(); y += SPACING) {
                g.fillOval(x, y, 4, 4); // Desenha um pequeno círculo como ponto
            }
        }
    }
}