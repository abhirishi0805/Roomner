package com.example.roomner;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class uploadDP extends AppCompatActivity {

    ImageView ivDP;
    Button btnSkip, btnUpload, btnProceed;
    TextView tvUploading, tvPercentage;
    ProgressBar pbUploading;

    Uri imageUri;
    
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_d_p);

        ivDP = findViewById(R.id.ivDP);
        btnSkip = findViewById(R.id.btnSkip);
        btnUpload = findViewById(R.id.btnUpload);
        btnProceed = findViewById(R.id.btnProceed);
        tvUploading = findViewById(R.id.tvUploading);
        pbUploading = findViewById(R.id.pbUploading);
        tvPercentage = findViewById(R.id.tvPercentage);

        btnProceed.setVisibility(View.GONE);

        storageReference = FirebaseStorage.getInstance().getReference();


        Intent intent = new Intent(this, personalDetails.class);
        intent.putExtra("email", getIntent().getExtras().getString("email"));
        intent.putExtra("password", getIntent().getExtras().getString("password"));


        btnSkip.setOnClickListener(v -> {
            startActivity(intent);
        });


        btnUpload.setOnClickListener(v -> {
            choosePicture();
        });


        btnProceed.setOnClickListener(v -> {
            startActivity(intent);
        });

    }

    private void choosePicture()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            ivDP.setImageURI(imageUri);
            uploadPicture();
        }
    }


    private void uploadPicture()
    {
        tvUploading.setVisibility(View.VISIBLE);
        pbUploading.setVisibility(View.VISIBLE);
        tvPercentage.setVisibility(View.VISIBLE);

        StorageReference riversRef = storageReference.child("Profile Pictures").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        riversRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> {
                    btnProceed.setVisibility(View.VISIBLE);
                    tvUploading.setVisibility(View.GONE);
                    pbUploading.setVisibility(View.GONE);
                    tvPercentage.setVisibility(View.GONE);
                    Snackbar.make(findViewById(android.R.id.content), "Image Uploaded", BaseTransientBottomBar.LENGTH_SHORT).show();
                })
                .addOnFailureListener(exception -> {
                    tvUploading.setVisibility(View.GONE);
                    pbUploading.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Failed to upload", Toast.LENGTH_SHORT).show();
                })
                .addOnProgressListener(snapshot -> {
                    double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    pbUploading.setProgress((int)progressPercent);
                    tvPercentage.setText(String.format("%d%%", (int) progressPercent));
                });
    }
}