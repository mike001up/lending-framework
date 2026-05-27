package com.pig4cloud.pig.admin.api.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.pig4cloud.pig.admin.api.entity.BizCollectionSchedule;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class BizCollectionScheduleVo extends BizCollectionSchedule {


	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTimeStart;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTimeEnd;

}
