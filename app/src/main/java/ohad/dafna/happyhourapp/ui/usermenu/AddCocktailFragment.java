package ohad.dafna.happyhourapp.ui.usermenu;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentAddCocktailBinding;

public class AddCocktailFragment extends Fragment {

    private AddCocktailViewModel mViewModel;
    private FragmentAddCocktailBinding binding;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(AddCocktailViewModel.class);
        binding = FragmentAddCocktailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonUploadImage.setOnClickListener(v -> {
            selectImage();
        });

        binding.buttonSave.setOnClickListener(v -> {
            mViewModel.insertRecipe(new MyRecipeDatabase(
                    binding.edittextCocktailName.getText().toString(),
                    binding.multilineIngredients.getText().toString(),
                    binding.multilineInstructions.getText().toString(),
                    binding.textviewImagePath.getText().toString()));
            NavHostFragment.findNavController(this).navigate(R.id.action_addCocktailFragment_to_myRecipesFragment);

        });

        binding.buttonCancel.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_addCocktailFragment_to_userMenuFragment));

        binding.getRoot().setOnClickListener(v -> {
            InputMethodManager imm = (InputMethodManager) requireContext()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        });
    }

    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(requireActivity().getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                binding.textviewImagePath.setText(selectedImageUri.toString());
                binding.imageviewDone.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}