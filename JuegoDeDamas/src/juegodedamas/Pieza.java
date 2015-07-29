package juegodedamas;

/**
 *
 * @author JhonFrayser
 */
public class Pieza {
    protected int finalDesRow = 0;
    protected int finalDesColumn = 0;
    protected String strErrorMsg = "";
    
    public Pieza(){
            }
    
    //
    //Verifica si la celda esta vacia
    private boolean checkAxisMove(int newRow, int newColumn, int[][] playerMatrix) {
        
        if (playerMatrix[newRow][newColumn] != 0) {//Si no esta vacia
            
            strErrorMsg = "La pieeza esta bloqueando el camino";
            return false;
            
        }
        
        return true;
        
    }
}
