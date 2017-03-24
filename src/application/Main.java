package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class Main extends Application {
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("Swimming");
			primaryStage.getIcons().add(new Image("file:Shark.jpg"));
			
			
			
			MainScreenScene test = new MainScreenScene();
			Scene scene = new Scene(test.loadScreenOne());
		   
			primaryStage.setScene(scene);
		    primaryStage.show();
		    
		    
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
