package cl.dcc;


import java.io.*;

/*
    Obtener los datos
	Generar tablas y todas esas cosas.
*/

public class DataReport {

    private PrintWriter writer, summary;
    public boolean console;


    public DataReport(Experiment experiment , boolean console) {

        // First create the folder
        File theDir = new File("Results");
        if (!theDir.exists())
            theDir.mkdir();


        String filename = "Results/" + experiment.getName() + "-" + experiment.getTypeSequence();
        this.console = console;

        try {
            writer  = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".txt", true)));
            summary = new PrintWriter(new BufferedWriter(new FileWriter(filename + "_summary.txt", true)));

        } catch (IOException e) {
            System.out.println("ERROR ABRIENDO ARCHIVO!!!");
        }
    }

    public void makeReport(Experiment experiment) {
        writer.println("" + experiment.getReport());
        if (console)
            System.out.println("" + experiment.getReport());
    }


    public void close() {

        writer.close();
        summary.close();
    }

    public void flush() {
        summary.flush();
        writer.flush();
    }

}