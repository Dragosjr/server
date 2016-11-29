/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.Member;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

/**
 *
 * @author Dennis
 */
public class UserDAO {
    
    private Session session;
    private Transaction tx;
    private Criteria cr;
    
    public void addUser(Member user){
        try{
            session= HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
           
        }catch(Exception ex){
            System.out.println("add user fail: "+ ex);
        }finally{
            session.close();
        }
    }
    
    public Member verUser(Member sender, int id){
        session= HibernateUtil.getSessionFactory().openSession();
        try{
            tx=session.beginTransaction();
            cr=session.createCriteria(Member.class);
            List<Member> users= cr.list();
            
            for(int index=0; index<users.size(); index++){
                if(sender.getName().equals(users.get(index).getName()) && sender.getPassword().equals(users.get(index).getPassword())){
                    return users.get(index);
		}
            }
            
            tx.commit();
   
        }catch(Exception ex){
            System.out.print(ex);
        }finally{
            session.close();
        }
        return null;
    }
    
    public List<Member> searchUser(String searchname){
        List<Member> tempo= new ArrayList<Member>();
        session=HibernateUtil.getSessionFactory().openSession();
        tx=null;
        try{
            tx=session.beginTransaction();
            cr=session.createCriteria(Member.class);
            List<Member> users = cr.list();
            for(int i =0;i<users.size();i++){
                if(users.get(i).getName().contains(searchname)){
                    tempo.add(users.get(i));
                }
            }
            
        }catch(Exception ex){
            System.out.print(ex);
        }finally{
            session.close();
        }
        return tempo;
    }
    
    public List<Member> listMembers(){
        session=HibernateUtil.getSessionFactory().openSession();
        tx=null;
        try{
            tx=session.beginTransaction();
            cr=session.createCriteria(Member.class);
            List<Member> members=cr.list();
            
            tx.commit();
            
            return members;
        }catch(Exception ex){
           System.out.print(ex);
        }finally{
            session.close();
        }
        return null;
    }
    
    
    
}

