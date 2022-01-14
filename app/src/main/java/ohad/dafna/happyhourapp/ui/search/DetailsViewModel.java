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

public class DetailsViewModel extends AndroidViewModel {
    private final MutableLiveData<CocktailDatabase> currentCocktail = new MutableLiveData<>();
    private final MutableLiveData<List<CocktailDatabase>> cocktails = new MutableLiveData<>();
    private final SingleLiveData<CocktailDatabase> likedCocktail = new SingleLiveData<>();
    private final DBManager manager;

    public DetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        manager = new DBManager(application.getApplicationContext());
        String cString = DetailsHelper.getInstance().getCocktailName();
        CocktailDataSource.getSharedInstance().getCurrentCocktail(currentCocktail, manager, cString);
    }

    public MutableLiveData<CocktailDatabase> getCurrentCocktail() {
        return currentCocktail;
    }

    public MutableLiveData<List<CocktailDatabase>> getCocktails() {
        return cocktails;
    }

    public void updateCocktail(CocktailDatabase cocktailDatabase) {
        manager.updateCocktail(cocktailDatabase);
    }

    public SingleLiveData<CocktailDatabase> getLikedCocktail() {
        return likedCocktail;
    }
}