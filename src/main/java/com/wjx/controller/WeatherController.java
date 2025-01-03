package com.wjx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wjx.common.result.ApiResult;
import com.wjx.common.rpc.BaseService;
import com.wjx.database.convertor.ChinaCityCodeConvertor;
import com.wjx.database.dataobject.ChinaCityCodeDO;
import com.wjx.database.dataobject.UserTaskDO;
import com.wjx.database.mapper.ChinaCityCodeMapper;
import com.wjx.dto.ChinaCityCodeDTO;
import com.wjx.dto.UserTaskDTO;
import com.wjx.dto.UserTaskDelCmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/weather")
public class WeatherController extends BaseService {
    @Autowired
    ChinaCityCodeConvertor chinaCityCodeConvertor;
    @Autowired
    ChinaCityCodeMapper chinaCityCodeMapper;

    @GetMapping("/getAllProvince")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getTaskById() {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeLeft(ChinaCityCodeDO::getAdCode, "0000");
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }

    @GetMapping("/getCityByProvince")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCityByProvince(@RequestParam("adCode") String adCode) {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeRight(ChinaCityCodeDO::getAdCode, adCode);
        queryWrapper.and(s -> s.notLikeLeft(ChinaCityCodeDO::getAdCode, "0000"));
        //直辖市数组
        if (!Arrays.asList("11", "12", "31", "50", "71", "81", "82").contains(adCode)) {
            queryWrapper.and(s -> s.likeLeft(ChinaCityCodeDO::getAdCode, "00"));
        }
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }

    @GetMapping("/getCountyByCity")
    public ResponseEntity<ApiResult<ArrayList<ChinaCityCodeDTO>>> getCountyByCity(@RequestParam("adCode") String adCode) {
        LambdaQueryWrapper<ChinaCityCodeDO> queryWrapper = Wrappers.lambdaQuery(ChinaCityCodeDO.class)
                .likeRight(ChinaCityCodeDO::getAdCode, adCode);
        queryWrapper.and(s -> s.notLikeLeft(ChinaCityCodeDO::getAdCode, "00"));
        List<ChinaCityCodeDO> res = chinaCityCodeMapper.selectList(queryWrapper);
        List<ChinaCityCodeDTO> result = chinaCityCodeConvertor.toDataTransferObjList(res);
        return ResponseEntity.ok(ok(result));
    }
}
