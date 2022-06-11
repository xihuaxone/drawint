package com.drawint.start.controller;

import com.drawint.domain.ApiResult;
import com.drawint.domain.dto.TokenPayload;
import com.drawint.service.TerminalService;
import com.drawint.start.annotation.LoginAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Validated
@ResponseBody
@RequestMapping(path = "/terminals")
@RestController
public class TerminalController {
    @Autowired
    private TerminalService terminalService;

    @LoginAuth
    @RequestMapping(path = "/{tm_id}/actions/{action_code}", method = RequestMethod.PUT)
    public ApiResult<Void> doAction(@PathVariable(name = "tm_id") Long tmId,
                                @PathVariable(name = "action_code") String actionCode,
                                HttpServletRequest request) {
        TokenPayload payload = (TokenPayload)request.getAttribute("payload");
        Long uid = payload.getUid();
        terminalService.doAction(tmId, actionCode, uid);
        return ApiResult.<Void>builder().build();
    }
}
