package com.pig4cloud.pig.common.data.handler;

import com.baomidou.mybatisplus.extension.plugins.handler.MultiDataPermissionHandler;
import com.pig4cloud.pig.common.data.context.DataScopeContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Table;
import org.springframework.util.StringUtils;

/**
 * MyBatis-Plus 数据权限处理器
 * 从 ThreadLocal 读取切面写好的 SQL 片段，自动追加到 SQL
 */
public class DataPermissionHandler implements MultiDataPermissionHandler {

    @Override
    public Expression getSqlSegment(Table table, Expression where, String mappedStatementId) {
        String scopeSql = DataScopeContext.get();
        if (!StringUtils.hasText(scopeSql)) {
            return null;
        }
        try {
            return CCJSqlParserUtil.parseCondExpression(scopeSql);
        } catch (Exception e) {
            throw new RuntimeException("数据权限SQL解析失败: " + scopeSql, e);
        }
    }
}