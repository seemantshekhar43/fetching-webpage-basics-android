package comandydevo.wixsite.seemantshekhar43.fetchingwebpagebasic;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    // first of all take permission from user in AndroidManifest.xml
    /* defining a  new class arguments: <what is url(string), what to do
                     while fetching url (void), what we got (string)>*/
    public class SetupFetch extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String page ="";
            HttpURLConnection urlConnection = null;

            // introducing try and catch method to handle any exception
            try {
                url = new URL(strings[0]);// it will take the 1st string as input

                // opening browser
                urlConnection =(HttpURLConnection) url.openConnection();

                //get input url
                InputStream input = urlConnection.getInputStream();

                //read the input
                InputStreamReader read = new InputStreamReader(input);

                //introducing an integer data which read the read var.
                int data;
                 data = read.read();

                 // the value of data is (-1) at the end of the file
                while (data!=-1){
                    char storeData = (char) data; //converting data to character
                    page+=storeData; // all data are stored to page
                    data =read.read(); // it will help to run the loop finitely.
                    } return page;

            }
            catch (Exception e){
                e.printStackTrace();
                return "Cannot fetch the web page.";

            }
              }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creating an instance of the class SetupFetch
        SetupFetch request = new SetupFetch();

        // nullifying if any value of page
        String page = null;

        //introducing try catch to handle exception.
        try {

            // it will set the input equal to www... and whole process will run
            page = request.execute("http://www.HiteshChoudhary.com/").get();
        }catch (Exception e){
            e.printStackTrace();
        }

        // lets watch whats happening
        Log.i("My site",page);
        Log.i("DONE","DONE");

    }

}

