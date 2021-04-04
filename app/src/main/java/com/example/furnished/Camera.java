package com.example.furnished;

import android.content.Context;
import android.graphics.ColorSpace;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.MotionEvent;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.assets.RenderableSource.RecenterMode;
import com.google.ar.sceneform.assets.RenderableSource.SourceType;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.rendering.ModelRenderable.Builder;
import com.google.ar.sceneform.rendering.Renderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;
import com.google.firebase.FirebaseApp;
import com.google.firebase.storage.FileDownloadTask.TaskSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.IOException;

public class Camera extends AppCompatActivity {
    private String ProductId;
    private ArFragment arFragment;
    private ModelRenderable renderable;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        ProductId = getIntent().getStringExtra("pid");
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);
        displayModel();
    }

    private void displayModel() {
        FirebaseApp.initializeApp(this);
        StorageReference modelRef = FirebaseStorage.getInstance().getReference().child("Product AR files");
        String stringBuilder = this.ProductId +".glb";
        modelRef = modelRef.child(stringBuilder);
        try {
            File file = File.createTempFile("out", "glb");
            modelRef.getFile(file).addOnSuccessListener(new OnSuccessListener<TaskSnapshot>() {
                public void onSuccess(TaskSnapshot taskSnapshot) {
                    buildModel(file);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) ->{

        AnchorNode anchorNode = new AnchorNode(hitResult.createAnchor());
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.setParent(anchorNode);
        node.setRenderable(renderable);
        node.select();
    });





    }

    private void buildModel(File file) {
        RenderableSource renderableSource = RenderableSource
                .builder()
                .setSource(this,Uri.parse(file.getPath()), SourceType.GLB)
                .setRecenterMode(RecenterMode.ROOT)
                .build();
        ModelRenderable
                .builder()
                .setSource(this, renderableSource)
                .setRegistryId(file.getPath())
                .build()
                .thenAccept(modelRenderable ->{
                    Toast.makeText(this,"Model Built",Toast.LENGTH_SHORT).show();
                    renderable = modelRenderable;
    } );

    }
}