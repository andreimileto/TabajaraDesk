/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import apoio.HibernateUtil;
import entidade.Users;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;


/**
 *
 * @author AndreiMileto
 */
public class UserDAO {
        Users user;
    List<Object[]> listResult;
    
    
    public ArrayList<Users> listar(Users user) {
        this.user = user;
        List resultado = null;

        ArrayList<Users> lista = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            String sql = "";

         //   if (user.getLogin().equals(user.getNome())) {
                sql = "from Users  "
                        + "where (upper(login) like '%" + user.getLogin().toUpperCase() + "%')";
                    //    + "or upper(nome) like '%" + user.getNome().toUpperCase() + "%')"
                      //  + "and situacao ='A'";
         //   } else {

           //     sql = "from Usuario  "
           //             + "where login = '" + user.getLogin() + "' "
           //             + "and situacao ='A'";
          //  }
            String sel = sql;
            System.out.println(sel);
            
            org.hibernate.Query q = session.createQuery(sql);
            System.out.println("após sql");
            resultado = q.list();

            for (Object o : resultado) {
                Users users = ((Users) ((Object) o));
                lista.add(users);
            }

        } catch (HibernateException he) {
            he.printStackTrace();
        }
        return lista;
    }
}
