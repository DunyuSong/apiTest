package com.api.test.entity;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @date 2019/01/04 15:38
 */
@Data
@ApiModel(value = "测试用例Model")
public class TestCaseEntity {
    //id编号
    @ApiModelProperty(value = "id编号")
    private Integer id;
    //测试用例编号
    @ApiModelProperty(value = "测试用例编号")
    private String caseId;
    //测试用例名称
    @ApiModelProperty(value = "测试用例名称")
    private String caseName;
    //所属应用
    @ApiModelProperty(value = "所属应用")
    private String application;
    //前置条件
    @ApiModelProperty(value = "前置条件")
    private String precondition;
    //所属功能（或版本）
    @ApiModelProperty(value = "所属功能（或版本）")
    private String subordinate;
    //入参
    @ApiModelProperty(value = "入参")
    private String param;
    //操作步骤
    @ApiModelProperty(value = "操作步骤")
    private String steps;
    //预期结果
    @ApiModelProperty(value = "预期结果")
    private String expectResult;
    //等级
    @ApiModelProperty(value = "等级")
    private String caseLevel;
    //所属api
    @ApiModelProperty(value = "所属api")
    private String api;
    //执行类型
    @ApiModelProperty(value = "执行类型")
    private Integer executeType;
    //是否删除
    @ApiModelProperty(value = "是否删除")
    private Integer isDelete;
    //作者
    @ApiModelProperty(value = "作者")
    private String author;
    //创建时间
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    //更新时间
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    //是否冒烟用例
    @ApiModelProperty(value = "是否冒烟用例")
    private Integer isSmokeCase;
    @ApiModelProperty(value = "环境选择")
    private String env;
    @ApiModelProperty(value = "所属子功能")
    private String subfunctions;
    @ApiModelProperty(value = "请求方式")
    private String method;
    @ApiModelProperty(value = "请求头信息")
    private String header;
    @ApiModelProperty(value = "前置角色")
    private String role;
    @ApiModelProperty(value = "角色账号")
    private String roleAccount;
    @ApiModelProperty(value = "sqlJson")
    private String sqlJson;
    @ApiModelProperty(value = "sqlJsonPre")
    private String sqlJsonPre;
    @ApiModelProperty(value = "class_tags")
    private String class_tags;
    @ApiModelProperty(value = "tags")
    private String tags;

}
