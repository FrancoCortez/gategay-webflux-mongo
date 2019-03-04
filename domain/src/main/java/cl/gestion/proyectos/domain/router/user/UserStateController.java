package cl.gestion.proyectos.domain.router.user;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.UserStateEntity;
import cl.gestion.proyectos.domain.router.base.BaseRestController;
import cl.gestion.proyectos.domain.services.user.UserStateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = BaseRestController.basePath + "user-state")
@Api(value = "User state service", description = "User state operation services")
public class UserStateController extends BaseRestController<UserStateEntity, String, BaseRequest> {
    public UserStateController(final UserStateService userStateService) {
        super(userStateService);
    }

}
