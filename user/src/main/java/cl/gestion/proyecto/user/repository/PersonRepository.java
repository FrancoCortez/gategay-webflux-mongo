package cl.gestion.proyecto.user.repository;

import cl.gestion.proyecto.user.model.entity.PersonEntity;
import cl.gestion.proyecto.user.model.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<PersonEntity, String> {

    public Mono<UserEntity> findByRut(String rut);
}
