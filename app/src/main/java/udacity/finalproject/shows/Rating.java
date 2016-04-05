package udacity.finalproject.shows;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lobster on 05.04.16.
 */

@DatabaseTable(tableName = "rating")
public class Rating {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String userName;

    @DatabaseField
    private String showName;

    @DatabaseField
    private float rating;

    public Rating(){

    }

    public Rating(String userName, String showName, float rating) {
        this.userName = userName;
        this.showName = showName;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public float getRating() {
        return rating;
    }

    public float getRoundedRating(){
        return (float)Math.round(rating*10.0f)/10.0f;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
}
