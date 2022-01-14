package ohad.dafna.happyhourapp.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IngredientService {
    @GET("api/json/v1/1/list.php")
    Call<IngredientResponse> getIngredients(@Query("i") String list);
}
