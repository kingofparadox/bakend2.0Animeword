package com.animeword.bakend.controls;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.animeword.bakend.services.ProductoService;
import com.animeword.bakend.shared.saredproductos.DtoCategtegoria;
import com.animeword.bakend.shared.saredproductos.DtoProductoEntrada;

@RestController
@RequestMapping("/api/v1")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    // ==================== ENDPOINTS DE CATEGORÍAS ====================

    @PostMapping("/categorias")
    public ResponseEntity<String> crearCategoria(@RequestBody DtoCategtegoria dto) {
        String mensaje = productoService.createCategori(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
    }

    @GetMapping("/categorias")
    public ResponseEntity<List<DtoCategtegoria>> listarCategorias() {
        return ResponseEntity.ok(productoService.listarC());
    }

    @GetMapping("/categorias/{nombre}")
    public ResponseEntity<DtoCategtegoria> buscarCategoria(@PathVariable String nombre) {
        return ResponseEntity.ok(productoService.buscar(nombre));
    }

    @PutMapping("/categorias/{viejo}")
    public ResponseEntity<DtoCategtegoria> actualizarCategoria(
            @PathVariable String viejo, 
            @RequestBody DtoCategtegoria dto) {
        return ResponseEntity.ok(productoService.Update(viejo, dto));
    }

    @DeleteMapping("/categorias/{nombre}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable String nombre) {
        String mensaje = productoService.delete(nombre);
        return ResponseEntity.ok(mensaje);
    }

    // ==================== ENDPOINTS DE PRODUCTOS ====================

    @PostMapping("/productos")
    public ResponseEntity<DtoProductoEntrada> crearProducto(@RequestBody DtoProductoEntrada dto) {
        DtoProductoEntrada nuevoProducto = productoService.createProducto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<DtoProductoEntrada>> listarProductos() {
        return ResponseEntity.ok(productoService.listar());
    }

    @GetMapping("/productos/categoria/{categoria}")
    public ResponseEntity<List<DtoProductoEntrada>> listarPorCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(productoService.listaPorCategoria(categoria));
    }

    @PutMapping("/productos")
    public ResponseEntity<DtoProductoEntrada> actualizarProducto(@RequestBody DtoProductoEntrada dto) {
        return ResponseEntity.ok(productoService.update(dto));
    }

    @DeleteMapping("/productos")
    public ResponseEntity<String> eliminarProducto(@RequestBody DtoProductoEntrada dto) {
        String mensaje = productoService.delete(dto);
        return ResponseEntity.ok(mensaje);
    }
}