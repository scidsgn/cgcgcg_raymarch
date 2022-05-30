package com.scidsgn.raymarch.gui;

import com.scidsgn.raymarch.state.State;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final State state;
    private final RenderPanel renderPanel;

    public SettingsPanel(State state, RenderPanel renderPanel) {
        this.state = state;
        this.renderPanel = renderPanel;

        prepareUI();
    }

    void prepareUI() {
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Object:"));

        JComboBox<String> objectSelector = new JComboBox<>(state.getObjectNames());
        objectSelector.setSelectedIndex(0);
        objectSelector.addActionListener(e -> {
            state.setCurrentObject(objectSelector.getSelectedIndex());
        });
        add(objectSelector);

        add(new JLabel("Material:"));

        JComboBox<String> shaderSelector = new JComboBox<>(state.getShaderNames());
        shaderSelector.setSelectedIndex(0);
        shaderSelector.addActionListener(e -> {
            state.setCurrentShader(shaderSelector.getSelectedIndex());
        });
        add(shaderSelector);

        add(new JLabel("Lighting setup (Basic only):"));

        JComboBox<String> lcSelector = new JComboBox<>(state.getLightingContextNames());
        lcSelector.setSelectedIndex(0);
        lcSelector.addActionListener(e -> {
            state.setCurrentLightingContext(lcSelector.getSelectedIndex());
        });
        add(lcSelector);

        add(new JLabel("HDRI (PBR only):"));

        JComboBox<String> hdriSelector = new JComboBox<>(state.getHDRINames());
        hdriSelector.setSelectedIndex(0);
        hdriSelector.addActionListener(e -> {
            state.setCurrentHDRI(hdriSelector.getSelectedIndex());
        });
        add(hdriSelector);

        add(new JLabel("Camera angle:"));

        JSlider cameraAngle = new JSlider(0, 360, 0);
        cameraAngle.addChangeListener(e -> {
            state.setCameraAngle((double) cameraAngle.getValue() * Math.PI / 180.);
        });
        add(cameraAngle);

        add(new JLabel(""));
        add(new JLabel(""));

        JButton renderBasic = new JButton("Render (Basic)");
        renderBasic.addActionListener(e -> {
            state.renderBasic(renderPanel);
        });
        add(renderBasic);

        JButton renderPBR = new JButton("Render (PBR)");
        renderPBR.addActionListener(e -> {
            state.renderPBR(renderPanel);
        });
        add(renderPBR);
    }
}
