import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Delete {
    public static TextField name;
    public static void delete() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Delete Item");
        name = new TextField();
        
        Button deleteEntry = new Button("Delete");
        deleteEntry.setOnAction(e -> {
            try {
                showDelete(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Add.class.getName()).log
                (Level.SEVERE, null, ex);
            }
        });
        Button quit = new Button("Back");
        quit.setOnAction(e -> primaryStage.close());
        
        VBox vbox = new VBox(name, deleteEntry, quit);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #009AFF;");
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void showDelete(Stage primaryStage) throws Exception {
        String message;
        String nameOfItem = name.getText();
        int i = 0;
        boolean found = false;
        while ((i < Entry.count) && (found != true)) {
            if (Entry.entryList[i].name.equals(nameOfItem)) {
                Entry.entryList[i].name = "".trim();
                Entry.entryList[i].quantity = "".trim();
                Entry.entryList[i].notes = "".trim();
                found = true;
                Entry.count--;
                break;
            }
            i++;
        }
        if (found == true) {
            message = "Item deleted";
            
        } else {
            message = "Item does not exist ";
        }
        
        Entry.StoreInventory(Entry.FILE_NAME);
        Label showMessage = new Label(message);
        showMessage.setAlignment(Pos.CENTER);
        Scene scene = new Scene(showMessage, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

