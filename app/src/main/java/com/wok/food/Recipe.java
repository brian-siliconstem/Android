package com.wok.food;
import java.io.Serializable;
import java.util.HashSet;

/**
 * Created by b on 2/15/2016.
 */
public class Recipe  implements Serializable{
    HashSet<FoodItem> ingredients;
}
