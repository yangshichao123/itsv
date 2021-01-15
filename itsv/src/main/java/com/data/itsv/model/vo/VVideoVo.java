package com.data.itsv.model.vo;

import com.data.itsv.model.VVideo;
import lombok.Data;

@Data
public class VVideoVo extends VVideo {

    private String cameraTypeName;
    private String state;
    private String channelIp;
    private String channelType;
    private String channelTypes;
    private String planExeState;
    private String planExeStates;
    private String storeState;
    private String storeStates;
    private String storeExceptionDesc;
    private String storePlanType;
    private String storePlanTypes;

    /**
     * @author madi
     * @说明：vss编号
     *
     * */
    private String vssCode;
    /**
     * @author madi
     * @说明：vss名称
     *
     * */
    private String vssName;
    /**
     * @author madi
     * @说明：接入服务名称
     *
     * */
    private String pagName;
    /**
     * @author madi
     * @说明：接入服务编号
     *
     * */
    private String pagCode;
    /**
     * @author madi
     * @说明：数据转发服务编号
     *
     * */
    private String vtduCode;
    /**
     * @author madi
     * @说明：数据转发服务名称
     *
     * */
    private String vtduName;
    /**
     * @author madi
     * @说明：中心网关服务名称
     *
     * */
    private String cmsName;
    /**
     * @author madi
     * @说明：中心网关服务编号
     *
     * */
    private String cmsCode;

    /**
     * 主设备类型编号
     * */
    private String  fdTypeCode;
    private String  isLocal;
    private String  mainRes;
    private String  mainImageQuality;
    private String  mainCodeModel;
    private String  mainTransModel;
    private String  childRes;
    private String  childImageQuality;
    private String  childCodeModel;
    private String  childTransModel;
}
