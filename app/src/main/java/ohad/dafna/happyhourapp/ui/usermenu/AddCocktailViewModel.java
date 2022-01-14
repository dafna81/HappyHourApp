package ohad.dafna.happyhourapp.ui.usermenu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;

public class AddCocktailViewModel extends AndroidViewModel {
    private final MutableLiveData<List<MyRecipeDatabase>> myRecipes = new MutableLiveData<>();
    private final DBManager manager;

    public AddCocktailViewModel(@NonNull @NotNull Application application) {
        super(application);
        manager = new DBManager(application.getApplicationContext());
    }

    public void insertRecipe(MyRecipeDatabase myRecipe) {
        manager.insertMyRecipe(myRecipe);
    }

    public MutableLiveData<List<MyRecipeDatabase>> getMyRecipes() {
        return myRecipes;
    }
}