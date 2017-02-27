package es.esy.vivekrajendran.newsapi.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class NewsAsync extends AsyncTask<String, Void, Boolean> {

    public static final String TAG = "TAG";
    public static final String NEWS = "news";
    public static final String PROVIDERS = "providers";
    public static final String IMAGES = "images";
    public static final String PHOTO = "photo";
    private Context context;

    public NewsAsync(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(String... params) {
        String jsonString = doNetworkOperation(params[0]);
        doParse(jsonString ,params);
        return true;
    }

    private void doParse(String json, String[] param) {
        String path = param[1];
        switch (path) {
            case NEWS:
                new NewsParser.LatestNewsParser(context)
                        .resolveJSON(json);
                break;
            case PROVIDERS:
                new NewsParser.ProviderParser(context)
                        .resolveJSON(json);
                break;
            case IMAGES:
                new MultimediaParser.ImageParser(context)
                        .resolveImage(json);
                break;
            case PHOTO:
                new MultimediaParser.VideoParser(context)
                        .resolveVideo(json);
                break;
            default:
                throw new IllegalArgumentException("No valid param[1] found");
        }
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }

    private String doNetworkOperation(String params) {
        HttpURLConnection mURLConnection;
        StringBuilder mStringBuilder;

        try {
            URL url = new URL(params);
            mURLConnection = (HttpURLConnection) url.openConnection();
            mURLConnection.setRequestMethod("GET");
            mURLConnection.setConnectTimeout(10000);
            mURLConnection.setReadTimeout(30000);

            BufferedReader mBufferedReader = new BufferedReader(
                    new InputStreamReader(mURLConnection.getInputStream()));

            String temp;
            mStringBuilder = new StringBuilder();
            while ((temp = mBufferedReader.readLine()) != null) {
                mStringBuilder.append(temp);
            }
            Log.i(TAG, "doNetworkOperation: " + mStringBuilder.toString());
            return mStringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "doNetworkOperation: " + e.getMessage());
            return "Err" + e.toString();
        }
    }
}
