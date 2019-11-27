/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tabajaradesk;

import DAO.ActivityEventDAO;
import DAO.ClientDAO;
import DAO.EventDAO;
import api.EventoAPI;
import apoio.ConexaoBD;
import entidade.Event;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import tela.Login;

/**
 *
 * @author AndreiMileto
 */
public class TabajaraDesk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        //   Util.enviodeEmail();
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        
        if (ConexaoBD.getInstance()
                .getConnection() != null) {
//            JOptionPane.showMessageDialog(null, "conectou");
            Login senha = new Login();
            senha.setVisible(true);

            //
//            Event event = new Event();
//            
//            event.setName("testeeee new");
//            event.setSync('N');
//            event.setId(10);
//            EventDAO dao = new EventDAO();
//            dao.salvar(event);
            //
            EventoAPI api = new EventoAPI();
            try {

                
                ActivityEventDAO actDAO = new ActivityEventDAO();
                
                actDAO.deleteAll();
                EventDAO event = new EventDAO();
                event.deleteAll();
                
                ClientDAO client = new ClientDAO();
                client.deleteAll();
                
                api.getEventos();
                api.getClientes();
                api.getActivityEvent();
                
            } catch (Exception e) {
                
            }
            
        } else {
            JOptionPane.showMessageDialog(null, "Erro ao abrir banco de dados, entre em contato com o suporte!");
        }
        
    }
    
}
