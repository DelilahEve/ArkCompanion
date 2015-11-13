package ca.delilaheve.arkcompanion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.delilaheve.arkcompanion.adapter.ServerManagerFragment;
import ca.delilaheve.arkcompanion.fragment.ArtifactsFragment;
import ca.delilaheve.arkcompanion.fragment.BeaconsFragment;
import ca.delilaheve.arkcompanion.fragment.DossierFragment;
import ca.delilaheve.arkcompanion.fragment.DyesFragment;
import ca.delilaheve.arkcompanion.fragment.ItemQualityFragment;
import ca.delilaheve.arkcompanion.fragment.KibbleFragment;
import ca.delilaheve.arkcompanion.fragment.PatchNotesFragment;
import ca.delilaheve.arkcompanion.fragment.RecipesFragment;

public class FragmentContainerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        int pos = getIntent().getExtras().getInt("ItemPos");

        Fragment fragment;

        switch (pos) {
            case 0:
                fragment = new ArtifactsFragment();
                break;
            case 1:
                fragment = new BeaconsFragment();
                break;
            case 2:
                fragment = new ItemQualityFragment();
                break;
            case 3:
                fragment = new DossierFragment();
                break;
            case 4:
                fragment = new KibbleFragment();
                break;
            case 5:
                fragment = new DyesFragment();
                break;
            case 6:
                fragment = new RecipesFragment();
                break;
            case 7:
                fragment = new PatchNotesFragment();
                break;
            case 8:
                fragment = new ServerManagerFragment();
                break;
            default:
                fragment = new ArtifactsFragment();
                break;
        }

        System.out.println("Adding fragment " + pos);
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
