package ca.delilaheve.arkcompanion.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import ca.delilaheve.arkcompanion.R;
import ca.delilaheve.arkcompanion.data.Recipe;
import ca.delilaheve.arkcompanion.util.asynctask.AsyncTaskImplementer;
import ca.delilaheve.arkcompanion.util.loader.RecipesLoader;
import ca.delilaheve.arkcompanion.util.tools.AsyncFileRetriever;

public class RecipesFragment extends Fragment implements AsyncTaskImplementer {

    private View view;

    private AsyncFileRetriever retriever;

    private RecipesLoader loader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recipes, container, false);

        loadXml();

        return view;
    }

    private void loadXml(){
        // load XML files
        try {
            URL fileUrl = new URL("http://delilaheve.github.io/assets/ArkCompanion/xml/recipes.xml");
            retriever = new AsyncFileRetriever(fileUrl, this, "fileRetriever");
            Thread thread = new Thread(retriever);
            thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTaskComplete(String taskId) {
        switch (taskId) {
            case "fileRetriever":

                loader = new RecipesLoader(null, this, "loader", retriever.getInputStream());
                Thread thread = new Thread(loader);
                thread.start();

                break;
            case "loader":

                ArrayList<Recipe> recipes = loader.getRecipes();

                LinearLayout recipeTable = (LinearLayout) view.findViewById(R.id.recipe_table);
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                for(Recipe recipe : recipes) {

                    View row = inflater.inflate(R.layout.item_recipe_row, null, false);

                    TextView name, materials, effects;
                    name = (TextView) row.findViewById(R.id.recipe);
                    materials = (TextView) row.findViewById(R.id.materials);
                    effects = (TextView) row.findViewById(R.id.effects);

                    name.setText(recipe.getName());

                    if(recipe.getMaterials() != null){

                        String materialsString = "";
                        for(String material : recipe.getMaterials())
                            materialsString += material + "\n\n";
                        materials.setText(materialsString);

                    }

                    if(recipe.getEffects() != null){

                        String effectsString = "";
                        for(String effect : recipe.getEffects())
                            effectsString += effect + "\n\n";
                        effects.setText(effectsString);

                    }

                    recipeTable.addView(row);

                }

                break;
        }
    }

    @Override
    public Activity getCurrentActivity() {
        return getActivity();
    }
}
