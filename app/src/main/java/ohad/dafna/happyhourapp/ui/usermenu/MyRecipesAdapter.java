package ohad.dafna.happyhourapp.ui.usermenu;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.databinding.MyRecipesItemBinding;

public class MyRecipesAdapter extends RecyclerView.Adapter<MyRecipesAdapter.VH> {
    private final List<MyRecipeDatabase> myRecipes;
    MutableLiveData<MyRecipeDatabase> clickedRecipe;

    public MyRecipesAdapter(List<MyRecipeDatabase> myRecipes, MutableLiveData<MyRecipeDatabase> clickedRecipe) {
        this.myRecipes = myRecipes;
        this.clickedRecipe = clickedRecipe;
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        MyRecipesItemBinding binding = MyRecipesItemBinding.inflate(inflater, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        MyRecipeDatabase myRecipe = myRecipes.get(position);
        holder.binding.textviewCocktailName.setText(myRecipe.getCocktailName());
        Uri uri = Uri.parse(myRecipe.getImagePath());
        Picasso.get().load(uri).centerCrop().resize(100, 100)
                .into(holder.binding.imageViewCocktailImage);
        holder.binding.getRoot().setOnClickListener((v -> clickedRecipe.postValue(myRecipe)));
    }

    @Override
    public int getItemCount() {
        return myRecipes.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        MyRecipesItemBinding binding;

        public VH(MyRecipesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
