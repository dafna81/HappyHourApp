package ohad.dafna.happyhourapp.models;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IngredientDataSource {
    private static IngredientDataSource sharedInstance;

    private IngredientDataSource() {
    }

    public static IngredientDataSource getSharedInstance() {
        if (sharedInstance == null) {
            sharedInstance = new IngredientDataSource();
        }
        return sharedInstance;
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    IngredientService service = retrofit.create(IngredientService.class);

    public void getIngredients(MutableLiveData<List<Ingredient>> callback, MutableLiveData<Throwable> exCallback) {
        Call<IngredientResponse> ingredientResponseCall = service.getIngredients("list");
        ingredientResponseCall.enqueue(new Callback<IngredientResponse>() {
            @Override
            public void onResponse(Call<IngredientResponse> call, Response<IngredientResponse> response) {
                IngredientResponse ingredientResponse = response.body();
                if (ingredientResponse == null || ingredientResponse.getIngredients() == null) {
                    System.err.println("Ingredient response is null");
                    return;
                }
                callback.postValue(ingredientResponse.getIngredients());
            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                System.err.println("Failure");
                exCallback.postValue(t);
            }
        });
    }
}
