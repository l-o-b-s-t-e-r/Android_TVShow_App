package udacity.finalproject.shows;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.share.ShareApi;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lobster on 04.04.16.
 */

public class InfoActivity extends AppCompatActivity {

    private CallbackManager callbackManager;

    private TVShow tvShowInfo;
    private Rating rating;

    private TextView textViewFeedBack;
    private TextView textViewRating;
    private TextView textViewName;
    private TextView textViewGenre;
    private TextView textViewDescription;

    private ImageView imageView;
    private RatingBar ratingBar;

    private ImageButton shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        findViews();

        Map<String, Object> properties = new HashMap<>();
        properties.put("userName", MainActivity.userName);
        properties.put("showName", tvShowInfo.getName());

        List<Rating> ratings = MainActivity.databaseHelper.getAllBy(properties, Rating.class);
        rating = (ratings.isEmpty()) ? null : ratings.get(0);

        if (rating != null) {
            ratingBar.setRating(rating.getRating());
            shareButton.setVisibility(View.VISIBLE);
        }

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float userRating, boolean fromUser) {
                if (rating != null) {
                    tvShowInfo.setRating((tvShowInfo.getRating() * tvShowInfo.getEvaluationsNumber() - rating.getRating() + userRating) / tvShowInfo.getEvaluationsNumber());
                    rating.setRating(userRating);

                    MainActivity.databaseHelper.save(tvShowInfo, TVShow.class);
                    MainActivity.databaseHelper.save(rating, Rating.class);
                } else {
                    tvShowInfo.setRating((tvShowInfo.getRating() * tvShowInfo.getEvaluationsNumber() + userRating) / (tvShowInfo.getEvaluationsNumber() + 1));
                    tvShowInfo.setEvaluationsNumber(tvShowInfo.getEvaluationsNumber() + 1);
                    MainActivity.databaseHelper.save(tvShowInfo, TVShow.class);

                    rating = new Rating(MainActivity.userName, tvShowInfo.getName(), userRating);
                    shareButton.setVisibility(View.VISIBLE);
                    MainActivity.databaseHelper.save(rating, Rating.class);
                }

                textViewRating.setText(String.valueOf((tvShowInfo.getRoundedRating())));
                Log.i("USER MARK", String.valueOf(userRating));
            }
        });

    }

    private void findViews() {
        textViewName = (TextView) findViewById(R.id.name);
        textViewGenre = (TextView) findViewById(R.id.genre);
        textViewDescription = (TextView) findViewById(R.id.description);
        textViewRating = (TextView) findViewById(R.id.rating);
        textViewFeedBack = (TextView) findViewById(R.id.feed_back);

        imageView = (ImageView) findViewById(R.id.main_image);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        shareButton = (ImageButton) findViewById(R.id.share_btn);

        fillViews();
    }

    private void fillViews() {
        tvShowInfo = MainActivity.databaseHelper.getFirst("showId", getIntent().getIntExtra(Intent.EXTRA_TEXT, 0), TVShow.class);

        imageView.setImageResource(tvShowInfo.getImageId());
        textViewName.setText(tvShowInfo.getName());
        textViewGenre.setText(tvShowInfo.getGenre());
        textViewRating.setText(String.valueOf(tvShowInfo.getRoundedRating()));
        textViewDescription.setText(tvShowInfo.getDescription());
    }

    public void leaveFeedBack(View view) {
        if (textViewFeedBack != null && textViewFeedBack.getText().length() != 0) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, textViewFeedBack.getText())
                    .putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email)})
                    .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));

            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, getString(R.string.toast_error_message), Toast.LENGTH_LONG).show();
            }
        }
    }

    public void share(View view) {
        LoginManager manager = LoginManager.getInstance();
        manager.logInWithPublishPermissions(this, Arrays.asList("publish_actions"));
        manager.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Bitmap image = BitmapFactory.decodeResource(getResources(), tvShowInfo.getImageId());
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .setCaption(getString(R.string.your_rate) + " " + rating.getRoundedRating() + '\n' + "https://github.com/l-o-b-s-t-e-r/finalProject")
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                ShareApi.share(content, null);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException exception) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }
}
