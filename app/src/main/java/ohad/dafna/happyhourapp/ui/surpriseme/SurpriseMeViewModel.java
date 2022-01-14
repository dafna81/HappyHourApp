package ohad.dafna.happyhourapp.ui.surpriseme;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.models.CocktailDataSource;
import ohad.dafna.happyhourapp.utils.SingleLiveData;

public class SurpriseMeViewModel extends AndroidViewModel {
    private final MutableLiveData<CocktailDatabase> randomCocktail = new MutableLiveData<>();
    private final MutableLiveData<Throwable> exc = new MutableLiveData<>();
    private final SingleLiveData<CocktailDatabase> likedCocktail = new SingleLiveData<>();
    private final DBManager manager;

    public SurpriseMeViewModel(@NonNull @NotNull Application application) {
        super(application);
        manager = new DBManager(application.getApplicationContext());
        CocktailDataSource.getSharedInstance().getRandomCocktail(randomCocktail, manager, exc);
    }

    public MutableLiveData<CocktailDatabase> getRandomCocktail() {
        return randomCocktail;
    }

    public MutableLiveData<Throwable> getExc() {
        return exc;
    }

    public SingleLiveData<CocktailDatabase> getLikedCocktail() {
        return likedCocktail;
    }

    public void updateCocktail(CocktailDatabase cocktailDatabase) {
        manager.updateCocktail(cocktailDatabase);
    }
}