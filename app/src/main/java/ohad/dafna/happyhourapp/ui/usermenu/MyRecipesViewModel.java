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
import ohad.dafna.happyhourapp.utils.SingleLiveData;

public class MyRecipesViewModel extends AndroidViewModel {
    private final MutableLiveData<List<MyRecipeDatabase>> myRecipes = new MutableLiveData<>();
    private final MutableLiveData<Throwable> exc = new MutableLiveData<>();
    private final SingleLiveData<MyRecipeDatabase> clickedRecipe = new SingleLiveData<>();

    public MyRecipesViewModel(@NonNull @NotNull Application application) {
        super(application);
        DBManager manager = new DBManager(application.getApplicationContext());
        CocktailDataSource.getSharedInstance().getMyRecipes(myRecipes, manager, exc);
    }

    public MutableLiveData<List<MyRecipeDatabase>> getAllMyRecipes() {
        return myRecipes;
    }

    public MutableLiveData<Throwable> getExc() {
        return exc;
    }

    public SingleLiveData<MyRecipeDatabase> getClickedRecipe() {
        return clickedRecipe;
    }
}