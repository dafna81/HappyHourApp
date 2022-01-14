package ohad.dafna.happyhourapp.database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;

import ohad.dafna.happyhourapp.models.Cocktail;

@Entity(tableName = "cocktails")
public class CocktailDatabase implements Parcelable {
    @NonNull
    @PrimaryKey()
    private String idDrink;

    private int like;
    private String strDrink;
    private String strInstructions;
    private String strDrinkThumb;
    private String strIngredient1, strIngredient2, strIngredient3,
            strIngredient4, strIngredient5, strIngredient6,
            strIngredient7, strIngredient8, strIngredient9,
            strIngredient10, strIngredient11, strIngredient12,
            strIngredient13, strIngredient14, strIngredient15;
    private String strMeasure1, strMeasure2, strMeasure3,
            strMeasure4, strMeasure5, strMeasure6,
            strMeasure7, strMeasure8, strMeasure9,
            strMeasure10, strMeasure11, strMeasure12,
            strMeasure13, strMeasure14, strMeasure15;

    public CocktailDatabase() {
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public CocktailDatabase(Cocktail cocktail) {
        strDrink = cocktail.getStrDrink();
        idDrink = cocktail.getIdDrink();
        strInstructions = cocktail.getStrInstructions();
        strDrinkThumb = cocktail.getStrDrinkThumb();
        strIngredient1 = cocktail.getStrIngredient1();
        strIngredient2 = cocktail.getStrIngredient2();
        strIngredient3 = cocktail.getStrIngredient3();
        strIngredient4 = cocktail.getStrIngredient4();
        strIngredient5 = cocktail.getStrIngredient5();
        strIngredient6 = cocktail.getStrIngredient6();
        strIngredient7 = cocktail.getStrIngredient7();
        strIngredient8 = cocktail.getStrIngredient8();
        strIngredient9 = cocktail.getStrIngredient9();
        strIngredient10 = cocktail.getStrIngredient10();
        strIngredient11 = cocktail.getStrIngredient11();
        strIngredient12 = cocktail.getStrIngredient12();
        strIngredient13 = cocktail.getStrIngredient13();
        strIngredient14 = cocktail.getStrIngredient14();
        strIngredient15 = cocktail.getStrIngredient15();
        strMeasure1 = cocktail.getStrMeasure1();
        strMeasure2 = cocktail.getStrMeasure2();
        strMeasure3 = cocktail.getStrMeasure3();
        strMeasure4 = cocktail.getStrMeasure4();
        strMeasure5 = cocktail.getStrMeasure5();
        strMeasure6 = cocktail.getStrMeasure6();
        strMeasure7 = cocktail.getStrMeasure7();
        strMeasure8 = cocktail.getStrMeasure8();
        strMeasure9 = cocktail.getStrMeasure9();
        strMeasure10 = cocktail.getStrMeasure10();
        strMeasure11 = cocktail.getStrMeasure11();
        strMeasure12 = cocktail.getStrMeasure12();
        strMeasure13 = cocktail.getStrMeasure13();
        strMeasure14 = cocktail.getStrMeasure14();
        strMeasure15 = cocktail.getStrMeasure15();
    }

    protected CocktailDatabase(Parcel in) {
        idDrink = in.readString();
        like = in.readInt();
        strDrink = in.readString();
        strInstructions = in.readString();
        strDrinkThumb = in.readString();
        strIngredient1 = in.readString();
        strIngredient2 = in.readString();
        strIngredient3 = in.readString();
        strIngredient4 = in.readString();
        strIngredient5 = in.readString();
        strIngredient6 = in.readString();
        strIngredient7 = in.readString();
        strIngredient8 = in.readString();
        strIngredient9 = in.readString();
        strIngredient10 = in.readString();
        strIngredient11 = in.readString();
        strIngredient12 = in.readString();
        strIngredient13 = in.readString();
        strIngredient14 = in.readString();
        strIngredient15 = in.readString();
        strMeasure1 = in.readString();
        strMeasure2 = in.readString();
        strMeasure3 = in.readString();
        strMeasure4 = in.readString();
        strMeasure5 = in.readString();
        strMeasure6 = in.readString();
        strMeasure7 = in.readString();
        strMeasure8 = in.readString();
        strMeasure9 = in.readString();
        strMeasure10 = in.readString();
        strMeasure11 = in.readString();
        strMeasure12 = in.readString();
        strMeasure13 = in.readString();
        strMeasure14 = in.readString();
        strMeasure15 = in.readString();
    }

    public static final Creator<CocktailDatabase> CREATOR = new Creator<CocktailDatabase>() {
        @Override
        public CocktailDatabase createFromParcel(Parcel in) {
            return new CocktailDatabase(in);
        }

        @Override
        public CocktailDatabase[] newArray(int size) {
            return new CocktailDatabase[size];
        }
    };

    public ArrayList<String> getIngredients() {
        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add(strIngredient1);
        ingredients.add(strIngredient2);
        ingredients.add(strIngredient3);
        ingredients.add(strIngredient4);
        ingredients.add(strIngredient5);
        ingredients.add(strIngredient6);
        ingredients.add(strIngredient7);
        ingredients.add(strIngredient8);
        ingredients.add(strIngredient9);
        ingredients.add(strIngredient10);
        ingredients.add(strIngredient11);
        ingredients.add(strIngredient12);
        ingredients.add(strIngredient13);
        ingredients.add(strIngredient14);
        ingredients.add(strIngredient15);
        ingredients.removeIf(s -> s == null);
        return ingredients;
    }

    public HashSet<String> getIngredientsAndMeasures() {
        HashSet<String> ingAndMeas = new HashSet<>();
        ingAndMeas.add(getStrMeasure1() + " " + getStrIngredient1());
        ingAndMeas.add(getStrMeasure2() + " " + getStrIngredient2());
        ingAndMeas.add(getStrMeasure3() + " " + getStrIngredient3());
        ingAndMeas.add(getStrMeasure4() + " " + getStrIngredient4());
        ingAndMeas.add(getStrMeasure5() + " " + getStrIngredient5());
        ingAndMeas.add(getStrMeasure6() + " " + getStrIngredient6());
        ingAndMeas.add(getStrMeasure7() + " " + getStrIngredient7());
        ingAndMeas.add(getStrMeasure8() + " " + getStrIngredient8());
        ingAndMeas.add(getStrMeasure9() + " " + getStrIngredient9());
        ingAndMeas.add(getStrMeasure10() + " " + getStrIngredient10());
        ingAndMeas.add(getStrMeasure11() + " " + getStrIngredient11());
        ingAndMeas.add(getStrMeasure12() + " " + getStrIngredient12());
        ingAndMeas.add(getStrMeasure13() + " " + getStrIngredient13());
        ingAndMeas.add(getStrMeasure14() + " " + getStrIngredient14());
        ingAndMeas.add(getStrMeasure15() + " " + getStrIngredient15());

        return ingAndMeas;
    }

    @NotNull
    public String getIdDrink() {
        return idDrink;
    }

    public void setIdDrink(String idDrink) {
        this.idDrink = idDrink;
    }

    public boolean isLiked() {
        return like == 1;
    }

    public void setLiked(boolean liked) {
        this.like = liked ? 1 : 0;
    }

    public String getStrDrink() {
        return strDrink;
    }

    public void setStrDrink(String strDrink) {
        this.strDrink = strDrink;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrDrinkThumb() {
        return strDrinkThumb;
    }

    public void setStrDrinkThumb(String strDrinkThumb) {
        this.strDrinkThumb = strDrinkThumb;
    }

    public String getStrIngredient1() {
        if (strIngredient1 == null) {
            return "null";
        }
        return strIngredient1;
    }

    public void setStrIngredient1(String strIngredient1) {
        this.strIngredient1 = strIngredient1;
    }

    public String getStrIngredient2() {
        if (strIngredient2 == null) {
            return "null";
        }
        return strIngredient2;
    }

    public void setStrIngredient2(String strIngredient2) {
        this.strIngredient2 = strIngredient2;
    }

    public String getStrIngredient3() {
        if (strIngredient3 == null) {
            return "null";
        }
        return strIngredient3;
    }

    public void setStrIngredient3(String strIngredient3) {
        this.strIngredient3 = strIngredient3;
    }

    public String getStrIngredient4() {
        if (strIngredient4 == null) {
            return "null";
        }
        return strIngredient4;
    }

    public void setStrIngredient4(String strIngredient4) {
        this.strIngredient4 = strIngredient4;
    }

    public String getStrIngredient5() {
        if (strIngredient5 == null) {
            return "null";
        }
        return strIngredient5;
    }

    public void setStrIngredient5(String strIngredient5) {
        this.strIngredient5 = strIngredient5;
    }

    public String getStrIngredient6() {
        if (strIngredient6 == null) {
            return "null";
        }
        return strIngredient6;
    }

    public void setStrIngredient6(String strIngredient6) {
        this.strIngredient6 = strIngredient6;
    }

    public String getStrIngredient7() {
        if (strIngredient7 == null) {
            return "null";
        }
        return strIngredient7;
    }

    public void setStrIngredient7(String strIngredient7) {
        this.strIngredient7 = strIngredient7;
    }

    public String getStrIngredient8() {
        if (strIngredient8 == null) {
            return "null";
        }
        return strIngredient8;
    }

    public void setStrIngredient8(String strIngredient8) {
        this.strIngredient8 = strIngredient8;
    }

    public String getStrIngredient9() {
        if (strIngredient9 == null) {
            return "null";
        }
        return strIngredient9;
    }

    public void setStrIngredient9(String strIngredient9) {
        this.strIngredient9 = strIngredient9;
    }

    public String getStrIngredient10() {
        if (strIngredient10 == null) {
            return "null";
        }
        return strIngredient10;
    }

    public void setStrIngredient10(String strIngredient10) {
        this.strIngredient10 = strIngredient10;
    }

    public String getStrIngredient11() {
        if (strIngredient11 == null) {
            return "null";
        }
        return strIngredient11;
    }

    public void setStrIngredient11(String strIngredient11) {
        this.strIngredient11 = strIngredient11;
    }

    public String getStrIngredient12() {
        if (strIngredient12 == null) {
            return "null";
        }
        return strIngredient12;
    }

    public void setStrIngredient12(String strIngredient12) {
        this.strIngredient12 = strIngredient12;
    }

    public String getStrIngredient13() {
        if (strIngredient13 == null) {
            return "null";
        }
        return strIngredient13;
    }

    public void setStrIngredient13(String strIngredient13) {
        this.strIngredient13 = strIngredient13;
    }

    public String getStrIngredient14() {
        if (strIngredient14 == null) {
            return "null";
        }
        return strIngredient14;
    }

    public void setStrIngredient14(String strIngredient14) {
        this.strIngredient14 = strIngredient14;
    }

    public String getStrIngredient15() {
        if (strIngredient15 == null) {
            return "null";
        }
        return strIngredient15;
    }

    public void setStrIngredient15(String strIngredient15) {
        this.strIngredient15 = strIngredient15;
    }

    public String getStrMeasure1() {
        if (strMeasure1 == null) {
            return "";
        }
        return strMeasure1;
    }

    public void setStrMeasure1(String strMeasure1) {
        this.strMeasure1 = strMeasure1;
    }

    public String getStrMeasure2() {
        if (strMeasure2 == null) {
            return "";
        }
        return strMeasure2;
    }

    public void setStrMeasure2(String strMeasure2) {
        this.strMeasure2 = strMeasure2;
    }

    public String getStrMeasure3() {
        if (strMeasure3 == null) {
            return "";
        }
        return strMeasure3;
    }

    public void setStrMeasure3(String strMeasure3) {
        this.strMeasure3 = strMeasure3;
    }

    public String getStrMeasure4() {
        if (strMeasure4 == null) {
            return "";
        }
        return strMeasure4;
    }

    public void setStrMeasure4(String strMeasure4) {
        this.strMeasure4 = strMeasure4;
    }

    public String getStrMeasure5() {
        if (strMeasure5 == null) {
            return "";
        }
        return strMeasure5;
    }

    public void setStrMeasure5(String strMeasure5) {
        this.strMeasure5 = strMeasure5;
    }

    public String getStrMeasure6() {
        if (strMeasure6 == null) {
            return "";
        }
        return strMeasure6;
    }

    public void setStrMeasure6(String strMeasure6) {
        this.strMeasure6 = strMeasure6;
    }

    public String getStrMeasure7() {
        if (strMeasure7 == null) {
            return "";
        }
        return strMeasure7;
    }

    public void setStrMeasure7(String strMeasure7) {
        this.strMeasure7 = strMeasure7;
    }

    public String getStrMeasure8() {
        if (strMeasure8 == null) {
            return "";
        }
        return strMeasure8;
    }

    public void setStrMeasure8(String strMeasure8) {
        this.strMeasure8 = strMeasure8;
    }

    public String getStrMeasure9() {
        if (strMeasure9 == null) {
            return "";
        }
        return strMeasure9;
    }

    public void setStrMeasure9(String strMeasure9) {
        this.strMeasure9 = strMeasure9;
    }

    public String getStrMeasure10() {
        if (strMeasure10 == null) {
            return "";
        }
        return strMeasure10;
    }

    public void setStrMeasure10(String strMeasure10) {
        this.strMeasure10 = strMeasure10;
    }

    public String getStrMeasure11() {
        if (strMeasure11 == null) {
            return "";
        }
        return strMeasure11;
    }

    public void setStrMeasure11(String strMeasure11) {
        this.strMeasure11 = strMeasure11;
    }

    public String getStrMeasure12() {
        if (strMeasure12 == null) {
            return "--";
        }
        return strMeasure12;
    }

    public void setStrMeasure12(String strMeasure12) {
        this.strMeasure12 = strMeasure12;
    }

    public String getStrMeasure13() {
        if (strMeasure13 == null) {
            return "";
        }
        return strMeasure13;
    }

    public void setStrMeasure13(String strMeasure13) {
        this.strMeasure13 = strMeasure13;
    }

    public String getStrMeasure14() {
        if (strMeasure14 == null) {
            return "";
        }
        return strMeasure14;
    }

    public void setStrMeasure14(String strMeasure14) {
        this.strMeasure14 = strMeasure14;
    }

    public String getStrMeasure15() {
        if (strMeasure15 == null) {
            return "";
        }
        return strMeasure15;
    }

    public void setStrMeasure15(String strMeasure15) {
        this.strMeasure15 = strMeasure15;
    }

    @Override
    public String toString() {
        return "CocktailDatabase{" +
                "idDrink='" + idDrink + '\'' +
                ", like=" + like +
                ", strDrink='" + strDrink + '\'' +
                ", strInstructions='" + strInstructions + '\'' +
                ", strDrinkThumb='" + strDrinkThumb + '\'' +
                ", strIngredient1='" + strIngredient1 + '\'' +
                ", strIngredient2='" + strIngredient2 + '\'' +
                ", strIngredient3='" + strIngredient3 + '\'' +
                ", strIngredient4='" + strIngredient4 + '\'' +
                ", strIngredient5='" + strIngredient5 + '\'' +
                ", strIngredient6='" + strIngredient6 + '\'' +
                ", strIngredient7='" + strIngredient7 + '\'' +
                ", strIngredient8='" + strIngredient8 + '\'' +
                ", strIngredient9='" + strIngredient9 + '\'' +
                ", strIngredient10='" + strIngredient10 + '\'' +
                ", strIngredient11='" + strIngredient11 + '\'' +
                ", strIngredient12='" + strIngredient12 + '\'' +
                ", strIngredient13='" + strIngredient13 + '\'' +
                ", strIngredient14='" + strIngredient14 + '\'' +
                ", strIngredient15='" + strIngredient15 + '\'' +
                ", strMeasure1='" + strMeasure1 + '\'' +
                ", strMeasure2='" + strMeasure2 + '\'' +
                ", strMeasure3='" + strMeasure3 + '\'' +
                ", strMeasure4='" + strMeasure4 + '\'' +
                ", strMeasure5='" + strMeasure5 + '\'' +
                ", strMeasure6='" + strMeasure6 + '\'' +
                ", strMeasure7='" + strMeasure7 + '\'' +
                ", strMeasure8='" + strMeasure8 + '\'' +
                ", strMeasure9='" + strMeasure9 + '\'' +
                ", strMeasure10='" + strMeasure10 + '\'' +
                ", strMeasure11='" + strMeasure11 + '\'' +
                ", strMeasure12='" + strMeasure12 + '\'' +
                ", strMeasure13='" + strMeasure13 + '\'' +
                ", strMeasure14='" + strMeasure14 + '\'' +
                ", strMeasure15='" + strMeasure15 + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idDrink);
        dest.writeInt(like);
        dest.writeString(strDrink);
        dest.writeString(strInstructions);
        dest.writeString(strDrinkThumb);
        dest.writeString(strIngredient1);
        dest.writeString(strIngredient2);
        dest.writeString(strIngredient3);
        dest.writeString(strIngredient4);
        dest.writeString(strIngredient5);
        dest.writeString(strIngredient6);
        dest.writeString(strIngredient7);
        dest.writeString(strIngredient8);
        dest.writeString(strIngredient9);
        dest.writeString(strIngredient10);
        dest.writeString(strIngredient11);
        dest.writeString(strIngredient12);
        dest.writeString(strIngredient13);
        dest.writeString(strIngredient14);
        dest.writeString(strIngredient15);
        dest.writeString(strMeasure1);
        dest.writeString(strMeasure2);
        dest.writeString(strMeasure3);
        dest.writeString(strMeasure4);
        dest.writeString(strMeasure5);
        dest.writeString(strMeasure6);
        dest.writeString(strMeasure7);
        dest.writeString(strMeasure8);
        dest.writeString(strMeasure9);
        dest.writeString(strMeasure10);
        dest.writeString(strMeasure11);
        dest.writeString(strMeasure12);
        dest.writeString(strMeasure13);
        dest.writeString(strMeasure14);
        dest.writeString(strMeasure15);
    }
}
