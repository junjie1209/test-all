package com.nantian.test.validation.pojo;

import com.nantian.test.validation.valodation.ValidGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * TODO
 *
 * @author: Junjie Zhang
 * @date: 2022/2/10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Null(message = "新增Student时，id只能为空", groups = ValidGroup.Crud.Create.class)
    @NotNull(message = "修改Student时，id不能为空", groups = ValidGroup.Crud.Update.class)
    private String id;

    @NotNull(message = "classId 不能为空")
    private String classId;

    @Size(max = 33)
    @NotNull(message = "name 不能为空")
    private String name;

    @Pattern(regexp = "((^Man$|^Woman$|^UGM$))", message = "sex 值不在可选范围")
    @NotNull(message = "sex 不能为空")
    private String sex;

    @Email(message = "email 格式不正确")
    @NotNull(message = "email 不能为空")
    private String email;

}
