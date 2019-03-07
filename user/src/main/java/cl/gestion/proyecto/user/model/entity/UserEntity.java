package cl.gestion.proyecto.user.model.entity;

import cl.gestion.proyecto.user.model.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity extends BaseEntity {

    @TextIndexed
    private String username;
    @TextIndexed
    private String mail;
    @JsonIgnore
    private String password;

    //Relation
    private String idTypeUser;
    private String idUserState;
    private List<String> idsRoles;

    private boolean isBloke;
    private boolean isInactive;
    private boolean isEnabled;
}
