package ohad.dafna.happyhourapp.models;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.database.DBManager;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CocktailDataSource {
    // using Singleton design pattern
    private static CocktailDataSource sharedInstance;

    private CocktailDataSource() {
    }

    public static CocktailDataSource getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new CocktailDataSource();
        }
        return sharedInstance;
    }

    // using Retrofit to fetch the info from API
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // using the interface to retrieve the info we're looking for
    CocktailService service = retrofit.create(CocktailService.class);

    //
    public void getCocktails(MutableLiveData<List<CocktailDatabase>> callback, DBManager manager, MutableLiveData<Throwable> exCallback) {
        manager.getAllCocktails(cocktailDatabases -> {
            if (!cocktailDatabases.isEmpty()) {
                callback.postValue(cocktailDatabases);
            } else {
                String[] firstLetters = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o",
                        "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
                ArrayList<CocktailDatabase> allCocktails = new ArrayList<>();
                for (String firstLetter : firstLetters) {
                    Call<CocktailResponse> cocktailResponseCall = service.getCocktails(firstLetter);
                    cocktailResponseCall.enqueue(new Callback<CocktailResponse>() {
                        @RequiresApi(api = Build.VERSION_CODES.N)
                        @Override
                        public void onResponse(Call<CocktailResponse> call, Response<CocktailResponse> response) {
                            CocktailResponse cocktailResponse = response.body();
                            if (cocktailResponse == null || cocktailResponse.getCocktailList() == null) {
                                System.out.println("Cocktail response is null");
                                return;
                            }
                            for (Cocktail c : cocktailResponse.getCocktailList()) {
                                CocktailDatabase cd = new CocktailDatabase(c);
                                allCocktails.add(cd);
                                manager.insertCocktail(cd);
                            }
                            if (firstLetter.equals("z")) {
                                callback.postValue(allCocktails);
                            }
                        }

                        @Override
                        public void onFailure(Call<CocktailResponse> call, Throwable t) {
                            exCallback.postValue(t);
                        }
                    });
                }
            }
        });
    }

    public void getMyRecipes(MutableLiveData<List<MyRecipeDatabase>> callback, DBManager manager, MutableLiveData<Throwable> exCallback) {
        manager.getAllMyRecipes(myRecipeDatabases -> {
            if (!myRecipeDatabases.isEmpty()) {
                callback.postValue(myRecipeDatabases);
            }
        });
    }

    public void getCurrentRecipe(MutableLiveData<MyRecipeDatabase> callback, DBManager manager, String cocktailName) {
        manager.getRecipe(myRecipeDatabase -> {
            callback.postValue(myRecipeDatabase);
        }, cocktailName);
    }

    public void getRandomCocktail(MutableLiveData<CocktailDatabase> callback, DBManager manager, MutableLiveData<Throwable> exCallback) {
        manager.getAllCocktails(cocktailDatabases -> {
            callback.postValue(cocktailDatabases.get(new Random().nextInt(cocktailDatabases.size())));
        });
    }

    public void getCurrentCocktail(MutableLiveData<CocktailDatabase> callback, DBManager manager, String cocktailName) {
        manager.getCocktail(cocktailDatabase -> {
            callback.postValue(cocktailDatabase);
        }, cocktailName);
    }
}
