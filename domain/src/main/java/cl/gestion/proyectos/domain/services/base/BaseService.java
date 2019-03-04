package cl.gestion.proyectos.domain.services.base;

import cl.gestion.proyectos.domain.model.base.BaseEntity;
import cl.gestion.proyectos.domain.model.base.BaseRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.List;

public interface BaseService<T extends BaseEntity, ID extends String, R extends BaseRequest> {

    Mono<?> create(R request);

    Mono<?> edit(R request, ID id);

    Mono<?> deleteById(final ID id);

    Flux<?> findAll(String id, String name, String description, Date createDate);

    Mono<?> findById(ID id);

    Flux<?> findAllById(List<ID> ids);

    Mono<?> findByName(final String name);
}
