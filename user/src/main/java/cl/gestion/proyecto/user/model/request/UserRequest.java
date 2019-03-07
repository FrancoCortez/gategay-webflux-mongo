package cl.gestion.proyecto.user.model.request;

import lombok.*;

import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private String username;
    private String mail;
    private String password;

    //Relation
    private String idTypeUser;
    private String idUserState;
    private List<String> idsRoles;

    private boolean isBloke;
    private boolean isInactive;
    private boolean isEnabled;
}
