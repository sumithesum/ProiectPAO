package Momentan;

public class Game {
    private String name;
    private String description;
    private String tags;
    private String price;
    private String rating;
    private String releaseDate;
    private String developer;
    private String ageRating;

    public Game(String name, String description, String tags, String price, String rating, String releaseDate, String developer, String ageRating) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.price = price;
        this.rating = rating;
        this.releaseDate = releaseDate;
        this.developer = developer;
        this.ageRating = ageRating;
    }
    public Game() {
        this.name = null;
        this.description = null;
        this.tags = null;
        this.price = null;
        this.rating = null;
        this.releaseDate = null;
        this.developer = null;
        this.ageRating = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public String getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(String ageRating) {
        this.ageRating = ageRating;
    }
}
