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
}
