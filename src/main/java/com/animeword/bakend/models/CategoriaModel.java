package com.animeword.bakend.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity 
@Table (name = "Categoria")
@Builder 
@Data  
@NoArgsConstructor 
@AllArgsConstructor 
public class CategoriaModel {
    @Id
    @GeneratedValue (strategy =GenerationType.IDENTITY)
    private  Long id;
    @Column (name = "categoria",unique = true, nullable = false)
    private String nombre;
    
}
