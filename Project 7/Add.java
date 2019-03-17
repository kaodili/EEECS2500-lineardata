import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Add {

    public static TextField name, quantity, notes;

    public static void addEntry() throws Exception {
        Stage primaryStage = new Stage();
        name = new TextField();
        quantity = new TextField();
        quantity.setPromptText("Enter a quantity greater than 0");
        notes = new TextField();

        Button submitEntry = new Button("Submit");
        submitEntry.setOnAction(e -> {
            try {
                addNew(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Add.class.getName()).log
                (Level.SEVERE, null, ex);
            }
        });
        Button close = new Button("Close");
        close.setOnAction(e -> primaryStage.close());

        Label label = new Label("Name");
        Label label1 = new Label("Quantity");
        Label label2 = new Label("Notes");

        VBox vbox1 = new VBox(label, name, label1, quantity, label2, notes);

        vbox1.setPadding(new Insets(10, 10, 10, 10));
        vbox1.setSpacing(10);

        VBox vbox2 = new VBox();
        vbox2.setAlignment(Pos.CENTER);
        vbox2.getChildren().addAll(vbox1, submitEntry, close);
        vbox2.setSpacing(10);
        vbox2.setStyle("-fx-background-color: #009AFF;");

        Scene scene = new Scene(vbox2, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void addNew(Stage primaryStage) throws Exception {
        String nameOfItem = name.getText();
        String quantityOfItem = quantity.getText();
        String notesOfItem = notes.getText();

        Entry entry = new Entry(nameOfItem, quantityOfItem, notesOfItem);
        Entry.entryList[Entry.count] = entry;
        Entry.count = Entry.count + 1;

        try {
            Entry.StoreInventory(Entry.FILE_NAME);
        } catch (Exception ex) {
            Logger.getLogger(Add.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Entry.StoreInventory(Entry.FILE_NAME);
        Entry.showStore();
        name.clear();
        quantity.clear();
        notes.clear();
    }
}
