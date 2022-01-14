package ohad.dafna.happyhourapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * An interface for querying data from the "MyRecipe" database
 */
@Dao
public interface MyRecipeDao {
    @Query("SELECT * FROM myrecipes")
    LiveData<List<MyRecipeDatabase>> getAll();

    @Query("SELECT * FROM myrecipes")
    List<MyRecipeDatabase> getAllMyRecipes();

    @Query("SELECT * FROM myrecipes WHERE `key` = :key")
    LiveData<MyRecipeDatabase> getMyRecipesDatabase(long key);

    //search

    @Query("SELECT * FROM myrecipes WHERE cocktailName Like :cocktailName")
    MyRecipeDatabase findByName(String cocktailName);

    @Insert
    void insert(MyRecipeDatabase myRecipe);

    @Delete
    void delete(MyRecipeDatabase myRecipeDatabase);

    @Update
    void update(MyRecipeDatabase myRecipeDatabase);
}
