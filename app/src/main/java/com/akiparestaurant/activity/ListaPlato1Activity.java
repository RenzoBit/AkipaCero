package com.akiparestaurant.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.akiparestaurant.bean.Categoria;
import com.akiparestaurant.bean.PedidoDetalle;
import com.akiparestaurant.bean.Plato;
import com.akiparestaurant.tool.JSONHttpClient;
import com.akiparestaurant.tool.ServiceUrl;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SimpleAdapter.ViewBinder;

public class ListaPlato1Activity extends ListActivity {

	ArrayList<HashMap<String, String>> listaPlato;
	//listaPlato;
	LinkedList<Categoria> listaCategoria = new LinkedList<Categoria>();
	Spinner spinner1;
	EditText editsearch;
	String filtroc = "";
	String filtrop = "";
	Button btnBuscar;
	int idplato = 0;
	int idcliente = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_plato_1);
		inicializa();
		listaPlato = new ArrayList<HashMap<String, String>>();
		new listarPlato().execute();
	}

	private void inicializa() {
		idcliente = getIntent().getIntExtra("idcliente", 0);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		editsearch = (EditText) findViewById(R.id.editsearch);
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnBuscar.setOnClickListener(btnBuscarClick);
		ListView lstPlato = getListView();
		lstPlato.setOnItemClickListener(lstPlatoClick);
	}
	
	private View.OnClickListener btnBuscarClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			filtrop = editsearch.getText().toString().trim();
			filtroc = ((Categoria) spinner1.getSelectedItem()).getIdcategoria() + "";
			new listarPlato2().execute();
		}
	};
	
	class listarPlato extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("idcliente", "1"));
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			Plato[] l = jsonHttpClient.Get(ServiceUrl.PLATO1, nameValuePairs, Plato[].class);
			if (l.length > 0) {
				for (Plato o : l) {
					HashMap<String, String> mapVehiculo = new HashMap<String, String>();
					mapVehiculo.put(Plato.PLATO_ID, String.valueOf(o.getIdplato()));
					mapVehiculo.put(Plato.PLATO_NO, o.getNombre());
					mapVehiculo.put(Plato.PLATO_PR, String.valueOf(o.getPrecio()));
					mapVehiculo.put(Plato.PLATO_CA, String.valueOf(o.getCalificacion()));
					mapVehiculo.put(Plato.PLATO_RE, String.valueOf(o.getRelevancia()));
					listaPlato.add(mapVehiculo);
				}
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ListaPlato1Activity.this);
			progressDialog.setMessage("Cargando platos. Espere un momento...");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String s) {
			progressDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					ListAdapter adapter = new SimpleAdapter(ListaPlato1Activity.this, listaPlato , R.layout.item_plato_1, new String[] { Plato.PLATO_RE, Plato.PLATO_ID, Plato.PLATO_NO, Plato.PLATO_PR, Plato.PLATO_CA }, new int[] { R.id.textViewIde, R.id.textViewId, R.id.textViewName1, R.id.textViewName2, R.id.ratingBar1 });
							((SimpleAdapter) adapter).setViewBinder(new MyBinder());
					setListAdapter(adapter);

				}
			});
			new listarCategoria().execute();
		}
	}
	
	class listarPlato2 extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("nombre", filtrop));
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			Plato[] l = jsonHttpClient.Get(ServiceUrl.PLATO3, nameValuePairs, Plato[].class);
			if (l.length > 0) {
				listaPlato = new ArrayList<HashMap<String, String>>();
				for (Plato o : l) {
					HashMap<String, String> mapVehiculo = new HashMap<String, String>();
					mapVehiculo.put(Plato.PLATO_ID, String.valueOf(o.getIdplato()));
					mapVehiculo.put(Plato.PLATO_NO, o.getNombre());
					mapVehiculo.put(Plato.PLATO_PR, String.valueOf(o.getPrecio()));
					mapVehiculo.put(Plato.PLATO_CA, String.valueOf(o.getCalificacion()));
					listaPlato.add(mapVehiculo);
				}
			}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ListaPlato1Activity.this);
			progressDialog.setMessage("Cargando platos. Espere un momento...");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String s) {
			progressDialog.dismiss();
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					ListAdapter adapter = new SimpleAdapter(ListaPlato1Activity.this, listaPlato , R.layout.item_plato_1, new String[] { Plato.PLATO_ID, Plato.PLATO_NO, Plato.PLATO_PR, Plato.PLATO_CA }, new int[] { R.id.textViewId, R.id.textViewName1, R.id.textViewName2, R.id.ratingBar1 });
							((SimpleAdapter) adapter).setViewBinder(new MyBinder());
					setListAdapter(adapter);

				}
			});
		}
	}
	
	class listarCategoria extends AsyncTask<String, String, String> {

		private ProgressDialog progressDialog;

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			Categoria[] l = jsonHttpClient.Get(ServiceUrl.CATEGORIA, nameValuePairs, Categoria[].class);
			if (l.length > 0)
				for (Categoria o : l) {
					listaCategoria.add(o);
				}
			return null;
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ListaPlato1Activity.this);
			progressDialog.setMessage("Cargando categoría. Espere un momento...");
			progressDialog.show();
		}

		@Override
		protected void onPostExecute(String s) {
			progressDialog.dismiss();
			ArrayAdapter<Categoria> spinner_adapter = new ArrayAdapter<Categoria>(getApplicationContext(), android.R.layout.simple_spinner_item, listaCategoria);
			//Añadimos el layout para el menú y se lo damos al spinner
			spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner1.setAdapter(spinner_adapter);
		}
	}
	
	class MyBinder implements ViewBinder{
	    @Override
	    public boolean setViewValue(View view, Object data, String textRepresentation) {
	        if(view.getId() == R.id.ratingBar1){
	            String stringval = (String) data;
	            float ratingValue = Float.parseFloat(stringval);
	            RatingBar ratingBar = (RatingBar) view;
	            ratingBar.setRating(ratingValue);
	            return true;
	        }
	        return false;
	    }
	}
	
	private AdapterView.OnItemClickListener lstPlatoClick = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			idplato = Integer.parseInt(((TextView) view.findViewById(R.id.textViewId)).getText().toString());
			new guardarPlato().execute();
		}

	};
	
	class guardarPlato extends AsyncTask<String, String, String> {
		@Override
		protected void onPostExecute(String s) {
			if (s.equals("0"))
				Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_LONG).show();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected String doInBackground(String... params) {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			nameValuePairs.add(new BasicNameValuePair("idcliente", idcliente + ""));
			nameValuePairs.add(new BasicNameValuePair("idplato", idplato + ""));
			JSONHttpClient jsonHttpClient = new JSONHttpClient();
			PedidoDetalle o = new PedidoDetalle();
			o.setIdcliente(idcliente);
			o.setIdplato(idplato);
			jsonHttpClient.PostObject(ServiceUrl.PEDIDODETALLE, o, PedidoDetalle.class);

				Intent intent = new Intent(ListaPlato1Activity.this, VerPlatoActivity.class);
	            intent.putExtra("idplato", o.getIdplato());
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                //finish();


			return o.getIdcliente() + "";
		}
	}
	
}
