package com.model;
/**
 *
 * @author cj
 */
public class Register {
    private int register_id;
    private String nickname;
    private int person_id;
    private int user_id;

    public int getRegister_id() {
        return register_id;
    }
    
    public void setRegister_id(int register_id) {
        this.register_id = register_id;
    }
    
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getPerson_id() {
        return person_id;
    }

    public void setPerson_id(int person_id) {
        this.person_id = person_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
