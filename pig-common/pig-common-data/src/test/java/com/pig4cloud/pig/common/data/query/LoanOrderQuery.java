package com.pig4cloud.pig.common.data.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanOrderQuery {
    private Long current;
    private Long size;
    private String status;
}
