package com.jyd.handler;

import com.alibaba.fastjson.JSONObject;
import com.jyd.config.NettyClientConfig;
import com.jyd.controller.websocket.ContourWebSocket;
import com.jyd.domain.BaseEntity;
import com.jyd.domain.ContourData;
import com.jyd.domain.decoder.BasePojo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zhenlin Jin
 * @date 2021/7/27 17:46
 */
@Slf4j
@Component("contour")
public class ContourDataParse extends AbstractPojoParse<ContourData> {

    @Override
    public BaseEntity parseString2Object(BasePojo basePojo) {
        // 先进行异或校验
        // if (!checkData(basePojo.getData())) {
        //     log.warn("校验未通过:通道[{}],功能码[{}]", basePojo.getChannelId(), basePojo.getCode());
        //     return null;
        // }
        // 将字符串解析为对象
        ContourData data = null;
        try {
            data = parse(basePojo);
        } catch (Exception e) {
            log.warn("解析极坐标出错:{}", e.getMessage());
            return null;
        }
        String name = NettyClientConfig.CLIENT_MAP.get(basePojo.getChannelId()).getName();
        // 将对象转换为JSON传给websocket
        ContourWebSocket.sendMsg4Channel(name, JSONObject.toJSONString(data));
        return data;
    }

    @Override
    protected ContourData parse(BasePojo basePojo) throws Exception {
        String data = basePojo.getData();
        ContourData contour = new ContourData();
        contour.setHead(data.substring(0, 6));
        contour.setLength(Integer.parseInt(data.substring(6, 10), RADIX_HEX));
        contour.setCode(basePojo.getCode());// 10,12
        contour.setNumber(data.substring(12, 20));
        contour.setCAN_ID(Integer.parseInt(data.substring(20, 24), RADIX_HEX));
        List list = new LinkedList();
        int start = 24;
        for (int i = 1; i <= 400; i++) {
            String xy = data.substring(start, start + 4);
            int anInt = Integer.parseInt(xy, RADIX_HEX);
            float[] coordinate = new float[2];
            coordinate[0] = anInt;
            coordinate[1] = Float.parseFloat(FLOAT_FORMAT.format(i * 0.27f));
            start += 4;
            list.add(coordinate);
        }
        contour.setArray(list);
        contour.setCheck(data.substring(1624, 1626));
        return contour;
    }

}
