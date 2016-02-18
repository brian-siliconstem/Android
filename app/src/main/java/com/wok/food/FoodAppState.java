package com.wok.food;

/**
 * Created by b on 2/17/2016.
 */
public class FoodAppState {
    public RecipeBook recipeBook;
    public Pantry pantry;
    public MealMenu menu;

    public FoodAppState(){
        menu = new MealMenu();
        pantry = new Pantry();
        recipeBook = new RecipeBook();
    }
}
