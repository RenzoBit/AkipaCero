package com.akiparestaurant.activity;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.akiparestaurant.activity.ListaPlato1Activity.MyBinder;
import com.akiparestaurant.activity.ListaPlato1Activity.listarCategoria;
import com.akiparestaurant.activity.ListaPlato1Activity.listarPlato;
import com.akiparestaurant.bean.PedidoDetalle;
import com.akiparestaurant.bean.Plato;
import com.akiparestaurant.tool.JSONHttpClient;
import com.akiparestaurant.tool.ServiceUrl;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.SimpleAdapter.ViewBinder;

public class VerPlatoActivity extends ListActivity{
	
	ArrayList<HashMap<String, String>> listaCo = new ArrayList<HashMap<String, String>>();
	ImageView imageView1;
	TextView txtNombre, txtPrecio, txtDescripcion;
	int idplato = 0;
	Plato plato;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ver_plato_1);
		inicializa();
	}
	
	private void inicializa() {
		idplato = getIntent().getIntExtra("idplato", 0);
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute(ServiceUrl.IMAGE_URL + idplato + ".jpg");
		txtNombre = (TextView) findViewById(R.id.txtNombre);
		txtPrecio = (TextView) findViewById(R.id.txtPrecio);
		txtDescripcion = (TextView) findViewById(R.id.txtDescripcion);
		new listarPlato().execute();
		new listarComentario().execute();
	}
	
	class listarComentario extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("idplato", idplato + ""));
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			try {

				PedidoDetalle[] l = jsonHttpClient.Get(ServiceUrl.PEDIDODETALLE2, nameValuePairs, PedidoDetalle[].class);
				if (l.length > 0) {
					listaCo = new ArrayList<HashMap<String, String>>();
					for (PedidoDetalle o : l) {
						HashMap<String, String> mapVehiculo = new HashMap<String, String>();
						mapVehiculo.put(PedidoDetalle.DISPOSITIVO_CO, o.getComentario());
						mapVehiculo.put(PedidoDetalle.DISPOSITIVO_FA, o.getFacebook());
						mapVehiculo.put(PedidoDetalle.DISPOSITIVO_NO, o.getNombre());
						mapVehiculo.put(PedidoDetalle.DISPOSITIVO_CA, String.valueOf(o.getCalificacion()));
						listaCo.add(mapVehiculo);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(VerPlatoActivity.this);
			progressDialog.setMessage("Cargando comentarios. Espere un momento...");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String s) {
			progressDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					ListAdapter adapter = new SimpleAdapter(VerPlatoActivity.this, listaCo , R.layout.item_pedido_detalle_1, new String[] { PedidoDetalle.DISPOSITIVO_CO, PedidoDetalle.DISPOSITIVO_NO, PedidoDetalle.DISPOSITIVO_CA }, new int[] { R.id.xtextViewName2, R.id.xtextViewName1, R.id.xratingBar1 });
							((SimpleAdapter) adapter).setViewBinder(new MyBinder());
					setListAdapter(adapter);

				}


			});
		}
	}
	
	class MyBinder implements ViewBinder{
	    @Override
	    public boolean setViewValue(View view, Object data, String textRepresentation) {
	        if(view.getId() == R.id.xratingBar1){
	            String stringval = (String) data;
	            float ratingValue = Float.parseFloat(stringval);
	            RatingBar ratingBar = (RatingBar) view;
	            ratingBar.setRating(ratingValue);
	            return true;
	        }
	        return false;
	    }
	}
	
	private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	    ImageView bmImage;

	    public DownloadImageTask(ImageView bmImage) {
	        this.bmImage = bmImage;
	    }

	    protected Bitmap doInBackground(String... urls) {
	        String urldisplay = urls[0];
	        Bitmap mIcon11 = null;
	        try {
	            InputStream in = new java.net.URL(urldisplay).openStream();
	            mIcon11 = BitmapFactory.decodeStream(in);
	        } catch (Exception e) {
	            Log.e("Error", e.getMessage());
	            e.printStackTrace();
	        }
	        return mIcon11;
	    }

	    protected void onPostExecute(Bitmap result) {
	        bmImage.setImageBitmap(result);
	    }
	}
	
	class listarPlato extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("idplato", idplato + ""));
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			plato = jsonHttpClient.Get(ServiceUrl.PLATO4, nameValuePairs, Plato.class);
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(VerPlatoActivity.this);
			progressDialog.setMessage("Cargando plato. Espere un momento...");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String s) {
			progressDialog.dismiss();
			txtNombre.setText(plato.getNombre());
			txtPrecio.setText("Precio S/. "  + String.valueOf(plato.getPrecio()));
			txtDescripcion.setText(plato.getDescripcion());
		}
	}
	

}
