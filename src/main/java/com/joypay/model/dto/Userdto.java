package com.joypay.model.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author dcj
 * @Date 2023/7/18 17:53
 * 演示:
 */
@Data
public class Userdto {
    @NotNull
    @Length(min = 1, max = 12)
    private String username;
    @NotNull
    @Length(min = 11)
    private String phone;
    @NotNull
    @Length(min = 8)
    private String password;
}
