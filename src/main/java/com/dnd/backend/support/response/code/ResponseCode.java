package com.dnd.backend.support.response.code;


public sealed interface ResponseCode permits ErrorCode, SuccessCode { }
