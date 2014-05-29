package cl.dcc;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @author fquintan
 *
 * Nodo para usarse en el R-Tree guarda el maximo y minimo numero de elementos
 * de cada nodo, la dimension del espacio, su MBR propio y los de sus hijos
 * asi como su posicion en el archivo donde se guarda el R-Tree y las de sus hijos
 * Ejemplo del formato de un Node con maximo de hijos 4 al ser guardado en disco
 *
 * --------------------------------------------------------------------------------------------------------------------------
 * |dim|max|n_child|  myMBR  |MBR1|MBR2|MBR3|MBR4|RefFile1|RefFile2|RefFile3|RefFile4|MyRefFile|
 * --------------------------------------------------------------------------------------------------------------------------
 * |4B |4B |  4B   |16*dim B | 4 * 16 * dim B    |   8B   |   8B   |   8B   |    8B  |   8B    |
 *
 */

public class Node {

    private int min, max;
    private int numOfChildren;
    private int dimension;

    private long myFilePos;

    private long[] childrenFilePos;

    public int getMin() {
        return min;
    }
    public void setMin(int min) {
        this.min = min;
    }
    public int getMax() {
        return max;
    }
    public void setMax(int max) {
        this.max = max;
    }
    public int getNumOfChildren() {
        return numOfChildren;
    }
    public void setNumOfChildren(int numOfChildren) {
        this.numOfChildren = numOfChildren;
    }
    public int getDimension() {
        return dimension;
    }
    public void setDimension(int dimension) {
        this.dimension = dimension;
    }
    public long getMyFilePos() {
        return myFilePos;
    }
    public void setMyFilePos(long myFilePos) {
        this.myFilePos = myFilePos;
    }
    public long[] getChildrenFilePos() {
        return childrenFilePos;
    }
    public void setChildrenFilePos(long[] childrenFilePos) {
        this.childrenFilePos = childrenFilePos;
    }
    /**
     * Construye un nuevo Nodo vacio especificando su dimension,
     * su numero maximo de elementos y su posicion en archivo
     * */
    public Node(int dimension, int max, long filePos){
        this.dimension = dimension;
        this.max = max;
        min = max / 2;
        numOfChildren = 0;
        myFilePos = filePos;
        childrenFilePos = new long[max+1];
    }

    /**
     * Reconstruye un nodo a partir de la informacion almacenada en buffer
     * */
    public Node(byte [] buffer){
        int ini= 0;
        dimension= ByteBuffer.wrap(buffer, ini, 4).getInt();
        ini= ini+4; //dejo el puntero al final del campo dimension
        max= ByteBuffer.wrap(buffer, ini, 4).getInt();
        ini= ini+4; //dejo el puntero al final del campo maxChildren
        min = this.max/2;
        numOfChildren=  ByteBuffer.wrap(buffer, ini, 4).getInt();
        ini= ini+4; //dejo el puntero al final del campo numOfChildren
        myMBR = new MBR(buffer, ini, dimension);
        ini += 16*dimension;//dejo el puntero al final del campo myMBR
        MBRchildren = new MBR[max +1];
        childrenFilePos = new long[max+1];
        for (int i=0; i< numOfChildren; i++){
            MBRchildren[i] = new MBR(buffer, ini, dimension);
            ini = ini + 16*dimension; //dejo el puntero al final del ultimo MBR leido
        }
        ini= ini+ (max+1-numOfChildren)*16*dimension; //dejo el puntero al final del ultimo MBR posible (aunque este no exista para no leer basura)

        for (int i=0; i<numOfChildren; i++){
            childrenFilePos[i]= ByteBuffer.wrap(buffer, ini, 8).getLong();
            ini= ini+8; //dejo el puntero al final de la referencia
        }
        ini= ini + (max+1-numOfChildren)*8; //dejo el puntero al final de la ultima referencia posible (aunque no existan)
        myFilePos= ByteBuffer.wrap(buffer, ini, 8).getLong();
        ini= ini + 8; //dejo el puntero al final de la referencia
    }


    /**
     * Retorna la posicion en archivo correspondiente
     * al i-esimo hijo del nodo
     *
     * @param i indice del hijo requerido (de 0 a max-1)
     * */
    public long getChildFilePos(int i){
        return childrenFilePos[i];
    }
    /**
     * Retorna true si el nodo excedio su capacidad maxima
     * */
    public boolean isFull(){
        return numOfChildren > max;
    }
    /**
     * Retorna true si el nodo esta vacio, es decir, no tiene hijos
     * */
    public boolean isEmpty(){
        return numOfChildren == 0;
    }



    /**
     * Escribe en el buffer un nodo completo con el formato especificado comenzando en el byte start (myPagePos)
     *
     * @param buffer
     * @throws IOException
     */
    public void writeToBuffer(byte [] buffer) throws IOException {
        int ini= 0;
        ByteBuffer.wrap(buffer, ini, 4).putInt(dimension);
        ini= ini+4; //dejo el puntero al final del campo dimension

        ByteBuffer.wrap(buffer, ini, 4).putInt(max);
        ini= ini+4; //dejo el puntero al final del campo max

        ByteBuffer.wrap(buffer, ini, 4).putInt(numOfChildren);
        ini= ini+4; //dejo el puntero al final del campo numOfRegions

        myMBR.writeToBuffer(buffer, ini);
        ini += 16*dimension;

        for (int i=0; i< numOfChildren; i++){
            MBRchildren[i].writeToBuffer(buffer, ini);
            ini = ini + 16*dimension; //dejo el puntero al final del ultimo MBR escrito
        }

        ini= ini+ (max+1-numOfChildren)*16*dimension; //dejo el puntero al final del ultimo MBR (aunque este no exista para no leer basura)
        for (int i=0; i<numOfChildren; i++){
            ByteBuffer.wrap(buffer, ini, 8).putLong(childrenFilePos[i]);
            ini= ini+8; //dejo el puntero al final de la referencia
        }
        ini= ini + (max+1-numOfChildren)*8; //dejo el puntero al final de la 8va referencia (aunque no existan)
        ByteBuffer.wrap(buffer, ini, 8).putLong(myFilePos);
        ini= ini + 8; //dejo el puntero al final de la referencia
    }


    /**
     * Elimina todos los hijos del nodo, sus MBR y sus referencias
     * y resetea el contador de hijos
     * @throws Exception
     * */
    public void clear() throws Exception{
        childrenFilePos = new long[max+1];
        numOfChildren = 0;
    }



}
