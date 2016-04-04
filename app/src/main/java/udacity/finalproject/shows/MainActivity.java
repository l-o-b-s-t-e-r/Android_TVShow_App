package udacity.finalproject.shows;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    static public String NUMBER="NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        List<Show> shows = Arrays.asList(new Show("Scrubs", "Comedy", 8.5f, R.drawable.scrubs),
                                         new Show("Friends", "Comedy", 9.1f, R.drawable.friends),
                                         new Show("Breaking Bad", "Triller", 8.9f, R.drawable.breaking_bad),
                                         new Show("Game of Thrones", "Fanstastic", 8.5f, R.drawable.game_of_thrones),
                                         new Show("How I met your mother", "Comedy", 9.0f, R.drawable.himym)
                );

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, shows);
        recyclerView.setAdapter(adapter);

        DatabaseHelper databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        try {
            Log.i("COUNT",String.valueOf(databaseHelper.getShowDao().countOf()));
        }catch (SQLException e){

        }
    }
}
