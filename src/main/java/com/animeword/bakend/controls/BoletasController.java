package com.animeword.bakend.controls;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.animeword.bakend.services.BoletasService;
import com.animeword.bakend.shared.saredproductos.DtoProductoPedido;
import com.animeword.bakend.shared.sharedBoletas.DtoBoletas;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletasController {

    private final BoletasService boletasService;

    public BoletasController(BoletasService boletasService) {
        this.boletasService = boletasService;
    }

    @PostMapping("/{rut}")
    public ResponseEntity<DtoBoletas> crearBoleta(
            @PathVariable String rut,
            @Valid @RequestBody List<DtoProductoPedido> productos) {
        DtoBoletas nuevaBoleta = boletasService.create(rut, productos);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaBoleta);
    }

    @GetMapping
    public ResponseEntity<List<DtoBoletas>> listarTodasLasBoletas() {
        return ResponseEntity.ok(boletasService.boletas());
    }

    @GetMapping("/usuario/{rut}")
    public ResponseEntity<List<DtoBoletas>> listarBoletasPorUsuario(@PathVariable String rut) {
        return ResponseEntity.ok(boletasService.boletasUser(rut));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarBoleta(@PathVariable Long id) {
        return ResponseEntity.ok(boletasService.Deleteboleta(id));
    }
}