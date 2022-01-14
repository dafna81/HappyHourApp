package ohad.dafna.happyhourapp.ui.surpriseme;

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
import ohad.dafna.happyhourapp.databinding.FragmentDetailsBinding;

public class SurpriseMeFragment extends Fragment {

    private SurpriseMeViewModel mViewModel;
    private FragmentDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(SurpriseMeViewModel.class);
        binding = FragmentDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getRandomCocktail().observe(getViewLifecycleOwner(), cocktailDatabase -> {
            binding.textviewDetailsTitle.setText(cocktailDatabase.getStrDrink());
            Picasso.get().load(Uri.parse(cocktailDatabase.getStrDrinkThumb())).into(binding.imageviewDetails);
            HashSet<String> ingredientsAndMeasures = cocktailDatabase.getIngredientsAndMeasures();
            for (String s : ingredientsAndMeasures) {
                if (!s.contains("null")) {
                    if (s.contains("\n")) {
                        s = s.replace("\n", "");
                    }
                    binding.textviewDetailsIngredients.append(s + "\n");
                }
                System.out.println(s);
            }
            binding.textviewDetailsInstructions.setText(cocktailDatabase.getStrInstructions());
            binding.textviewDetailsTitle.setText(cocktailDatabase.getStrDrink());
            binding.imageviewDetails.setContentDescription(cocktailDatabase.getStrDrink());
            boolean liked = cocktailDatabase.isLiked();
            if (liked) {
                binding.imageviewLike.setImageResource(R.drawable.filled_heart);
            } else {
                binding.imageviewLike.setImageResource(R.drawable.empty_heart);
            }
        });

        binding.imageviewLike.setOnClickListener(v -> {
            if (mViewModel.getRandomCocktail().getValue().isLiked()) {
                mViewModel.getRandomCocktail().getValue().setLiked(false);
                binding.imageviewLike.setImageResource(R.drawable.empty_heart);
            } else {
                binding.imageviewLike.setImageResource(R.drawable.filled_heart);
                mViewModel.getRandomCocktail().getValue().setLiked(true);
            }
            mViewModel.updateCocktail(mViewModel.getRandomCocktail().getValue());
        });
    }
}