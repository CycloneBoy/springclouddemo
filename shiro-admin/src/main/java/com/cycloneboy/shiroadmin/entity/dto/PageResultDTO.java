package com.cycloneboy.shiroadmin.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: creditcard
 * @description: 分页查询结果DTO
 * @author: cycloneboy
 * @create:2018-03-18 11:42
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDTO {

    private Long total;

    private List<?> rows;

}
