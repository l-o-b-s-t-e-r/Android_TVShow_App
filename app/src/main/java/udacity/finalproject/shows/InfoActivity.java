package udacity.finalproject.shows;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;

/**
 * Created by Lobster on 04.04.16.
 */

public class InfoActivity extends AppCompatActivity {

    private TVShow tvShowInfo;
    private Rating rating;

    private TextView textViewFeedBack;
    private TextView textViewRating;
    private TextView textViewName;
    private TextView textViewGenre;
    private TextView textViewDescription;

    private ImageView imageView;
    private RatingBar ratingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        textViewName = (TextView) findViewById(R.id.name);
        textViewGenre = (TextView) findViewById(R.id.genre);
        textViewDescription = (TextView) findViewById(R.id.description);
        textViewRating = (TextView) findViewById(R.id.rating);
        textViewFeedBack = (TextView) findViewById(R.id.feed_back);

        imageView = (ImageView) findViewById(R.id.main_image);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Intent intent = getIntent();
        try {
            QueryBuilder<TVShow, Integer> queryBuilder = MainActivity.databaseHelper.getShowDao().queryBuilder();
            PreparedQuery<TVShow> preparedQuery = queryBuilder.where().eq("showId", intent.getIntExtra(Intent.EXTRA_TEXT, 0)).prepare();

            tvShowInfo = MainActivity.databaseHelper.getShowDao().queryForFirst(preparedQuery);
            imageView.setImageResource(tvShowInfo.getImageId());
            textViewName.setText(tvShowInfo.getName());
            textViewGenre.setText(tvShowInfo.getGenre());
            textViewRating.setText(String.valueOf(tvShowInfo.getRoundedRating()));
            textViewDescription.setText(tvShowInfo.getDescription());


            QueryBuilder<Rating, Integer> queryBuilderRating = MainActivity.databaseHelper.getRatingDao().queryBuilder();
            Where<Rating, Integer> where = queryBuilderRating.where();
            PreparedQuery<Rating> preparedQueryRating = where.and(where.eq("userName", MainActivity.userName), where.eq("showName", tvShowInfo.getName())).prepare();

            rating = MainActivity.databaseHelper.getRatingDao().queryForFirst(preparedQueryRating);

            if (rating != null) {
                ratingBar.setRating(rating.getRating());
            }

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float userRating, boolean fromUser) {
                    try {
                        if (rating != null) {
                            tvShowInfo.setRating((tvShowInfo.getRating()*tvShowInfo.getEvaluationsNumber()-rating.getRating()+userRating)/tvShowInfo.getEvaluationsNumber());
                            MainActivity.databaseHelper.getShowDao().update(tvShowInfo);
                            rating.setRating(userRating);
                            MainActivity.databaseHelper.getRatingDao().update(rating);
                        } else {
                            tvShowInfo.setRating((tvShowInfo.getRating() * tvShowInfo.getEvaluationsNumber() + userRating) / (tvShowInfo.getEvaluationsNumber() + 1));
                            tvShowInfo.setEvaluationsNumber(tvShowInfo.getEvaluationsNumber() + 1);
                            MainActivity.databaseHelper.getShowDao().update(tvShowInfo);

                            rating = new Rating(MainActivity.userName, tvShowInfo.getName(), userRating);
                            MainActivity.databaseHelper.getRatingDao().create(rating);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    textViewRating.setText(String.valueOf((tvShowInfo.getRoundedRating())));
                    Log.i("USER RATING", String.valueOf(userRating));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void leaveFeedBack(View view) {
        if (textViewFeedBack!=null && textViewFeedBack.getText().length()!=0) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, textViewFeedBack.getText());
            intent.putExtra(Intent.EXTRA_EMAIL, new String[] {getString(R.string.email)});
            intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));

            try {
                startActivity(intent);
            }catch (ActivityNotFoundException e){
                Toast.makeText(this, getString(R.string.toast_error_message),Toast.LENGTH_LONG).show();
            }
        }
    }
}
