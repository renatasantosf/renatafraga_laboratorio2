package cinema;

import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @authors Diego Pinto e Renata Fraga
 */
public class Cinema extends Application  {

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/MainPainel.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
