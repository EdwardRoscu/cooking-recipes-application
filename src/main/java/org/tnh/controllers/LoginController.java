package org.tnh.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.tnh.exceptions.InvalidPasswordException;
import org.tnh.exceptions.InvalidUsernameException;
import org.tnh.exceptions.UncompletedFieldsException;
import org.tnh.model.LoggedUser;
import org.tnh.model.User;
import org.tnh.services.RecipeService;
import org.tnh.services.UserService;
import java.util.Objects;

public class LoginController
{
    private Stage stage;
    private Parent root;

    @FXML
    private Text loginMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private static TextField search;

    public static String getSearch() {
        return search.getText();
    }

    public void handleCreateAccountAction(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("register.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Registration");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public void handleLoginAction(ActionEvent event) throws Exception
    {
        try {
            UserService.loginUncompletedFields(username.getText(), password.getText());
            LoggedUser.loggedUser = UserService.loggedUser(username.getText(), password.getText());
            loginMessage.setText("Successful Log In!");
            if (UserService.getUserRole(username.getText()).equals("Junior Chef")) {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("junior.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Junior Chef");
            } else {
                root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("head.fxml")));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Head Chef");
            }
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch(UncompletedFieldsException | InvalidUsernameException | InvalidPasswordException e) {
            loginMessage.setText(e.getMessage());
        }
    }

    public void handleShowRecipes(ActionEvent event) throws Exception {
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("list_recipes.fxml")));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("List of recipes");
        stage.setScene(new Scene(root, 1280, 720));
        stage.show();
    }

    public void handleSearch(ActionEvent event) throws Exception {
        try {
            RecipeService.uncompletedNameField(search.getText());

            root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("search.fxml")));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setTitle("List of recipes");
            stage.setScene(new Scene(root, 1280, 720));
            stage.show();
        } catch(UncompletedFieldsException e) {



        }
    }

}
