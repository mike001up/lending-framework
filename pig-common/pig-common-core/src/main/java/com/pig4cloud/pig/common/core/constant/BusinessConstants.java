/*
 * Copyright (c) 2020 pig4cloud Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.common.core.constant;

/**
 * @author lengleng
 * @date 2019/2/1
 */
public interface BusinessConstants {

	/**
	 * 收款状态 0 待收款;1 延迟收款;2 已收款
	 */
	Integer PAYMENT_STATUS = 2;
	/**
	 * 审核状态 0 待审核;1 审核不通过;2 审核通过
	 */
	Integer REVIEW_STATUS = 2;
	/**
	 * 状态: 1=正常, 2=停用
	 */
	Integer NORMAL = 1;

}
