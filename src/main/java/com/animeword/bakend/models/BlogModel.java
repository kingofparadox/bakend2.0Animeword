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
@Table (name = "blog")
@Builder 
@Data @AllArgsConstructor @NoArgsConstructor 
public class BlogModel {
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private  Long Id;
    @Column (name = "titulo", nullable = false, unique = true)
    private String titulo;
    @Column (name = "fecha", nullable = false)
    private LocalDateTime fecha;
    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn (name = "userID", nullable = false)
    private  UserModel autor;
    @Column(name = "contenido_json", columnDefinition = "jsonb", nullable = false)
    private String contenidoJson;

}
