package ohad.dafna.happyhourapp.ui.search;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentSearchResultsBinding;
import ohad.dafna.happyhourapp.utils.MyItemDecoration;

public class SearchResultsFragment extends Fragment {

    private SearchResultsViewModel mViewModel;
    private FragmentSearchResultsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SearchResultsViewModel.class);
        binding = FragmentSearchResultsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CocktailDatabase> selectedIngredientCocktailList = new ArrayList<>();
        mViewModel.getCocktails().observe(getViewLifecycleOwner(), cocktails -> {
            Bundle args = getArguments();
            if (args != null) {
                String ingredientName = args.getString("ingredientName");
                List<CocktailDatabase> cocktailList = mViewModel.getCocktails().getValue();
                if (cocktailList != null) {
                    for (CocktailDatabase cocktail : cocktailList) {
                        List<String> ingredients = cocktail.getIngredients();
                        for (String ingredient : ingredients) {
                            if (ingredient == null) {
                                return;
                            }
                            if (ingredient.equals(ingredientName)) {
                                selectedIngredientCocktailList.add(cocktail);
                            }
                        }
                    }
                }
                SearchResultsAdapter adapter = new SearchResultsAdapter(selectedIngredientCocktailList,
                        mViewModel.getClickedCocktail());
                binding.recyclerviewSearchResults.setAdapter(adapter);
                binding.recyclerviewSearchResults.addItemDecoration(new MyItemDecoration());
                binding.recyclerviewSearchResults.setLayoutManager(new LinearLayoutManager(requireContext()));
            }
        });

        mViewModel.getClickedCocktail().observe(getViewLifecycleOwner(), (cocktail -> {
            Bundle detailsArgs = new Bundle();
            detailsArgs.putParcelable("cocktail", cocktail);
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_searchResultsFragment_to_detailsFragment, detailsArgs);
        }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}