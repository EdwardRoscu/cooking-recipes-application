package org.tnh.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.tnh.model.Recipe;
import org.tnh.services.RecipeService;

import java.util.Objects;

public class ListRecipesController extends AbstractTableViewController {

    @FXML
    private TableView<Recipe> recipesTableView;

    public void initialize() {
        setTable();
    }

    public void setTable() {
        initVars(recipeName, recipeCalories, recipeTime, recipeInstructions, recipeRating);
        ObservableList<Recipe> recipes_obs = FXCollections.observableArrayList();
        recipes_obs.addAll(Objects.requireNonNull(RecipeService.populateData()));
        recipesTableView.setItems(recipes_obs);
    }

}
