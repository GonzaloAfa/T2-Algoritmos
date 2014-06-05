package cl.dcc;


import java.io.*;

/*
    Obtener los datos
	Generar tablas y todas esas cosas.
*/

public class DataReport {

    private PrintWriter writer, summary;
    public boolean console;

    private Statistics statistics;

    public DataReport(Statistics statistics , boolean console) {

        this.statistics = statistics;

        // First create the folder
        File theDir = new File("Results");
        if (!theDir.exists())
            theDir.mkdir();


        String filename = "Results/" + statistics.getName() + " - "
                +statistics.getPartition()+" - " + statistics.getTypeSequence();
        this.console = console;

        try {
            writer  = new PrintWriter(new BufferedWriter(new FileWriter(filename + ".txt", true)));
            summary = new PrintWriter(new BufferedWriter(new FileWriter(filename + "_summary.txt", true)));

        } catch (IOException e) {
            System.out.println("ERROR ABRIENDO ARCHIVO!!!");
        }
    }

    public void makeReport() {
        writer.print(""   +this.statistics.getReport());
        summary.print(""  +this.statistics.getSummary());

        if (console)
            System.out.println("" + statistics.getSummary());
    }


    public void close() {

        writer.close();
        summary.close();
    }

    public void flush() {
        summary.flush();
        writer.flush();
    }

    public void makeHeader() {
        writer.println(this.statistics.getHeader());
        summary.println(this.statistics.getHeader());
    }
}