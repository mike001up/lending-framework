package com.pig4cloud.pig.common.excel;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TimestampStringConverter implements Converter<Timestamp> {

    @Override
    public Class<Timestamp> supportJavaTypeKey() {
        return Timestamp.class;
    }

    @Override
    public WriteCellData<String> convertToExcelData(Timestamp value,
                                                    ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        if (value == null) {
            return new WriteCellData<>("");
        }
        String formatted = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
        return new WriteCellData<>(formatted);
    }
}