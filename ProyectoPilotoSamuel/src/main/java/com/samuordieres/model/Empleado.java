package com.samuordieres.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="empleados")
public class Empleado {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
 
    @Size(min=3, max=50)
    @Column(name = "nombre", nullable = false)
    private String nombre;
 
    @NotNull
    @DateTimeFormat(pattern="dd/MM/yyyy") 
    @Column(name = "fecha_alta", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    private LocalDate fechaAlta;
 
    @NotNull
    @Digits(integer=8, fraction=2)
    @Column(name = "salario", nullable = false)
    private BigDecimal salario;
     
    @NotEmpty
    @Column(name = "dni", unique=true, nullable = false)
    private String dni;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
 
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }
 
    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
 
    public BigDecimal getSalario() {
        return salario;
    }
 
    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }
 
    public String getDni() {
        return dni;
    }
 
    public void setDni(String dni) {
        this.dni = dni;
    }
 
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((dni == null) ? 0 : dni.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Empleado))
            return false;
        Empleado other = (Empleado) obj;
        if (id != other.id)
            return false;
        if (dni == null) {
            if (other.dni != null)
                return false;
        } else if (!dni.equals(other.dni))
            return false;
        return true;
    }
 
    @Override
    public String toString() {
        return "Empleado [id=" + id + ", nombre=" + nombre + ", fechaAlta="
                + fechaAlta + ", salario=" + salario + ", dni=" + dni + "]";
    }

}

