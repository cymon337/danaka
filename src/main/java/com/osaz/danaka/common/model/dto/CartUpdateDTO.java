package com.osaz.danaka.common.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartUpdateDTO {
    List<CartUpdateDTO> cartUpdateDTOList;
    private String cartNo;
    private String amount;
}
