package com.shenkai.network.beans;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Author:shenkai
 * Time:2020/4/23 13:50
 * Description:
 */
public class TencentBaseResponse {
    @SerializedName("showapi_res_code")
    @Expose
    public Integer showapiResCode;
    @SerializedName("showapi_res_error")
    @Expose
    public String showapiResError;
}
