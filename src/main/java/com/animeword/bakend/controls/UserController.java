package com.animeword.bakend.controls;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.animeword.bakend.services.UserService;
import com.animeword.bakend.shared.shareduserrol.DtoLogin;
import com.animeword.bakend.shared.shareduserrol.DtoRol;
import com.animeword.bakend.shared.shareduserrol.DtoUserEntrada;
import com.animeword.bakend.shared.shareduserrol.DtoUserSalida;


    @RestController
@RequestMapping("/api/v1/usuarios") // Ruta base para los endpoints
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // ==================== ENDPOINTS DE USUARIOS ====================

    // 1. Listar todos los usuarios (GET)
    @GetMapping("/users")
    public ResponseEntity<List<DtoUserSalida>> obtenerUsuarios() {
        List<DtoUserSalida> usuarios = userService.usuarios();
        return ResponseEntity.ok(usuarios);
    }

    // 2. Registrar usuario (POST)
    @PostMapping("/users/register")
    public ResponseEntity<String> registrarUsuario(@RequestBody DtoUserEntrada dto) {
        String respuesta = userService.createuser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // 3. Login de usuario (POST)
    @PostMapping("/users/login")
    public ResponseEntity<DtoUserSalida> loginUsuario(@RequestBody DtoLogin dto) {
        DtoUserSalida salida = userService.longin(dto);
        return ResponseEntity.ok(salida);
    }

    // 4. Actualizar usuario (PUT) - Credenciales por parámetros y nuevos datos en el body
    @PutMapping("/users/update")
    public ResponseEntity<DtoUserSalida> actualizarUsuario(
            @RequestParam String correo,
            @RequestParam String password,
            @RequestBody DtoUserEntrada dtoE) {
        DtoLogin dtol = new DtoLogin(correo, password);
        DtoUserSalida salida = userService.updateUser(dtol, dtoE);
        return ResponseEntity.ok(salida);
    }

    // 5. Eliminar usuario (DELETE)
    @DeleteMapping("/users/delete")
    public ResponseEntity<String> eliminarUsuario(@RequestBody DtoUserEntrada entrada) {
        String respuesta = userService.deleteUser(entrada);
        return ResponseEntity.ok(respuesta);
    }

    // ==================== ENDPOINTS DE ROLES ====================

    // 6. Listar roles (GET)
    @GetMapping("/roles")
    public ResponseEntity<List<DtoRol>> listarRoles() {
        List<DtoRol> roles = userService.listaRoles();
        return ResponseEntity.ok(roles);
    }

    // 7. Crear rol (POST)
    @PostMapping("/roles")
    public ResponseEntity<String> crearRol(@RequestBody DtoRol nuevoRol) {
        String respuesta = userService.crearRol(nuevoRol);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    // 8. Actualizar rol (PUT)
    @PutMapping("/roles")
    public ResponseEntity<String> actualizarRol(@RequestBody DtoRol nuevoRol) {
        String respuesta = userService.update(nuevoRol);
        return ResponseEntity.ok(respuesta);
    }

    // 9. Eliminar rol (DELETE)
    @DeleteMapping("/roles")
    public ResponseEntity<String> eliminarRol(@RequestBody DtoRol nuevoRol) {
        String respuesta = userService.delate(nuevoRol);
        return ResponseEntity.ok(respuesta);
    }
}

