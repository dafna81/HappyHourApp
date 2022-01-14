package ohad.dafna.happyhourapp.ui.usermenu;

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
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentMyRecipesBinding;
import ohad.dafna.happyhourapp.utils.MyItemDecoration;

public class MyRecipesFragment extends Fragment {
    private MyRecipesViewModel mViewModel;
    private FragmentMyRecipesBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(MyRecipesViewModel.class);
        binding = FragmentMyRecipesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view,
                              @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<MyRecipeDatabase> myRecipeDatabaseList = new ArrayList<>();
        mViewModel.getAllMyRecipes().observe(getViewLifecycleOwner(), myRecipeDatabases -> {
            myRecipeDatabases = mViewModel.getAllMyRecipes().getValue();
            if (myRecipeDatabases != null) {
                myRecipeDatabaseList.addAll(myRecipeDatabases);
            }
            MyRecipesAdapter adapter = new MyRecipesAdapter(myRecipeDatabaseList, mViewModel.getClickedRecipe());
            binding.recyclerviewMyRecipes.setAdapter(adapter);
            binding.recyclerviewMyRecipes.addItemDecoration(new MyItemDecoration());
            binding.recyclerviewMyRecipes.setLayoutManager(new LinearLayoutManager(MyRecipesFragment.this.requireContext()));
        });
        mViewModel.getClickedRecipe().observe(getViewLifecycleOwner(), myRecipeDatabase -> {
            Bundle detailsArgs = new Bundle();
            detailsArgs.putParcelable("myRecipe", myRecipeDatabase);
            NavHostFragment.findNavController(this).navigate(R.id.action_myRecipesFragment_to_myRecipesDetailsFragment, detailsArgs);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}