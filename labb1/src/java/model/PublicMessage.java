/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Table;

 @Table(name = "T_PUBLICMESSAGE") 
public class PublicMessage {
    private int id;
    private int sender;
    private int receiver;
    private String message;
    private String name;
    
    public PublicMessage(int sender, int receiver, String message){
        this.sender=sender;
        this.receiver=receiver;
        this.message=message;
    }

    public PublicMessage() {
       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    
}
