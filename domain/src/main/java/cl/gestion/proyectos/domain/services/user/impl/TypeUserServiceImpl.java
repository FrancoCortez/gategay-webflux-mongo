package cl.gestion.proyectos.domain.services.user.impl;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.TypeUserEntity;
import cl.gestion.proyectos.domain.repository.user.TypeUserRepository;
import cl.gestion.proyectos.domain.services.base.impl.BaseServiceImpl;
import cl.gestion.proyectos.domain.services.user.TypeUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TypeUserServiceImpl extends BaseServiceImpl<TypeUserEntity, String, BaseRequest> implements TypeUserService {
    // private final RolesRepository rolesRepository;
    public TypeUserServiceImpl(final TypeUserRepository typeUserRepository) {
        super(typeUserRepository, TypeUserEntity.class);
        // this.rolesRepository = rolesRepository;
    }

}
