/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.PrivateMessageDAO;
import java.util.Collections;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import model.PrivateMessage;

/**
 *
 * @author Dennis
 */

@ManagedBean(name="privatebean")
@SessionScoped
public class PrivateMessageBean {
    private int id;
    private int sender;
    private int receiver;
    private String message;
    private String statusVer;
    private PrivateMessageDAO db;
    private PrivateMessage pm;
    private List<PrivateMessage> privatemessages;
    
    @ManagedProperty(value="#{bean}") 
    MemberBean memberbean;
    
    public PrivateMessageBean(){
        db=new PrivateMessageDAO();
        pm=new PrivateMessage();
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
    
    public String getStatusVer(){
        return statusVer;
    }
    
    public void setStatusVer(String statusVer){
        this.statusVer = statusVer;
    }

    public PrivateMessageDAO getDb() {
        return db;
    }

    public void setDb(PrivateMessageDAO db) {
        this.db = db;
    }

    public PrivateMessage getPm() {
        return pm;
    }

    public void setPm(PrivateMessage pm) {
        this.pm = pm;
    }

    public List<PrivateMessage> getPrivatemessages() {
        return privatemessages;
    }

    public void setPrivatemessages(List<PrivateMessage> privatemessages) {
        this.privatemessages = privatemessages;
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
        
        pm.setSender(sender);
        pm.setReceiver(receiver);
        pm.setMessage(message);
        
        db.add(pm);
        message="";
        setStatusVer("PM skickat!");
    }
    
    public void list(){
        int temp=memberbean.getReceiver().getId();
        privatemessages=db.List(temp);
        
         for(int i=0; i<privatemessages.size();i++){
            for(int j=0; j<memberbean.listMembers().size();j++){
                  if(privatemessages.get(i).getSender()==memberbean.listMembers().get(j).getId()){
			privatemessages.get(i).setName(memberbean.listMembers().get(j).getName());
                   }
            }
        }
         
        Collections.reverse(privatemessages);
    }

}
