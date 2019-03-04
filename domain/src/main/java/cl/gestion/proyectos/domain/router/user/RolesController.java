package cl.gestion.proyectos.domain.router.user;

import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.model.user.RolesEntity;
import cl.gestion.proyectos.domain.router.base.BaseRestController;
import cl.gestion.proyectos.domain.services.user.RoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = BaseRestController.basePath + "role")
@Api(value = "Role service", description = "Role operation services")
public class RolesController extends BaseRestController<RolesEntity, String, BaseRequest> {
    public RolesController(final RoleService roleService) {
        super(roleService);
    }

}
