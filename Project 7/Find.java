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

public class Find {

    public static TextField name;

    public static void find() {
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Find Item");
        name = new TextField();

        Button findEntry = new Button("Find");
        findEntry.setOnAction(e -> {
            try {
                showFound(primaryStage);
            } catch (Exception ex) {
                Logger.getLogger(Add.class.getName()).log
                (Level.SEVERE, null, ex);
            }
        });
        Button quit = new Button("Back");
        quit.setOnAction(e -> primaryStage.close());

        VBox vbox = new VBox(name, findEntry, quit);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.setSpacing(10);
        vbox.setStyle("-fx-background-color: #009AFF;");
        Scene scene = new Scene(vbox, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void showFound(Stage primaryStage) {
        String message;
        String nameOfItem = name.getText();
        int i = 0;
        boolean found = false;
        while ((i < Entry.count) && (found != true)) {
            if (Entry.entryList[i].name.equals(nameOfItem)) {
                found = true;
                break;
            }
            i++;
        }
        if (found == true) {
            message = Entry.entryList[i].name
                    + "\t" + Entry.entryList[i].quantity + "\t"
                    + Entry.entryList[i].notes;

        } else {
            message = nameOfItem + " is not on your list.";
        }

        Label showMessage = new Label(message);
        showMessage.setAlignment(Pos.CENTER);
        Scene scene = new Scene(showMessage, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
