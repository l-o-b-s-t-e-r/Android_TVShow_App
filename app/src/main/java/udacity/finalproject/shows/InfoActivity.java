package udacity.finalproject.shows;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Lobster on 04.04.16.
 */
public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();
        Log.i("EEEEEEEEE", String.valueOf(intent.getIntExtra(MainActivity.NUMBER,0)));
    }
}
