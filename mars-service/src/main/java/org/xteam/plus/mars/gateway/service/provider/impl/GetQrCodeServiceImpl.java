package org.xteam.plus.mars.gateway.service.provider.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.xteam.plus.mars.common.JsonUtils;
import org.xteam.plus.mars.common.qrcode.QRCodeBuilder;
import org.xteam.plus.mars.common.qrcode.drawing.*;
import org.xteam.plus.mars.domain.UserInfo;
import org.xteam.plus.mars.fdfs.DfsClient;
import org.xteam.plus.mars.fdfs.FileInfo;
import org.xteam.plus.mars.fdfs.impl.DfsClientImpl;
import org.xteam.plus.mars.gateway.service.provider.GateWayService;
import org.xteam.plus.mars.gateway.service.provider.GlobalErrorMessage;
import org.xteam.plus.mars.gateway.service.provider.HttpRequestBody;
import org.xteam.plus.mars.gateway.service.provider.HttpResponseBody;
import org.xteam.plus.mars.manager.UserInfoManager;

import javax.annotation.Resource;
import java.awt.*;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: yankun
 * Date: 2018-04-03
 * Time: 20:53
 * 获取用户二维码工作证
 */
@Component
public class GetQrCodeServiceImpl implements GateWayService {
    private final String METHOD_NAME = "cn.zaoangongcheng.api.gateway.user.qrcode.get";

    @Resource
    private UserInfoManager userInfoManager;

    /**
     * 背景图片
     */
    @Value("${mars.employeeCard.backGround}")
    private String  background;

    /**
     * 二维码链接
     */
    @Value("${mars.employeeCard.url}")
    private String  url;

    private DfsClient dfsClient = new DfsClientImpl();

    @Override
    public String getMethodName() {
        return METHOD_NAME;
    }

    @Override
    public HttpResponseBody gateWay(HttpRequestBody httpRequestBody) throws Exception {
        if (httpRequestBody.getUserId() == null) {
            return new HttpResponseBody(GlobalErrorMessage.MISSING_PARAMETERS);
        }
        UserInfo userInfo = userInfoManager.get(new UserInfo().setUserId(BigDecimal.valueOf(Long.valueOf(httpRequestBody.getUserId()))));
        if (userInfo == null) {
            return new HttpResponseBody(GlobalErrorMessage.OBJECT_ISNULL);
        }

        //判断工作证是否在,不存在则生成工作证
        if (StringUtils.isEmpty(userInfo.getEmployeeCard())) {
            String employeeCard = getEmployeeCard(userInfo);
            userInfo.setEmployeeCard(employeeCard);
            userInfo.setUpdated(new Date());
            userInfoManager.update(userInfo);
        }
        Map<String, Object> bizContentMap = JsonUtils.transform(userInfo, HashMap.class);
        //加密身份证号
        if (userInfo != null && userInfo.getIdNumber() != null) {
            bizContentMap.put("idNumber", userInfo.getIdNumber().replaceAll("(\\d{4})\\d{10}(\\w{4})", "$1*****$2"));
        }
        return new HttpResponseBody(GlobalErrorMessage.SUCCESS).setBizContent(JsonUtils.toJSON(bizContentMap));
    }

    /**
     * 获取工作证
     *
     * @param userInfo
     * @return
     */
    private String getEmployeeCard(UserInfo userInfo) throws Exception {
        //创建临时文件
        File tempFile = File.createTempFile("temp_" + UUID.randomUUID().toString(), ".png");
        QRCodeBuilder qrCodeBuilder = new QRCodeBuilder()
                .setBackGround(background)
                .add(new QrCodeDrawingImpl()
                        .setUrl(url+userInfo.getUserId())
                        .setSize(new Size(200, 200))
                        .setPosition(new Position(160, 365))
                );
        //头像
        if (!StringUtils.isEmpty(userInfo.getWxHeadPortrait())) {
            qrCodeBuilder.add(new ImageDrawingImpl()
                    .setImageUrl(userInfo.getWxHeadPortrait())
                    .setPosition(new Position(210, 170))
                    .setSize(new Size(100, 100))
            );
        }

        //实名
        if (!StringUtils.isEmpty(userInfo.getRealName())) {
            qrCodeBuilder.add(new TextDrawingImpl()
                    .setFont(new Font("粗体", Font.BOLD, 30))
                    .setPosition(new Position(220, 300))
                    .setColor(Color.WHITE)
                    .setText(userInfo.getRealName())
            );
        }
        //用户ID
        qrCodeBuilder.add(new TextDrawingImpl()
                .setFont(new Font("粗体", Font.BOLD, 30))
                .setPosition(new Position(155, 780))
                .setColor(Color.black)
                .setText("No." + userInfo.getUserId())
        );
        //绘图到文件
        qrCodeBuilder.draw(tempFile);
        //上传文件到文件服务器
        FileInfo fileInfo = dfsClient.upload(tempFile);
        tempFile.deleteOnExit();
        //删除临时文件
        tempFile.delete();
        return fileInfo.getStoreAddress();
    }
}
