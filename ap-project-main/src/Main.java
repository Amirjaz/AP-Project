
import business_domain.Shop;
import database.*;
import user_interface.*;

public class Main {
    public static Database db;
    public static void main(String[] args) {
        String filename = "database/file.txt";

        db = new Database(filename);

        Shop shop = db.readData();
        db.start();


        UI.start();
        GUI.init();

        Menu.shopMenu(shop);

        db.close();
        db.writeData();
        GUI.close();
        System.exit(0);
    }
}