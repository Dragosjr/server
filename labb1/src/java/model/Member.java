
package model;



import javax.persistence.Table;

 @Table(name = "t_member") 
public class Member {
    
    private int id;
    private String name;
    private String password;
    
    public Member(String name, String password){
        this.name=name;
        this.password=password;
    }

    public Member(){
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
