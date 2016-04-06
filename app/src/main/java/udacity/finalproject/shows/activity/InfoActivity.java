package udacity.finalproject.shows.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.facebook.login.LoginManager;

import udacity.finalproject.shows.R;

/**
 * Created by Lobster on 04.04.16.
 */

public class InfoActivity extends AppCompatActivity {

    private BlankFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        int showId = getIntent().getIntExtra(Intent.EXTRA_TEXT, 1);
        fragment = (BlankFragment) getFragmentManager().findFragmentById(R.id.list_fragment);
        fragment.setShowId(showId);
    }

    public void share(View view) {
        fragment.share(view, this, LoginManager.getInstance());
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        fragment.callbackManager.onActivityResult(requestCode, responseCode, data);
    }

    public void leaveFeedBack(View view) {
        fragment.leaveFeedBack(view);
    }
}
