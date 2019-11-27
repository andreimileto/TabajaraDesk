/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DAO.ActivityEventDAO;
import DAO.ClientDAO;
import DAO.EventDAO;
import DAO.MensagemRetorno;
import entidade.ActivityEvent;
import entidade.Client;
import entidade.Event;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author AndreiMileto
 */
public class EventoAPI {

    // private final String url = "http://localhost:5004/api";
    private final String url = "http://localhost:8080/api/events";
    //   private final String cidade = "3459035"; //lajeado
       private final String apiKey = "5BF2CDD9AF84BB7BB06B7361AC29C468BED3E9BFC8";
    //   private final String lang = "pt";

    private final ApiRepository api = new OkHttpAPI();

    public MensagemRetorno getEventos() throws IOException {
        String finalUrl = url;
        String resposta = api.get(finalUrl, ApiRepository.JSON, null);
        MensagemRetorno msg = new MensagemRetorno();
      //  test();

        try {
            JSONArray json = new JSONArray(resposta);
            this.JSONtoList(json);
            //msg.setObjeto(this.JSONtoObject(json));
            msg.setSucesso(true);
        } catch (JSONException e) {
            e.printStackTrace();
            msg.setMensagem("As chamadas a API foram esgotadas! Aguarde o tempo necessário para nova requisição");
        }
        return msg;
    }

    public MensagemRetorno getClientes() throws IOException {
        String finalUrl = "http://localhost:8080/api/users";
        String resposta = api.get(finalUrl, ApiRepository.JSON, null);
        MensagemRetorno msg = new MensagemRetorno();

        try {
            JSONArray json = new JSONArray(resposta);
            this.JSONtoListClient(json);
            //msg.setObjeto(this.JSONtoObject(json));
            msg.setSucesso(true);
        } catch (JSONException e) {
            e.printStackTrace();
            msg.setMensagem("As chamadas a API foram esgotadas! Aguarde o tempo necessário para nova requisição");
        }
        return msg;
    }

    public MensagemRetorno getActivityEvent() throws IOException {
        String finalUrl = "http://localhost:8080/api/activities";
        String resposta = api.get(finalUrl, ApiRepository.JSON, null);
        MensagemRetorno msg = new MensagemRetorno();

        try {
            JSONArray json = new JSONArray(resposta);
            this.JSONtoListActivity(json);
            //msg.setObjeto(this.JSONtoObject(json));
            msg.setSucesso(true);
        } catch (JSONException e) {
            e.printStackTrace();
            msg.setMensagem("As chamadas a API foram esgotadas! Aguarde o tempo necessário para nova requisição");
        }
        return msg;
    }

    private Event JSONtoObject(JSONObject json) {
        //  Evento evento = new Evento();
        Event event = new Event();
        //  Client client = new Client();
        //   evento.setId(json.getInt("id"));
        //  evento.setDescricao(json.getString("name"));
        event.setName(json.getString("name"));
        event.setSync('N');
        event.setId(json.getInt("id"));
        EventDAO eventDAO = new EventDAO();
        eventDAO.salvar(event);
        return event;
    }

    private Client JSONtoObjectClient(JSONObject json) {
        //  Evento evento = new Evento();
        Client client = new Client();
        //  Client client = new Client();
        client.setId(json.getInt("id"));
        client.setName(json.getString("name"));
        client.setEmail(json.getString("email"));
        client.setSync('N');

        ClientDAO clientDAO = new ClientDAO();
        clientDAO.salvar(client);
        return client;
    }

    private ActivityEvent JSONtoObjectActivity(JSONObject json) {
          Event event = new Event();
        ActivityEvent activity = new ActivityEvent();
        
          Client client = new Client();
           client.setId(json.getJSONObject("client").getInt("id"));
           System.out.println(client.getId()+ "  achou o id");
           event.setId(json.getJSONObject("event").getInt("id"));
           System.out.println(event.getId()+ " achou o id do evento");
        // client.setName(json.getString("name"));
        // client.setEmail(json.getString("email"));
        activity.setSync('N');
        activity.setId(json.getInt("id"));
        System.out.println(activity.getId() + " achou o id act");
        activity.setClient(client);
        activity.setEvent(event);
        activity.setDate(null);
        activity.setStatus('T');
        
        ActivityEventDAO actDAO = new ActivityEventDAO();
        actDAO.salvar(activity);
        

        //    ClientDAO clientDAO = new ClientDAO();
        //    clientDAO.salvar(client);
        return activity;
    }

    private List<Event> JSONtoList(JSONArray json) {
        System.out.println(json);
        List<Event> eventos = new ArrayList<>();
        json.forEach(event -> {
            eventos.add(JSONtoObject((JSONObject) event));
        });
        System.out.println(eventos.toString());

   //     EventDAO eventDAO = new EventDAO();

        return new ArrayList<>();
    }

    private List<Client> JSONtoListClient(JSONArray json) {
        List<Client> clients = new ArrayList<>();
        json.forEach(client -> {
            clients.add(JSONtoObjectClient((JSONObject) client));
        });
        System.out.println(clients.toString());

        //   EventDAO eventDAO = new EventDAO();
        return new ArrayList<>();
    }

    private List<ActivityEvent> JSONtoListActivity(JSONArray json) {
        System.out.println(json);
        List<ActivityEvent> activitys = new ArrayList<>();
        json.forEach(activity -> {
            activitys.add(JSONtoObjectActivity((JSONObject) activity));
               System.out.println(activitys.get(0));
        });
        System.out.println(activitys.get(0));

        //   EventDAO eventDAO = new EventDAO();
        return new ArrayList<>();
    }

    public static void test() throws IOException {

        String json = ""
                + "{"                
                + "\"id\": \"testclient\","
                + "\"name\": \"testpass\","
                + "\"email\": \"chaves\""               
                + "}";

        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/users")
                .post(body)
                .addHeader("cache-control", "no-cache")
                .addHeader("postman-token", "5BF2CDD9AF84BB7BB06B7361AC29C468BED3E9BFC8")
                .build();

        Response response = client.newCall(request).execute();
        System.out.println(response + " testeeee");
    }

}
