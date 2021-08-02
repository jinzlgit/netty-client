package com.jyd.domain.web;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Zhenlin Jin
 * @date 2021/7/26 13:45
 */
@Data
public class ConnectDTO implements Serializable {

    @NotBlank(message = "名称不能为空")
    private String clientName;

    @NotBlank(message = "IP不能为空")
    private String ip;

    @NotNull(message = "端口不能为空")
    private Integer port;
}
