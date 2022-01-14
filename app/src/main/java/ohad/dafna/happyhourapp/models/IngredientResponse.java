package ohad.dafna.happyhourapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IngredientResponse {
    @SerializedName("drinks")
    private List<Ingredient> ingredients;

    public IngredientResponse() {
    }

    public List<Ingredient> getIngredients() {
        ingredients.sort(new IngredientComparator());
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "IngredientResponse{" +
                "ingredients=" + ingredients +
                '}';
    }
}
