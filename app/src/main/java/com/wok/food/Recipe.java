package com.wok.food;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by b on 2/15/2016.
 */
public class Recipe  implements Serializable{
    public String recipeName;
    public List<String> recipeInstructions=new ArrayList<String>();
    public List<FoodItem> ingredients=new ArrayList<FoodItem>();
    public Recipe(String rname, String rinst, FoodItem rfood){

        this.recipeName=rname;
        this.recipeInstructions.add(rinst);
        this.ingredients.add(rfood);
    }
}
