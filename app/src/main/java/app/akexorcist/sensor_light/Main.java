package app.akexorcist.sensor_light;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

public class Main extends Activity {
	TextView textLight;
	SensorManager sensorManager;
	Sensor sensor;
 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
 
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		// để nhanh chóng các đối tượng của lớp cảm biến bằng cách gọi phương thức getDefaultSensor() của lớp SensorManager.
		sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		
		textLight = (TextView) findViewById(R.id.textLight);
	}
 
	public void onResume() {
		super.onResume();
		sensorManager.registerListener(lightListener, sensor,
				SensorManager.SENSOR_DELAY_NORMAL);
	}
 
	public void onStop() {
		super.onStop();
		sensorManager.unregisterListener(lightListener);
	}
	public SensorEventListener lightListener = new SensorEventListener() {
		// Một khi cảm biến được khai báo,đăng ký nghe của nó và ghi đè lên hai phương pháp được onAccuracyChanged và onSensorChanged
		public void onAccuracyChanged(Sensor sensor, int acc) { }
 
		public void onSensorChanged(SensorEvent event) {
			float x = event.values[0];

			textLight.setText((int)x + " lux");
		}
	};
}