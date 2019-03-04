package cl.gestion.proyectos.domain.model.user;

import cl.gestion.proyectos.domain.model.base.BaseEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
// @NoArgsConstructor
@Builder
@Document(collection = "userState")
public class UserStateEntity extends BaseEntity {


}
