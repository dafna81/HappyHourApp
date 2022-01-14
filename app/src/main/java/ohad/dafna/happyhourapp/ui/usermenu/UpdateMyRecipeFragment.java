package ohad.dafna.happyhourapp.ui.usermenu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import org.jetbrains.annotations.NotNull;

import ohad.dafna.happyhourapp.R;
import ohad.dafna.happyhourapp.database.MyRecipeDatabase;
import ohad.dafna.happyhourapp.databinding.FragmentUpdateMyRecipeBinding;
import ohad.dafna.happyhourapp.ui.search.DetailsHelper;

import static android.app.Activity.RESULT_OK;

public class UpdateMyRecipeFragment extends Fragment {
    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private UpdateMyRecipeViewModel mViewModel;
    private FragmentUpdateMyRecipeBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        if (args != null) {
            MyRecipeDatabase myRecipe = args.getParcelable("myRecipe");
            DetailsHelper.getInstance().setCocktailName(myRecipe.getCocktailName());
            mViewModel = new ViewModelProvider(this).get(UpdateMyRecipeViewModel.class);
        }
        binding = FragmentUpdateMyRecipeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel.getCurrentRecipe().observe(getViewLifecycleOwner(), myRecipeDatabase -> {
            binding.edittextCocktailName.setText(myRecipeDatabase.getCocktailName());
            binding.multilineIngredients.setText(myRecipeDatabase.getIngredients());
            binding.multilineInstructions.setText(myRecipeDatabase.getInstructions());
            binding.textviewImagePath.setText(myRecipeDatabase.getImagePath());

            binding.getRoot().setOnClickListener(v -> {
                InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
            });

            binding.buttonUploadImage.setOnClickListener(v -> {
                if (ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(requireActivity(),
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_STORAGE_PERMISSION);
                }
                selectImage();
            });

            binding.buttonSave.setOnClickListener(v -> {
                myRecipeDatabase.setCocktailName(binding.edittextCocktailName.getText().toString());
                myRecipeDatabase.setImagePath(binding.textviewImagePath.getText().toString());
                myRecipeDatabase.setIngredients(binding.multilineIngredients.getText().toString());
                myRecipeDatabase.setInstructions(binding.multilineInstructions.getText().toString());
                mViewModel.updateMyRecipe(myRecipeDatabase);
                Bundle updatedRecipe = new Bundle();
                updatedRecipe.putParcelable("myRecipe", myRecipeDatabase);
                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_updateMyRecipeFragment_to_myRecipesDetailsFragment, updatedRecipe);
            });
        });

        binding.buttonCancel.setOnClickListener(v -> NavHostFragment.findNavController(this)
                .navigate(R.id.action_updateMyRecipeFragment_to_myRecipesDetailsFragment, getArguments()));
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