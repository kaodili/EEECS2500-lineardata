import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Entry {
    public static Entry[] entryList = new Entry[200];
    public String name, quantity, notes;
    public static int count;
    public static final String FILE_NAME = "Project 7.txt";
    public static File file = new File(FILE_NAME);
   
    public Entry () {
        name = "";
        quantity = "0";
        notes = "no notes";         
    }
    
    public Entry(String name, String quantity, String notes) {
        name = name.toLowerCase();
        this.name = name;
        this.quantity = quantity;
        this.notes = notes;
    }

    Entry(String nameOfItem, TextField quantityOfItem, String notesOfItem) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public void setName(String name) {
        name = " ";
    }
    
    public String getName() {
        return name;
    }
    
    public void setQuantity(String quantity) {
        quantity = " ";
    }
    
    public String getQuantity() {
        return quantity;
    }
    
    public void setNotes(String notes) {
        notes = " ";
    }
    
    public String getNotes() {
        return notes;
    }
    
    public static void readInventory(String FileName) throws Exception {
        Scanner readFile = new Scanner(file);
        while (readFile.hasNextLine()) {
            entryList[count] = new Entry();
            entryList[count].name = readFile.next();
            entryList[count].quantity = readFile.next();
            entryList[count].notes = readFile.nextLine().trim();
            count++;
        }
    }
    
    public static void StoreInventory(String FileName) throws Exception {
        sort();
        PrintStream P = new PrintStream(FileName);
        
        for (int i = 0; i < count; i++) {
            P.println(entryList[i].name + "\t"
                    + entryList[i].quantity + "\t"
                    + entryList[i].notes.trim());
        }
        P.close();
  }
    
    public static void sort() {
        int k;
        Entry temp;
        boolean keepSorting = true;
        
        while (keepSorting == true) {
            keepSorting = false;
            for (k = 0; k < count - 1; k++) {
                if ((entryList[k].name != null) && (entryList[k].name.
                        compareTo(entryList[k+1].name) > 0)) {
                    temp = entryList[k];
                    entryList[k] = entryList[k+1];
                    entryList[k+1] = temp;
                    keepSorting = true;
                }
            }
        }
    }

   public static void showStore() {
    Stage primaryStage = new Stage();
    String storeMessage;
    storeMessage = "Inventory stored";       
    
    Label showMessage = new Label(storeMessage);
    Scene scene = new Scene(showMessage, 200, 50);
    showMessage.setAlignment(Pos.CENTER);
    
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
   