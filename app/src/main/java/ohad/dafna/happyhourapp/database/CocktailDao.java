package ohad.dafna.happyhourapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * An interface for querying data from the "Cocktail" database
 */
@Dao
public interface CocktailDao {
    @Query("SELECT * FROM cocktails")
    LiveData<List<CocktailDatabase>> getAll();

    @Query("SELECT * FROM cocktails")
    List<CocktailDatabase> getAllCocktails();

    @Query("SELECT * FROM cocktails WHERE idDrink = :id")
    LiveData<CocktailDatabase> getCocktailDatabase(String id);

    //search

    @Query("SELECT * FROM cocktails WHERE strDrink Like :cocktailName")
    CocktailDatabase findByName(String cocktailName);

    @Query("SELECT * FROM cocktails WHERE `like` =1")
    LiveData<List<CocktailDatabase>> getLikedCocktails();

    @Insert
    void insert(CocktailDatabase cocktail);

    @Delete
    void delete(CocktailDatabase cocktailDatabase);

    @Query("UPDATE cocktails SET `like` = :toset WHERE strDrink = :cocktailName AND idDrink = :cocktailId")
    void setLiked(String cocktailName, String cocktailId, int toset);
}
