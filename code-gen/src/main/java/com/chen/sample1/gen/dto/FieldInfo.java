package com.chen.sample1.gen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc
 * @Author Chentian
 * @date 2021/7/21
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class FieldInfo {
    private String fieldComment;
    private String fieldClass;
    private String fieldName;
    private String fieldKey;
    private String columnName;
    private String extra;
    private boolean isPrimary;


}
