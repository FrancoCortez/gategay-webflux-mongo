package cl.gestion.proyecto.user.model.entity;

import cl.gestion.proyecto.user.model.base.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "persons")
public class PersonEntity extends BaseEntity {
    @TextIndexed
    private String rut;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private List<Integer> cellphone;
    private Date dateOfBirth;

    //Relation
    private String sex;
    private String town;

}
