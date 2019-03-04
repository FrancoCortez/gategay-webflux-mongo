package cl.gestion.proyectos.domain.services.user.impl;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.RolesEntity;
import cl.gestion.proyectos.domain.repository.user.RolesRepository;
import cl.gestion.proyectos.domain.services.base.impl.BaseServiceImpl;
import cl.gestion.proyectos.domain.services.user.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleServiceImpl extends BaseServiceImpl<RolesEntity, String, BaseRequest> implements RoleService {
    // private final RolesRepository rolesRepository;
    public RoleServiceImpl(final RolesRepository rolesRepository) {
        super(rolesRepository, RolesEntity.class);
        // this.rolesRepository = rolesRepository;
    }

}
