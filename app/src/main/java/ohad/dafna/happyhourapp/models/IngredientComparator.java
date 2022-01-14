package ohad.dafna.happyhourapp.models;

import java.util.Comparator;

public class IngredientComparator implements Comparator<Ingredient> {
    @Override
    public int compare(Ingredient o1, Ingredient o2) {
        return o1.getStrIngredient1().compareToIgnoreCase(o2.getStrIngredient1());
    }
}
