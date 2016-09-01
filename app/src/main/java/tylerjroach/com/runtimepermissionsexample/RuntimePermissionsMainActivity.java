package tylerjroach.com.runtimepermissionsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class RuntimePermissionsMainActivity extends AppCompatActivity implements
    CameraPermissionsDialogFragment.CameraPermissionsGrantedCallback {

  FloatingActionButton fab;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    fab = (FloatingActionButton) findViewById(R.id.capture);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        navigateToCaptureFragment();
      }
    });
  }

  @Override public void navigateToCaptureFragment() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
      Toast.makeText(this, "Ready to open camera", Toast.LENGTH_LONG).show();
    } else {
      CameraPermissionsDialogFragment.newInstance().show(getSupportFragmentManager(), CameraPermissionsDialogFragment.class.getName());
    }
  }

}
