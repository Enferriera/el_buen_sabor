package com.example.buensaborback.business.facade;

import com.example.buensaborback.business.facade.Base.BaseFacade;
import com.example.buensaborback.domain.dto.EmpresaDto;
import com.example.buensaborback.domain.dto.EmpresaLargeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


public interface EmpresaFacade extends BaseFacade<EmpresaDto,EmpresaDto, Long> {
    EmpresaLargeDto addSucursal(Long idEmpresa, Long idSucursal);
    EmpresaLargeDto findWithSucursalesById(Long id);


}
