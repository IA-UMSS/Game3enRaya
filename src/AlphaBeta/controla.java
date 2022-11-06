
package AlphaBeta;

import java.awt.Color;
import javax.swing.JLabel;
public class controla
{
    public tablero tablero;
    public PanelTabla pnlTablero;
    public int Empezar;
    public JLabel d;
    
    public controla() {
        this.Empezar = 0;
        (this.tablero = new tablero()).imprimir();
    }
    
    public void conectar(final PanelTabla pnlTablero, final JLabel g) {
        this.pnlTablero = pnlTablero;
        this.d = g;
    }
    
    public void empezar(final int Emp) {
        this.Empezar = Emp;
        if (this.Empezar == 1) {
            this.d.setForeground(Color.CYAN);
            this.d.setText("JUEGA");
        }
    }
    
    public void pintar(final int i, final int j, final int G) {
        if (G == 0) {
            this.pnlTablero.setText(i, j, "X");
        }
        else if (G == 1) {
            this.pnlTablero.setText(i, j, "O");
        }
    }
    
    public void Nuevo() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.pnlTablero.setText(i, j, " ");
                this.Empezar = 0;
                this.d.setText(" ");
            }
        }
        this.tablero.nuevoTablero();
    }
    
    public void Actualizar(final int row, final int col) {
        if (this.tablero.ValidarInicio() != 9 || this.Empezar == 1) {
            if (!this.tablero.validarEmpate()) {
                this.tablero.primerTurno(row, col);
                this.pnlTablero.ActualizarTablero(this.tablero.getTablero());
                if (this.tablero.validarGanador() == 1) {
                    this.d.setForeground(Color.RED);
                    this.d.setText("HAS PERDIDO");
                }
                else if (this.tablero.validarGanador() == -1) {
                    this.d.setForeground(Color.GREEN);
                    this.d.setText("HAS GANADO");
                }
                else if (this.tablero.validarEmpate()) {
                    this.d.setForeground(Color.BLUE);
                    this.d.setText("EMPATE");
                }
            }
        }
        else {
            this.d.setForeground(Color.CYAN);
            this.d.setText("Por favor inicia.....");
        }
    }
}