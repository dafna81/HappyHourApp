package ohad.dafna.happyhourapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * The main database of the application
 */
@Database(entities = {CocktailDatabase.class, MyRecipeDatabase.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CocktailDao cocktailDao();

    public abstract MyRecipeDao myRecipeDao();

    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context, AppDatabase.class, "cocktaildb").build();
        }
        return instance;
    }
}
