package com.mettre.usually.base;

import com.mettre.usually.enum_.ResultEnum;
import com.mettre.usually.exception.CustomerException;

public class ReturnType {

    public static int ReturnType(int type, ResultEnum resultEnum) {
        if (type < 1) {
            throw new CustomerException(resultEnum);
        }
        return type;
    }

    public static long ReturnType(Long type, ResultEnum resultEnum) {
        if (type < 1) {
            throw new CustomerException(resultEnum);
        }
        return type;
    }

    public static Object ReturnType(Object object, ResultEnum resultEnum) {
        if (object ==null) {
            throw new CustomerException(resultEnum);
        }
        return object;
    }
}
