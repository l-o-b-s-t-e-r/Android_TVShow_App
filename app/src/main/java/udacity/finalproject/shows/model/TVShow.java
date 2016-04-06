package udacity.finalproject.shows.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Lobster on 04.04.16.
 */

@DatabaseTable(tableName = "shows")
public class TVShow {

    @DatabaseField(generatedId = true)
    private int showId;

    @DatabaseField
    private String name;

    @DatabaseField
    private String genre;

    @DatabaseField
    private float rating;

    @DatabaseField
    private String description;

    @DatabaseField
    private int imageId;

    @DatabaseField
    private int evaluationsNumber;

    public TVShow() {

    }

    public TVShow(String name, String genre, float rating, String description, int imageId, int evaluationsNumber) {
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.imageId = imageId;
        this.evaluationsNumber = evaluationsNumber;
    }

    public int getShowId() {
        return showId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getRoundedRating() {
        return (float) Math.round(rating * 10.0f) / 10.0f;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getEvaluationsNumber() {
        return evaluationsNumber;
    }

    public void setEvaluationsNumber(int evaluationsNumber) {
        this.evaluationsNumber = evaluationsNumber;
    }
}

