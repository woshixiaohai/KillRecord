package com.qhsoft.killrecord.util;

import java.util.List;

/**
 * Description:
 * Author:lin
 * Date:2017-09-07
 */

public class NullableUtil {

    public static int listSize(List list) {
        return list == null ? 0 : list.size();
    }

}
