package com.api.test.entity;

import lombok.Data;

/**
 * @author xiaoyu
 * @create 2020-11-06
 * @description
 */
@Data
public class JarInfoPluginsEntity {
    Integer id;
    String packageName;
    String voidName;
    String params;
    String url;
    String assertPath;
    Integer isDelete;

}