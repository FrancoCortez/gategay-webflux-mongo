package cl.gestion.proyectos.domain.utils.model;

import lombok.*;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    private String code;
    private Object msg;
}
