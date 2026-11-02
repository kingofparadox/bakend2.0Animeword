package com.animeword.bakend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "usuarios")
@Builder 
@Data 
@AllArgsConstructor 
@NoArgsConstructor 

public class UserModel {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    
    private Long id;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "rol_id", nullable = false)
    private  RolModel rol;

    @Column (name = "nombre", nullable = false)
    private  String nombre;
    @Column (name = "apellido", nullable = false)
    private  String apellido;
    @Column (name = "rut", nullable = false, unique = true)
    private  String rut;
    @Column (name = "correo", nullable = false, unique = true)
    private  String correo;
    @Column (name = "password", nullable = false, unique = true)
    private  String haspassword;

}
