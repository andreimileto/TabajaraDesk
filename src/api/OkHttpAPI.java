package api;



import api.ApiRepository;
import java.io.IOException;
import okhttp3.*;
import okhttp3.Request.Builder;
import org.json.JSONObject;

/**
 * @author guilherme-souza
 */
public class OkHttpAPI implements ApiRepository {
    
    private OkHttpClient client;
    
    public OkHttpAPI() {
        this.client = new OkHttpClient(); 
    }
    
    @Override
    public String get(String url, String mediaType, String[] headers) throws IOException {
        Request request = new Request.Builder()
            .url(url)
            .build();

        Response response = client.newCall(request).execute();
        
        return response.body().string();
    }

    @Override
    public String post(String url, String body, String mediaTypeParam, String[] headers) throws IOException {
         String JSON = "application/json; charset=utf-8";
        MediaType mediaType = MediaType.parse(JSON);
        RequestBody constrBody = RequestBody.create(mediaType, body);
        
        Builder builder = new Request.Builder()
          .url(url)
          .post(constrBody);
        Request request = builder.build();
        
        Response response = client.newCall(request).execute();
        
        return response.body().string();
    }

 
}
