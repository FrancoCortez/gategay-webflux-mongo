package cl.gestion.proyecto.user.service.base.impl;

import cl.gestion.proyecto.user.model.base.AuditingEntity;
import cl.gestion.proyecto.user.model.base.BaseEntity;
import cl.gestion.proyecto.user.service.base.BaseService;
import cl.gestion.proyecto.user.utils.model.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
@Slf4j
public class BaseServiceImpl<T extends BaseEntity, ID extends String> implements BaseService<T, ID> {

    private final List<ValidationResult> error = new ArrayList<>();

    protected void initErrorList() {
        this.error.clear();
    }

    protected List<ValidationResult> getListError(ValidationResult data) {
        this.error.add(data);
        return this.error;
    }


    protected List<ValidationResult> getListError() {
        return this.error;
    }


    protected Mono<ResponseEntity<?>> monoResponseErrorList(List<ValidationResult> errors) {
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    protected Mono<ResponseEntity<?>> monoErrorHandler(Exception ex) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code("1")
                .msg(ex.getMessage())
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    protected Mono<ResponseEntity<?>> monoErrorHandler(String code, String msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.badRequest().body(errors));
    }

    protected Mono<ResponseEntity<?>> monoWarningHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        //return ServerResponse.status(409).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.status(409).body(errors));
    }

    protected Mono<ResponseEntity<?>> monoOkHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(200).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.ok(errors));
    }

    protected Mono<ResponseEntity<?>> monoOkObject(Object response) {
        // return ServerResponse.status(200).body(fromObject(response));
        return Mono.justOrEmpty(ResponseEntity.ok(response));
    }

    protected Mono<ResponseEntity<?>> monoOkHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion Realizada con exito")
                .build());
        //return ServerResponse.status(200).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.ok(errors));
    }

    protected Mono<ResponseEntity<?>> monoCreateHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(201).body(fromObject(errors));
        return Mono.justOrEmpty(ResponseEntity.status(201).body(errors));
    }

    protected Mono<ResponseEntity<?>> monoCreateHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion realizada con exito")
                .build());
        return Mono.justOrEmpty(ResponseEntity.status(201).body(errors));
    }


///////////////////////////////////////////////////////////////////////////////////////////////////////


    protected Flux<ResponseEntity<?>> fluxResponseErrorList(List<ValidationResult> errors) {
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    protected Flux<ResponseEntity<?>> fluxErrorHandler(Exception ex) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code("1")
                .msg(ex.getMessage())
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    protected Flux<ResponseEntity<?>> fluxErrorHandler(String code, String msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.badRequest().body(fromObject(errors));
        return Flux.just(ResponseEntity.badRequest().body(errors));
    }

    protected Flux<ResponseEntity<?>> fluxWarningHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        //return ServerResponse.status(409).body(fromObject(errors));
        return Flux.just(ResponseEntity.status(409).body(errors));
    }

    protected Flux<ResponseEntity<?>> fluxOkHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(200).body(fromObject(errors));
        return Flux.just(ResponseEntity.ok(errors));
    }

    protected Flux<ResponseEntity<?>> fluxOkObject(Object response) {
        // return ServerResponse.status(200).body(fromObject(response));
        return Flux.just(ResponseEntity.ok(response));
    }

    protected Flux<ResponseEntity<?>> fluxOkHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion Realizada con exito")
                .build());
        //return ServerResponse.status(200).body(fromObject(errors));
        return Flux.just(ResponseEntity.ok(errors));
    }

    protected Flux<ResponseEntity<?>> fluxCreateHandler(String code, Object msg) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg(msg)
                .build());
        // return ServerResponse.status(201).body(fromObject(errors));
        return Flux.just(ResponseEntity.status(201).body(errors));
    }

    protected Flux<ResponseEntity<?>> fluxCreateHandler(String code) {
        List<ValidationResult> errors = new ArrayList<>();
        errors.add(ValidationResult.builder()
                .code(code)
                .msg("Operacion realizada con exito")
                .build());
        return Flux.just(ResponseEntity.status(201).body(errors));
    }


    protected AuditingEntity generateAuditingEntity(AuditingEntity auditingEntity) {
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
