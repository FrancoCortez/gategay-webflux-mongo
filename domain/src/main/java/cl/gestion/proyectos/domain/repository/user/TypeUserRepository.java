package cl.gestion.proyectos.domain.repository.user;

import cl.gestion.proyectos.domain.model.user.TypeUserEntity;
import cl.gestion.proyectos.domain.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeUserRepository extends BaseRepository<TypeUserEntity, String> {

}
