package cl.gestion.proyectos.domain.model.base;

import lombok.*;
import org.springframework.data.annotation.*;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("DefaultAnnotationParam")
@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuditingEntity implements Serializable {

    @CreatedBy
    private String createdBy;
    @CreatedDate
    private Date createdDate;
    @LastModifiedBy
    private String lastModifiedBy;
    @LastModifiedDate
    private Date lastModifiedDate;
    @Version
    private Long version;
    private Boolean delete;

}
