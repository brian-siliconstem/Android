package com.wok.food;

import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by b on 2/17/2016.
 */
public class FoodAppState implements Serializable{
    public RecipeBook recipeBook;
    public HashMap<String,Recipe> openRecipes;
    public Pantry pantry;
    public MealMenu menu;
    public AllFoods allFoods;

    public final String recipeBookFilename="com.wok.food."+"recipeBook"+".json";
    public final String pantryFilename="com.wok.food."+"pantry"+".json";
    public final String menuFilename="com.wok.food."+"menu"+".json";
    public final String allFoodsFilename="com.wok.food."+"allFoods"+".json";

    public FoodAppState(){
        menu = new MealMenu();
        pantry = new Pantry();
        recipeBook = new RecipeBook();
        allFoods=new AllFoods();
    }
    public void saveAllFoodsJson(FileOutputStream outputStream) throws Exception{
        Gson gson = new Gson();

        String s="";
        Type allFoodsType = new TypeToken<AllFoods>() { }.getType();
        s = gson.toJson(this.allFoods,allFoodsType);
        try {
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e) {
            throw new Exception("saveJson file output exception"+e.getMessage());
        }
    }
    public void loadAllFoodsJson(FileInputStream inputStream) throws Exception{

        StringBuilder sb = new StringBuilder();
        try{
            InputStreamReader isr = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        }
        catch(java.io.FileNotFoundException e){
            throw new Exception("loadGSON file not found exception:"+e.getMessage());
        }
        catch(java.io.IOException e){
            throw new Exception("loadGSON IO exception:"+e.getMessage());
        }
        catch(Exception e)
        {
            throw new Exception("Other exception:"+e.getMessage());
        }

        String json = sb.toString();
        Gson gson = new Gson();

        Type allFoodsType = new TypeToken<AllFoods>() { }.getType();

        this.allFoods = gson.fromJson(json, allFoodsType);



    }

}
