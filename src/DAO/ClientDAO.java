/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Client;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author AndreiMileto
 */


public class ClientDAO {
    Client client;
     public boolean salvar(Object o) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        boolean retorno = false;
        try {

            session = HibernateUtil.getSessionFactory().openSession();

            Transaction t = session.beginTransaction();
            System.out.println("sql = "+t);
            session.merge(o);

            t.commit();

            retorno = true;
        } catch (HibernateException he) {
//            LogHelper.makeLog(this, "algo de errado ocorreu" + he.getMessage());
            he.printStackTrace();
        } finally {
            session.close();
        }
        return retorno;
    }
     
      public int ultimoId(Client client) {

        this.client = client;
        List resultado = null;
        ArrayList<Client> lista = new ArrayList<>();
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "";

            sql = "select max(id) from Client  ";

            String sel = sql;
            // System.out.println(sel + " select cliente");
            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();

            for (Object o : resultado) {
                id = ((Integer) ((Object) o));
                //lista.add(tar);
            }

//
        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();

        return id;
    }
      
      
       public int proxID() {

      
        List resultado = null;
    //    ArrayList<ActivityEvent> lista = new ArrayList<>();
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "";

            sql = "select max(id) from ActivityEvent";

            String sel = sql;
            System.out.println(sel + " select ActivityEvent");
            org.hibernate.Query q = session.createQuery(sql);
            resultado = q.list();
            System.out.println(resultado + " ---- resultado");
            for (Object o : resultado) {
                id = ((Integer) ((Object) o));
                //lista.add(tar);
                System.out.println(id + " --- id");
            }

//
        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();

        return id + 1;
    }
      
      
          public void deleteAll() {

        try {
            
            Session session = HibernateUtil.getSessionFactory().openSession();
              Transaction t = session.beginTransaction();
   // Session session = HibernateUtil.currentSession();
    
        String hql = "delete from Client";
        Query query = session.createQuery(hql);
        //query.setString("name","Product 1");
        int rowCount = query.executeUpdate();
        t.commit();
//
        } catch (HibernateException he) {
            he.printStackTrace();
        }// finally {
//            session.close();

    }
          
          
              public ArrayList<Client> listar(Client client) {
        this.client=client;
        List resultado = null;

        ArrayList<Client> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Client  "
                
                    + " order by name";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Client cli = ((Client) ((Object) o));
                lista.add(cli);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
          
}
