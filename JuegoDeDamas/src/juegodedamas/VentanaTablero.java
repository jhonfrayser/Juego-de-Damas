package juegodedamas;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
/**
 *
 * @author JhonFrayser
 */
public class VentanaTablero extends  TableroDeDama implements MouseListener, MouseMotionListener {
   
       private final int refreshRate = 5;
    
    private Image[][] imgPlayer = new Image[2][6];
    private String[] strPlayerName = {"Jhon Frayser", "TU --> MI CONTRICANTE"};
    private String strStatusMsg = "";
    private CellMatriz cellMatrix = new CellMatriz();
    private int currentPlayer = 1, startRow = 0, startColumn = 0, pieceBeingDragged = 0;
    private int startingX = 0, startingY = 0, currentX = 0, currentY = 0, refreshCounter = 0;
    private boolean firstTime = true, hasWon = false, isDragging = false;
    
 
    
    public VentanaTablero() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);     
    }
    
    //Siver para cambiar los mensajes que vez en el tablero
    private String getPlayerMsg() {
        
        if (hasWon) {
            return "Felicitaciones " + strPlayerName[currentPlayer - 1] + ", tu eres el ganadaror!";
        } else if (firstTime) {
            return "" + strPlayerName[0] + " tu eres los Negros, " + strPlayerName[1] + " tu eres los Blancos. Presiona nuevo juego para empezar";
        } else {
            return "" + strPlayerName[currentPlayer - 1] + " mueve";
        }
        
    }
    
    //Comiezan el juego de nuevo
    private void resetBoard() {
        
        hasWon = false;
        currentPlayer = 1;
        strStatusMsg = getPlayerMsg();
        cellMatrix.resetMatrix();
        repaint();
        
    }
    
    public void setupImages(Image[] imgRed, Image[] imgBlue) {
        
        imgPlayer[0] = imgRed;
        imgPlayer[1] = imgBlue;
        resetBoard();
        
    }
    
    //Establece los nombres a los jugadores
    
    public void setNames(String strPlayer1Name, String strPlayer2Name) {
        
        strPlayerName[0] = strPlayer1Name;
        strPlayerName[1] = strPlayer2Name;
        strStatusMsg = getPlayerMsg();
        repaint();
        
    }
    
    protected void drawExtra(Graphics g) {
        
        for (int i = 0; i < vecPaintInstructions.size(); i++) {
            
            currentInstruction = (objPaintInstruction)vecPaintInstructions.elementAt(i);
            int paintStartRow = currentInstruction.getStartRow();
            int paintStartColumn = currentInstruction.getStartColumn();
            int rowCells = currentInstruction.getRowCells();
            int columnCells = currentInstruction.getColumnCells();
            
            for (int row = paintStartRow; row < (paintStartRow + rowCells); row++) {
                
                for (int column = paintStartColumn; column < (paintStartColumn + columnCells); column++) {
                    
                    int playerCell = cellMatrix.getPlayerCell(row, column);
                    int pieceCell = cellMatrix.getPieceCell(row, column);
                    
                    if (playerCell != 0) {
                        
                        try {
                            g.drawImage(imgPlayer[playerCell - 1][pieceCell], ((column + 1) * 50), ((row + 1) * 50), this);
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }
                        
                    }
                    
                }
                
            }
            
        }
        
        if (isDragging) {
            g.drawImage(imgPlayer[currentPlayer - 1][pieceBeingDragged], (currentX - 25), (currentY - 25), this);
        }
        
        g.setColor(new Color(0,0,0));
        g.drawString(strStatusMsg, 50, 475);
        
        vecPaintInstructions.clear();
    }
    
    
    //Nuevo juego
    public void newGame() {
        
        firstTime = false;
        resetBoard();
        
    }
    
    ////////////
      private void checkMove(int desRow, int desColumn) {
        
        boolean legalMove = false;
        
        if (cellMatrix.getPlayerCell(desRow,desColumn) == currentPlayer) {
            strStatusMsg = "No puedes mover";
        } //////
            
        }
        
        
 
    /////////
    
   
    
    private void unsucessfullDrag() {
        
        cellMatrix.setPieceCell(startRow, startColumn, pieceBeingDragged);
        cellMatrix.setPlayerCell(startRow, startColumn, currentPlayer);
        
    }
    
    private void updatePaintInstructions(int desRow, int desColumn) {
        
        currentInstruction = new objPaintInstruction(startRow, startColumn, 1);
        vecPaintInstructions.addElement(currentInstruction);
        
        currentInstruction = new objPaintInstruction(desRow, desColumn);
        vecPaintInstructions.addElement(currentInstruction);
        
    }
    
    
    /*Sirve para poder controloar los eventos del mouse, esto es lo que se llama el famoso
     drag and drop(arrastrar y soltar)
     */
    
    public void mouseClicked(MouseEvent e) {
    }
    
    public void mouseEntered(MouseEvent e) {
    }
     public void mouseExited(MouseEvent e) {
        mouseReleased(e);
    }
   
    
    public void mousePressed(MouseEvent e) {
        
        if (!hasWon && !firstTime) {
            
            int x = e.getX();
            int y = e.getY();
            
            if ((x > 60 && x < 430) && (y > 60 && y < 430)) {
                
                startRow = findWhichTileSelected(y);
                startColumn = findWhichTileSelected(x);
                
                if (cellMatrix.getPlayerCell(startRow, startColumn) == currentPlayer) {
                    
                    pieceBeingDragged = cellMatrix.getPieceCell(startRow, startColumn);
                    cellMatrix.setPieceCell(startRow, startColumn, 6);
                    cellMatrix.setPlayerCell(startRow, startColumn, 0);
                    isDragging = true;
                    
                } else {
                    isDragging = false;
                }
                
            }
            
        }
        
    }
    //
    public void mouseReleased(MouseEvent e) {
        
        if (isDragging) {
            
            isDragging = false;
            
            int desRow = findWhichTileSelected(currentY);
            int desColumn = findWhichTileSelected(currentX);
            checkMove(desRow, desColumn);
            repaint();
            
        }
        
    }
      //
   
    
     /*
     * 	El mouseDragged () la función se llama una vez cada vez que se mueve 
     * el ratón mientras se pulsa un botón del ratón. (Si un botón no está 
     * siendo presionado, mouseMoved () se llama en su lugar.) Mouse y eventos
     * de teclado sólo funcionan cuando un programa tiene draw()(dibujar) . 
     * Sin draw () , el código sólo se ejecuta una vez y luego se detiene la escucha de eventos. 
     */
    public void mouseDragged(MouseEvent e) {
        
        if (isDragging) {
            
            int x = e.getX();
            int y = e.getY();
            
            if ((x > 60 && x < 430) && (y > 60 && y < 430)) {
                
                if (refreshCounter >= refreshRate) {
                    
                    currentX = x;
                    currentY = y;
                    refreshCounter = 0;
                    int desRow = findWhichTileSelected(currentY);
                    int desColumn = findWhichTileSelected(currentX);
                    
                    updatePaintInstructions(desRow, desColumn);
                    repaint();
                    
                } else {
                    refreshCounter++;
                }
                
            }
            
        }
        
    }
    
    public void mouseMoved(MouseEvent e) {
    }
    
    public void gotFocus() {
        repaint();
    }
    
}
