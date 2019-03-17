import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class List {
    
    public static void listAll () {
        Stage primaryStage = new Stage();
         
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
 
        TableColumn quantityCol = new TableColumn("Quantity");
        quantityCol.setMinWidth(100);
        quantityCol.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
 
        TableColumn notesCol = new TableColumn("Notes");
        notesCol.setMinWidth(200);
        notesCol.setCellValueFactory(
                new PropertyValueFactory<>("notes"));
 
        TableView<Entry> entryTable = new TableView<>();
        entryTable.setItems(arrayOfEntries());
        entryTable.getColumns().addAll(nameCol, quantityCol, notesCol);
        entryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        VBox vbox = new VBox();
        vbox.setSpacing(0);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().add(entryTable);
        
        Scene listScene = new Scene(vbox);
        primaryStage.setScene(listScene);
        primaryStage.show();
    }
    public static ObservableList<Entry> arrayOfEntries() {
        ObservableList<Entry> array = observableArrayList();
        for (int i = 0; i < Entry.count; i++) {
            array.addAll(Entry.entryList[i]);
        }
        return array;
    }
}