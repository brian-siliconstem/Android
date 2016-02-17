package com.example.b.class8;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by b on 2/15/2016.
 */
public class MainPagerAdapter extends FragmentPagerAdapter {

        boolean showToast=false;
        private static int numberOfTabs=5;
        public static String tabTitles[] = new String[] { "Need to Buy","Menu", "Pantry", "Recipes","Food" };
        private Context context;
        public MainPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
            toast( "MainPagerAdapter:constructor");
        }

        @Override
        public Fragment getItem(int position) {
            toast("MainPagerAdapter:getItem:" + position);
            switch (position) {
                case 0:
                    ShoppingListFragment shoppingList =ShoppingListFragment.newInstance();//= new ShoppingListFragment();
                    toast("MainPagerAdapter:getItem:ShoppingListFragment");
                    return shoppingList;
                case 1:
                    MenuListFragment menuList =  MenuListFragment.newInstance();
                    toast("MainPagerAdapter:getItem:MenuListFragment");
                    return menuList;
                case 2:
                    PantryFragment pantry =  PantryFragment.newInstance();
                    return pantry;
                case 3:
                    RecipeListFragment recipes =  RecipeListFragment.newInstance();
                    return recipes;
                case 4:
                    FoodListFragment food =  FoodListFragment.newInstance();

                    return food;
                default:
                    return null;
            }
        }


    @Override
        public int getCount() {
       // Toast.makeText(this.context, "MainPagerAdapter:getCount: "+numberOfTabs, Toast.LENGTH_SHORT).show();
            return numberOfTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            toast("MainPagerAdapter:getPageTitle:"+position);
            return tabTitles[position];
        }
    public void toast(String message){
        if (showToast) Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show();
    }
}
