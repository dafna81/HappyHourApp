package ohad.dafna.happyhourapp.ui.search;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.HashSet;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.CocktailDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentDetailsBinding;

public class DetailsFragment extends Fragment {

    private DetailsViewModel mViewModel;
    private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle args = getArguments();
        if (args != null) {
            CocktailDatabase cocktail = args.getParcelable("cocktail");
            DetailsHelper.getInstance().setCocktailName(cocktail.getStrDrink());
            mViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);
        }
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getCurrentCocktail().observe(getViewLifecycleOwner(), cocktailDatabase -> {
            binding.textviewDetailsTitle.setText(cocktailDatabase.getStrDrink());
            HashSet<String> ingAndMeas = cocktailDatabase.getIngredientsAndMeasures();
            for (String ingAndMea : ingAndMeas) {
                if (!ingAndMea.contains("null")) {
                    if (ingAndMea.contains("\n")) {
                        ingAndMea = ingAndMea.replace("\n", "");
                    }
                    binding.textviewDetailsIngredients.append(ingAndMea + "\n");
                }
                System.out.println(ingAndMea);
            }

            binding.textviewDetailsInstructions.setText(cocktailDatabase.getStrInstructions());
            Picasso.get().load(Uri.parse(cocktailDatabase.getStrDrinkThumb())).into(binding.imageviewDetails);
            binding.imageviewDetails.setContentDescription(cocktailDatabase.getStrDrink());
            if (cocktailDatabase.isLiked()) {
                binding.imageviewLike.setImageResource(R.drawable.filled_heart);
            } else {
                binding.imageviewLike.setImageResource(R.drawable.empty_heart);
            }
        });

        binding.imageviewLike.setOnClickListener(v -> {
            if (mViewModel.getCurrentCocktail().getValue() != null) {
                if (mViewModel.getCurrentCocktail().getValue().isLiked()) {
                    binding.imageviewLike.setImageResource(R.drawable.empty_heart);
                    mViewModel.getCurrentCocktail().getValue().setLiked(false);
                } else {
                    binding.imageviewLike.setImageResource(R.drawable.filled_heart);
                    mViewModel.getCurrentCocktail().getValue().setLiked(true);
                }
                mViewModel.updateCocktail(mViewModel.getCurrentCocktail().getValue());
            } else {
                System.err.println("mViewModel.getCurrentCocktail().getValue() == null");
            }
        });
    }
}

