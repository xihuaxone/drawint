package com.drawint.start.controller;

import com.drawint.common.BizExceptionType;
import com.drawint.common.exception.BizException;
import com.drawint.domain.ApiResult;
import com.drawint.domain.bo.ActionRegisterBO;
import com.drawint.domain.bo.TerminalBO;
import com.drawint.domain.bo.TerminalRegisterBO;
import com.drawint.domain.dto.TokenPayload;
import com.drawint.domain.enums.TerminalActionCodeEnum;
import com.drawint.service.TerminalMngService;
import com.drawint.domain.enums.ConcurrencyLevelEnum;
import com.drawint.start.annotation.LoginAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@Validated
@ResponseBody
@RequestMapping(path = "/manager/terminals")
@RestController
public class TerminalMngController {
    @Autowired
    private TerminalMngService terminalMngService;

    @LoginAuth
    @RequestMapping(path = "", method = RequestMethod.POST)
    public ApiResult<TerminalBO> registerTerminal(@RequestBody TerminalRegisterBO terminalRegisterBO,
                                                  HttpServletRequest request) {
        TokenPayload payload = (TokenPayload)request.getAttribute("payload");

        ActionRegisterBO actionConnCheck = new ActionRegisterBO();
        // 默认至少有一个连通性检查action，如果前端未传递，在这里自动补上；
        if (!terminalRegisterBO.hasConnCheckAction()) {
            actionConnCheck.setName("Check Connection");
            actionConnCheck.setCode(TerminalActionCodeEnum.CONN_CHECK.getCode());
            actionConnCheck.setConcurrencyLevel(ConcurrencyLevelEnum.SERIAL.getCode());
            terminalRegisterBO.getActionList().add(actionConnCheck);
        }
        terminalMngService.registerTerminal(terminalRegisterBO, payload.getUid());
        TerminalBO terminalBO = terminalMngService.get(terminalRegisterBO.getTopic(), payload.getUid());
        if (terminalBO == null) {
            throw new BizException(BizExceptionType.TERMINAL_REGISTER_FAILED);
        }
        return ApiResult.<TerminalBO>builder().data(terminalBO).build();
    }

    @LoginAuth
    @RequestMapping(path = "/{topic}", method = RequestMethod.GET)
    public ApiResult<TerminalBO> getTerminal(@PathVariable String topic,
                                             HttpServletRequest request) {
        TokenPayload payload = (TokenPayload)request.getAttribute("payload");
        TerminalBO terminalBO = terminalMngService.get(topic, payload.getUid());
        if (terminalBO == null) {
            throw new BizException(BizExceptionType.TERMINAL_REGISTER_FAILED);
        }
        return ApiResult.<TerminalBO>builder().data(terminalBO).build();
    }

    @LoginAuth
    @RequestMapping(path = "/{tm_id}/action", method = RequestMethod.POST)
    public ApiResult<Void> registerAction(@PathVariable @NotNull Long tm_id,
                                          @RequestBody List<ActionRegisterBO> actionRegisterBOList,
                                          HttpServletRequest request) {
        TokenPayload payload = (TokenPayload)request.getAttribute("payload");

        terminalMngService.registerAction(tm_id, actionRegisterBOList, payload.getUid());
        return ApiResult.<Void>builder().build();
    }

    @LoginAuth
    @RequestMapping(path = "", method = RequestMethod.GET)
    public ApiResult<List<TerminalBO>> list(HttpServletRequest request) {
        TokenPayload payload = (TokenPayload)request.getAttribute("payload");
        return ApiResult.<List<TerminalBO>>builder().data(terminalMngService.list(payload.getUid())).build();
    }
}
