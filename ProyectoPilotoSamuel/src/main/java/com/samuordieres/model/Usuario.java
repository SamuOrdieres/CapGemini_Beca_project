package com.samuordieres.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="usuarios")
public class Usuario {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @NotEmpty
    @Column(name = "email", unique=true, nullable = false)
    private String login;
    
    @Size(min=3, max=50)
    @Column(name = "password", nullable = false)
    private String password;
 
    @NotNull
    @Digits(integer=1,fraction=0)
    @Column(name = "nivel", nullable = false)
    private Integer nivel;
     

 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public Integer getNivel() {
        return nivel;
    }
 
    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }
 
    public String getLogin() {
        return login;
    }
 
    public void setLogin(String login) {
        this.login = login;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Usuario))
            return false;
        Usuario other = (Usuario) obj;
        if (id != other.id)
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Empleado [id=" + id + ", login=" + login + ", password=" + password + ", nivel=" + nivel + "]";
    }

}

