package cl.gestion.proyecto.user.utils.model;

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
