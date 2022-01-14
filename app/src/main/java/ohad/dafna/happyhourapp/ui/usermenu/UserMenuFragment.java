package ohad.dafna.happyhourapp.ui.usermenu;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.databinding.FragmentUserMenuBinding;

public class UserMenuFragment extends Fragment {
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private FragmentUserMenuBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUserMenuBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonAddCocktails.setOnClickListener(v -> {
            NavHostFragment.findNavController(this)
                    .navigate(R.id.action_userMenuFragment_to_addCocktailFragment);
            if (ContextCompat.checkSelfPermission(requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_CODE_STORAGE_PERMISSION);
            }
        });
        binding.buttonMyFavorites.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_userMenuFragment_to_favoritesFragment));
        binding.buttonMyRecipes.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_userMenuFragment_to_myRecipesFragment2));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}