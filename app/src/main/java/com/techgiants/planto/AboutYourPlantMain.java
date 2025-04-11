package com.techgiants.planto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import org.pytorch.IValue;
import org.pytorch.Module;
import org.pytorch.Tensor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class AboutYourPlantMain extends Fragment {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CAMERA_PERMISSION = 100;

    private Uri photoURI;
    private ImageButton captureButton;
    private ImageView imageView;
    private TextView resultTextView;
    private Module model;

    public AboutYourPlantMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_your_plant_main, container, false);

        imageView = view.findViewById(R.id.image_view);
        resultTextView = view.findViewById(R.id.result_text_view);
        captureButton = view.findViewById(R.id.scannermain);

        captureButton.setOnClickListener(v -> checkCameraPermission());

        loadModel();

        return view;
    }

    private void loadModel() {
        try {
            String modelPath = assetFilePath(); // This should point to the model in assets
            Log.d("Model", "Loading model from: " + modelPath);
            model = Module.load(modelPath); // Load the model
            Log.d("Model", "Model loaded successfully.");
        } catch (IOException e) {
            Log.e("Model", "Error accessing model file", e);
            Toast.makeText(getActivity(), "Failed to access model. Check file path.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("Model", "Model loading failed", e);
            Toast.makeText(getActivity(), "Model loading failed. Please check the model file.", Toast.LENGTH_SHORT).show();
        }
    }

    private String assetFilePath() throws IOException {
        File file = new File(getContext().getFilesDir(), "model_scripted.pt");
        if (!file.exists()) {
            try (InputStream in = getContext().getAssets().open("model_scripted.pt");
                 FileOutputStream out = new FileOutputStream(file)) {
                byte[] buffer = new byte[1024];
                int read;
                while ((read = in.read(buffer)) != -1) {
                    out.write(buffer, 0, read);
                }
                Log.d("Model", "Model file copied successfully.");
            } catch (IOException e) {
                Log.e("Model", "Error copying model from assets", e);
                throw e;
            }
        }
        return file.getAbsolutePath();
    }


    private Tensor preprocessImage(Bitmap bitmap) {
        Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, 224, 224, false);
        int[] intValues = new int[224 * 224];
        float[] floatValues = new float[224 * 224 * 3];

        resizedBitmap.getPixels(intValues, 0, resizedBitmap.getWidth(), 0, 0,
                resizedBitmap.getWidth(), resizedBitmap.getHeight());

        for (int i = 0; i < intValues.length; ++i) {
            final int val = intValues[i];
            floatValues[i * 3 + 0] = ((val >> 16) & 0xff) / 255.0f; // Red
            floatValues[i * 3 + 1] = ((val >> 8) & 0xff) / 255.0f;  // Green
            floatValues[i * 3 + 2] = (val & 0xff) / 255.0f;         // Blue
        }

        return Tensor.fromBlob(floatValues, new long[]{1, 3, 224, 224});
    }

    private void classifyImage(Bitmap bitmap) {
        Tensor inputTensor = preprocessImage(bitmap);
        Tensor outputTensor = model.forward(IValue.from(inputTensor)).toTensor();
        float[] scores = outputTensor.getDataAsFloatArray();
        int predictedClassIndex = getPredictedClassIndex(scores);

        String[] classes = {"Aloe Vera", "Areca Palm", "Boston Fern", "Chinese Evergreen", "Dracaena",
                "Money Tree", "Peace Lily", "Rubber Plant", "Snake Plant", "ZZ Plant"};
        String predictedClass = classes[predictedClassIndex];
        resultTextView.setText(predictedClass);
    }

    private int getPredictedClassIndex(float[] scores) {
        int maxIndex = 0;
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] > scores[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        } else {
            dispatchTakePictureIntent();
        }
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
            try {
                File photoFile = createImageFile();
                if (photoFile != null) {
                    photoURI = FileProvider.getUriForFile(requireActivity(),
                            requireActivity().getPackageName() + ".fileprovider", photoFile);
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
            } catch (IOException ex) {
                Toast.makeText(getActivity(), "Error creating file", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private File createImageFile() throws IOException {
        String imageFileName = "JPEG_" + System.currentTimeMillis() + "_";
        File storageDir = requireActivity().getExternalFilesDir(null);
        return File.createTempFile(imageFileName, ".jpg", storageDir);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), photoURI);
                imageView.setImageBitmap(bitmap);
                classifyImage(bitmap);
            } catch (IOException e) {
                Log.e("Image Capture", "Failed to load image", e);
                Toast.makeText(getActivity(), "Failed to load image", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getActivity(), "Image capture failed", Toast.LENGTH_SHORT).show();
        }
    }
}
