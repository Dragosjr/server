/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PublicMessageDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.PublicMessage;



@ManagedBean(name="msgbean")
@SessionScoped
public class PublicMessageBean {
    private int id;
    private int sender;
    private int receiver;
    private String message;
    
    private PublicMessage publicmessage;
    private PublicMessageDAO db;
    private List<PublicMessage> publicmessages;

    
    @ManagedProperty(value="#{bean}") 
    MemberBean memberbean;
    
    public PublicMessageBean(){
        publicmessage=new PublicMessage();
        db= new PublicMessageDAO();
       
    }
    
    @PostConstruct
    public void init(){
	this.list();
    }

    public List<PublicMessage> getPublicmessages() {
        return publicmessages;
    }

    public void setPublicmessages(List<PublicMessage> publicmessages) {
        this.publicmessages = publicmessages;
    }
    
    
    public PublicMessageDAO getDb() {
        return db;
    }

    public void setDb(PublicMessageDAO db) {
        this.db = db;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getReceiver() {
        return receiver;
    }

    public void setReceiver(int receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PublicMessage getPublicmessage() {
        return publicmessage;
    }

    public void setPublicmessage(PublicMessage publicmessage) {
        this.publicmessage = publicmessage;
    }

    public MemberBean getMemberbean() {
        return memberbean;
    }

    public void setMemberbean(MemberBean memberbean) {
        this.memberbean = memberbean;
    }
    
    public void add(){
       sender=memberbean.getSender().getId();
       receiver=memberbean.getReceiver().getId();
        
       publicmessage.setSender(sender);
       publicmessage.setReceiver(receiver);
       publicmessage.setMessage(message);
       
       db.add(publicmessage);
       
    
    
    }
    
    public void list(){
        int temp=memberbean.getReceiver().getId();
        publicmessages=db.List(temp);
        
		
        for(int i=0; i<publicmessages.size();i++){
            for(int j=0; j<memberbean.listMembers().size();j++){
                  if(publicmessages.get(i).getSender()==memberbean.listMembers().get(j).getId()){
			publicmessages.get(i).setName(memberbean.listMembers().get(j).getName());
                   }
            }
        }
        
        Collections.reverse(publicmessages);

    }
    
}
