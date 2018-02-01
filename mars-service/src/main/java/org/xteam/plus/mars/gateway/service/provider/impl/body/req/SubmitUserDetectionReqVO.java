package org.xteam.plus.mars.gateway.service.provider.impl.body.req;

import java.io.Serializable;

public class SubmitUserDetectionReqVO  implements Serializable {

    private String checkReport;

    public String getCheckReport() {
        return checkReport;
    }

    public void setCheckReport(String checkReport) {
        this.checkReport = checkReport;
    }
}
