package com.example.calculos_matematicos.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Divisor {
    private Integer divisor;
    private List<Integer> divisores;
}
