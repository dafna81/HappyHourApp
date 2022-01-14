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

import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.FavoritesItemBinding;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.VH> {
    private final List<CocktailDatabase> cocktails;
    MutableLiveData<CocktailDatabase> clickedCocktail;

    public FavoritesAdapter(List<CocktailDatabase> cocktails, MutableLiveData<CocktailDatabase> clickedCocktail) {
        this.cocktails = cocktails;
        this.clickedCocktail = clickedCocktail;
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FavoritesItemBinding binding = FavoritesItemBinding.inflate(inflater, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        CocktailDatabase cocktail = cocktails.get(position);
        if (cocktail.isLiked()) {
            holder.binding.textviewCocktailName.setText(cocktail.getStrDrink());
            Picasso.get().load(Uri.parse(cocktail.getStrDrinkThumb())).into(holder.binding.imageViewCocktailImage);
            holder.binding.getRoot().setOnClickListener(v -> clickedCocktail.postValue(cocktail));
        }
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        FavoritesItemBinding binding;

        public VH(FavoritesItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
