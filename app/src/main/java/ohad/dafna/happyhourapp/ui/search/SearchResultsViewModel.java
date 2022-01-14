package ohad.dafna.happyhourapp.ui.search;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.models.CocktailDataSource;
import ohad.dafna.happyhourapp.utils.SingleLiveData;

public class SearchResultsViewModel extends AndroidViewModel {
    private final MutableLiveData<List<CocktailDatabase>> cocktails = new MutableLiveData<>();
    private final MutableLiveData<Throwable> exc = new MutableLiveData<>();
    private final SingleLiveData<CocktailDatabase> clickedCocktail = new SingleLiveData<>();

    public SearchResultsViewModel(@NonNull @NotNull Application application) {
        super(application);
        DBManager manager = new DBManager(application.getApplicationContext());
        CocktailDataSource.getSharedInstance().getCocktails(cocktails, manager, exc);
    }

    public MutableLiveData<List<CocktailDatabase>> getCocktails() {
        return cocktails;
    }

    public MutableLiveData<Throwable> getExc() {
        return exc;
    }

    public SingleLiveData<CocktailDatabase> getClickedCocktail() {
        return clickedCocktail;
    }


}