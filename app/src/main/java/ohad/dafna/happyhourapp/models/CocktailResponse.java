package ohad.dafna.happyhourapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CocktailResponse {
    @SerializedName("drinks")
    private List<Cocktail> cocktailList;

    public CocktailResponse() {
    }

    public List<Cocktail> getCocktailList() {
        return cocktailList;
    }

    public void setCocktailList(List<Cocktail> cocktailList) {
        this.cocktailList = cocktailList;
    }

    @Override
    public String toString() {
        return "CocktailResponse{" +
                "cocktailList=" + cocktailList +
                '}';
    }
}
