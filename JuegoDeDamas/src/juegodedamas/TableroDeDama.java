package juegodedamas;
import java.util.*;
import java.awt.*;
/**
 *
 * @author JhonFrayser
 */
public class TableroDeDama extends Canvas {
     protected objPaintInstruction currentInstruction = null;
    protected Vector vecPaintInstructions = new Vector();
    public void tablerodamas() {
    }
    public void actualizar(Graphics g) {
        paint(g);
    }
    
}
