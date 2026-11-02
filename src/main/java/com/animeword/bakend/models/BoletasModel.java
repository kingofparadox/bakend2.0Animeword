package com.animeword.bakend.models;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name ="boletas")
@Builder 
@Data 
@AllArgsConstructor 
@NoArgsConstructor  
public class BoletasModel {
    
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;   
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "userID", nullable = false)
    private  UserModel user;
    @Column (name = "total",nullable = false)
    private Integer total;
    @Column (name = "fecha",nullable = false)
    private  LocalDateTime fecha;
    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
        name = "boleta_producto", // Tabla intermedia que JPA creará solita en Neon DB
        joinColumns = @JoinColumn(name = "boleta_id"),
        inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    private  List<ProductoModel> productos;
    

}
