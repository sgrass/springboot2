package org.cx.domain;

import org.cx.validation.constraints.ValidCardNum;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author grass
 * @date 2017/10/21
 */
public class User {

    @NotNull
    @Max(value = 100)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ValidCardNum
    private String carNum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }
}
