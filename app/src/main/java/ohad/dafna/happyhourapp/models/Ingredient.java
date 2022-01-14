package ohad.dafna.happyhourapp.models;

public class Ingredient {
    private String strIngredient1;

    public Ingredient(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient1() {
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    @Override
    public String toString() {
        return strIngredient1;
    }
}
