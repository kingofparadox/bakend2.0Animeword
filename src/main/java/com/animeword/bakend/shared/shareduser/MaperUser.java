package com.animeword.bakend.shared.shareduser;

import com.animeword.bakend.models.UserModel;

public class MaperUser {

    public static DtoUserSalida  modelTodto (UserModel entidad){
        return  new DtoUserSalida(
            entidad.getNombre()+" "+ entidad.getApellido(),
            entidad.getRut(),
            entidad.getCorreo(),
            entidad.getRol().getNombre()
        );

    }
    public static 
}
