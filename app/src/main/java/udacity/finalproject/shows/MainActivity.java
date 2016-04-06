package udacity.finalproject.shows;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    static public String userName = "user-name"; //temporary
    static public DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        BlankFragment fragment = (BlankFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        if (fragment != null) {
            fragment.setShowId(1);
        }

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        List<TVShow> shows = databaseHelper.getAll(TVShow.class);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, shows, fragment);
        recyclerView.setAdapter(adapter);


    }
}
