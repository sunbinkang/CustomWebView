package com.example.base.autoservice;

import java.util.ServiceLoader;

/**
 * Created by BinKang on 2021/3/12.
 * Des :
 */
public final class XiangxueServiceLoader {
    private XiangxueServiceLoader() {}

    public static <S> S load(Class<S> service) {

        try {
            return ServiceLoader.load(service).iterator().next();
        } catch (Exception e) {
            return null;
        }

    }
}
