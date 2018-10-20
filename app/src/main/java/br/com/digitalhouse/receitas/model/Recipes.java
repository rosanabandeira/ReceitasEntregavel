package br.com.digitalhouse.receitas.model;

import android.graphics.drawable.Drawable;

public class Recipes {

    private String imgRecipes;
    private String recipes;


    public void setImgRecipes(String imgRecipes) {
        this.imgRecipes = imgRecipes;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }

    public Recipes(String imgRecipes, String recipes) {
        this.imgRecipes = imgRecipes;
        this.recipes = recipes;


    }


    public String getImgRecipes() {
        return imgRecipes;
    }
}
