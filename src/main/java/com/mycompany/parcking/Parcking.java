

package com.mycompany.parcking;

import java.awt.event.ActionListener;
import javax.swing.SwingUtilities;

public class Parcking {
public static void main(String[] args) {
        
        SwingUtilities.invokeLater(ParquimetroGui::new);
    }
}

// Clase Timer para el cronómetro
class Timer extends javax.swing.Timer {
    public Timer(int delay, ActionListener listener) {
        super(delay, listener);
    }

 
     
     
 

}
