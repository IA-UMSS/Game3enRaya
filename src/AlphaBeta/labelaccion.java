
package AlphaBeta;

import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;

public class labelaccion extends MouseAdapter
{
    private JLabel label;
    private controla controlador;
    private int row;
    private int col;
    
    public labelaccion(final int row, final int col, final JLabel label, final controla controlador) {
        this.label = label;
        this.controlador = controlador;
        this.row = row;
        this.col = col;
    }
    
    @Override
    public void mouseClicked(final MouseEvent evento) {
        if (this.label.getText() != "") {
            if (evento.isMetaDown()) {
                System.out.println("Bot\u00f3n derecho.");
            }
            else if (evento.isAltDown()) {
                System.out.println("Bot\u00f3n medio.");
            }
            else {
                this.controlador.Actualizar(this.row, this.col);
            }
        }
    }
}