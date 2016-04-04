package udacity.finalproject.shows;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayout.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);

        List<Show> shows = Arrays.asList(new Show("Scrubs", "Comedy", 8, R.drawable.scrubs),
                                         new Show("Friends", "Comedy", 9, R.drawable.friends),
                                         new Show("Breaking Bad", "Triller", 9, R.drawable.breaking_bad),
                                         new Show("Game of Thrones", "Fanstastic", 8, R.drawable.game_of_thrones),
                                         new Show("How I met your mother", "Comedy", 8, R.drawable.himym)
                );

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(shows);
        recyclerView.setAdapter(adapter);
    }
}
