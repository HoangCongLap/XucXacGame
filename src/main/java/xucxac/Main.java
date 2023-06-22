package xucxac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BoardGame.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("RoomCreate.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}






//    @Override
//    public void start(Stage stage) throws IOException {
//
//        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("BoardGame.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    public static void main(String[] args) {
//                launch(args);
//    }
//}