package cl.gestion.proyectos.domain.repository.user;

import cl.gestion.proyectos.domain.model.user.UserStateEntity;
import cl.gestion.proyectos.domain.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStateRepository extends BaseRepository<UserStateEntity, String> {

}
