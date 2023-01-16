package com.osaz.danaka.product.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class imgPathDTO {

    private int imgNo;
    private int productNo;
    private String imgCategory;
    private String orgFileName;
    private String sysFileName;
    private String savePath;
}
