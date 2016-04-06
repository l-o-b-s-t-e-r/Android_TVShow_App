package udacity.finalproject.shows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.util.List;

import udacity.finalproject.shows.R;
import udacity.finalproject.shows.RecyclerViewAdapter;
import udacity.finalproject.shows.database.DatabaseHelper;
import udacity.finalproject.shows.model.TVShow;

public class MainActivity extends AppCompatActivity {

    static public String userName = "user-name"; //temporary
    static public DatabaseHelper databaseHelper;

    private BlankFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);

        fragment = (BlankFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        if (fragment != null) {
            fragment.setShowId(1);
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        List<TVShow> shows = databaseHelper.getAll(TVShow.class);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, shows, fragment);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        fragment.callbackManager.onActivityResult(requestCode, responseCode, data);
    }

    public void share(View view) {
        fragment.share(view, this, LoginManager.getInstance());
    }

    public void leaveFeedBack(View view) {
        fragment.leaveFeedBack(view);
    }

}
