package com.pig4cloud.pig.admin.api.vo;

import com.pig4cloud.pig.admin.api.enums.IdTypeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
/**
 * @author nick
 * @date 2026/07/05
 */
@Data
@Schema(description = "用户实名认证传输对象")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCertificationInfoVO {
    /**
     * 证件类型（身份证/驾驶证/护照）
     */
    @Schema(description = "证件类型：1-身份证 2-驾驶证 3-护照", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "证件类型不能为空")
    private IdTypeEnum idType;

    /**
     * 真实姓名
     */
    @Schema(description = "真实姓名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "真实姓名不能为空")
    private String realName;

    /**
     * 证件号码（身份证号/驾照号/护照号）
     */
    @Schema(description = "证件号码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "证件号码不能为空")
    private String idNumber;

    /**
     * 证件正面图片URL（必须包含人脸照片页）
     */
    @Schema(description = "证件正面图片URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "证件正面图片不能为空")
    private String frontImageUrl;

    /**
     * 证件背面图片URL（身份证和部分驾照有背面，护照通常只有正面，允许为空）
     */
    @Schema(description = "证件背面图片URL（非必须，如护照可留空）")
	@NotBlank(message = "证件正面图片不能为空")
    private String backImageUrl;

    /**
     * 【新增】手持证件照片URL（用户本人手持证件+身份证正面合影，用于反欺诈）
     */
    @Schema(description = "手持证件照片URL（本人+证件合影）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "手持证件照片不能为空")
    private String handheldPhotoUrl;

    /**
     * 证件签发日期（建议使用 LocalDate，若使用 Instant 则前端传时间戳）
     */
    @Schema(description = "证件签发日期")
	@NotBlank
    private Instant issueDate;

    /**
     * 证件有效期截止日期（如 2026-12-31）
     */
    @Schema(description = "证件有效期截止日期")
	@NotBlank
    private Instant expireDate;
}