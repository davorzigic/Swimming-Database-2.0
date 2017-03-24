package application;

import java.time.LocalDate;
import java.util.Optional;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Callback;

public class ViewCoachesScene extends ViewSwimmersScene {

	TextField idCoach, licenceNumber, email, phoneNumber, username, password;

	public BorderPane loadCoachesScreen() {

		final ObservableList<Coach> data = FXCollections.observableArrayList();
		TableView<Coach> coachesTable = new TableView<>();

		BorderPane viewCoachesPane = new BorderPane();
		viewCoachesPane.setPrefSize(1200, 600);

		VBox vBox = new VBox(5);
		vBox.setAlignment(Pos.TOP_CENTER);

		// BACK BUTTON
		final Button backBtn = new Button("Back");
		backBtn.setPadding(new Insets(5, 5, 5, 5));
		backBtn.setPrefSize(100, 20);
		backBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				backBtn.getScene().setRoot(loadScreenOne());
			}

		});
		
		// DELETE BUTTON
				Button deleteBtn = new Button("Delete");
				deleteBtn.setPadding(new Insets(5, 5, 5, 5));
				deleteBtn.setPrefSize(100, 20);

				deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						// TODO Auto-generated method stub
						Coach selectedItem = coachesTable.getSelectionModel().getSelectedItem();

						if (selectedItem == null) {
							Alert noSelectionWarning = new Alert(AlertType.WARNING);
							noSelectionWarning.setTitle("No Selection");
							noSelectionWarning.setHeaderText("No Person Selected");
							noSelectionWarning.setContentText("Please select a person in the table.");

							noSelectionWarning.showAndWait();
						} else {

							Alert deleteConfirmation = new Alert(AlertType.CONFIRMATION);
							deleteConfirmation.setTitle("Confirmation Dialog");
							// alert.setHeaderText("Look, a Confirmation Dialog");
							deleteConfirmation.setContentText("Do you really want to delete selected coach?");

							Optional<ButtonType> result = deleteConfirmation.showAndWait();
							if (result.get() == ButtonType.OK) {
								// ... user chose OK

								try {
									String query = "DELETE FROM coaches WHERE idCoach = ?";
									pst = conn.prepareStatement(query);
									pst.setInt(1, selectedItem.getIdInteger());
									pst.execute();
									pst.close();
								} catch (Exception e3) {
									// TODO: handle exception
									e3.printStackTrace();
								}
								coachesTable.getItems().remove(selectedItem);
							} else {
								// ... user chose CANCEL or closed the dialog
								deleteConfirmation.close();
							}

							// Don't select anything after deleting
							coachesTable.getSelectionModel().clearSelection();
							System.out.println();
						}
					}

				});
				
				VBox rightSideButtons = new VBox(5);
				rightSideButtons.setPadding(new Insets(10,10,10,10));
				rightSideButtons.getChildren().addAll(backBtn, deleteBtn);
				viewCoachesPane.setRight(rightSideButtons);

		

		// Getting the ID from the database and inserting it into the first
		// column
		TableColumn<Coach, Number> column1 = new TableColumn<Coach, Number>("ID");
		column1.setMinWidth(20);
		column1.setCellValueFactory(new PropertyValueFactory<Coach, Number>("idSwimmer"));
		column1.setCellValueFactory(new Callback<CellDataFeatures<Coach, Number>, ObservableValue<Number>>() {
			public ObservableValue<Number> call(CellDataFeatures<Coach, Number> u) {
				// u.getValue() returns the Person instance for a particular
				// TableView row

				return u.getValue().getId();
			}
		});

		// Getting the first name from the database and inserting it into
		// the second column
		TableColumn<Coach, String> column2 = new TableColumn<Coach, String>("First name");
		column2.setMinWidth(100);
		column2.setCellValueFactory(new PropertyValueFactory<Coach, String>("firstName"));
		column2.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the Person instance for a particular
				// TableView row

				return u.getValue().getFirstName();
			}
		});

		// Getting the last name from the database and inserting it into the
		// third column
		TableColumn<Coach, String> column3 = new TableColumn<Coach, String>("Last Name");
		column3.setMinWidth(100);
		column3.setCellValueFactory(new PropertyValueFactory<Coach, String>("lastName"));
		column3.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the Person instance for a particular
				// TableView row

				return u.getValue().getLastName();
			}
		});

		// Getting the DOB from the database and inserting it into the
		// fourth column
		TableColumn<Coach, String> column4 = new TableColumn<Coach, String>("DOB");
		column4.setMinWidth(100);
		column4.setCellValueFactory(new PropertyValueFactory<Coach, String>("DOB"));
		column4.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the User instance for a particular
				// TableView row

				return u.getValue().getEmail();
			}
		});

		// Getting the DOB from the database and inserting it into the

		TableColumn<Coach, String> column5 = new TableColumn<Coach, String>("Registration number");
		column5.setMinWidth(100);
		column5.setCellValueFactory(new PropertyValueFactory<Coach, String>("registrationNumber"));
		column5.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the User instance for a particular
				// TableView row

				return u.getValue().getPhone();
			}
		});

		// Getting the DOB from the database and inserting it into the

		TableColumn<Coach, String> column6 = new TableColumn<Coach, String>("DOJ");
		column6.setMinWidth(100);
		column6.setCellValueFactory(new PropertyValueFactory<Coach, String>("DOJ"));
		column6.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the User instance for a particular
				// TableView row

				return u.getValue().getUsername();
			}
		});

		// Getting the DOB from the database and inserting it into the

		TableColumn<Coach, String> column7 = new TableColumn<Coach, String>("Parent Name");
		column7.setMinWidth(100);
		column7.setCellValueFactory(new PropertyValueFactory<Coach, String>("parentName"));
		column7.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the User instance for a particular
				// TableView row

				return u.getValue().getPassword();
			}
		});

		// Getting the DOB from the database and inserting it into the

		TableColumn<Coach, String> column8 = new TableColumn<Coach, String>("Contact Number");
		column8.setMinWidth(100);
		column8.setCellValueFactory(new PropertyValueFactory<Coach, String>("contactNumber"));
		column8.setCellValueFactory(new Callback<CellDataFeatures<Coach, String>, ObservableValue<String>>() {
			public ObservableValue<String> call(CellDataFeatures<Coach, String> u) {
				// u.getValue() returns the User instance for a particular
				// TableView row

				return u.getValue().getLincenceNumber();
			}
		});

		// Here was unchecked warning
		coachesTable.getColumns().addAll(column1, column2, column3, column4, column5, column6, column7, column8);
		coachesTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		// Sorting button in the table
		coachesTable.setTableMenuButtonVisible(true);

		VBox vBoxForTable = new VBox();
		vBoxForTable.getChildren().add(coachesTable);
		Group viewCoachesGroup = new Group();
		viewCoachesGroup.getChildren().addAll(vBoxForTable);

		// Filling the table with the data from the database
		try {
			data.clear(); // clears the table
			String query = "select * from coaches";

			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				data.add(new Coach(rs.getInt("idCoach"), rs.getString("firstName"), rs.getString("lastName"),
						rs.getString("email"), rs.getString("phone"), rs.getString("username"),
						rs.getString("password"), rs.getString("licenceNumber")));
				coachesTable.setItems(data);
			}
			pst.close();
			rs.close();
		} catch (Exception e2) {
			System.err.println(e2);

		}

		viewCoachesPane.setCenter(vBoxForTable);

		// Defining textfields for the left side of the scene
		idCoach = new TextField();
		idCoach.setPrefSize(150, 20);
		idCoach.setFont(Font.font("SanSerif", 15));
		idCoach.setPromptText("ID");

		firstName = new TextField();
		firstName.setPrefSize(150, 20);
		firstName.setFont(Font.font("SanSerif", 15));
		firstName.setPromptText("First Name");

		lastName = new TextField();
		lastName.setPrefSize(150, 20);
		lastName.setFont(Font.font("SanSerif", 15));
		lastName.setPromptText("Last Name");

		email = new TextField();
		email.setPrefSize(150, 20);
		email.setFont(Font.font("SanSerif", 15));
		email.setPromptText("E-mail");

		phoneNumber = new TextField();
		phoneNumber.setPrefSize(150, 20);
		phoneNumber.setFont(Font.font("SanSerif", 15));
		phoneNumber.setPromptText("Phone Number");

		username = new TextField();
		username.setPrefSize(150, 20);
		username.setFont(Font.font("SanSerif", 15));
		username.setPromptText("Username");

		password = new TextField();
		password.setPrefSize(150, 20);
		password.setFont(Font.font("SanSerif", 15));
		password.setPromptText("Password");

		licenceNumber = new TextField();
		licenceNumber.setPrefSize(150, 20);
		licenceNumber.setFont(Font.font("SanSerif", 15));
		licenceNumber.setPromptText("Licence Number");

		// Place for textfields
		VBox firstRow = new VBox(5);
		firstRow.getChildren().addAll(idCoach, firstName, lastName, email, phoneNumber, username, password,
				licenceNumber);
		firstRow.setPadding(new Insets(5, 10, 10, 10));

		// SAVE BUTTON
		Button saveButton = new Button("Save");
		saveButton.setPrefSize(150, 15);
		saveButton.setFont(Font.font("SanSerif", 15));

		saveButton.setOnAction(e -> {
			try {

				if (idCoach.getText().isEmpty()) {
					System.out.println("You dont have ID");
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your ID field is empty.");
					alert.showAndWait();
				} else if (firstName.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your First Name field is empty.");
					alert.showAndWait();
				} else if (lastName.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your Last Name field is empty.");
					alert.showAndWait();
				} else if (email.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("Your e-mail field is empty.");
					alert.showAndWait();
				} else if (phoneNumber.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("You did not enter a phone number.");
					alert.showAndWait();
				} else if (username.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("You did not enter a username.");
					alert.showAndWait();
				} else if (password.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Information dialog");
					alert.setHeaderText(null);
					alert.setContentText("You did not enter a password.");
					alert.showAndWait();
				} else {
					Alert saveConfirmation = new Alert(AlertType.CONFIRMATION);
					saveConfirmation.setTitle("Confirmation Dialog");
					// alert.setHeaderText("Look, a Confirmation Dialog");
					saveConfirmation.setContentText("Save coach?");

					Optional<ButtonType> result = saveConfirmation.showAndWait();
					if (result.get() == ButtonType.OK) {
						// ... user chose OK
						String query = "INSERT INTO coaches (idCoach, firstName, lastName, email, phone, username, password, licenceNumber) VALUES(?,?,?,?,?,?,?,?)";
						pst = conn.prepareStatement(query);
						Integer value1A = Integer.parseInt(idCoach.getText());
						pst.setInt(1, value1A);
						// pst.setString(1, idSwimmer.getText());
						pst.setString(2,
								(firstName.getText().substring(0, 1).toUpperCase() + firstName.getText().substring(1)));
						pst.setString(3,
								(lastName.getText().substring(0, 1).toUpperCase() + lastName.getText().substring(1)));
						pst.setString(4, email.getText());
						pst.setString(5, phoneNumber.getText());
						pst.setString(6, username.getText());
						pst.setString(7, password.getText());
						pst.setString(8, phoneNumber.getText());
						pst.execute();
						pst.close();

						clearFields();

						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Information dialog");
						alert.setHeaderText(null);
						alert.setContentText("User has been created!");
						alert.showAndWait();

						try {
							data.clear(); // clears the table
							String queryRefresh = "select * from coaches";

							pst = conn.prepareStatement(queryRefresh);
							rs = pst.executeQuery();

							while (rs.next()) {
								data.add(new Coach(rs.getInt("idCoach"), rs.getString("firstName"),
										rs.getString("lastName"), rs.getString("email"), rs.getString("phone"),
										rs.getString("username"), rs.getString("password"),
										rs.getString("licenceNumber")));
								coachesTable.setItems(data);
							}
							pst.close();
							rs.close();
						} catch (Exception e2) {
							System.err.println(e2);

						}
					} else {
						saveConfirmation.close();
					}

				}

			} catch (Exception e1) {
				System.err.println(e1);
				e1.printStackTrace();
			}
		});

		VBox secondRow = new VBox(5);
		secondRow.getChildren().add(saveButton);
		secondRow.setPadding(new Insets(0, 10, 10, 10));
		
		VBox.setMargin(saveButton, new Insets(370,0,0,0));
		
		Group group = new Group();
		group.getChildren().addAll(secondRow,firstRow);
		
		viewCoachesPane.setLeft(group);
		
		coachesTable.getSelectionModel().clearSelection();

		return viewCoachesPane;
	}
	
	/***
	 * This method clears the fields after pressing the Save button
	 */
	public void clearFields() {
		idCoach.clear();
		firstName.clear();
		lastName.clear();
		email.clear();;
		phoneNumber.clear();
		username.clear();
		password.clear();
		licenceNumber.clear();
	}

}
