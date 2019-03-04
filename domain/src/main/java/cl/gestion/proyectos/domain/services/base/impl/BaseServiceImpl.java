package cl.gestion.proyectos.domain.services.base.impl;

import cl.gestion.proyectos.domain.model.base.AuditingEntity;
import cl.gestion.proyectos.domain.model.base.BaseEntity;
import cl.gestion.proyectos.domain.model.base.BaseRequest;
import cl.gestion.proyectos.domain.repository.base.BaseRepository;
import cl.gestion.proyectos.domain.services.base.BaseService;
import cl.gestion.proyectos.domain.utils.model.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.beans.BeanUtils.copyProperties;

@SuppressWarnings("unused")
@Slf4j
public class BaseServiceImpl<T extends BaseEntity, ID extends String, R extends BaseRequest> implements BaseService<T, ID, R> {

    private final List<ValidationResult> error = new ArrayList<>();
    private BaseRepository<T, ID> tidBaseRepository;
    private T tClass;

    public BaseServiceImpl(BaseRepository<T, ID> tidBaseRepository, Class<T> classT) {
        this.tidBaseRepository = tidBaseRepository;
        try {
            this.tClass = classT.newInstance();
        } catch (Exception ex) {
            log.info("Error {}", ex.getMessage());
        }
    }

