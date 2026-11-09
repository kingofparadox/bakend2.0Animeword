package com.animeword.bakend.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.animeword.bakend.error.CredencialesInvalidasException;
import com.animeword.bakend.error.errorNoencotrado;
import com.animeword.bakend.models.RolModel;
import com.animeword.bakend.models.UserModel;
import com.animeword.bakend.repo.RolRepo;
import com.animeword.bakend.repo.UserRepo;
import com.animeword.bakend.shared.shareduserrol.DtoLogin;
import com.animeword.bakend.shared.shareduserrol.DtoRol;
import com.animeword.bakend.shared.shareduserrol.DtoUserEntrada;
import com.animeword.bakend.shared.shareduserrol.DtoUserSalida;
import com.animeword.bakend.shared.shareduserrol.MaperUser;

@Service 
public class UserService {
    
    private  final UserRepo repoUser;
    private  final PasswordEncoder encritador;
    private  final RolRepo repoRol;

    public UserService (UserRepo userRepo,
        PasswordEncoder encrptador, RolRepo rolRepo) {

        this.repoUser= userRepo;
        this.encritador =encrptador;
        this.repoRol = rolRepo;
    }

    public List<DtoUserSalida> usuarios(){
        List<UserModel> listaE =repoUser.findAll();
        if(listaE.size()== 0){
            throw new errorNoencotrado("error no existen usuarios registrados");
        }
        List<DtoUserSalida>listaDto= new ArrayList<>();
        for (UserModel entity : listaE) {
            listaDto.add(MaperUser.modelTodtoSalida(entity));
        }
        return listaDto;
    }
    public String  createuser(DtoUserEntrada dto){
        String paswordString = encritador.encode(dto.password());
        RolModel rol =repoRol.findByNombre(dto.rol()).orElseThrow(()->new 
        errorNoencotrado("rol "+dto.rol()+" no existe"));
        repoUser.save(MaperUser.insertUser(dto, paswordString, rol)) ;
        return ("user creado con exito");
    }

    public  DtoUserSalida longin(DtoLogin dto){
        UserModel user =repoUser.findByCorreo(dto.correo()).orElseThrow(()-> new
        errorNoencotrado("correo no encontrado"));
        if(encritador.matches( dto.password(),user.getHaspassword())){
            return new DtoUserSalida(user.getNombre()+" "+user.getApellido()
            , user.getRut(), user.getCorreo(), user.getRol().getNombre());
        }else{
            throw new CredencialesInvalidasException("Contraseña incorrecta");
        }
    }
    
    public  DtoUserSalida updateUser (DtoLogin dtol,DtoUserEntrada dtoE){
        Long id = repoUser.findByRut(longin(dtol).rut()).get().getId();
        if (id == null){
            throw new errorNoencotrado("usuario no encintrado");
        }
        String paswordString = encritador.encode(dtoE.password());
        RolModel rol =repoRol.findByNombre(dtoE.rol()).orElseThrow(()->new 
        errorNoencotrado("rol "+dtoE.rol()+" no existe"));
        
        return MaperUser.modelTodtoSalida(repoUser.save(MaperUser.upadteUser(id, dtoE, paswordString, rol)));
    }

    public  String deleteUser(DtoUserEntrada entrada){
        Long id = repoUser.findByRut(entrada.rut()).get().getId();
        if (id == null){
            throw new errorNoencotrado("user no encontrado");
        }
        repoUser.deleteById(id);
        return "Usuario eliminado con éxito";
    }

    // METODOS DE  ROLES 

    public  List<DtoRol> listaRoles (){
        List<RolModel> Lista=repoRol.findAll();
        if (Lista.size()== 0){
            throw new errorNoencotrado("error no existen roles ");
        }
        List <DtoRol> lista= new ArrayList<>();
        for (RolModel rol : Lista) {
            lista.add(MaperUser.salidaRol(rol));
            
        }
        return  lista;
    }

    public String crearRol(DtoRol nuevoRol) {
        RolModel nombreRol=  repoRol.save(MaperUser.createRol(nuevoRol));
        return "Rol '" + nombreRol.getNombre() + "' creado con éxito";
    }

    public String update(DtoRol nuevoRol) {
        Long id= repoRol.findByNombre(nuevoRol.nombre()).get().getId();
        if (id == null){
            throw new errorNoencotrado("rol no encontrado");
        }
        RolModel nombreRol=  repoRol.save(MaperUser.createRol(nuevoRol));
        return "Rol '" + nombreRol.getNombre() + "'actualizado con éxito";
    }

    public String delate(DtoRol nuevoRol) {
        Long id= repoRol.findByNombre(nuevoRol.nombre()).get().getId();
        if (id == null){
            throw new errorNoencotrado("rol no encontrado");
        }
        repoRol.deleteById(id);
        return "Rol '" + nuevoRol.nombre() + "'eliminado con éxito";
    }
}
