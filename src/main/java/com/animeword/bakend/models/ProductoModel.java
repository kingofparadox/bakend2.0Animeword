package com.animeword.bakend.models;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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
@Table (name="producto") 
@Data 
@NoArgsConstructor  
@AllArgsConstructor 
@Builder 

public class ProductoModel {

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
 ///data Extraño la data clas de cotlin 
    private Long id;
    @Column  (name = "nombre", nullable = false)
    private  String nombre;
    @Column(name="codigoBarras", nullable = false, unique=true)
    private  String codigoBarras;
    @Column(name= "precio",nullable = false)
    private Integer precio;
    @Column(name= "stock", nullable = false)
    private  Integer stock;
    @Column(name="oferta",nullable = false)
    private Integer oferta;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "imagen_paths", columnDefinition = "jsonb", nullable = false)
    private List<String> imagenPaths;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "categoria", nullable = false)
    private  CategoriaModel categoria;
}
