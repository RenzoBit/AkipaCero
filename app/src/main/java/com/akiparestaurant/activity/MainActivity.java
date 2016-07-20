package com.akiparestaurant.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	Button btnPlato1, btnPlato2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inicializa();
	}
	
	private void inicializa() {
		btnPlato1 = (Button) findViewById(R.id.btnPlato1);
		btnPlato1.setOnClickListener(btnPlato1Click);
		btnPlato2 = (Button) findViewById(R.id.btnPlato2);
		btnPlato2.setOnClickListener(btnPlato2Click);
	}
	
	private View.OnClickListener btnPlato1Click = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), ListaPlato1Activity.class);
			intent.putExtra("idcliente", 1);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	};
	
	private View.OnClickListener btnPlato2Click = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(getApplicationContext(), ListaPlato1Activity.class);
			intent.putExtra("idcliente", 5);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
		}
	};
}
