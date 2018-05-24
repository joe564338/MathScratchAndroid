package com.packages.joe.mathscratchandroid;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.drive.DriveClient;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.drive.DriveResourceClient;
import com.google.android.gms.drive.MetadataChangeSet;
import com.google.android.gms.drive.query.Filters;
import com.google.android.gms.drive.query.Query;
import com.google.android.gms.drive.query.SearchableField;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import java.util.Set;
import com.google.android.gms.common.api.Scope;


import java.util.HashSet;

public class SetUpDrive extends AppCompatActivity {
    GoogleSignInClient googleSignInClient;
    DriveClient driveClient;
    DriveResourceClient driveResourceClient;
    private static final int REQUEST_CODE_CAPTURE_IMAGE = 1;
    private static final int REQUEST_CODE_SIGN_IN = 0;
    private static final int REQUEST_CODE_CREATOR = 2;
    private static final int RC_SIGN_IN = 9001;
    String FOLDER_NAME = "MathScratch";
    Toast succuss;
    Toast fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_up_drive);
        googleSignInClient = buildGoogleSignInClient();
        succuss = Toast.makeText(this, "Success! Created Folder for app", Toast.LENGTH_LONG);
        fail = Toast.makeText(this, "Couldn't make folder for app", Toast.LENGTH_LONG);
        signIn();

    }
    private GoogleSignInClient buildGoogleSignInClient() {
        GoogleSignInOptions signInOptions =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestScopes(Drive.SCOPE_FILE)
                        .build();
        return GoogleSignIn.getClient(this, signInOptions);
    }
    private void updateViewWithGoogleSignInAccountTask(Task<GoogleSignInAccount> task) {
        Log.i("UpdateView", "Update view with sign in account task");
        task.addOnSuccessListener(
                new OnSuccessListener<GoogleSignInAccount>() {
                    @Override
                    public void onSuccess(GoogleSignInAccount googleSignInAccount) {
                        Log.i("Sign In", "Sign in success");
                        // Build a drive client.
                        driveClient = Drive.getDriveClient(getApplicationContext(), googleSignInAccount);
                        // Build a drive resource client.
                        driveResourceClient =
                                Drive.getDriveResourceClient(getApplicationContext(), googleSignInAccount);
                        // Start camera.
                        startActivityForResult(
                                new Intent(MediaStore.ACTION_IMAGE_CAPTURE), REQUEST_CODE_CAPTURE_IMAGE);
                    }
                })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("FAIL SIGN IN", "Sign in failed", e);
                            }
                        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(@Nullable Task<GoogleSignInAccount> completedTask) {
        Log.d("Sign IN RESULT", "handleSignInResult:" + completedTask.isSuccessful());

        try {
            // Signed in successfully, show authenticated U
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            initializeDriveClient(account);

        } catch (ApiException e) {
            // Signed out, show unauthenticated UI.
            Log.w("CANT SIGN IN", "handleSignInResult:error", e);

        }
    }
    /** Start sign in activity. */
    private void signIn() {
        Log.i("START SIGN IN", "Start sign in");
        Set<Scope> requiredScopes = new HashSet<>(2);
        requiredScopes.add(Drive.SCOPE_FILE);
        requiredScopes.add(Drive.SCOPE_APPFOLDER);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        System.out.println("Sign in account initialized   " + signInAccount);
        if (signInAccount != null && signInAccount.getGrantedScopes().containsAll(requiredScopes)) {
            System.out.println("IF");
            initializeDriveClient(signInAccount);

        } else {
            System.out.println("ELSE");

            startActivityForResult(googleSignInClient.getSignInIntent(), RC_SIGN_IN);

        }

    }
    private void initializeDriveClient(GoogleSignInAccount signInAccount) {
        driveClient = Drive.getDriveClient(getApplicationContext(), signInAccount);
        driveResourceClient = Drive.getDriveResourceClient(getApplicationContext(), signInAccount);
        System.out.println("Signed in and creating drive client");
        onDriveClientReady();
    }

    protected void onDriveClientReady() {
        createFolder();
    }
    private void createFolder(){

        driveResourceClient
                .getRootFolder()
                .continueWithTask(task -> {
                    DriveFolder parentFolder = task.getResult();
                    MetadataChangeSet changeSet = new MetadataChangeSet.Builder()
                            .setTitle(FOLDER_NAME)
                            .setMimeType(DriveFolder.MIME_TYPE)
                            .setStarred(true)
                            .build();
                    return driveResourceClient.createFolder(parentFolder, changeSet);
                })
                .addOnSuccessListener(this,
                        driveFolder -> {
                            succuss.show();
                            System.out.println("File created");
                            finish();
                        })
                .addOnFailureListener(this, e -> {
                    Log.e("FAIL", "Unable to create file", e);
                    System.out.println("Failed to make file");
                    fail.show();
                    finish();
                });
    }

}
