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
@Table(name="roles") 
@Data 
@NoArgsConstructor 
@AllArgsConstructor 
@Builder 

public class RolModel {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
 ///data Extraño la data clas de cotlin 
    private Long id;
    @Column (unique = true, nullable = false, length = 60)
    private String nombre;
}
