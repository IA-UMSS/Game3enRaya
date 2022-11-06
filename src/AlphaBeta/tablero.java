
package AlphaBeta;

import java.awt.Component;
import javax.swing.JOptionPane;

public class tablero
{
    int[][] tablero;
    int diag;
    int lineas;
    int columnas;
    int minmax;
    int min;
    int Gana;
    
    public tablero() {
        this.tablero = new int[3][3];
        this.diag = 0;
        this.lineas = 0;
        this.columnas = 0;
        this.minmax = -100;
        this.min = 100;
        this.Gana = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.tablero[i][j] = 0;
            }
        }
    }
    
    public void nuevoTablero() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                this.tablero[i][j] = 0;
            }
        }
        this.Gana = 0;
    }
    
    public void imprimir() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(" " + this.tablero[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int[][] getTablero() {
        return this.tablero;
    }
    
    public void primerTurno(final int row, final int col) {
        this.Ganador();
        if (this.Gana != 1 || this.Gana != -1) {
            if (this.tablero[row][col] == 0) {
                this.tablero[row][col] = -1;
                this.Ganador();
                if (this.Gana != 1 && this.Gana != -1) {
                    this.minMax();
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "campo ocupado");
            }
        }
    }
    
    public void Ganador() {
        if ((this.tablero[0][0] == -1 && this.tablero[1][1] == -1 && this.tablero[2][2] == -1) 
        || (this.tablero[0][2] == -1 && this.tablero[1][1] == -1 && this.tablero[2][0] == -1)) {
            this.Gana = -1;
        }
        for (int i = 0; i < 3; ++i) {
            if (this.tablero[i][0] == -1 && this.tablero[i][1] == -1 && this.tablero[i][2] == -1) {
                this.Gana = -1;
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (this.tablero[0][i] == -1 && this.tablero[1][i] == -1 && this.tablero[2][i] == -1) {
                this.Gana = -1;
            }
        }
        if ((this.tablero[0][0] == 1 && this.tablero[1][1] == 1 && this.tablero[2][2] == 1) 
        || (this.tablero[0][2] == 1 && this.tablero[1][1] == 1 && this.tablero[2][0] == 1)) {
            this.Gana = 1;
        }
        for (int i = 0; i < 3; ++i) {
            if (this.tablero[i][0] == 1 && this.tablero[i][1] == 1 && this.tablero[i][2] == 1) {
                this.Gana = 1;
            }
        }
        for (int i = 0; i < 3; ++i) {
            if (this.tablero[0][i] == 1 && this.tablero[1][i] == 1 && this.tablero[2][i] == 1) {
                this.Gana = 1;
            }
        }
    }
    
    public boolean minMax() {
        int temp_max = 0;
        int temp_min = 0;
        int temp_minmax = 0;
        int save_col = -1;
        int save_row = -1;
        this.minmax = -100;
        this.min = 100;
        int i = 0;
        int contador = 0;
        while (i < 3) {
            for (int j = 0; j < 3; ++j) {
                if (this.tablero[i][j] == 0) {
                    if (this.tablero[1][1] == 0) {
                        this.tablero[1][1] = 1;
                        return this.estaLleno();
                    }
                    this.tablero[i][j] = 1;
                    ++contador;
                    temp_max = this.revisadiag(true) + this.revisaColumna(true) +
                     this.revisaLinea(true);
                    if (temp_max < -50) {
                        this.Gana = 2;
                        return true;
                    }
                    for (int i2 = 0; i2 < 3; ++i2) {
                        for (int j2 = 0; j2 < 3; ++j2) {
                            if (this.tablero[i2][j2] == 0) {
                                this.tablero[i2][j2] = -1;
                                temp_min = this.revisadiag(false) + this.revisaColumna(false) +
                                 this.revisaLinea(false);
                                if (temp_min < -50) {
                                    save_row = i2;
                                    save_col = j2;
                                }
                                if (temp_min < this.min) {
                                    this.min = temp_min;
                                }
                                this.tablero[i2][j2] = 0;
                            }
                        }
                    }
                    temp_minmax = temp_max - this.min;
                    if (temp_minmax > this.minmax) {
                        if (temp_minmax < 50) {
                            save_row = i;
                            save_col = j;
                        }
                        this.minmax = temp_minmax;
                    }
                    this.tablero[i][j] = 0;
                }
            }
            ++i;
        }
        if (save_row >= 0 && save_row < 3 && save_col >= 0 && save_col < 3) {
            this.tablero[save_row][save_col] = 1;
        }
        return this.estaLleno();
    }
    
    public boolean estaLleno() {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (this.tablero[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public int revisadiag(final boolean isMax) {
        int min = 1;
        int max = -1;
        if (isMax) {
            min = -1;
            max = 1;
        }
        this.diag = 0;
        if (this.tablero[0][0] != min && this.tablero[1][1] != min && this.tablero[2][2] != min) {
            if (this.tablero[0][0] == max && this.tablero[1][1] == max && this.tablero[2][2] == max) {
                return -100;
            }
            ++this.diag;
        }
        if (this.tablero[0][2] != min && this.tablero[1][1] != min && this.tablero[2][0] != min) {
            if (this.tablero[0][2] == max && this.tablero[1][1] == max && this.tablero[2][0] == max) {
                return -100;
            }
            ++this.diag;
        }
        if (isMax) {
            return this.diag;
        }
        this.diag *= -1;
        return 0 - this.diag;
    }
    
    public int revisaColumna(final boolean isMax) {
        int min = 1;
        int max = -1;
        if (isMax) {
            min = -1;
            max = 1;
        }
        this.columnas = 0;
        if (this.tablero[0][0] != min && this.tablero[1][0] != min && this.tablero[2][0] != min) {
            if (this.tablero[0][0] == max && this.tablero[1][0] == max && this.tablero[2][0] == max) {
                return -100;
            }
            ++this.columnas;
        }
        if (this.tablero[0][1] != min && this.tablero[1][1] != min && this.tablero[2][1] != min) {
            if (this.tablero[0][1] == max && this.tablero[1][1] == max && this.tablero[2][1] == max) {
                return -100;
            }
            ++this.columnas;
        }
        if (this.tablero[0][2] != min && this.tablero[1][2] != min && this.tablero[2][2] != min) {
            if (this.tablero[0][2] == max && this.tablero[1][2] == max && this.tablero[2][2] == max) {
                return -100;
            }
            ++this.columnas;
        }
        if (isMax) {
            return this.columnas;
        }
        this.columnas *= -1;
        return 0 - this.columnas;
    }
    
    public int revisaLinea(final boolean isMax) {
        int min = 1;
        int max = -1;
        if (isMax) {
            min = -1;
            max = 1;
        }
        this.lineas = 0;
        if (this.tablero[0][0] != min && this.tablero[0][1] != min && this.tablero[0][2] != min) {
            if (this.tablero[0][0] == max && this.tablero[0][1] == max && this.tablero[0][2] == max) {
                return -100;
            }
            ++this.lineas;
        }
        if (this.tablero[1][0] != min && this.tablero[1][1] != min && this.tablero[1][2] != min) {
            if (this.tablero[1][0] == max && this.tablero[1][1] == max && this.tablero[1][2] == max) {
                return -100;
            }
            ++this.lineas;
        }
        if (this.tablero[2][0] != min && this.tablero[2][1] != min && this.tablero[2][2] != min) {
            if (this.tablero[2][0] == max && this.tablero[2][1] == max && this.tablero[2][2] == max) {
                return -100;
            }
            ++this.lineas;
        }
        if (isMax) {
            return this.lineas;
        }
        this.lineas *= -1;
        return 0 - this.lineas;
    }
    
    public boolean validarEmpate() {
        int cont = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (this.tablero[i][j] == 0) {
                    ++cont;
                }
            }
        }
        this.Ganador();
        return this.Gana != 1 && this.Gana != -1 && cont == 0;
    }
    
    public int ValidarInicio() {
        int cont = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                ++cont;
            }
        }
        return cont;
    }
    
    public int validarGanador() {
        int vencedor = 0;
        this.Ganador();
        if (this.Gana == 1) {
            vencedor = 1;
        }
        if (this.Gana == -1) {
            vencedor = -1;
        }
        return vencedor;
    }
}

