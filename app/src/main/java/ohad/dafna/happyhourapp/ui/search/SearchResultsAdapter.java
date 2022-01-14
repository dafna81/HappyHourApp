package ohad.dafna.happyhourapp.ui.search;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.SearchResultsItemBinding;

public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.VH> {
    private final List<CocktailDatabase> cocktails;
    MutableLiveData<CocktailDatabase> clickedCocktail;

    public SearchResultsAdapter(List<CocktailDatabase> cocktails, MutableLiveData<CocktailDatabase> clickedCocktail) {
        this.cocktails = cocktails;
        this.clickedCocktail = clickedCocktail;
    }

    @NonNull
    @NotNull
    @Override
    public VH onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SearchResultsItemBinding binding = SearchResultsItemBinding.inflate(inflater, parent, false);
        return new VH(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull VH holder, int position) {
        CocktailDatabase cocktail = cocktails.get(position);
        holder.binding.textviewCocktailName.setText(cocktail.getStrDrink());
        Picasso.get().load(Uri.parse(cocktail.getStrDrinkThumb())).into(holder.binding.imageViewCocktailImage);
        if (cocktail.isLiked()) {
            holder.binding.imageviewLike.setImageResource(R.drawable.filled_heart);
        } else {
            holder.binding.imageviewLike.setImageResource(R.drawable.empty_heart);
        }
        holder.binding.getRoot().setOnClickListener(v -> {
            clickedCocktail.postValue(cocktail);
        });
    }

    @Override
    public int getItemCount() {
        return cocktails.size();
    }

    public static class VH extends RecyclerView.ViewHolder {
        SearchResultsItemBinding binding;

        public VH(SearchResultsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
