package com.techelevator.logger;

import java.io.*;

public class Audit implements Closeable {

    private File auditFile;
    private PrintWriter writer;

    public Audit(String pathName) {
        this.auditFile = new File(pathName);
        if (this.auditFile.exists()){
            try {
                this.writer = new PrintWriter(new FileWriter(this.auditFile, true));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                this.writer = new PrintWriter(this.auditFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(String message) {
        this.writer.println(message);
        this.writer.flush();
    }


    @Override
    public void close() throws IOException {
        this.writer.close();
    }
}
