/*
 * Copyright 2015-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package com.amazonaws.demo.s3transferutility;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

/*
 * This is the beginning screen that lets the user select if they want to upload or download
 */
public class MainActivity extends Activity {

    private Button btnDownload;
    private Button btnUpload;
    public int  READ_EXTERNAL_STORAGE_CODE =98;
    public int  WRITE_EXTERNAL_STORAGE_CODE =99;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
    }

    private void initUI() {
        btnDownload = (Button) findViewById(R.id.buttonDownloadMain);
        btnUpload = (Button) findViewById(R.id.buttonUploadMain);

        btnDownload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                {
                    checkPermission(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            WRITE_EXTERNAL_STORAGE_CODE);
                }

            }
        });

        btnUpload.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                {
                    checkPermission(
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            READ_EXTERNAL_STORAGE_CODE);
                }


            }
        });
    }

    // Function to check and request permission
    public void checkPermission(String permission, int requestCode)
    {

        // Checking if permission is not granted
        if (ContextCompat.checkSelfPermission(
                MainActivity.this,
                permission)
                == PackageManager.PERMISSION_DENIED) {
            ActivityCompat
                    .requestPermissions(
                            MainActivity.this,
                            new String[] { permission },
                            requestCode);
        }
        else {
            Toast
                    .makeText(MainActivity.this,
                            "Permission already granted",
                            Toast.LENGTH_SHORT)
                    .show();
            if (requestCode == READ_EXTERNAL_STORAGE_CODE){
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
            startActivity(intent);}
            else {   Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent); }

        }
    }

    // This function is called when user accept or decline the permission.
// Request Code is used to check which permission called this function.
// This request code is provided when user is prompt for permission.

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        super
                .onRequestPermissionsResult(requestCode,
                        permissions,
                        grantResults);

        if (requestCode == READ_EXTERNAL_STORAGE_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(MainActivity.this,
                        "Camera Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
                startActivity(intent);

            }
            else {
                Toast.makeText(MainActivity.this,
                        "READ external permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
        if (requestCode == WRITE_EXTERNAL_STORAGE_CODE) {

            // Checking whether user granted the permission or not.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                // Showing the toast message
                Toast.makeText(MainActivity.this,
                        "Write Permission Granted",
                        Toast.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity.this, DownloadActivity.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(MainActivity.this,
                        "WRITE external permission Denied",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }




}
