package com.pig4cloud.pig.common.log.event;

import com.pig4cloud.pig.common.core.entity.RemoteSysLogDTO;

// import com.pig4cloud.pig.admin.api.entity.SysLog;
import lombok.Data;

/**
 * spring event log
 *
 * @author lengleng
 * @date 2023/8/11
 */
@Data
public class SysLogEventSource extends RemoteSysLogDTO {

	/**
	 * 参数重写成object
	 */
	private Object body;

}
