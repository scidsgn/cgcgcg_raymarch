package com.scidsgn.raymarch.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class RenderPanel extends JPanel {
    private BufferedImage render;

    public RenderPanel() {
        setPreferredSize(new Dimension(600, 600));

        render = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);
    }

    public void setRender(BufferedImage render) {
        this.render = render;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.clearRect(0, 0, 600, 600);
        g.drawImage(render, 0, 0, null);
    }
}
