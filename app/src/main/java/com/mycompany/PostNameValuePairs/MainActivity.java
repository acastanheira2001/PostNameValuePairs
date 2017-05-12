package com.mycompany.PostNameValuePairs;
import android.app.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.util.*;
import android.widget.*;
import java.io.*;
import java.util.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.conn.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.tsccm.*;
import org.apache.http.message.*;
import org.apache.http.params.*;
import javax.xml.transform.*;

public class MainActivity extends Activity 
{
	
	//Declare uma variável de instância para o player
	private MediaPlayer mp = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		//new PutMethodDemo().execute();
		PutMethodDemo pm = new PutMethodDemo();
		pm.setOnPostCompletedListener(this);
		pm.execute();
				
		
    }
    
    @Override
    public void onPostCompleted(String result){
		//procurar pelo texto rbtnhora
		if(!result.contains("rbtnhora\" value=\"-1\""))
			Log.e("Response_agenda  ", "Agenda fechada");
		else
		{
			Log.e("Response_agenda  ", "Agenda aberta");
			//emite um som.
			playMusic();
			}
		}
	
	
	public void playMusic() {

		//Se algum som ainda estiver a tocar pára-o
		releasePlayer();

		Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), notification);
		
		//mp = MediaPlayer.create(this, R.raw.ulso);
		mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
				public void onCompletion(MediaPlayer mp) {
					//No final de tocar liberta o media player para poder ser novamente utilizado
					releasePlayer();
				}
			});

		//Toca o som
		mp.start();

	}

