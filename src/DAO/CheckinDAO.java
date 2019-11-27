/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Checkin;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 *
 * @author AndreiMileto
 */
public class CheckinDAO {
    Checkin chk;
    
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
      
      
      
            public ArrayList<Checkin> listarSync() {
       /// this.client=client;
        List resultado = null;

        ArrayList<Checkin> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "from Checkin where Sync = 'N' ";
            String sel = sql;
            System.out.println(sel);
            org.hibernate.Query q = session.createQuery(sql);

            resultado = q.list();

            for (Object o : resultado) {
                Checkin check = ((Checkin) ((Object) o));
                lista.add(check);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
      
}
