package ca.delilaheve.arkcompanion.util.loader;

import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Recipe;

public class RecipesLoader extends XMLLoader {

    private ArrayList<Recipe> recipes;

    private Recipe recipe;

    public RecipesLoader() {
        recipes = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("level"))
            recipe = new Recipe();
    }

    @Override
    public void textRead(String tag, String text) {
        switch (tag) {
            case "name":
                recipe.setName(text);
                break;
            case "material":
                recipe.addMaterial(text);
                break;
            case "effect":
                recipe.addEffect(text);
                break;
        }
    }

    @Override
    public void endTagRead(String tag) {
        if(tag.equals("level"))
            recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
