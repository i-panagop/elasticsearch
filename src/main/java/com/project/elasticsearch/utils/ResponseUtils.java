package com.project.elasticsearch.utils;

import com.project.elasticsearch.dto.ResponseDto;

public class ResponseUtils {

    public static ResponseDto createResponse(String status, String code, String message) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(status);
        responseDto.setCode(code);
        responseDto.setMessage(message);
        return responseDto;
    }

    public static ResponseDto createResponse(String res, String code) {
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus(res);
        responseDto.setCode(code);
        return responseDto;
    }
}
