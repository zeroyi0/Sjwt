package com.shiro.sjwtm.controller.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class UserVo {

    @ApiModelProperty
    private String userName;

    @ApiModelProperty
    private String userPwd;

}
