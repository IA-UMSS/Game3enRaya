
package AlphaBeta;

import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class juegograph extends JFrame implements ActionListener
{
    private static final long serialVersionUID = 1L;
    private PanelTabla pt;
    private controla cv;
    private JMenuBar barr;
    private JMenuItem item1;
    private JMenuItem item2;
    private JMenu empezars;
    private JMenu novo;
    private JLabel mensajes;
    
    public juegograph() {
        this.setLayout(null);
        this.setSize(365, 500);
        this.setBackground(Color.black);
        this.setJMenuBar(this.barr = new JMenuBar());
        this.empezars = new JMenu("EmpezarPartida");
        this.novo = new JMenu("NuevaPartida");
        (this.item1 = new JMenuItem("Empezar")).addActionListener(this);
        this.empezars.add(this.item1);
        (this.item2 = new JMenuItem("Nuevo")).addActionListener(this);
        this.novo.add(this.item2);
        this.barr.add(this.empezars);
        this.barr.add(this.novo);
        this.getContentPane().setBackground(Color.black);
        this.cv = new controla();
        (this.pt = new PanelTabla(this.cv)).setBounds(0, 0, 350, 350);
        this.getContentPane().add(this.pt);
        (this.mensajes = new JLabel()).setFont(new Font("Arial", 1, 22));
        this.mensajes.setBounds(120, 352, 200, 100);
        this.getContentPane().add(this.mensajes);
        this.cv.conectar(this.pt, this.mensajes);
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        if (e.getActionCommand().equals("Empezar")) {
            final int Empezar = 1;
            this.cv.empezar(Empezar);
        }
        else if (e.getActionCommand().equals("Nuevo")) {
            this.cv.Nuevo();
        }
    }
}