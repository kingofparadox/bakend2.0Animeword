package com.animeword.bakend.controls;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.animeword.bakend.services.BlogService;
import com.animeword.bakend.shared.sharedBlog.DtoBlogEntrada;
import com.animeword.bakend.shared.sharedBlog.DtoBlogSalida;
import com.animeword.bakend.shared.sharedBlog.DtoContendio;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // Record auxiliar para recibir ambos datos en un solo JSON desde el frontend/Postman
    public record BlogRequest(
        @Valid DtoBlogEntrada blog,
        @Valid List<DtoContendio> contenido
    ) {}

    @GetMapping
    public ResponseEntity<List<DtoBlogSalida>> listarBlogs() {
        return ResponseEntity.ok(blogService.Blog());
    }

    @PostMapping("/{rut}")
    public ResponseEntity<DtoBlogSalida> crearBlog(
            @PathVariable String rut,
            @Valid @RequestBody BlogRequest request) {
        DtoBlogSalida creado = blogService.create(request.blog(), rut, request.contenido());
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping
    public ResponseEntity<DtoBlogSalida> actualizarBlog(@Valid @RequestBody BlogRequest request) {
        DtoBlogSalida actualizado = blogService.update(request.blog(), request.contenido());
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{titulo}")
    public ResponseEntity<String> eliminarBlog(@PathVariable String titulo) {
        return ResponseEntity.ok(blogService.delete(titulo));
    }
}