package com.wangshuo.store.myconventer;

import org.apache.commons.beanutils.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/5/1 0001.
 */
public class Myconventer implements Converter {
    @Override
    public <T> T convert(Class<T> aClass, Object o) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date=sdf.parse((String)o);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}

