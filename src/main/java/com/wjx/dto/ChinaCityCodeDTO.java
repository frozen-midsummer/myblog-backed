package com.wjx.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.wjx.common.dto.DTO;
import lombok.Data;

@Data
public class ChinaCityCodeDTO extends DTO {
    private String name;
    private Integer adCode;
    private Integer cityCode;
}
