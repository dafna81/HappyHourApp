package ohad.dafna.happyhourapp.ui.usermenu;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.models.CocktailDataSource;
import ohad.dafna.happyhourapp.ui.search.DetailsHelper;

public class MyRecipesDetailsViewModel extends AndroidViewModel {
    private final MutableLiveData<MyRecipeDatabase> currentRecipe = new MutableLiveData<>();
    private final MutableLiveData<List<MyRecipeDatabase>> myRecipes = new MutableLiveData<>();
    private final DBManager manager;

    public MyRecipesDetailsViewModel(@NonNull @NotNull Application application) {
        super(application);
        manager = new DBManager(application.getApplicationContext());
        String cString = DetailsHelper.getInstance().getCocktailName();
        CocktailDataSource.getSharedInstance().getCurrentRecipe(currentRecipe, manager, cString);
    }

    public MutableLiveData<MyRecipeDatabase> getCurrentRecipe() {
        return currentRecipe;
    }

    public MutableLiveData<List<MyRecipeDatabase>> getMyRecipes() {
        return myRecipes;
    }

    public void deleteRecipe(MyRecipeDatabase myRecipeDatabase) {
        manager.deleteMyRecipe(myRecipeDatabase);
    }
}