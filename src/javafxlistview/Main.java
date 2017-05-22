package javafxlistview;/*
        Licensed to the Apache Software Foundation (ASF) under one
        or more contributor license agreements.  See the NOTICE file
        distributed with this work for additional information
        regarding copyright ownership.  The ASF licenses this file
        to you under the Apache License, Version 2.0 (the
        "License"); you may not use this file except in compliance
        with the License.  You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing,
        software distributed under the License is distributed on an
        "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
        KIND, either express or implied.  See the License for the
        specific language governing permissions and limitations
        under the License.
 */


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	boolean isVisible;
    /*@Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        primaryStage.setTitle("Turais");
        primaryStage.setScene(new Scene(root, 640, 400));
        primaryStage.show();

    }*/


    public static void main(String[] args) {
        launch(args);
    }
    
    
    @Override
    public void start(Stage primaryStage) {

        // text field to show password as unmasked
        final TextField textField = new TextField();
        // Set initial state
        textField.setManaged(false);
        textField.setVisible(false);

        // Actual password field
        final PasswordField passwordField = new PasswordField();

       // CheckBox checkBox = new CheckBox("Show/Hide password");
        Button button = new Button("show/hide password");
        
        button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(isVisible)
				{
					isVisible = false;
					passwordField.setVisible(true);
					passwordField.setManaged(true);
					textField.setManaged(false);
					textField.setVisible(false);
				}
				else
				{
					isVisible = true;
					passwordField.setVisible(false);
					passwordField.setManaged(false);
					textField.setVisible(true);
					textField.setManaged(true);
				}
				
			}
		});

        // Bind properties. Toggle textField and passwordField
        // visibility and managability properties mutually when checkbox's state is changed.
        // Because we want to display only one component (textField or passwordField)
        // on the scene at a time.
       /* textField.managedProperty().bind(checkBox.selectedProperty());
        textField.visibleProperty().bind(checkBox.selectedProperty());

        passwordField.managedProperty().bind(checkBox.selectedProperty().not());
        passwordField.visibleProperty().bind(checkBox.selectedProperty().not());*/

        // Bind the textField and passwordField text values bidirectionally.
        textField.textProperty().bindBidirectional(passwordField.textProperty());

        VBox root = new VBox(10);
        root.getChildren().addAll(passwordField, textField, button);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("Demo");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



