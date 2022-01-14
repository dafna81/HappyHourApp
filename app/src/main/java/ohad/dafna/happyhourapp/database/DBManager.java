package ohad.dafna.happyhourapp.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class DBManager {
    private final CocktailDao cocktailDao;
    private final MyRecipeDao myRecipeDao;

    private final ExecutorService service = Executors.newSingleThreadExecutor();

    public void insertCocktail(CocktailDatabase cocktail) {
        service.execute(new CocktailInsertionTask(cocktail));
    }

    public void insertMyRecipe(MyRecipeDatabase myRecipe) {
        service.execute(new MyRecipeInsertionTask(myRecipe));
    }

    public void updateCocktail(CocktailDatabase cocktail) {
        service.execute(new CocktailUpdateTask(cocktail));
    }

    public void updateMyRecipe(MyRecipeDatabase myRecipe) {
        service.execute(new MyRecipeUpdateTask(myRecipe));
    }

    public void deleteMyRecipe(MyRecipeDatabase myRecipe) {
        service.execute(new MyRecipeDeletionTask(myRecipe));
    }

    class MyRecipeDeletionTask implements Runnable {
        MyRecipeDatabase myRecipe;

        public MyRecipeDeletionTask(MyRecipeDatabase myRecipe) {
            this.myRecipe = myRecipe;
        }

        @Override
        public void run() {
            myRecipeDao.delete(myRecipe);
        }
    }

    class CocktailInsertionTask implements Runnable {
        CocktailDatabase cocktail;

        public CocktailInsertionTask(CocktailDatabase cocktail) {
            this.cocktail = cocktail;
        }

        @Override
        public void run() {
            getAllCocktails(cocktailDatabases -> {
                if (cocktailDatabases.stream().noneMatch(cocktail -> cocktail.getIdDrink().equals(CocktailInsertionTask.this.cocktail.getIdDrink()))) {
                    cocktailDao.insert(cocktail);
                }
            });
        }
    }

    class MyRecipeInsertionTask implements Runnable {
        MyRecipeDatabase recipe;

        public MyRecipeInsertionTask(MyRecipeDatabase myRecipe) {
            this.recipe = myRecipe;
        }

        @Override
        public void run() {
            getAllMyRecipes(myRecipeDatabases -> {
                if (myRecipeDatabases.stream().noneMatch(recipe -> recipe.getCocktailName().equals(MyRecipeInsertionTask.this.recipe.getCocktailName()))) {
                    myRecipeDao.insert(recipe);
                }
            });
        }
    }

    class CocktailUpdateTask implements Runnable {
        CocktailDatabase cocktail;

        public CocktailUpdateTask(CocktailDatabase cocktail) {
            this.cocktail = cocktail;
        }

        @Override
        public void run() {
            cocktailDao.setLiked(cocktail.getStrDrink(), cocktail.getIdDrink(), cocktail.isLiked() ? 1 : 0);
        }
    }

    class MyRecipeUpdateTask implements Runnable {
        MyRecipeDatabase myRecipe;

        public MyRecipeUpdateTask(MyRecipeDatabase myRecipe) {
            this.myRecipe = myRecipe;
        }

        @Override
        public void run() {
            myRecipeDao.update(myRecipe);
        }
    }

    public DBManager(Context context) {
        cocktailDao = AppDatabase.getInstance(context).cocktailDao();
        myRecipeDao = AppDatabase.getInstance(context).myRecipeDao();
    }

    public void getAllCocktails(Consumer<List<CocktailDatabase>> consumer) {
        new Thread(() -> {
            List<CocktailDatabase> cocktails = cocktailDao.getAllCocktails();
            consumer.accept(cocktails);
        }).start();
    }

    public void getCocktail(Consumer<CocktailDatabase> consumer, String cocktailName) {
        new Thread(() -> {
            CocktailDatabase cocktailDatabase = cocktailDao.findByName(cocktailName);
            consumer.accept(cocktailDatabase);
        }).start();
    }

    public LiveData<List<CocktailDatabase>> getCocktailsLive() {
        return cocktailDao.getAll();
    }

    public void getAllMyRecipes(Consumer<List<MyRecipeDatabase>> consumer) {
        new Thread(() -> {
            List<MyRecipeDatabase> myRecipes = myRecipeDao.getAllMyRecipes();
            consumer.accept(myRecipes);
        }).start();
    }

    public void getRecipe(Consumer<MyRecipeDatabase> consumer, String myRecipeName) {
        new Thread(() -> {
            MyRecipeDatabase myRecipeDatabase = myRecipeDao.findByName(myRecipeName);
            consumer.accept(myRecipeDatabase);
        }).start();
    }

    public List<MyRecipeDatabase> getAllMyRecipes() {
        return myRecipeDao.getAllMyRecipes();
    }
}
