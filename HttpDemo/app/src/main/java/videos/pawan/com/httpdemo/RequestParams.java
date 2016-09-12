package videos.pawan.com.httpdemo;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by pawan on 2/15/2016.
 *
 * Basically there is php page with a webservice which accepts the parameters in the format
 *
 * http://de.theappsdr.com/lectures/params.php
 * and params as
 * param1=value1&param2=value2
 */
public class RequestParams {

    String method,baseURL;
    HashMap<String,String> params = new HashMap<String,String>();

    public RequestParams(String baseURL, String method) {
        this.baseURL = baseURL;
        this.method = method;
    }

    public void addParams(String key, String value){
        params.put(key,value);
    }

    public String getEncodedParameter(){
        //loop over the key/value pairs of the params(HashMap)
        //append to a stringbuilder key=value
        // figure out how to add & symbol

        StringBuilder stringBuilder = new StringBuilder();
        for (String key:params.keySet()) {
            try {
                String value = URLEncoder.encode(params.get(key),"UTF-8");
                if (stringBuilder.length()>0){
                    stringBuilder.append("&");
                }
                stringBuilder.append(key+"="+value);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    public String getEncodedURL(){
        return this.baseURL + "?" + getEncodedParameter();
    }

    public HttpURLConnection setupConnection() throws IOException {
        if(method.equals("GET")){
            URL url = new URL(getEncodedURL());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // connection.setRequestMethod("POST");
            return connection;
        }else{//post method
            URL url = new URL(this.baseURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST"); // connection.setRequestMethod("POST")
            connection.setDoOutput(true);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write(getEncodedParameter());
            outputStreamWriter.flush();
            return connection;
        }
    }
}
