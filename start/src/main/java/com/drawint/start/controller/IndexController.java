package com.drawint.start.controller;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.ApiResult;
import com.drawint.domain.bo.DoorActionBO;
import com.drawint.domain.dto.DoorDTO;
import com.drawint.service.DoorService;
import com.drawint.service.RelayDeviceService;
import com.drawint.start.annotation.LoginAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@ResponseBody
@RestController
@RequestMapping(path = "/index")
public class IndexController {
    @Autowired
    RelayDeviceService relayDeviceService;

    @Autowired
    DoorService doorService;

    @LoginAuth
    @RequestMapping(path = "/light_switch/", method = RequestMethod.GET)
    public ApiResult<Void> lightSwitch(@RequestParam(required = false) Boolean sign) {
        boolean success = relayDeviceService.switchStatus(0);
        if (!success) {
            throw new BizException(BizExceptionType.RELAY_DEVICE_CONTROL_ERROR);
        }
        return ApiResult.<Void>builder().build();
    }

    @LoginAuth
    @RequestMapping(path = "/door/", method = RequestMethod.POST)
    public ApiResult<Void> door(@RequestBody DoorDTO doorDTO) throws InterruptedException {
        DoorActionBO doorActionBO = new DoorActionBO();
        doorActionBO.setAction(doorDTO.getAction());
        doorActionBO.setSwitchPort(doorDTO.getSwitchPort());
        doorService.doAction(doorActionBO);
        Thread.sleep(1000 * 3); // 延时一段时间，确保开关动作完全做完；
        return ApiResult.<Void>builder().build();
    }
}
