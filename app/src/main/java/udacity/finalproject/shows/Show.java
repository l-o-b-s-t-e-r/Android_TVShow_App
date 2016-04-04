package udacity.finalproject.shows;

/**
 * Created by Lobster on 04.04.16.
 */
public class Show {

    private String name;
    private String genre;
    private int rating;
    private int imageId;

    public Show(String name, String genre, int rating, int imageId){
        this.name = name;
        this.genre = genre;
        this.rating = rating;
        this.imageId = imageId;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImage(int imageId) {
        this.imageId = imageId;
    }
}