//Método para libertar o media player
	private void releasePlayer() {
		if (mp != null) {
			mp.stop();
			mp.release();
			mp = null;
		}
	}
	
	
	
	
	private class PutMethodDemo extends AsyncTask<String , Void ,String>
	{

		HttpResponse response;
		String server_content = null;
		HttpClient client = getThreadSafeClient();

		private OnPostCompletedListener onPostCompletedListener;
		
		
		public interface OnPostCompletedListener{
			void onPostCompleted(String result);
		}
		
		public void setOnPostCompletedListener(OnPostCompletedListener onPostCompletedListener){
			this.onPostCompletedListener = onPostCompletedListener;
		}
		
		
		
		public DefaultHttpClient getThreadSafeClient()  {

			DefaultHttpClient client = new DefaultHttpClient();
			ClientConnectionManager mgr = client.getConnectionManager();
			HttpParams params = client.getParams();
			client = new DefaultHttpClient(new ThreadSafeClientConnManager(params,   mgr.getSchemeRegistry()), params);
			return client;
		} 
		
		
		
		
		@Override
		protected String doInBackground(String... strings) {
			
			HttpPost post = new HttpPost("http://www.consuladoportugalrjservicos.com/public_html/exec");
				
			doGet("http://consuladoportugalrj.org.br");		
			doGet("http://www.consuladoportugalrjservicos.com/public_html/");
			
			//post
			
			List<NameValuePair> pairs = new ArrayList<NameValuePair>();

			pairs.add(new BasicNameValuePair("modulo", "modulo.login"));
			pairs.add(new BasicNameValuePair("acao", "login101"));
			pairs.add(new BasicNameValuePair("txtcpf", "02355180776"));
			pairs.add(new BasicNameValuePair("txtusuario","acastanheira2001@yahoo.com.br"));
			pairs.add(new BasicNameValuePair("txtsenha", "carioca"));
			
			try
			{
				post.setEntity(new UrlEncodedFormEntity(pairs));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
			
			try
			 {
			 response = client.execute(post);
			 }
			 catch (IOException e)
			 {

			 e.printStackTrace();
			 }



  			// Getting the status code.
			int statusCode = response.getStatusLine().getStatusCode();

			Log.e("Response_code  ", "" + statusCode);

			HttpEntity httpEntity = response.getEntity();


			try
			{
				server_content = readStream(httpEntity.getContent());
			}
			catch (UnsupportedOperationException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			//post 2
			pairs.clear();
			pairs.add(new BasicNameValuePair("modulo", "modulo.servicos"));
			pairs.add(new BasicNameValuePair("acao", "servico990"));
			
			
			try
			{
				post.setEntity(new UrlEncodedFormEntity(pairs));
			}
			catch (UnsupportedEncodingException e)
			{		
				e.printStackTrace();
			}

			try
			{
				response = client.execute(post);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

  			// Getting the status code.
			statusCode = response.getStatusLine().getStatusCode();

			Log.e("Response_code  ", "" + statusCode);

			httpEntity = response.getEntity();

			try
			{
				server_content = readStream(httpEntity.getContent());
			}
			catch (UnsupportedOperationException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
			Log.e("server_content",  server_content);
			
		/*	if(!server_content.contains("rbtnhora\" value=\"-1\""))
				return null;*/
			
			
				
				//post 3
		/*	pairs.clear();
			pairs.add(new BasicNameValuePair("modulo", "modulo.servicos"));
			pairs.add(new BasicNameValuePair("acao", "servico1902"));
			pairs.add(new BasicNameValuePair("idservico", "81"));
			pairs.add(new BasicNameValuePair("idsrv", "94"));
			pairs.add(new BasicNameValuePair("agendardia", ""));
			pairs.add(new BasicNameValuePair("rbtnhora", "-1"));
				
			
			try
			{
				post.setEntity(new UrlEncodedFormEntity(pairs));
			}
			catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}

			try
			{
				response = client.execute(post);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}



  			// Getting the status code.
			statusCode = response.getStatusLine().getStatusCode();

			Log.e("Response_code  ", "" + statusCode);

			httpEntity = response.getEntity();


			try
			{
				server_content = readStream(httpEntity.getContent());
			}
			catch (UnsupportedOperationException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}			
*/
			return null;
		}

		@Override
		protected void onPostExecute(String s) {
			super.onPostExecute(s);
			
			if(onPostCompletedListener != null){
				//Chama o listener passando a string
				onPostCompletedListener.onPostCompleted(s);
			}
			
			
			
			//procurar pelo texto rbtnhora
			if(!server_content.contains("rbtnhora\" value=\"-1\""))
				Log.e("Response_agenda  ", "Agenda fechada valor de s" + s);
			else
			{
				Log.e("Response_agenda  ", "Agenda aberta valor de s" + s);
				//emite um som.
				

			}
				
			
			
			final EditText edittext1 = (EditText) findViewById(R.id.mainEditText1);
			edittext1.setText(server_content);
			
			//WebView myWebView = (WebView) findViewById(R.id.webview);
            //myWebView.loadData(server_content, "text/html", null);
			

		}


// Converting InputStream to String

		private String readStream(InputStream in) {
			BufferedReader reader = null;
			StringBuffer response = new StringBuffer();
			try {
				reader = new BufferedReader(new InputStreamReader(in));
				String line = "";
				while ((line = reader.readLine()) != null) {
					response.append(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return response.toString();
		}


		private HttpResponse doGet(String enderecoGet)
		{

			HttpGet get = new HttpGet(enderecoGet);
					
			try
			{
			response = client.execute(get);
			}
			catch (IOException e)
			{		
			e.printStackTrace();
			}


			// Getting the status code.
			int statusCode = response.getStatusLine().getStatusCode();

			Log.v("Response_source  ", "" + enderecoGet);
			Log.v("Response_code  ", "" + statusCode);	
		

			return response;

			}	
		

	}
	
	
	
}

/*
				 int status = urlConnection.getResponseCode();

				 Log.e("Response","responseCode "+ responseCode);


				 System.out.println("Request URL ... " + url.toString());

				 boolean redirect = false;

				 // normally, 3xx is redirect
				 //int status = conn.getResponseCode();
				 if (status != HttpURLConnection.HTTP_OK) {
				 if (status == HttpURLConnection.HTTP_MOVED_TEMP
				 || status == HttpURLConnection.HTTP_MOVED_PERM
				 || status == HttpURLConnection.HTTP_SEE_OTHER)
				 redirect = true;
				 }

				 System.out.println("Response Code ... " + status);

				 if (redirect) {

				 // get redirect url from "location" header field
				 String newUrl = conn.getHeaderField("Location");

				 // get the cookie if need, for login
				 String cookies = conn.getHeaderField("Set-Cookie");

				 // open the new connnection again
				 conn = (HttpURLConnection) new URL(newUrl).openConnection();
				 conn.setRequestProperty("Cookie", cookies);
				 conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				 conn.addRequestProperty("User-Agent", "Mozilla");
				 conn.addRequestProperty("Referer", "google.com");

				 System.out.println("Redirect to URL : " + newUrl);

				 */





