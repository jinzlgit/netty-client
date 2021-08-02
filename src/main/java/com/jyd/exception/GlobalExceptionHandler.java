package com.jyd.exception;

import com.jyd.domain.web.Result;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.channels.ClosedChannelException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author Zhenlin Jin
 * @date 2021/7/26 14:12
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object methodArgument(MethodArgumentNotValidException e) {
        return Result.fail().code(222).msg("参数异常")
                .data(e.getAllErrors().stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining()));
    }

    @ExceptionHandler(ConnectException.class)
    public Object connectException(ConnectException e) {
        return Result.fail().msg("建立连接失败").data(e.getMessage());
    }

    @ExceptionHandler(DisconnectException.class)
    public Object disconnectException(ConnectException e) {
        return Result.fail().msg("断开连接失败").data(e.getMessage());
    }

    @ExceptionHandler(ClosedChannelException.class)
    public Object closedChannel(ClosedChannelException e) {
        return Result.fail().msg("连接断开");
    }

    @ExceptionHandler(CommonException.class)
    public Object commonException(CommonException e) {
        return Result.fail().msg(e.getMessage());
    }

    @ExceptionHandler(DecoderException.class)
    public Object decoderException(DecoderException e) {
        return Result.fail().msg("解码异常").data(e.getMessage());
    }

    @ExceptionHandler(EncoderException.class)
    public Object encoderException(EncoderException e) {
        return Result.fail().msg("解码异常").data(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Object exceptionHandler(Exception e) {
        return Result.fail().code(222).msg("系统异常").data(e.getMessage());
    }

}
