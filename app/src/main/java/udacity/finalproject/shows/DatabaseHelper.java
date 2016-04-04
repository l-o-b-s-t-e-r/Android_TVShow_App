package udacity.finalproject.shows;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.support.annotation.Nullable;
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

    /************************************************
     * Suggested Copy/Paste code. Everything from here to the done block.
     ************************************************/

    private static final String DATABASE_NAME = "udacity.db";
    private static final int DATABASE_VERSION = 5;

    private Dao<TVShow, Integer> dao;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.config);
        this.context = context;
    }

    /************************************************
     * Suggested Copy/Paste Done
     ************************************************/

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {

            // Create tables. This onCreate() method will be invoked only once of the application life time i.e. the first time when the application starts.
            TableUtils.createTable(connectionSource, TVShow.class);
            dao = getDao(TVShow.class);

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.friends))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(9.1f)
                    .description(context.getString(R.string.description_friends))
                    .imageId(R.drawable.friends)
                    .evaluationsNumber(100).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.himym))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(8.7f)
                    .description(context.getString(R.string.description_himym))
                    .imageId(R.drawable.himym)
                    .evaluationsNumber(60).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.breaking_bad))
                    .genre(context.getString(R.string.genre_thriller))
                    .rating(9.0f)
                    .description(context.getString(R.string.description_breaking_bad))
                    .imageId(R.drawable.breaking_bad)
                    .evaluationsNumber(75).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.game_of_thrones))
                    .genre(context.getString(R.string.genre_fantasy))
                    .rating(8.9f)
                    .description(context.getString(R.string.description_game_of_thrones))
                    .imageId(R.drawable.game_of_thrones)
                    .evaluationsNumber(120).build());

            dao.create(TVShowBuilder.builder()
                    .name(context.getString(R.string.scrubs))
                    .genre(context.getString(R.string.genre_comedy))
                    .rating(8.8f)
                    .description(context.getString(R.string.description_scrubs))
                    .imageId(R.drawable.scrubs)
                    .evaluationsNumber(90).build());

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            // In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
            //automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
            // existing database etc.

            TableUtils.dropTable(connectionSource, TVShow.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    // Create the getDao methods of all database tables to access those from android code.
    // Insert, delete, read, update everything will be happened through DAOs

    public Dao<TVShow, Integer> getShowDao() throws SQLException {
        if (dao == null) {
            dao = getDao(TVShow.class);
        }
        return dao;
    }
}
