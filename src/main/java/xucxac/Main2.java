package xucxac;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class Main2 extends Application {
    private Timer timer;

    @Override
    public void start(Stage stage) {
        try {

            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("BoardGame.fxml"));
//            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            timer = new Timer();
            TimerTask CleanUpTimerTask = new DataUpdateAction();
            timer.schedule(CleanUpTimerTask, 5000, 3000);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
