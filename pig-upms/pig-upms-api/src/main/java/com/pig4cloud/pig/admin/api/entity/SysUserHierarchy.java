package com.pig4cloud.pig.admin.api.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Schema(description = "用户层级关系")
@EqualsAndHashCode(callSuper = true)
public class SysUserHierarchy extends Model<SysUserHierarchy> {

	private static final long serialVersionUID = 1L;

	@Schema(description = "祖先节点")
	private Long ancestor;

	@Schema(description = "后代节点")
	private Long descendant;

	@Schema(description = "边数距离：自身为0，父子为1")
	private Integer depth;

	@Schema(description = "层级类型（如 agency, region）")
	private String hierarchyType;

}