package com.example.buensaborback.presentation.rest.Base;

import com.example.buensaborback.business.facade.Base.BaseFacadeImp;
import com.example.buensaborback.domain.dto.BaseDto;
import com.example.buensaborback.domain.entities.Base;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@Controller
public abstract class BaseControllerImp <E extends Base,D extends BaseDto, GD extends BaseDto, ID extends Serializable, F extends BaseFacadeImp<E,D,GD,ID>> implements BaseController<D,GD, ID> {

    private static final Logger logger = LoggerFactory.getLogger(BaseControllerImp.class);
    protected F facade;
    public BaseControllerImp(F facade){
        this.facade = facade;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GD> getById(@PathVariable ID id){
        //logger.info("INICIO GET BY ID {}",id);
        return ResponseEntity.ok(facade.getById(id));
    }

    @GetMapping("/paged/includeDeleted")
    public ResponseEntity<Page<GD>> getAllPaged(Pageable pageable) {
        //logger.info("INICIO GET ALL INCLUYENDO ELIMINADOS LÓGICOS");
        return ResponseEntity.ok(facade.getAllPaged(pageable));
    }

    @GetMapping("/includeDeleted")
    public ResponseEntity<List<GD>> getAll() {
        //logger.info("INICIO GET ALL INCLUYENDO ELIMINADOS LÓGICOS");
        return ResponseEntity.ok(facade.getAll());
    }

    @GetMapping()
    public ResponseEntity<List<GD>> getAllByBajaFalse() {
        //logger.info("INICIO GET ALL EXCLUYENDO ELIMINADOS LÓGICOS");
        return ResponseEntity.ok(facade.getAllByBajaFalse());
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<GD>> getAllByBajaFalse(Pageable pageable) {
        //logger.info("INICIO GET ALL EXCLUYENDO ELIMINADOS LÓGICOS");
        return ResponseEntity.ok(facade.getAllPagedByBajaFalse(pageable));
    }

    @PostMapping()
    public ResponseEntity<GD> create(@RequestBody D entity){
        //logger.info("INICIO CREATE {}",entity.getClass());
        return ResponseEntity.ok(facade.createNew(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GD> edit(@RequestBody D entity, @PathVariable ID id){
        //logger.info("INICIO EDIT {}",entity.getClass());
        return ResponseEntity.ok(facade.update(entity, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ID id){
        //logger.info("INICIO DELETE BY ID");
        facade.deleteById(id);
        return ResponseEntity.ok(null);
    }
}
