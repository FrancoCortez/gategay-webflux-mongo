package cl.gestion.proyectos.domain.model.base;

import lombok.*;

import java.io.Serializable;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseRequest implements Serializable {

    private String name;
    private String description;
}
