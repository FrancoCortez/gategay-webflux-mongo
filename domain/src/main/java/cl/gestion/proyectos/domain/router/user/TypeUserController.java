package cl.gestion.proyectos.domain.router.user;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.TypeUserEntity;
import cl.gestion.proyectos.domain.router.base.BaseRestController;
import cl.gestion.proyectos.domain.services.user.TypeUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = BaseRestController.basePath + "type-user")
@Api(value = "Role service", description = "Type user operation services")
public class TypeUserController extends BaseRestController<TypeUserEntity, String, BaseRequest> {
    public TypeUserController(final TypeUserService typeUserService) {
        super(typeUserService);
    }

}