    public Mono<?> create(final R request) {
        try {
            this.initErrorList();
            if (request.getName().isEmpty()) {
                log.info("El nombre es obligatorio");
                this.getListError(ValidationResult.builder().code("1").msg("El nombre  es obligatorio").build());
            }
            if (!this.getListError().isEmpty()) {
                return this.monoResponseErrorList(this.getListError());
            }
            T entity = this.tClass;
            BeanUtils.copyProperties(request, entity);
            entity.setAuditing(this.generateAuditingEntity(entity.getAuditing()));
            return this.tidBaseRepository.insert(entity);
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }

    public Mono<?> edit(final R request, final ID id) {
        try {
            this.initErrorList();
            if (id.isEmpty()) {
                log.info("El id es obligatorio para realizar la edicion");
                this.getListError(ValidationResult.builder().code("1").msg("El id es obligatorio para realizar la edicion").build());
            }
            if (request.getName().isEmpty()) {
                log.info("El nombre es obligatorio para realizar la edicion");
                this.getListError(ValidationResult.builder().code("1").msg("El nombre del rol es obligatorio para realizar la edicion").build());
            }
            if (!this.getListError().isEmpty()) {
                return this.monoResponseErrorList(this.getListError());
            }
            T entity = this.tClass;
            copyProperties(request, entity);
            entity.setAuditing(this.generateAuditingEntity(entity.getAuditing()));
            entity.set_id(id);
            return this.tidBaseRepository.save(entity);
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }

    public Mono<?> deleteById(final ID id) {
        try {
            this.initErrorList();
            if (id.isEmpty()) {
                log.info("El id es obligatorio para realizar la eliminacion");
                this.getListError(ValidationResult.builder().code("1").msg("El id es obligatorio para realizar la eliminacion").build());
            }
            this.tidBaseRepository.deleteById(id).toFuture().join();
            return monoOkHandler("1", "El registro sido eliminado con exito");
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }

    public Flux<?> findAll(final String id, final String name, final String description, final Date createDate) {
        try {
            log.info("Entre al bucar ttodos");
            String _description = Optional.ofNullable(description).orElse("");
            String _name = Optional.ofNullable(name).orElse("");
            String _id = Optional.ofNullable(id).orElse("");
            Date _createDate = Optional.ofNullable(createDate).orElse(null);

            return this.tidBaseRepository.findAll()
                    .filter(fill -> (fill.get_id().equals(_id) || _id.isEmpty()))
                    .filter(fill -> (fill.getName().equalsIgnoreCase(_name) || _name.isEmpty()))
                    .filter(fill -> (fill.getDescription().toLowerCase().contains(_description.toLowerCase()) || _description.isEmpty()))
                    .filter(fill -> (fill.getAuditing().getCreatedDate().equals(_createDate) || _createDate == null))
                    ;
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.fluxErrorHandler(ex);
        }
    }

    public Mono<?> findById(final ID id) {
        try {
            return this.tidBaseRepository.findById(id);
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }

    public Flux<?> findAllById(final List<ID> ids) {
        try {
            if (ids.isEmpty()) {
                return this.fluxErrorHandler("01", "No se encontraron ids para la busqueda");
            }
            return this.tidBaseRepository.findAllById(ids);
        } catch (Exception ex) {
            log.error("Error : {} ", ex.getMessage());
            return this.fluxErrorHandler(ex);
        }
    }

    public Mono<?> findByName(final String name) {
        try {
            if (name.isEmpty()) {
                return this.monoErrorHandler("01", "El nombre no puede ser null");
            }
            return this.tidBaseRepository.findByName(name);
        } catch (Exception ex) {
            log.error("Error: {} ", ex.getMessage());
            return this.monoErrorHandler(ex);
        }
    }

    private void initErrorList() {
        this.error.clear();
    }

    private List<ValidationResult> getListError(ValidationResult data) {
        this.error.add(data);
        return this.error;
    }


    private List<ValidationResult> getListError() {
        return this.error;
    }


    private Mono<ResponseEntity<?>> monoResponseErrorList(List<ValidationResult> errors) {
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    private Mono<ResponseEntity<?>> monoErrorHandler(Exception ex) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code("1")
                .msg(ex.getMessage())
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    private Mono<ResponseEntity<?>> monoErrorHandler(String code, String msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    private Mono<ResponseEntity<?>> monoWarningHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        //return ServerResponse.status(409).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.status(409).body(errors));
    }

    private Mono<ResponseEntity<?>> monoOkHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(200).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.ok(errors));
    }

    private Mono<ResponseEntity<?>> monoOkObject(Object response) {
        // return ServerResponse.status(200).body(fromObject(response));
        return Mono.justOrEmpty(ResponseEntity.ok(response));
    }

    private Mono<ResponseEntity<?>> monoOkHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion Realizada con exito")
                .build());
        //return ServerResponse.status(200).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.ok(errors));
    }

    private Mono<ResponseEntity<?>> monoCreateHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(201).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.status(201).body(errors));
    }

    private Mono<ResponseEntity<?>> monoCreateHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion realizada con exito")
                .build());
        return Mono.justOrEmpty(ResponseEntity.status(201).body(errors));
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////


    private Flux<ResponseEntity<?>> fluxResponseErrorList(List<ValidationResult> errors) {
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    private Flux<ResponseEntity<?>> fluxErrorHandler(Exception ex) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code("1")
                .msg(ex.getMessage())
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    private Flux<ResponseEntity<?>> fluxErrorHandler(String code, String msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    private Flux<ResponseEntity<?>> fluxWarningHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        //return ServerResponse.status(409).body(fromObject(errors));
        return Flux.just(ResponseEntity.status(409).body(errors));
    }

    private Flux<ResponseEntity<?>> fluxOkHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(200).body(fromObject(errors));
        return Flux.just(ResponseEntity.ok(errors));
    }

    private Flux<ResponseEntity<?>> fluxOkObject(Object response) {
        // return ServerResponse.status(200).body(fromObject(response));
        return Flux.just(ResponseEntity.ok(response));
    }

    private Flux<ResponseEntity<?>> fluxOkHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion Realizada con exito")
                .build());
        //return ServerResponse.status(200).body(fromObject(errors));
        return Flux.just(ResponseEntity.ok(errors));
    }

    private Flux<ResponseEntity<?>> fluxCreateHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(201).body(fromObject(errors));
        return Flux.just(ResponseEntity.status(201).body(errors));
    }

    private Flux<ResponseEntity<?>> fluxCreateHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion realizada con exito")
                .build());
        return Flux.just(ResponseEntity.status(201).body(errors));
    }


    private AuditingEntity generateAuditingEntity(AuditingEntity auditingEntity) {
        if (auditingEntity == null) {
            log.info("Generando auditing entity para un objeto nulo");
            return AuditingEntity.builder()
                    .createdBy("LEGACY")
                    .createdDate(new Date())
                    .delete(true)
                    .lastModifiedBy("LEGACY")
                    .lastModifiedDate(new Date())
                    .version(1L)
                    .build();
        } else {
            log.info("Generando auditing entity para un objeto ya existente en la base de datos.");
            return AuditingEntity.builder()
                    .createdBy(auditingEntity.getCreatedBy())
                    .createdDate(auditingEntity.getCreatedDate())
                    .delete(true)
                    .lastModifiedBy("NUEVO")
                    .lastModifiedDate(new Date())
                    .version(auditingEntity.getVersion() + 1)
                    .build();
        }
    }
}
