package ohad.dafna.happyhourapp.ui.usermenu;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentMyRecipesDetailsBinding;
import ohad.dafna.happyhourapp.ui.search.DetailsHelper;

public class MyRecipesDetailsFragment extends Fragment {
    private MyRecipesDetailsViewModel mViewModel;
    private FragmentMyRecipesDetailsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            MyRecipeDatabase myRecipeDatabase = args.getParcelable("myRecipe");
            DetailsHelper.getInstance().setCocktailName(myRecipeDatabase.getCocktailName());
            mViewModel = new ViewModelProvider(this).get(MyRecipesDetailsViewModel.class);
        }
        binding = FragmentMyRecipesDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getCurrentRecipe().observe(getViewLifecycleOwner(), myRecipeDatabase -> {
            binding.textviewDetailsTitle.setText(myRecipeDatabase.getCocktailName());
            Picasso.get().load(Uri.parse(myRecipeDatabase.getImagePath())).centerCrop()
                    .resize(300, 300).into(binding.imageviewDetails);
            binding.textviewDetailsIngredients.setText(myRecipeDatabase.getIngredients());
            binding.textviewDetailsInstructions.setText(myRecipeDatabase.getInstructions());
        });

        binding.imageviewDelete.setOnClickListener(v -> {
            mViewModel.deleteRecipe(mViewModel.getCurrentRecipe().getValue());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_myRecipesDetailsFragment_to_myRecipesFragment);
        });

        binding.imageviewEdit.setOnClickListener(v -> {
            Bundle recipeToUpdate = new Bundle();
            recipeToUpdate.putParcelable("myRecipe", mViewModel.getCurrentRecipe().getValue());
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_myRecipesDetailsFragment_to_updateMyRecipeFragment, recipeToUpdate);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}