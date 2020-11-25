package com.api.test.model.params;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author xiaoyu
 * @create 2020-11-25
 * @description
 */
@Data
public class UpdateJarParam {
    private int id;

    private MultipartFile multipartFile;
}