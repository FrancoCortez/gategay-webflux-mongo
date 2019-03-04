package cl.gestion.proyectos.domain.router.base;

import cl.gestion.proyectos.domain.model.base.BaseEntity;
import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.services.base.BaseService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

public class BaseRestController<T extends BaseEntity, ID extends String, R extends BaseRequest> {

    public final static String basePath = "/api/rest/v1/";
    private BaseService<T, ID, R> tidrBaseService;

    public BaseRestController(BaseService<T, ID, R> tidrBaseService) {
        this.tidrBaseService = tidrBaseService;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> create(@Valid @RequestBody final R request) {
        return this.tidrBaseService.create(request);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> edit(@PathVariable(value = "id", required = true) final ID id, @Valid @RequestBody final R request) {
        return this.tidrBaseService.edit(request, id);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> deleteById(@PathVariable(value = "id", required = true) final ID id) {
        return this.tidrBaseService.deleteById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<?> findAll(
            @RequestParam(value = "id", required = false) final String id,
            @RequestParam(value = "name", required = false) final String name,
            @RequestParam(value = "description", required = false) final String description,
            @RequestParam(value = "createDate", required = false) final Date createDate
    ) {
        return this.tidrBaseService.findAll(id, name, description, createDate);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> findById(@PathVariable(value = "id", required = true) final ID id) {
        return this.tidrBaseService.findById(id);
    }

    @GetMapping(value = "/find-all-ids", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<?> findAllById(@RequestParam(value = "ids", required = true) final List<ID> ids) {
        return this.tidrBaseService.findAllById(ids);
    }

    @GetMapping(value = "/find-by-name", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<?> findByName(@RequestParam(value = "name", required = true) final String name) {
        return this.tidrBaseService.findByName(name);
    }

}
