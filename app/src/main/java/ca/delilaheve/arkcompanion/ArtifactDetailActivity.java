package ca.delilaheve.arkcompanion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ca.delilaheve.arkcompanion.fragment.ArtifactFragment;

public class ArtifactDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        ArtifactFragment fragment = new ArtifactFragment();
        fragment.setArguments(getIntent().getExtras());
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
    }
}
