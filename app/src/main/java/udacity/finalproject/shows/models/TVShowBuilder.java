package udacity.finalproject.shows;

/**
 * Created by Lobster on 04.04.16.
 */
final public class TVShowBuilder {

    private String name;
    private String genre;
    private float rating;
    private String description;
    private int imageId;
    private int evaluationsNumber;

    public static TVShowBuilder builder() {
        return new TVShowBuilder();
    }

    public TVShowBuilder name(String name) {
        this.name = name;
        return this;
    }

    public TVShowBuilder genre(String genre) {
        this.genre = genre;
        return this;
    }

    public TVShowBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TVShowBuilder rating(float rating) {
        this.rating = rating;
        return this;
    }

    public TVShowBuilder evaluationsNumber(int evaluationsNumber) {
        this.evaluationsNumber = evaluationsNumber;
        return this;
    }

    public TVShowBuilder imageId(int imageId) {
        this.imageId = imageId;
        return this;
    }

    public TVShow build() {
        TVShow tvShowEntity = new TVShow();
        tvShowEntity.setDescription(this.description);
        tvShowEntity.setEvaluationsNumber(this.evaluationsNumber);
        tvShowEntity.setGenre(this.genre);
        tvShowEntity.setImageId(this.imageId);
        tvShowEntity.setRating(this.rating);
        tvShowEntity.setName(this.name);

        return tvShowEntity;
    }

}
