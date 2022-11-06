
package AlphaBeta;

import java.awt.Component;
import java.awt.event.MouseListener;
import java.awt.Font;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelTabla extends JPanel
{
    private static final long serialVersionUID = 1L;
    private JLabel[][] lblTablero;
    private controla cp;
    
    public PanelTabla(final controla c) {
        this.setLayout(new GridLayout(3, 3));
        this.setBackground(Color.black);
        this.lblTablero = new JLabel[3][3];
        this.cp = c;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                (this.lblTablero[i][j] = new JLabel("")).
                setBorder(new CompoundBorder(new EmptyBorder(0, 0, 0, 0)
                , BorderFactory.createLineBorder(Color.white)));
                this.lblTablero[i][j].setHorizontalAlignment(0);
                this.lblTablero[i][j].setVerticalAlignment(0);
                this.lblTablero[i][j].setForeground(Color.white);
                this.lblTablero[i][j].setFont(new Font("myFont", 1, 100));
                this.lblTablero[i][j].setText(" ");
                this.lblTablero[i][j].addMouseListener(new labelaccion(i, j, this.lblTablero[i][j], this.cp));
                this.add(this.lblTablero[i][j]);
            }
        }
    }
    
    public void setText(final int row, final int col, final String txt) {
        this.lblTablero[row][col].setText(txt);
    }
    
    public String getText(final int row, final int col) {
        return this.lblTablero[row][col].getText();
    }
    
    public void ActualizarTablero(final int[][] tablero) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (tablero[i][j] == -1) {
                    this.lblTablero[i][j].setText("X");
                }
                else if (tablero[i][j] == 1) {
                    this.lblTablero[i][j].setText("O");
                }
            }
        }
    }
}

