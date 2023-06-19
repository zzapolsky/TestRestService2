package com.example.testrestservice2.controller;


import com.example.testrestservice2.model.Request;
import com.example.testrestservice2.model.Response;
import com.example.testrestservice2.service.ModifyRequestService;
import com.example.testrestservice2.service.MyModifyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

    @Slf4j
    @RestController
    public class MyController {

        private final MyModifyService myModifyService;
        private final ModifyRequestService modifyRequestService;

        @Autowired
        public MyController(@Qualifier("ModifySystemTime") MyModifyService myModifyService,
                            ModifyRequestService modifyRequestService) {
            this.myModifyService = myModifyService;
            this.modifyRequestService = modifyRequestService;
        }

        @PostMapping(value = "/feedback")
        public ResponseEntity<Response> feedback(@RequestBody Request request) {

            log.warn("Входящий request: " + String.valueOf(request));

            Response response = Response.builder()
                    .uid(request.getUid())
                    .operationUid(request.getOperationUid())
                    .systemTime(request.getSystemTime())
                    .code("success")
                    .errorCode("")
                    .errorMessage("")
                    .build();

            modifyRequestService.modifyRq(request);

            Response responseAfterModify = myModifyService.modify(response);

            log.warn("Исходящий response: " + String.valueOf(response));

            return new ResponseEntity<>(responseAfterModify, HttpStatus.OK);
        }
    }