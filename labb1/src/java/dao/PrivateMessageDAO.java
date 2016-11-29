/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.PrivateMessage;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Dennis
 */
public class PrivateMessageDAO {
    private Session session;
    private Transaction tx;
    private Criteria cr;
    
    
    public void add(PrivateMessage message){
         try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(message);
            session.getTransaction().commit();
           
        }catch(Exception ex){
            System.out.println("add message fail: "+ ex);
        }finally{
            session.close();
        }
    }
    
    public List<PrivateMessage> List(int id){
        List<PrivateMessage> temp= new ArrayList<PrivateMessage>();
        session= HibernateUtil.getSessionFactory().openSession();
        tx=null;
        try{
            tx=session.beginTransaction();
            cr=session.createCriteria(PrivateMessage.class);
            List<PrivateMessage> messages=cr.list();
            
            for(int i=0;i<messages.size(); i++){
                if(messages.get(i).getReceiver()==id){
                    temp.add(messages.get(i));
                }
            }
            tx.commit();
        }catch(Exception ex){
            System.out.print(ex);
        }finally{
            session.close();
        }
        return temp;
    }
    
    
}
