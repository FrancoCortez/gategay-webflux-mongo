package cl.gestion.proyecto.user.service.impl;

import cl.gestion.proyecto.user.model.entity.UserEntity;
import cl.gestion.proyecto.user.model.request.UserRequest;
import cl.gestion.proyecto.user.repository.UserRepository;
import cl.gestion.proyecto.user.service.UserService;
import cl.gestion.proyecto.user.service.base.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class UserServiceImpl extends BaseServiceImpl<UserEntity, String> implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<?> create(final UserRequest request) {
        try {


            UserEntity entity = new UserEntity();
            BeanUtils.copyProperties(request, entity);
            entity.set_id(null);
            entity.setAuditing(null);
            entity.setAuditing(this.generateAuditingEntity(entity.getAuditing()));
            return this.userRepository.insert(entity);
        } catch (Exception ex) {
            log.error("Error {}", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }
}
