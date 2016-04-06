package udacity.finalproject.shows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Lobster on 04.04.16.
 */

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        int showId = getIntent().getIntExtra(Intent.EXTRA_TEXT, 1);
        BlankFragment fragment = (BlankFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        fragment.setShowId(showId);
    }
}
