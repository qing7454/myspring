package com.qing.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>****************************************************************************</p>
 * <p><b>Copyright © 2010-2017 Sanfangda team All Rights Reserved<b></p>
 * <ul style="margin:15px;">
 * <li>@description : com.qing.io</li>
 * <li>@version     : 1.0</li>
 * <li>@creation    : 2018年01月24日</li>
 * <li>@author     : fanrenqing</li>
 * </ul>
 * 内部资源读取
 * <p>****************************************************************************</p>
 */
public interface Resource {

    InputStream getInputStream() throws IOException;
}
