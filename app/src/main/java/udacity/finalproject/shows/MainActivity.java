package udacity.finalproject.shows;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static public String userName = "user-name"; //temporary
    static public DatabaseHelper databaseHelper;
    static public SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        database = databaseHelper.getWritableDatabase();

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        try {
            List<TVShow> shows = databaseHelper.getShowDao().queryForAll();
            RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, shows);
            recyclerView.setAdapter(adapter);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
