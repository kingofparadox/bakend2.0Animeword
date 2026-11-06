package com.animeword.bakend.shared.shareduserrol;

import com.animeword.bakend.models.RolModel;
import com.animeword.bakend.models.UserModel;

public class MaperUser {

    public static DtoUserSalida  modelTodtoSalida (UserModel entidad){
        return  new DtoUserSalida(
            entidad.getNombre()+" "+ entidad.getApellido(),
            entidad.getRut(),
            entidad.getCorreo(),
            entidad.getRol().getNombre()
        );

    }
    public static  UserModel  insertUser(DtoUserEntrada user, String haspass, RolModel rol){
        return  new UserModel(
            null,
            rol,
            user.nombre(),
            user.apellido(),
            user.rut(),
            user.correo(),
            haspass            
        );
    } 

    public static  UserModel  upadteUser(Long id, DtoUserEntrada user, String haspass, RolModel rol){
        return  new UserModel(
            id,
            rol,
            user.nombre(),
            user.apellido(),
            user.rut(),
            user.correo(),
            haspass            
        );
    } 

    public static  DtoRol salidaRol(RolModel rol) {
        return  new DtoRol(rol.getNombre());
    }
    public static  RolModel updateRol(Long idS,DtoRol rol) {
        return  new RolModel(idS, rol.nombre());
    }
    
    public static  RolModel createRol(DtoRol rol) {
        return  new RolModel(null, rol.nombre());
    }
    

}
