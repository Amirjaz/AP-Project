package database;

import business_domain.Shop;

import java.io.*;

import static java.lang.Thread.sleep;

/**
 * This class is used to save the data of our shops for the next runs of the application.
 *
 */

public class Database {
    private final String file_path;
    private Shop shop = new Shop();
    private MyThread thread;

    /**
     * MyTread is used to save the data each second.
     */
    private static class MyThread extends Thread{
        Database db;
        boolean running;
        MyThread(Database db){
            this.db = db;
            running = true;
        }

        public void turnOff() {
            running = false;
        }

        public void run(){
            while(running){
                db.writeData();
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Constructor for DataBase
     * @param file_path the path to the file in which the data is contained.
     */
    public Database(String file_path){
        this.file_path = file_path;
    }

    /**
     * Reads the data from the corresponding file and returns the <code>Shop</code> object.
     * @return Shop object read from the file
     */
    public Shop readData(){
        // Deserialization
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(file_path);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            this.shop = (Shop) in.readObject();

            in.close();
            file.close();

            //System.out.println("Object has been deserialized ");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
        return shop;
    }

    /**
     * Writes our data to the file.
     */
    public void writeData(){
        // Serialization
        try
        {
            //Saving of object in a file
            FileOutputStream file = new FileOutputStream(file_path);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // Method for serialization of object
            out.writeObject(this.shop);

            out.close();
            file.close();

            //System.out.println("Object has been serialized");
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    /**
     * Used to reset the data stored in the file.
     */
    public void resetData(){
        this.shop = new Shop();
        this.writeData();
    }

    /**
     * Starting the thread.
     */
    public void start(){
        this.thread = new MyThread(this);
        this.thread.start();
    }

    /**
     * Closing the thread in order to close the application.
     */
    public void close(){
        this.thread.turnOff();
        //sleep(1000);
    }
}
