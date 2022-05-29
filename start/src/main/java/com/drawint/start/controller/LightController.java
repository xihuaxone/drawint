package com.drawint.start.controller;

import com.drawint.domain.ApiResult;
import com.drawint.domain.validation.LuminanceRatio;
import com.drawint.service.LightService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@ResponseBody
@RestController
@RequestMapping(path = "/light")
public class LightController {
    @Autowired
    LightService lightService;

    @RequestMapping(path = "/{id}/open", method = RequestMethod.PUT)
    public ApiResult<Void> open(@PathVariable(name = "id") @NotNull Long id) {
        lightService.get(id).open();
        return ApiResult.<Void>builder().build();
    }

    @RequestMapping(path = "/{id}/close", method = RequestMethod.PUT)
    public ApiResult<Void> close(@PathVariable(name = "id") @NotNull Long id) {
        lightService.get(id).close();
        return ApiResult.<Void>builder().build();
    }

    @RequestMapping(path = "/{id}/switch", method = RequestMethod.PUT)
    public ApiResult<Void> switchStatus(@PathVariable(name = "id") @NotNull Long id) {
        lightService.get(id).switchStatus();
        return ApiResult.<Void>builder().build();
    }

    @RequestMapping(path = "/{id}/luminance", method = RequestMethod.PUT)
    public ApiResult<Void> setLuminance(@PathVariable(name = "id") @NotNull Long id,
                                        @RequestBody @LuminanceRatio Float luminanceRatio) {
        lightService.get(id).editLuminance(luminanceRatio);
        return ApiResult.<Void>builder().build();
    }
}
