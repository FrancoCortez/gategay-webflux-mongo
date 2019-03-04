package cl.gestion.proyectos.domain.repository.base;

import cl.gestion.proyectos.domain.model.base.BaseEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface BaseRepository<T extends BaseEntity, ID extends String> extends ReactiveMongoRepository<T, ID> {

    public Mono<T> findByName(String name);
}
