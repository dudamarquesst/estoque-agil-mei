package com.estoque.ui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Erro ao iniciar o tema visual.");
        }

        SwingUtilities.invokeLater(() -> {
            EstoqueGUI gui = new EstoqueGUI();
            gui.setVisible(true);
        });
    }
}
