package bean;


import dao.UserDAO;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import model.Member;
import org.hibernate.Session;



@ManagedBean(name="bean")
@SessionScoped
public class MemberBean {
    
    private Member user;
    private Member sender;
    private Member receiver;
    private String statusVer;
    private String statusAdd;
    private String searchname;
    private Session session;
    private UserDAO userdb;
    private List<Member> users;
    private HtmlDataTable skickas;
    private String apa;


    public MemberBean(){
        userdb=new UserDAO();
        user=new Member();
        sender=new Member();
        receiver=new Member();
    }
    
    @PostConstruct
    public void init(){
	
    }

    public String getStatusAdd() {
        return statusAdd;
    }

    public void setStatusAdd(String statusAdd) {
        this.statusAdd = statusAdd;
    }    
    
    public Member getUser() {
        return user;
    }

    public void setUser(Member user) {
        this.user = user;
    }

    public List<Member> getUsers() {
        return users;
    }

    public void setUsers(List<Member> users) {
        this.users = users;
    }
    
    public HtmlDataTable getSkickas() {
        if(skickas==null){
            skickas=new HtmlDataTable();
        }
        return skickas;
    }

    public void setSkickas(HtmlDataTable skickas) {
        this.skickas = skickas;
    }
    
    public String getSearchname() {
        return searchname;
    }

    public void setSearchname(String searchname) {
        this.searchname = searchname;
    }

    public String getStatusVer() {
        return statusVer;
    }

    public void setStatusVer(String statusVer) {
        this.statusVer = statusVer;
    }
    public Member getSender() {
        return sender;
    }

    public void setSender(Member sender) {
        this.sender = sender;
    }

    public Member getReceiver() {
        return receiver;
    }

    public void setReceiver(Member receiver) {
        this.receiver = receiver;
    }

    public void addUser() throws Exception{
        if(!user.getPassword().isEmpty() && user.getName().isEmpty()){
            setStatusAdd("Fyll i namn");
        }else if(!user.getName().isEmpty() && user.getPassword().isEmpty()){
            setStatusAdd("Fyll i losenord");
        }else if(user.getName().isEmpty() && user.getPassword().isEmpty()){
            setStatusAdd("Fyll i namn och losenord");
        }else{
             userdb.addUser(this.user);
             setStatusAdd("Anvandare registrerad");
        }
    }
    
    public void searchUser(){
        users=userdb.searchUser(searchname);
        
        searchname=null;
    }
    
    public List<Member> listMembers(){
        return userdb.listMembers();
    }
    
    public void verUser() throws Exception{
        Member temp;
       if(!sender.getPassword().isEmpty() && sender.getName().isEmpty()){
           setStatusVer("Fyll i namn");
       }
       else if(!sender.getName().isEmpty() && sender.getPassword().isEmpty()){
           setStatusVer("Fyll i lösenord");
       }
       else if(sender.getName().isEmpty() && sender.getPassword().isEmpty()){
           setStatusVer("Fyll i namn och lösenord");
       }
       else if(!sender.getName().isEmpty() && !sender.getPassword().isEmpty()){
            temp= userdb.verUser(sender, 0);
            if(temp!=null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("anvandare.xhtml");
                this.sender=temp;
                this.receiver=this.sender;
            }
            else{
                setStatusVer("Login misslyckades");
            }
       }
      
    }
    
    public void redirect(){
        this.receiver=this.sender;
    }
    
    public void logOut() throws IOException{
         this.reset();
         FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
        
    }
    
    public void reset(){
        this.sender=new Member();
        this.receiver=new Member();
    }
    
    public void skicka(Member temp) {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("besokarprofil.xhtml");
            receiver=temp;
            users.clear();		 
        } catch (IOException ex) {
            System.out.println("Error skickas: "+ex);
        }
}
    
}

	
		