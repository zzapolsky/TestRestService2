package com.example.testrestservice2.service;

import com.example.testrestservice2.model.Response;

public class ModifyUid implements MyModifyService {
    @Override
    public Response modify(Response response) {

        response.setUid("New Uid");
        return response;
    }
}