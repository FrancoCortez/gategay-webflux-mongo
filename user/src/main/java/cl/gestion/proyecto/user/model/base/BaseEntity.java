package cl.gestion.proyecto.user.model.base;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity implements Serializable {
    @Id
    private String _id;
    private AuditingEntity auditing;
}
