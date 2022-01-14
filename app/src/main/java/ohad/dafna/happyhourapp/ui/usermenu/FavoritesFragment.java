package ohad.dafna.happyhourapp.ui.usermenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentFavoritesBinding;
import ohad.dafna.happyhourapp.utils.MyItemDecoration;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel mViewModel;
    private FragmentFavoritesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(FavoritesViewModel.class);
        binding = FragmentFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getCocktails().observe(getViewLifecycleOwner(), cocktailDatabases -> {
            List<CocktailDatabase> likedCocktails = new ArrayList<>();
            for (CocktailDatabase cocktailDatabase : cocktailDatabases) {
                if (cocktailDatabase.isLiked()) {
                    likedCocktails.add(cocktailDatabase);
                }
            }
            FavoritesAdapter adapter = new FavoritesAdapter(likedCocktails,
                    mViewModel.getClickedCocktail());
            binding.recyclerviewFavorites.setAdapter(adapter);
            binding.recyclerviewFavorites.addItemDecoration(new MyItemDecoration());
            binding.recyclerviewFavorites.setLayoutManager(new LinearLayoutManager(requireContext()));
        });

        mViewModel.getClickedCocktail().observe(getViewLifecycleOwner(), (cocktail -> {
            Bundle detailsArgs = new Bundle();
            detailsArgs.putParcelable("cocktail", cocktail);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_favoritesFragment_to_detailsFragment, detailsArgs);
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}