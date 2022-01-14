package ohad.dafna.happyhourapp.ui.search;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.models.CocktailDataSource;
import ohad.dafna.happyhourapp.models.Ingredient;
import ohad.dafna.happyhourapp.models.IngredientDataSource;

public class SearchViewModel extends AndroidViewModel {
    private final MutableLiveData<List<CocktailDatabase>> mCocktails = new MutableLiveData<>();
    private final MutableLiveData<List<Ingredient>> mIngredients = new MutableLiveData<>();
    private final MutableLiveData<Throwable> exc = new MutableLiveData<>();

    public SearchViewModel(Application application) {
        super(application);
        DBManager manager = new DBManager(application.getApplicationContext());
        CocktailDataSource.getSharedInstance().getCocktails(mCocktails, manager, exc);
        IngredientDataSource.getSharedInstance().getIngredients(mIngredients, exc);
    }

    public MutableLiveData<List<CocktailDatabase>> getmCocktails() {
        return mCocktails;
    }

    public LiveData<List<Ingredient>> getmIngredients() {
        return mIngredients;
    }

    public LiveData<Throwable> getExc() {
        return exc;
    }

    public ArrayList<String> getIngredients() {
        ArrayList<String> allIngredients = new ArrayList<>();
        for (CocktailDatabase cocktailDatabase : Objects.requireNonNull(mCocktails.getValue())) {
            allIngredients.addAll(cocktailDatabase.getIngredients());
        }
        Set<String> ingredientSet = new HashSet<>(allIngredients);
        ArrayList<String> finalIngredients = new ArrayList<>(ingredientSet);
        return finalIngredients;
    }
}