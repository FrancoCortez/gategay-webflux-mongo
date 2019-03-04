package cl.gestion.proyectos.domain.repository.user;

import cl.gestion.proyectos.domain.model.user.RolesEntity;
import cl.gestion.proyectos.domain.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends BaseRepository<RolesEntity, String> {

}
