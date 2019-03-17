import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class Project7 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Entry.readInventory(Entry.FILE_NAME);
        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            try {
                Add.addEntry();
            } catch (Exception ex) {
                Logger.getLogger
                    (Project7.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        btnAdd.setMaxWidth(100);

        Button btnFind = new Button("Find");
        btnFind.setOnAction(e -> Find.find());
        btnFind.setMaxWidth(100);

        Button btnList = new Button("List");
        btnList.setOnAction(e -> List.listAll());
        btnList.setMaxWidth(100);

        Button btnDelete = new Button("Delete");
        btnDelete.setOnAction(e -> Delete.delete());
        btnDelete.setMaxWidth(100);

        Button btnQuit = new Button("Quit");
        btnQuit.setMaxWidth(100);
        btnQuit.setOnAction(e -> primaryStage.close());

        VBox vb = new VBox(btnAdd, btnList, btnFind, btnDelete, btnQuit);
        vb.setSpacing(10);
        vb.setPadding(new Insets(20));
        vb.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vb, 400, 250);
        scene.getStylesheets().addAll(this.getClass().getResource
            ("skin.css").toExternalForm());

        primaryStage.setTitle("Inventory Management");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
