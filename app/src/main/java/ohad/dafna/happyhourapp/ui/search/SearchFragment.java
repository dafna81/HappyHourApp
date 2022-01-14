package ohad.dafna.happyhourapp.ui.search;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentSearchBinding;
import ohad.dafna.happyhourapp.models.Ingredient;

public class SearchFragment extends Fragment {

    private SearchViewModel mViewModel;
    private FragmentSearchBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        binding = FragmentSearchBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewModel.getmIngredients().observe(getViewLifecycleOwner(), ingredients -> {
            Bundle byIngredientsArgs = new Bundle();
            ingredients.add(0, new Ingredient("Choose an ingredient:"));
            binding.spinnerIngredients.setAdapter(new ArrayAdapter<>(requireContext(),
                    R.layout.simple_spinner_item, ingredients));
            binding.spinnerIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Ingredient ingredient = ingredients.get(position);
                    if (!ingredient.equals(ingredients.get(0))) {
                        String ingredientName = ingredient.getStrIngredient1();
                        byIngredientsArgs.putString("ingredientName", ingredientName);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            binding.buttonSearchDrinks.setOnClickListener(v -> {
                if (byIngredientsArgs.isEmpty()) {
                    Toast.makeText(requireContext(), "Try again", Toast.LENGTH_SHORT).show();
                } else {
                    NavHostFragment.findNavController(SearchFragment.this)
                            .navigate(R.id.action_searchFragment_to_searchResultsFragment, byIngredientsArgs);
                }
            });
        });
        mViewModel.getmCocktails().observe(getViewLifecycleOwner(), cocktails -> {
            Bundle byNameArgs = new Bundle();
            ArrayList<String> cocktailNames = new ArrayList<>();
            for (CocktailDatabase cocktail : cocktails) {
                cocktailNames.add(cocktail.getStrDrink());
            }
            binding.autocompleteDrinkNames.setAdapter(new ArrayAdapter<>(requireContext(),
                    android.R.layout.simple_dropdown_item_1line, cocktailNames));
            binding.autocompleteDrinkNames.setOnItemClickListener((parent, view1, position, id) -> {
                InputMethodManager imm = (InputMethodManager) requireContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            });
            binding.buttonShowRecipe.setOnClickListener(v -> {
                if (binding.autocompleteDrinkNames.getText() != null) {
                    for (CocktailDatabase cocktail : cocktails) {
                        String drinkName = cocktail.getStrDrink();
                        if (binding.autocompleteDrinkNames.getText().toString().toLowerCase()
                                .equals(drinkName.toLowerCase())) {
                            byNameArgs.putParcelable("cocktail", cocktail);
                            NavHostFragment.findNavController(SearchFragment.this)
                                    .navigate(R.id.action_searchFragment_to_detailsFragment, byNameArgs);
                        } else {
                            binding.autocompleteDrinkNames.setError("Type a drink name");
                        }
                    }
                }
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}