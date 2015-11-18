package ca.delilaheve.arkcompanion.util.loader;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.data.Recipe;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;

public class RecipesLoader extends XMLLoader {

    private ArrayList<Recipe> recipes;

    private Recipe recipe;

    public RecipesLoader(URL url, AsyncTaskImplementer implementer, String taskId, InputStream input) {
        super(url, implementer, taskId, input);
        recipes = new ArrayList<>();
    }

    @Override
    public void startTagRead(String tag) {
        if(tag.equals("recipe"))
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
        if(tag.equals("recipe"))
            recipes.add(recipe);
    }

    public ArrayList<Recipe> getRecipes() {
        return recipes;
    }
}
