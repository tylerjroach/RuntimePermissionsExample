package tylerjroach.com.runtimepermissionsexample;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RuntimePermissionsMainActivity extends AppCompatActivity implements
    CameraPermissionsDialogFragment.CameraPermissionsGrantedCallback {

  FloatingActionButton fab;
  TextView status;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    fab = (FloatingActionButton) findViewById(R.id.capture);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        navigateToCaptureFragment();
      }
    });

    status = (TextView) findViewById(R.id.status);
  }

  @Override protected void onResume() {
    super.onResume();
    if (isPermissionGranted()) {
      status.setText("All permissions granted. Ready to open Camera");
    } else {
      status.setText("Permissions Needed");
    }
  }

  @Override public void navigateToCaptureFragment() {
    if (isPermissionGranted()) {
      Toast.makeText(this, "Opening Camera!", Toast.LENGTH_LONG).show();
    } else {
      CameraPermissionsDialogFragment.newInstance().show(getSupportFragmentManager(), CameraPermissionsDialogFragment.class.getName());
    }
  }

  private boolean isPermissionGranted() {
    return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED &&
        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
  }

}
