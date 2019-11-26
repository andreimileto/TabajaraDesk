/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

import DAO.EventDAO;
import DAO.MensagemRetorno;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private final String cidade = "3459035"; //lajeado
    private final String apiKey = "5BF2CDD9AF84BB7BB06B7361AC29C468BED3E9BFC8";
    private final String lang = "pt";
    
    private final ApiRepository api = new OkHttpAPI();
    
    public MensagemRetorno getEventos() throws IOException {
        String finalUrl = url;
        String resposta = api.get(finalUrl, ApiRepository.JSON, null);
      MensagemRetorno msg = new MensagemRetorno();
        
        try {
            JSONArray json = new JSONArray(resposta);
            this.JSONtoList(json);
            //msg.setObjeto(this.JSONtoObject(json));
            msg.setSucesso(true);
        } catch(JSONException e) {
            e.printStackTrace();
            msg.setMensagem("As chamadas a API foram esgotadas! Aguarde o tempo necessário para nova requisição");
        }        
        return msg;
    }
    
    private JSONArray getWeather(JSONObject json){
        return json.getJSONArray("weather");
    }
    
    private String getDescription(JSONObject json){
        JSONObject jsonWeather = new JSONObject(this.getWeather(json).get(0).toString());
        return jsonWeather.getString("description");
    }
    
    private double getTemperatura(JSONObject json){
        return json.getJSONObject("main").getDouble("temp");
    }
    
    private double getTemperaturaMaxima(JSONObject json){
        return json.getJSONObject("main").getDouble("temp_max");
    }
    
    private double getTemperaturaMinima(JSONObject json){
        return json.getJSONObject("main").getDouble("temp_min");
    }
    
//    public MensagemRetorno proximos5Dias() throws IOException {
//        String finalUrl = url+"/forecast?id="+cidade+"&lang="+lang+"&APPID="+apiKey+"&units=metric";
//        String resposta = api.get(finalUrl, ApiRepository.JSON, null);
//        System.out.println(resposta);
//        MensagemRetorno msg = new MensagemRetorno();
//        try {
//            JSONArray list = new JSONObject(resposta).getJSONArray("list");
//            for (Object object : list) {
//                JSONObject json = (JSONObject) object;
//                Evento evento = this.JSONtoObject(json); 
//                //previsao.setDia(new Date(json.getLong("dt") * 1000));
//               // msg.getLista().add(previsao);
//            }
//            msg.setSucesso(true);
//        } catch(JSONException e) {
//            msg.setMensagem("As chamadas a API foram esgotadas! Aguarde o tempo necessário para nova requisição");
//        }
//        return msg;
//    }
    
    private String proximosXDias(int dias) throws IOException {
        String finalUrl = url+"/forecast/daily?id="+cidade+"&lang="+lang+"&APPID="+apiKey+"&units=metric&cnt="+dias;
        return api.get(finalUrl, ApiRepository.JSON, null);
    }
    
    private Evento JSONtoObject(JSONObject json){
        Evento evento = new Evento();
        evento.setId(json.getInt("id"));
        evento.setDescricao(json.getString("name"));
        
        return evento;
    }
    
    private List<Evento> JSONtoList(JSONArray json){
        List<Evento> eventos = new ArrayList<>();
        json.forEach(event -> {
            eventos.add(JSONtoObject((JSONObject) event));
        });
        System.out.println(eventos.toString());
        
        EventDAO eventDAO = new EventDAO();
        eventDAO.salvar(eventos);
//        Evento evento = new Evento();
//            
//        evento.setId(json.getInt("id"));
//        evento.setDescricao(json.getString("nome"));
        //previsaoTempo.setTemperatura(this.getTemperatura(json));
        //previsaoTempo.setTemperaturaMaxima(this.getTemperaturaMinima(json));
        //previsaoTempo.setTemperaturaMinima(this.getTemperaturaMinima(json));
        
        return new ArrayList<>();
    }
    
    
}
