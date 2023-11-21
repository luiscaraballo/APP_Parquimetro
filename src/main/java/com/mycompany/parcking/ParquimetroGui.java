
package com.mycompany.parcking;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.ImageIcon;



public class ParquimetroGui {
    //Declaracion de variables
    private JFrame mainFrame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private JTextField placaTextField;
    private JLabel tiempoLabel, infoLabel;
    private Parquimetro parquimetro;
    private LocalDate fechaEntrada;
    private LocalTime horaEntrada;
    private Timer timer;
    private JLabel bienvenido;
    public ParquimetroGui() {
        parquimetro = new Parquimetro();
        initializeUI();   
    }

    
    
    private void initializeUI() {
        mainFrame = new JFrame("Parquímetro");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 700);
        mainFrame.setLocationRelativeTo(null);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setSize(800, 600);
         
        ImageIcon originalIcon = new ImageIcon("C:/Users/nikens.pierrelouis/Desktop/img.png"); // Reemplaza con la ruta de tu imagen
            Image image = originalIcon.getImage(); // Convierte ImageIcon a Image
                Image resizedImage = image.getScaledInstance(400, 360, Image.SCALE_SMOOTH); // Cambia 200, 200 al tamaño deseado
                    ImageIcon imageIcon = new ImageIcon(resizedImage); // Convierte de nuevo a ImageIcon  
                        JLabel imageLabel = new JLabel(imageIcon);
                       
        setupRegistroPanel(imageLabel);
            setupTiempoPanel();
                mainFrame.add(cardPanel);
                    mainFrame.setVisible(true);
    }

    
    
    private void setupRegistroPanel(JLabel imageLabel) {
        JPanel registroPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
                placaTextField = new JTextField(15);        
                    bienvenido = new JLabel("Bienvenido estimado, si desea usar el estacionamiento favor ingresar su numero de placa en el siguiente cuadro");
         
    JButton registrarButton = new JButton("Registrar Vehículo");
        registrarButton.addActionListener(this::registrarVehiculo);

    // Configuraciones para el mensage de bienvenido
    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(10, 0, 280, 0); // Margen arriba y abajo del bienvenido
                registroPanel.add(bienvenido, gbc);
    
    // Configuración para placaTextField (campo donde se ingresara la placa del auto)
    gbc = new GridBagConstraints(); // Reinicia gbc
        gbc.gridx = 0; // Columna 0
            gbc.gridy = 0; // Fila 0
                gbc.insets = new Insets(5, 70, 70, 0);
                    gbc.anchor = GridBagConstraints.LINE_END;
                        registroPanel.add(placaTextField,gbc);
          
    // Configuración para registrarButton (el boton para dejar guardada la placa
    gbc = new GridBagConstraints(); // Reinicia gbc
        gbc.gridx = 0; // Columna 0 (Misma columna que placaTextField)
            gbc.gridy = 0; // Misma fila que placaTextField
                gbc.insets = new Insets(5, 10, 20, 14); // Espacio a la izquierda para separarlo de placaTextField
                    gbc.anchor = GridBagConstraints.LINE_END;
                        registroPanel.add(registrarButton, gbc);                   
                                                     
              // Configuración para la imagen
    gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
            gbc.insets = new Insets(200, 0, 20, 200);
                registroPanel.add(imageLabel, gbc);
                               
    cardPanel.add(registroPanel, "Registro");
    }

     
     
     
    private void setupTiempoPanel() {
        JPanel tiempoPanel = new JPanel();
            infoLabel = new JLabel();
                tiempoLabel = new JLabel("00:00:00");
                    JButton calcularButton = new JButton("Calcular Monto");
                        calcularButton.addActionListener(this::calcularMonto);
                        
        tiempoPanel.add(infoLabel);
            tiempoPanel.add(tiempoLabel);
                tiempoPanel.add(calcularButton);

        cardPanel.add(tiempoPanel, "Tiempo");
    }

    
     
    private void registrarVehiculo(ActionEvent e) {
        String placa = placaTextField.getText();
            try {
                 parquimetro.registrarVehiculo(placa);
                    }          
                        catch (Exception ex) {
                            Logger.getLogger(ParquimetroGui.class.getName()).log(Level.SEVERE, null, ex);
                               }
            
        fechaEntrada = LocalDate.now();
            horaEntrada = LocalTime.now();
                infoLabel.setText("Vehículo asociado al numero de placa: " + "[ "+ placa + " ] " +" fue ingresado el: " + fechaEntrada +" a las: "+ horaEntrada + " Y lleva" );
                    startTimer();
                        cardLayout.show(cardPanel, "Tiempo");
    }

    
    
    private void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(1000, e -> updateTimer());
        timer.start();
    }

    
    private void updateTimer() {
        Duration duracion = Duration.between(horaEntrada, LocalTime.now());
            long horas = duracion.toHours();
                long minutos = duracion.toMinutesPart();
                    long segundos = duracion.toSecondsPart();
                        tiempoLabel.setText(String.format("%02d:%02d:%02d" +" horas estacionado" , horas, minutos, segundos));
    }

    private void calcularMonto(ActionEvent e) {
        try {
            timer.stop();
                // Esa parte del codigo es para calcular el monto y monstrarlo en pantalla
                double monto = parquimetro.calcularMonto(placaTextField.getText()); // Ejemplo, ajusta según tu lógica
                    JOptionPane.showMessageDialog(mainFrame, "Monto a pagar: " + monto);
                   }    
                        catch (Exception ex) {
                            Logger.getLogger(ParquimetroGui.class.getName()).log(Level.SEVERE, null, ex);
                                }
    }

    
}














