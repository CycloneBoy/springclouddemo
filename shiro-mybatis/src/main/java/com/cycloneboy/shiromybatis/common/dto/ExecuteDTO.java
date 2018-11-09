package com.cycloneboy.shiromybatis.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program:
 * @description: 操作执行的反馈
 * @author: cycloneboy
 * @create:2018-03-18 11:28
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExecuteDTO {

    private Boolean success;

    private String message;

    private Object value;

}

