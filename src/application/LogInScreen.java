package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class LogInScreen extends ViewSwimmersScene{
	
	Connection conn = SQLConnection.DbConnector();
	PreparedStatement pst;
	ResultSet rs = null;

	public BorderPane loadLogInScreen() {
		
		BorderPane logInPane = new BorderPane();
		
			

//		Color foreground = Color.rgb(255, 255, 255, 0.9);
//		// Rectangle background
//		Rectangle background = new Rectangle(320, 250);
//		background.setX(0);
//		background.setY(0);
//		background.setArcHeight(15);
//		background.setArcWidth(15);
//		background.setFill(Color.rgb(0, 0, 0, 0.55));
//		background.setStroke(foreground);
//		background.setStrokeWidth(1.5);

		VBox vbox = new VBox(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));

		Label label = new Label("Label");
		label.setTextFill(Color.WHITESMOKE);
		label.setFont(new Font("SanSerif", 20));

		TextField username = new TextField();
		username.setFont(Font.font("SanSerif", 20));
		username.setPromptText("Username");

		PasswordField password = new PasswordField();
		password.setFont(Font.font("SanSerif", 20));
		password.setPromptText("Password");

		// Login button
		Button btn = new Button("Login");
		btn.setFont(Font.font("SanSerif", 15));
//		btn.setOnAction(new EventHandler<ActionEvent>() {
//
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//
//				btn.getScene().setRoot(loadSwimmersScreen());
//			}
//
//		});
		btn.setOnAction(e -> {
			try {
				String query = "select * from coaches where username=? and password= ?";
				pst = conn.prepareStatement(query);
				pst.setString(1, username.getText());
				pst.setString(2, password.getText());
				rs = pst.executeQuery();

				if (rs.next()) {
					// label.setText("Login Successful");
					btn.getScene().setRoot(loadSwimmersScreen());
					System.out.println("test");
				} else {
					label.setText("Login Failed");
				}
				username.clear();
				password.clear();
				pst.close();
				rs.close();
			} catch (Exception e1) {
				label.setText("SQL Error");
				System.err.println(e1);
			}
		});
		
		VBox things = new VBox(5);
		things.getChildren().addAll(username,password,btn);
		logInPane.setCenter(things);
		
		return logInPane;
	}
}
