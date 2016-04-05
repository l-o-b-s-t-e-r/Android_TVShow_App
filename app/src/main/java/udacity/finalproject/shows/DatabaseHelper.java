package udacity.finalproject.shows;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Lobster on 04.04.16.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "udacity.db";
    private static final int DATABASE_VERSION = 14;

    private Dao<TVShow, Integer> dao;
    private Dao<Rating, Integer> ratingDao;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.config);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {

            TableUtils.createTable(connectionSource, TVShow.class);
            TableUtils.createTable(connectionSource, Rating.class);
            dao = getDao(TVShow.class);
            ratingDao = getDao(Rating.class);

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.friends))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(5.0f)
                    .description(context.getString(R.string.description_friends))
                    .imageId(R.drawable.friends)
                    .evaluationsNumber(15).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.himym))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(4.5f)
                    .description(context.getString(R.string.description_himym))
                    .imageId(R.drawable.himym)
                    .evaluationsNumber(10).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.breaking_bad))
                    .genre(context.getString(R.string.genre_thriller))
                    .rating(4.7f)
                    .description(context.getString(R.string.description_breaking_bad))
                    .imageId(R.drawable.breaking_bad)
                    .evaluationsNumber(12).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.game_of_thrones))
                    .genre(context.getString(R.string.genre_fantasy))
                    .rating(4.3f)
                    .description(context.getString(R.string.description_game_of_thrones))
                    .imageId(R.drawable.game_of_thrones)
                    .evaluationsNumber(8).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.scrubs))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(4.6f)
                    .description(context.getString(R.string.description_scrubs))
                    .imageId(R.drawable.scrubs)
                    .evaluationsNumber(9).build());

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            TableUtils.dropTable(connectionSource, TVShow.class, true);
            TableUtils.dropTable(connectionSource, Rating.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    public Dao<TVShow, Integer> getShowDao() throws SQLException {
        if (dao == null) {
            dao = getDao(TVShow.class);
        }
        return dao;
    }

    public Dao<Rating, Integer> getRatingDao() throws SQLException {
        if (ratingDao == null) {
            ratingDao = getDao(Rating.class);
        }
        return ratingDao;
    }

}
