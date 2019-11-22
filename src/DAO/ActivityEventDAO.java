/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.ActivityEvent;
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
public class ActivityEventDAO {
    ActivityEvent actEvent;
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
     
           public int consultaCheckin(ActivityEvent actEvent) {

        this.actEvent = actEvent;
        List resultado = null;
        ArrayList<ActivityEvent> lista = new ArrayList<>();
        int id = 0;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            String sql = "";

            sql = "select id from ActivityEvent  where id_event = "+actEvent.getEvent().getId()
                    + " and id_client = "+actEvent.getClient().getId();
            
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

        return id;
    }
    
}
