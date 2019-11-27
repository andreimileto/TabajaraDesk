/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Event;
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
public class EventDAO {
    Event event;
    
    
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
      
      
          public void deleteAll() {

        try {
            
            Session session = HibernateUtil.getSessionFactory().openSession();
              Transaction t = session.beginTransaction();
   // Session session = HibernateUtil.currentSession();
    
        String hql = "delete from Event";
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
          
          
          public ArrayList<Event> listar(Event event) {
        this.event=event;
        List resultado = null;

        ArrayList<Event> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Event  "
                
                    + " order by name";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Event eve = ((Event) ((Object) o));
                lista.add(eve);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
          
}
