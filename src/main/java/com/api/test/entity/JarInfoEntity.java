package com.api.test.entity;

import lombok.Data;

/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description jar包信息
 */
@Data
public class JarInfoEntity {
    Integer id;
    Integer jarVersion;
    String jarName;
    String url;
    String salt;
}