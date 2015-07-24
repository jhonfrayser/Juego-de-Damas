package juegodedamas;
import java.util.*;
import java.awt.*;
/**
 *
 * @author JhonFrayser
 */
public class TableroDeDama extends Canvas {
    
 // Crear Objeto
     protected objPaintInstruction currentInstruction = null;
     protected Vector vecPaintInstructions = new Vector();
    public void tablerodamas() {
    }
    
//  Usar Objetos y Crear Procedimientos y Funciones
    public void actualizar(Graphics g) {
        paint(g);
    }
    ////////////
    
    //////////
        //Dibuja y Pinta el Tablero---------------------
    private void drawTile(int fila, int columna, Graphics g) {
        
        if ((fila + 1) % 2 == 0) {
            
            if ((columna + 1) % 2 == 0) {
                g.setColor(new Color(255,255,255));
            } else {
                g.setColor(new Color(0,0,0));
            }
            
        } else {
            
            if ((columna + 1) % 2 == 0) {
                g.setColor(new Color(0,0,0));
            } else {
                g.setColor(new Color(255,255,255));
            }
            
        }
        
        g.fillRect((50 + (columna * 50)), (50 + (fila * 50)), 50, 50);
        
    }
    
    //Hace visible a las imagenes
    protected void drawExtra(Graphics g) {
    }
    
     //Permite Seleccionar  ----
    protected int findWhichTileSelected(int coor) {
        
        for (int i = 0; i < 8; i++) {
            
            if ((coor >= (50 + (i * 50))) && (coor <= (100 + (i * 50)))) {
                return i;
            }
            
        }
        
        return -1;
        
    }
    
    
}
