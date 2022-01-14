package ohad.dafna.happyhourapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myrecipes")
public class MyRecipeDatabase implements Parcelable {
    // properties

    //the unique id of each recipe the user adds to the database
    @PrimaryKey(autoGenerate = true)
    private long key;

    private String cocktailName;
    private String ingredients;
    private String instructions;
    private String imagePath;

    //empty constructor
    public MyRecipeDatabase() {
    }

    //constructor
    public MyRecipeDatabase(String cocktailName, String ingredients, String instructions, String imagePath) {
        this.cocktailName = cocktailName;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.imagePath = imagePath;
    }

    //implemented methods from Parcelable
    protected MyRecipeDatabase(Parcel in) {
        key = in.readLong();
        cocktailName = in.readString();
        ingredients = in.readString();
        instructions = in.readString();
        imagePath = in.readString();
    }

    public static final Creator<MyRecipeDatabase> CREATOR = new Creator<MyRecipeDatabase>() {
        @Override
        public MyRecipeDatabase createFromParcel(Parcel in) {
            return new MyRecipeDatabase(in);
        }

        @Override
        public MyRecipeDatabase[] newArray(int size) {
            return new MyRecipeDatabase[size];
        }
    };

    //getters & setters
    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    //to string method
    @Override
    public String toString() {
        return "MyRecipeDatabase{" +
                "key=" + key +
                ", cocktailName='" + cocktailName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", instructions='" + instructions + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    //more implemented methods from Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(key);
        dest.writeString(cocktailName);
        dest.writeString(ingredients);
        dest.writeString(instructions);
        dest.writeString(imagePath);
    }
}
