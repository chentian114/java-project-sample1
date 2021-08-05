package com.chen.sample1.gen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @desc
 * @Author Chentian
 * @date 2021/8/5
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PrimaryKeyDto {
    private String primaryType;
    private String primaryName;
    private boolean isUUID;

}
