package tresenraya;

import java.awt.Color;
import java.awt.FlowLayout;
import static java.lang.String.valueOf;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LogicaJuego {
    int turno, pX, pO; // Turno del jugador
    boolean habilitado; // Habilita y deshabilita el tablero

    /**
     * Inicializaremos el juego con las siguientes variables_
     * @param turno (Nos indicará el turno del jugador: 0 - X, 1 - O)
     * @param pX (Contiene el valor total de las victorias de este jugador)
     * @param pO (Contiene el valor total de las victorias de este jugador)
     */
    public LogicaJuego(int turno, int pX, int pO) {
        this.turno = turno;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Obtener turno
     * @return 
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Insertar turno
     * @param turno 
     */
    public void setTurno(int turno) {
        this.turno = turno;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * Llamaremos a este método para cambiar de turno
     */
    public void cambioTurno(){
        // Inserta código aquí...
        if(this.getTurno() == 0)this.setTurno(1);
        else this.setTurno(0);
        
    }
    
    /**
     * Comprobar si se ha conseguido un tres en raya, 
     * en caso que se haya conseguido devolverá 1, en caso contrario retorna 0 y continúa el juego
     * @param matriz (Tablero de juego)
     * @return 
     */
    public int comprobarJuego(int matriz[][]){
    /* se tendrá que comprobar que las posiciones correspondientes tengan el 
       mismo valor; es decir se tiene que ir comparando las casillas entre sí; 
       de forma horizontal, vertical y diagonal, obligatoriamente.  . 
       Se evalúa utilizar las estructuras correctas, para reducir el número de 
       iteraciones. Todo de forma organizada y optimizada recordemos 
       que estamos en un módulo del tercer semestre del ciclo.  Pero, 
       por si acaso posible pseudocódigo:   
        Comprobar si existe tres en raya:
            Comprobar horizontal			
            si no, Comprobar vertical        
            si no, Comprobar en diagonal
        Si no hay tres en raya:
    */   
         // Inserta código aquí...
         	if(
            (matriz[0][0] == matriz[0][1] && matriz[0][2] == matriz[0][0]) ||
            (matriz[1][0] == matriz[1][1] && matriz[1][2] == matriz[1][0])|| 
            (matriz[2][0] == matriz[2][1] && matriz[2][0] == matriz[2][2])
           )return 1;
			// Comprobar vertical
        else if(
            (matriz[0][0] == matriz[1][0] && matriz[2][0] == matriz[0][0]) ||
            (matriz[0][1] == matriz[1][1] && matriz[2][1] == matriz[0][1]) ||
            (matriz[0][2] == matriz[1][2] && matriz[2][2] == matriz[0][2])
           )return 1;
                     
			// sino Comprobar en diagonal
        else if(
            (matriz[0][0] == matriz[1][1] && matriz[0][0] == matriz[2][2]) ||
            (matriz[0][2] == matriz[1][1] && matriz[0][2] == matriz[2][0])
          )return 1;
        
        return 0;
    }
    
     /**
     * En caso de Ganador, mostrará la ventana, dentro del tablero; con el 
     * mensaje de enhorabuena al gandador; y le preguntará si desea continuar 
     * jugando.
     * Se valorará el código limpio, comprensible, organizado y optimizado; 
     * se puede implementar en 3 o 4 líneas de código
     * Obligatorio utilizar el operador condicional ?
     * @param jp (panel donde está situado el tablero)
     * variable local String mensajeGanador
     */   
    public void mostrarVentanaGanador(javax.swing.JPanel jp){
        // Inserta código aquí...
        
        String mensajeGanador = "Soy DanielCoronas,\n" + "doy la enhorabuena a las " + (getTurno() == 0 ? "O" : "X") + "!!!\n¿Quieres continuar jugando?";
        
         if (JOptionPane.showConfirmDialog(jp,mensajeGanador,"Enhorabuena Ganador",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null) == 0)
        { 
            cambioTurno();
        }else{            
            System.exit(0);
        }                    
    }
    /**
     * Deshabilitará el botón para evitar que se vuelva a posicionar una ficha en ese hueco
     * @param bt (Botón seleccionado)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     * @return 
     */
    public int tiradaJugador(javax.swing.JButton bt, int x, int y, 
            int matriz[][], javax.swing.JPanel jp, 
            javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
        
        // Deshabilita el botón
        bt.setEnabled(false);
        // Insertar la ficha en el botón
        //Llamamos al metodo poner ficha
        ponerFicha(matriz, x, y, bt);       
        // Comprobar si se ha ganado la partida y mostrar la ventana con el
        // mensaje cuando corresponda
        if(comprobarJuego(matriz) == 1){
            ganador(lX, lO);
         
         // Deshabilitar tablero
          habilitado = false;
            habilitarTablero(jp);
            mostrarVentanaGanador(jp);
        }else{
        cambioTurno();
        
            }
         return turno;
    }
    
    /**
     * Actualizar la puntuación de cada jugador al ganar la partida
     * Al finalizar el incremento de puntuación es necesario cambiar de turno
     * @param lX (JLabel del jugador X)
     * @param lO (JLabel del jugador O)
     */
    public void ganador(javax.swing.JLabel lX, javax.swing.JLabel lO){
        // Inserta código aquí...
      
        // Incrementa jugador ganador e inserta resultado en jLabel    
       if (getTurno() == 0) {
            pX++;
            lX.setText("" + pX);
        } else {
            pO++;
            lO.setText("" + pO);
        }
        cambioTurno();//Para que inicie la partida el que perdió.
    }
    
    /**
     * Habilitará o deshabilitará el tablero dependiendo del estado de la variable habilitado
     * @param jp  (Panel dónde se sitúa el tablero de juego)
     */
    public void habilitarTablero( javax.swing.JPanel jp){
        // Inserta código aquí...
        // Bloquea todos los elementos del JPanel
        
        int totalComponentes = jp.getComponents().length;
        for(int i = 0; i < totalComponentes; i++){
            jp.getComponent(i).setEnabled(habilitado);
        //No deshabilito el panel, sino que deshabilito todos los botones del panel
        }
        
    }
    
    /**
     * En ponerFicha, Insertaremos la ficha en la posición correspondiente de la matriz
     * Llamaremos al método pintarFicha
     * @param matriz (Tablero de juego)
     * @param x (Posición x en el tablero)
     * @param y (Posición y en el tablero)
     * @param bt (Botón pulsado)
     * turno  (para saber que ficha poner)
     */
    public void ponerFicha(int matriz[][], int x, int y, javax.swing.JButton bt){
        // Inserta código aquí... 
        //Se inserta la ficha en la posicion de la matriz
        matriz[x][y] = getTurno();
        // Insertar ficha en la posición de la matriz
        // mostrar la ficha correspondiente
        pintarFicha(bt);
    }
    
    /**
     * Pintará la ficha en el tablero de juego visual, es decir, en el botón
     * @param bt (Botón pulsado)
     */
    private void pintarFicha(javax.swing.JButton bt){
        // Inserta código aquí...
        // Si el turno es de 0 mostrará una X en rojo
        if(this.getTurno() == 0){
            bt.setForeground(Color.red);
            bt.setText("X");
        }
         // Si el turno es de 1, mostrará una O en azul 
         else {bt.setForeground(Color.blue);
        bt.setText("O");
        }
    }
    
    /**
     * Inicializa una nueva partida, reinicia la matriz (Tablero de juego) y habilita el tablero
     * 
     * ¡¡¡¡No es necesario modificar este método!!!!.
     * 
     * @param matriz (Tablero de juego)
     * @param jp (Panel dónde se sitúa el tablero de juego)
     */
    public void iniciarPartida(int matriz[][], javax.swing.JPanel jp){
        // Rellenamos la matriz por primera vez, evitando que se repitan los números
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matriz[x][y]=(x+10)*(y+10);
            }
        }

        // Habilitar tablero
         habilitado = true;
         habilitarTablero(jp);
         cambioTurno();
    }
}
