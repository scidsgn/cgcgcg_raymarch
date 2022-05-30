package com.scidsgn.raymarch.gui;

import com.scidsgn.raymarch.state.State;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private final RenderPanel renderPanel;
    private final SettingsPanel settingsPanel;

    public AppFrame(State state) {
        super("CGCGCG3");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setResizable(false);

        renderPanel = new RenderPanel();
        settingsPanel = new SettingsPanel(state, renderPanel);

        prepareUI();

        pack();
    }

    void prepareUI() {
        add(BorderLayout.CENTER, renderPanel);
        add(BorderLayout.EAST, settingsPanel);
    }
}
