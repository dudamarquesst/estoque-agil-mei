package com.estoque.ui;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Inicia a interface gráfica de forma segura
        SwingUtilities.invokeLater(() -> {
            EstoqueGUI gui = new EstoqueGUI();
            gui.setVisible(true);
        });
    }
}
