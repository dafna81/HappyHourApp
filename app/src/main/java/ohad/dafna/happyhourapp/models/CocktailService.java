package ohad.dafna.happyhourapp.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CocktailService {
    @GET("api/json/v1/1/search.php")
    Call<CocktailResponse> getCocktails(@Query("f") String firstLetter);

    @GET("api/json/v1/1/random.php")
    Call<CocktailResponse> getRandomCocktail();
}
