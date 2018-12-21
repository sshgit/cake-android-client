package com.waracle.androidtest.service;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.waracle.androidtest.data.Cake;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Cloud {

    private final static String TAG = "Cloud";
    private static final String TITLE= "title";
    private static final String DESC = "desc";
    private static final String IMAGE_URL = "image";

    public static class CloudResponse {
        final Object response;
        final String error;
        CloudResponse(Object response, String error){
            this.response = response;
            this.error = error;
        }
    }

    public interface CloudListener {
        void onFailure(String rawResponse);
    }

    private static HttpURLConnection prepareConnection(String urlStr, String requestMethod) throws IOException {
        final URL url =  new URL(urlStr);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(requestMethod);

        return connection;
    }

    public static CloudResponse getCakes(String url){
        List<Cake> cakes = new ArrayList<>();
        String error  = null;
        try {
            final HttpURLConnection connection = prepareConnection(url, "GET");
            final int httpCode = connection.getResponseCode();
            final boolean isSuccess = httpCode == HttpURLConnection.HTTP_OK ;
            final InputStreamReader inputStreamReader = new InputStreamReader(isSuccess
                    ? connection.getInputStream()
                    : connection.getErrorStream());
            final BufferedReader reader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            if(isSuccess){
                JSONArray jsonArray = new JSONArray(sb.toString());
                int length = jsonArray.length();
                for (int i = 0; i < length; i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    final String title = jsonObject.getString(TITLE);
                    final String desc = jsonObject.getString(DESC);
                    final String imageUrl = jsonObject.getString(IMAGE_URL);
                    Cake cake = new Cake(title, desc, imageUrl);
                    cakes.add(cake);
                }
            }else {
                error = sb.toString();
            }
        }catch (IOException | JSONException e){
            error = e.getLocalizedMessage();
            Log.d(TAG, error);
        }
        return new CloudResponse(cakes, error);
    }

    public static CloudResponse getImage(String url){
        Bitmap bitmap = null;
        String error = null;
        try {
            final HttpURLConnection connection = prepareConnection(url, "GET");
            final int httpCode = connection.getResponseCode();
            switch (httpCode) {
                case HttpURLConnection.HTTP_OK:
                    final InputStream inputStream = connection.getInputStream();
                    if (inputStream != null)
                        bitmap = BitmapFactory.decodeStream(inputStream);
                    break;
                case HttpURLConnection.HTTP_MOVED_PERM:
                case HttpURLConnection.HTTP_MOVED_TEMP:
                    String movedAddress = connection.getHeaderField("Location");
                    return getImage(movedAddress);
                default:
                    final InputStream errorStream = connection.getErrorStream();
                    if (errorStream != null) {
                        final InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
                        final BufferedReader reader = new BufferedReader(inputStreamReader);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        error = sb.toString();
                    } else
                        error = "Unknown Error";
            }
            if(bitmap == null)
                error = "Image not found";
        } catch (IOException e) {
            error = e.getLocalizedMessage();
            Log.d(TAG, error);
        }
        return new CloudResponse(bitmap, error);
    }

}
