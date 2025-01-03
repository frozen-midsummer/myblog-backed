package com.wjx.database.convertor;

import com.wjx.database.dataobject.ChinaCityCodeDO;
import com.wjx.dto.ChinaCityCodeDTO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ChinaCityCodeConvertor {
    ChinaCityCodeDTO toDataTransferObj(ChinaCityCodeDO chinaCityCodeDO);

    List<ChinaCityCodeDTO> toDataTransferObjList(List<ChinaCityCodeDO> chinaCityCodeDOList);

    ChinaCityCodeDO toDataObject(ChinaCityCodeDTO chinaCityCodeDTO);
}
