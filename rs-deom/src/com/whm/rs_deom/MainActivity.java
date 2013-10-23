package com.whm.rs_deom;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import com.whm.rs_deom.to.Book;
import com.whm.rs_deom.to.Category;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final TextView resultTextView = (TextView) findViewById(R.id.result_text);
		AsyncTask<String, Void, Category> simpleGetTask = new AsyncTask<String, Void, Category>() {
				@Override
				protected Category doInBackground(String... params) {
					//executed by a background thread
					//create a new RestTemplate instance
					RestTemplate restTemplate = new RestTemplate();
					//add the String message converter, since the result of
					// the call will be a String
					restTemplate.getMessageConverters().add(new SimpleXmlHttpMessageConverter());
					// Make the HTTP GET request on the url (params[0]),
					// marshaling the response to a String
					ResponseEntity<Category> response =restTemplate.getForEntity(params[0], Category.class);

					return response.getBody();
				}
				@Override
				protected void onPostExecute(Category cat) {
					// executed by the UI thread once the background
					// thread is done getting the result
					StringBuilder result = new StringBuilder();
					
					result.append("Book Categories: ")
						.append("id: "+ cat.getCategoryId()+"\n")
						.append("name: "+ cat.getCategoryName()+"\n");
					
					for(Book book:cat.getBooks()) {
						result.append("Book -> \n")
								.append("\r id:"+book.getBookId()+"\n")
								.append("\r isbn:"+book.getBookISBNnumber()+"\n")
								.append("\r name:"+book.getBookName()+"\n")
								.append("\r author:"+book.getAuthor()+"\n");
					}
					resultTextView.setText(result.toString());
				}
		};
		String url = "http://192.168.25.128:8181/cxf/demo/categoryservice/category/001/books";
		// triggers the task; it will update the resultTextView once
		// it is done
		simpleGetTask.execute(url);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
