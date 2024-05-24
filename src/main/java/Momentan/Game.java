package Momentan;

public class Game {

    private String name;
    private String description;
    private String tags;
    private String price;
    private String rating;
    private String developer;
    private String ageRating;

    public Game(String name, String description, String tags, String price, String rating, String developer, String ageRating) {
        this.name = name;
        this.description = description;
        this.tags = tags;
        this.price = price;
        this.rating = rating;
        this.developer = developer;
        this.ageRating = ageRating;
    }
    public Game(String name) {
        this.name = name;
        this.description = null;
        this.tags = null;
        this.price = null;
        this.rating = null;
        this.developer = null;
        this.ageRating = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getTags() {
        return tags;
    }

    public String getPrice() {
        return price;
    }

    public String getRating() {
        return rating;
    }

    public String getDeveloper() {
        return developer;
    }

    public String getAgeRating() {
        return ageRating;
    }
    public void  printInfo(){
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Tags: " + tags);
        System.out.println("Price: " + price);
        System.out.println("Rating: " + rating);
        System.out.println("Developer: " + developer);
        System.out.println("Age rating: " + ageRating);
    }

    public void setRating(String rating) {

    }
}
