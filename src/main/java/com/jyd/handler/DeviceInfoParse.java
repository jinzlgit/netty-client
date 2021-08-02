package com.jyd.handler;

import com.alibaba.fastjson.JSONObject;
import com.jyd.config.NettyClientConfig;
import com.jyd.controller.websocket.ContourWebSocket;
import com.jyd.domain.BaseEntity;
import com.jyd.domain.DeviceInfo;
import com.jyd.domain.decoder.BasePojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 17:46
 */
@Slf4j
@Component("device")
public class DeviceInfoParse extends AbstractPojoParse<DeviceInfo> {

    private static final String[] FAULT_STATUS;

    static {
        FAULT_STATUS = new String[]{"", "CAN2故障", "CAN1故障", "存储芯片故障", "温度传感器故障", "", "时钟芯片故障",
                        "K16故障", "K1故障", "S6故障", "S1故障", "加速度传感器故障", "状态寄存器故障", "FLASH故障",
                        "RAM故障", "机芯故障"};
    }

    @Override
    public BaseEntity parseString2Object(BasePojo basePojo) {
        // 先进行校验
        // if (!checkData(basePojo.getData())) {
        //     log.warn("校验未通过:通道[{}],功能码[{}]", basePojo.getChannelId(), basePojo.getCode());
        //     return null;
        // }
        // 解析为对象
        DeviceInfo deviceInfo = parse(basePojo);
        String name = NettyClientConfig.CLIENT_MAP.get(basePojo.getChannelId()).getName();
        // 将对象转换为JSON传给websocket
        ContourWebSocket.sendMsg4Channel(name, JSONObject.toJSONString(deviceInfo));
        return deviceInfo;
    }

    @Override
    protected DeviceInfo parse(BasePojo basePojo) {
        String data = basePojo.getData();
        DeviceInfo deviceInfo = new DeviceInfo();
        deviceInfo.setHead(data.substring(0, 6));
        deviceInfo.setLength(Integer.parseInt(data.substring(6, 10), RADIX_HEX));
        deviceInfo.setCode(basePojo.getCode());// 10,12
        deviceInfo.setNumber(data.substring(12, 20));
        deviceInfo.setCAN_ID(Integer.parseInt(data.substring(20, 24), RADIX_HEX));
        deviceInfo.setRGB(Integer.parseInt(data.substring(24, 28), RADIX_HEX));
        deviceInfo.setSensitivity(Integer.parseInt(data.substring(28, 32), RADIX_HEX));
        deviceInfo.setIndentation(Integer.parseInt(data.substring(32, 36), RADIX_HEX));
        deviceInfo.setFarthest(Integer.parseInt(data.substring(36, 40), RADIX_HEX));
        deviceInfo.setAngleStart(Integer.parseInt(data.substring(40, 42), RADIX_HEX));
        deviceInfo.setAngleEnd(Integer.parseInt(data.substring(42, 44), RADIX_HEX));
        deviceInfo.setFaultOffsetThreshold(Integer.parseInt(data.substring(44, 48), RADIX_HEX));
        deviceInfo.setFaultDistanceThreshold(Integer.parseInt(data.substring(48, 52), RADIX_HEX));
        deviceInfo.setOccludeFaultCount(Integer.parseInt(data.substring(52, 56), RADIX_HEX));
        deviceInfo.setDustedFaultCount(Integer.parseInt(data.substring(56, 60), RADIX_HEX));
        deviceInfo.setPicthStudy(Integer.parseInt(data.substring(60, 64), RADIX_HEX));
        deviceInfo.setRollStudy(Integer.parseInt(data.substring(64, 68), RADIX_HEX));
        deviceInfo.setValidAlarmCount(Integer.parseInt(data.substring(68, 72), RADIX_HEX));
        deviceInfo.setHeart(Integer.parseInt(data.substring(72, 76), RADIX_HEX));
        deviceInfo.setMovementStatus(Integer.parseInt(data.substring(76, 80), RADIX_HEX));
        deviceInfo.setBootloader(Integer.parseInt(data.substring(80, 84), RADIX_HEX));
        deviceInfo.setRelay(Integer.parseInt(data.substring(84, 88), RADIX_HEX));
        deviceInfo.setSendCANDelay(Integer.parseInt(data.substring(88, 92), RADIX_HEX));
        deviceInfo.setBuzzer(Integer.parseInt(data.substring(92, 96), RADIX_HEX));
        deviceInfo.setRunStatus(runStatus(data.substring(96, 100)));
        deviceInfo.setFaultStatus(Optional.ofNullable(faultStatus(data.substring(100, 104))).orElse("无"));
        deviceInfo.setVDD(Integer.parseInt(data.substring(104, 108), RADIX_HEX) / 100);
        deviceInfo.setPitch(Integer.parseInt(data.substring(108, 112), RADIX_HEX) / 100);
        deviceInfo.setRoll(Integer.parseInt(data.substring(112, 116), RADIX_HEX) / 100);
        deviceInfo.setTemperature(Integer.parseInt(data.substring(116, 120), RADIX_HEX) / 100);
        deviceInfo.setCheck(data.substring(120, 122));
        return deviceInfo;
    }

    /**
     * 运行状态翻译
     */
    private String runStatus(String s) {
        String binary = HEX_TO_BINARY_FORMAT.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(s, RADIX_HEX))));
        char[] chars = binary.toCharArray();
        StringBuilder sb = new StringBuilder();
        if (chars[1] == '1')
            sb.append("下行,");
        else
            sb.append("上行,");
        if (chars[2] == '1')
            sb.append("探测期,");
        if (chars[3] == '1')
            sb.append("系统旁路,");
        if (chars[4] == '1')
            sb.append("软件隔离,");
        if (chars[5] == '1')
            sb.append("蒙尘,");
        if (chars[6] == '1')
            sb.append("遮挡,");
        if (chars[7] == '1')
            sb.append("障碍物入侵,");
        if (chars[8] == '1')
            sb.append("时钟警告,");
        if (chars[9] == '1')
            sb.append("K16断开,");
        else
            sb.append("K16吸合,");
        if (chars[10] == '1')
            sb.append("K1断开,");
        else
            sb.append("K1吸合,");
        if (chars[11] == '1')
            sb.append("S1触发,");
        else
            sb.append("S1断开,");
        if (chars[12] == '1')
            sb.append("B6触发,");
        else
            sb.append("B6恢复,");
        if (chars[13] == '1')
            sb.append("温度警告,");
        if (chars[14] == '1')
            sb.append("位移警告,");
        if (chars[15] == '1')
            sb.append("VDD警告,");
        return sb.length() > 0 ? sb.substring(0, sb.length()-1) : null;
    }

    /**
     * 故障状态翻译
     */
    private String faultStatus(String s) {
        String binary = HEX_TO_BINARY_FORMAT.format(Integer.parseInt(Integer.toBinaryString(Integer.parseInt(s, RADIX_HEX))));
        char[] chars = binary.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '1') {
                sb.append(FAULT_STATUS[i]).append(",");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length()-1) : null;
    }

}
