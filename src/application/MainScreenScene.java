package application;

import java.sql.Connection;

import org.w3c.dom.events.EventException;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainScreenScene extends Main {

	Connection conn = SQLConnection.DbConnector();;

	public VBox loadScreenOne() {

		CheckConnection();

		VBox vBox = new VBox(5);
		vBox.setPrefSize(500, 500);
		vBox.setAlignment(Pos.CENTER);

		// IMAGE LOGO
		Image logo = new Image("file:logoViktorija.png");
		ImageView logoPlace = new ImageView();
		logoPlace.setImage(logo);
		logoPlace.setFitWidth(200);
		logoPlace.setPreserveRatio(true);
		logoPlace.setSmooth(true);
		logoPlace.setCache(true);

		// CONNECTION LABEL
		Label label = new Label();
		label.setTextFill(Color.RED);
		if (conn == null) {
			label.setText("Connection Not Successfull");
		} else {
			label.setText("Connection Successfull");
		}
		label.setFont(new Font("SanSerif", 15));

		// VIEW SWIMMERS BUTTON
		final Button viewSwimmer = new Button("View All Swimmers");
		viewSwimmer.setPrefSize(200, 20);
		viewSwimmer.setFont(Font.font("SanSerif", 15));
		viewSwimmer.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ViewSwimmersScene test = new ViewSwimmersScene();
				viewSwimmer.getScene().setRoot(test.loadSwimmersScreen());
			}

		});

		// VIEW COACHES BUTTON
		final Button viewCoach = new Button("View All Coaches");
		viewCoach.setPrefSize(200, 20);
		viewCoach.setFont(Font.font("SanSerif", 15));
		viewCoach.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				ViewCoachesScene test = new ViewCoachesScene();
				viewCoach.getScene().setRoot(test.loadCoachesScreen());
			}

		});

		// Text text = new Text("Screen One");

		// TESTING BUTTON
		final Button testing = new Button("Testing button");
		testing.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ViewSwimmersScene test = new ViewSwimmersScene();
				testing.getScene().setRoot(test.loadSwimmersScreen());

			}

		});

		// EXIT APPLICATION BUTTON
		Button closeBtn = new Button("Exit");
		closeBtn.setPrefSize(200, 20);
		closeBtn.setFont(Font.font("SanSerif", 15));
		closeBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				// get a handle to the stage
				Stage stage = (Stage) closeBtn.getScene().getWindow();
				// do what you have to do
				stage.close();

				// Another way of closing the app
				// Platform.exit();
				// System.exit(0);
			}

		});

		vBox.getChildren().addAll(logoPlace, label, viewSwimmer, viewCoach, testing, closeBtn);
		// vBox.setStyle("-fx-background-color: #8fbc8f;");

		return vBox;
	}

	/***
	 * This method checks do we have the connection with the database
	 */
	public void CheckConnection() {
		Connection conn = SQLConnection.DbConnector();
		if (conn == null) {
			System.out.println("Connection Not Successful");
			System.exit(1);
		} else {
			System.out.println("Connection Successful");
		}
	}

}
