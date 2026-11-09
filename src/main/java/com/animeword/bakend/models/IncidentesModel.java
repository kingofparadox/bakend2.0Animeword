package com.animeword.bakend.models;

import java.time.LocalDateTime;

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
@Table(name = "incidentes")
@Builder 
@Data @AllArgsConstructor @NoArgsConstructor 
public class IncidentesModel {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long id;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "userID", nullable = false)
    private UserModel  user;
    @Column (name = "descripcion",nullable = false)
    private String incidenteDescripcion;
    @Column (name = "FInicio",nullable = false)
    private LocalDateTime fechaInicio;
    @Column (name="resuelto",nullable = false)
    private boolean ressuelto;
    @Column (name = "FCierre",nullable = true)
    private LocalDateTime fechaCierre;
    @Column(name = "actualizaciones", columnDefinition = "jsonb", nullable = false)
    private String actualiazacionJson;
}
