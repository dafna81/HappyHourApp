package ohad.dafna.happyhourapp.ui.search;

public class DetailsHelper {
    private static DetailsHelper instance;
    private String cocktailName;

    private DetailsHelper() {
    }

    public DetailsHelper(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public static DetailsHelper getInstance() {
        if (instance == null) {
            instance = new DetailsHelper();
        }
        return instance;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    @Override
    public String toString() {
        return "DetailsHelper{" +
                "cocktailName='" + cocktailName + '\'' +
                '}';
    }
}
