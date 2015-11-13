package ca.delilaheve.arkcompanion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.delilaheve.arkcompanion.fragment.MainGridFragment;
import ca.delilaheve.arkcompanion.fragment.MainListFragment;

public class MainActivity extends AppCompatActivity {

    private boolean isListView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        // load view setting

        setFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_toggle_gridview:
                isListView = !isListView;
                item.setIcon(isListView ? R.drawable.action_view_grid : R.drawable.action_view_list);
                setFragment();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause() {
        // save view setting

        super.onPause();
    }

    private void setFragment() {
        Fragment fragment = isListView ? new MainListFragment() : new MainGridFragment();
        getFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();

    }
}
