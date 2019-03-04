package cl.gestion.proyectos.domain.services.user.impl;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.UserStateEntity;
import cl.gestion.proyectos.domain.repository.user.UserStateRepository;
import cl.gestion.proyectos.domain.services.base.impl.BaseServiceImpl;
import cl.gestion.proyectos.domain.services.user.UserStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserStateServiceImpl extends BaseServiceImpl<UserStateEntity, String, BaseRequest> implements UserStateService {
    public UserStateServiceImpl(final UserStateRepository userStateRepository) {
        super(userStateRepository, UserStateEntity.class);
    }

}
