package cl.dcc;

/**
 * @author fquintan
 * @modificate GonzaloAfa on 28-05-2014.
 *
 */


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.LinkedList;

import cl.dcc.KDNode;

public class MemoryManager {

    private final int blockSize = 4096;
    private final int numOfBuffers = 10;
    private RandomAccessFile file;
    private byte[] buffer;
    private HashMap<Long, KDNode> elements;
    private HashMap<Long, Boolean> bufWasModified;//indica si cada buffer a sido modificado desde que se leyo de disco
    private LinkedList<Long> priority;//indica que buffer va a ser sobreescrito
    private long position;
    private int numOfElements;

    // Constructor de la clase MemoryManager
    public MemoryManager(int numOfBuffers, int bufferSize) throws FileNotFoundException{

        file = new RandomAccessFile("data.bin", "rw");
        buffer = new byte[bufferSize];
        priority = new LinkedList<Long>();
        elements = new HashMap<Long, KDNode>();
        bufWasModified = new HashMap<Long, Boolean>();
        numOfElements = 0;
        position = 0;
    }

	/*-----------------------------------Administrador de memoria---------------------------*/


    /**
     * Recibe un Node y lo escribe en el buffer, si no tengo espacio libre
     * escribo uno de los buffers a disco (el de peor prioridad) y
     * sobreescribo el buffer con mi KDNode
     * @param node
     * @throws IOException
     * */

     public void saveNode(KDNode node) throws IOException{

        if(numOfElements < numOfBuffers){
            elements.put(node.getMyFilePos(), node);
            bufWasModified.put(node.getMyFilePos(), true);
            numOfElements++;
            improvePriority(node.getMyFilePos());
            return;
        }else{

            if(elements.containsKey(node.getMyFilePos())){
                // actualizo la info del nodo que esta en memoria principal
                elements.put(node.getMyFilePos(), node);
                bufWasModified.put(node.getMyFilePos(), true);
                improvePriority(node.getMyFilePos());
            }
            // No estaba en memoria y la memoria esta llena
            else{

                // Saco el ultimo elemento de memoria principal
                // almaceno la informacion de este elemento en memoria secundaria
                // libero el espacio de ese nodo en memoria principal
                long exit = priority.pollLast();
                KDNode temp = elements.get(exit);

                temp.writeToBuffer(buffer); // TODO



                writeBlockToFile(buffer, temp.getMyFilePos());
                bufWasModified.remove(exit);
                elements.remove(exit);

                // ingreso el nuevo nodo a memoria principal
                elements.put(node.getMyFilePos(), node);
                priority.addFirst(node.getMyFilePos());
                bufWasModified.put(node.getMyFilePos(), true);
            }
        }
    }

    /**
     * Carga y retorna un Node, revisa si la informacion esta en memoria principal
     * y de lo contrario lee el archivo en la posicion indicada y carga los
     * datos a un buffer en memoria principal
     * @throws IOException
     * */
    public KDNode loadNode(long filePos) throws IOException{

        // Si esta en memoria principal
        if(elements.containsKey(filePos)){
            improvePriority(filePos);
            return elements.get(filePos);
        }

        // Si no esta en memoria principal...
        // se supone que numOfElements no deberia ser menor que numOfBuffer
        // ya que si esto pasa, el nodo deberia estar en memoria principal

        if(numOfElements < numOfBuffers){
            readBlockFromFile(filePos, buffer);
            priority.addFirst(filePos);
            bufWasModified.put(filePos, false);
            numOfElements++;
            KDNode temp = new KDNode(buffer);
            elements.put(filePos, temp);
            return temp;
        }

        // no esta en memoria principal... liberar espacio
        long exit = priority.pollLast();
        KDNode exitNode = elements.get(exit);

        if(bufWasModified.get(exit)){


            // Guardamos en disco antes de eliminar si es distinto a lo que esta en memoria secundaria
            exitNode.writeToBuffer(buffer); // TODO


            writeBlockToFile(buffer, exit);
        }

        //procedemos a liberar espacio
        elements.remove(exit);
        bufWasModified.remove(exit);

        // Cargamos el nodo en memoria principal
        readBlockFromFile(filePos, buffer);
        KDNode temp = new KDNode(buffer);
        priority.addFirst(filePos);
        bufWasModified.put(filePos, true);
        elements.put(filePos, temp);

        // Retornamos el nodo en cuestion
        return temp;
    }

      /**
     * Escribe un bloque (buffer de bytes) en disco en la posicion indicada
     * @throws IOException
     * */
    public void writeBlockToFile(byte[] block, long pos) throws IOException{
        file.seek(pos);
        file.write(block);
    }


    /**
     * Lee un bloque de disco partiendo de la posicion pos y
     * lo escribe en el buffer
     * @param pos posicion del archivo para empezar a leer
     * @param buffer buffer donde escribir
     * @throws IOException
     * */
     public void readBlockFromFile(long pos, byte[] buffer) throws IOException{
        file.seek(pos);
        file.read(buffer);
    }

    public long getNewPosition(){
        long temp = position;
        position += buffer.length;
        return temp;
    }


    /**
     * Mejora la prioridad del elemento element
     * */
    private void improvePriority(long elmt){
        priority.remove(elmt);
        priority.offerFirst(elmt);
    }



}
