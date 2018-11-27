package com.journaldev.barcodevisionapi;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import com.journaldev.barcodevisionapi.MainActivity.*;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScannedBarcodeActivity extends AppCompatActivity {
    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    Button btnAction;
    String intentData = "";
    int isEmail = 0;
    String filename;
    EditText IPaddress;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            filename = extras.getString("fileName");
        }
        else {
            return;
        }

        initViews();
    }

    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
        btnAction = findViewById(R.id.btnAction);


        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (intentData.length() > 0) {
                    if (isEmail == 0)
                        startActivity(new Intent(ScannedBarcodeActivity.this, EmailActivity.class).putExtra("email_address", intentData));
                    else if (isEmail == 1) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(intentData)));
                    }
                    else if (isEmail == 2) {
                        IPaddress = findViewById(R.id.IPAddress);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + IPaddress.getText().toString())));
                    }
                    }


            }
        });
    }

    private void initialiseDetectorsAndSources() {

        Toast.makeText(getApplicationContext(), "Barcode scanner started", Toast.LENGTH_SHORT).show();

        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });


        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                if (barcodes.size() != 0) txtBarcodeValue.post(new Runnable() {

                    @Override
                    public void run() {

                        if (barcodes.valueAt(0).email != null) {
                            txtBarcodeValue.removeCallbacks(null);
                            intentData = barcodes.valueAt(0).email.address;
                            txtBarcodeValue.setText(intentData);
                            isEmail = 0;
                            btnAction.setText("ADD CONTENT TO THE MAIL");
                        } else if (barcodes.valueAt(0).url != null) {
                            isEmail = 1;
                            btnAction.setText("LAUNCH URL");
                            intentData = barcodes.valueAt(0).displayValue;
                            txtBarcodeValue.setText(intentData);
                            Toast.makeText(getApplicationContext(), intentData, Toast.LENGTH_SHORT).show();
                        } else {
//                                File path = ScannedBarcodeActivity.this.getFilesDir();
//                                File dir = new File("//sdcard//Download//");
//                                File dir = new File("/storage/sdcard0/Download");
                            isEmail = 2;
                            btnAction.setText("Upload Attendance File");
                            intentData = barcodes.valueAt(0).displayValue;
                            txtBarcodeValue.setText(intentData);

                            try {

//                                    File file = new File(dir, "student_data.txt");
                                File file = new File("/storage/sdcard0/Download", filename);

                                FileOutputStream stream = new FileOutputStream(file, true);

                                try {
                                    // PrintStream printstream = new PrintStream(stream);
                                    // printstream.print(intentData + "\n");
                                    String[] IData = intentData.split("\\r?\\n");
                                    String finalString = "";
                                    for (int i = 6; i <= 13; i++) {
                                        finalString += IData[1].charAt(i);
                                    }


//                                    String finalString = IData[1] + '\n';
//                                    if (finalString.charAt(11) == '1') {
//                                        int roll = (Character.getNumericValue(finalString.charAt(12)) * 10) + Character.getNumericValue(finalString.charAt(13));
//                                    }
//                                    else if (finalString.charAt(11) == '2') {
//                                        int roll = (Character.getNumericValue(finalString.charAt(12)) * 10) + Character.getNumericValue(finalString.charAt(13));
//                                    }

                                    Toast.makeText(getApplicationContext(), finalString, Toast.LENGTH_SHORT).show();
                                    finalString += "\n";
                                    stream.write(finalString.getBytes());
                                } finally {
                                    stream.close();
                                }
                            } catch (FileNotFoundException e) {
                                Toast.makeText(getApplicationContext(), "ERROR: File Not Found", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (IOException e) {
                                Toast.makeText(getApplicationContext(), "ERROR: IO Exception", Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                e.printStackTrace();
                            }
                        }
                    }
                });
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}
