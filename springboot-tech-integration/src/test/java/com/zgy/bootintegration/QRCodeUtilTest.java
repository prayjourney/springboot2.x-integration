package com.zgy.bootintegration;

import static com.zgy.bootintegration.utils.QRCodeUtil.createCodeToFile;

/**
 * @Author renjiaxin
 * @Date 2020/10/28
 * @Description
 */
public class QRCodeUtilTest {

    public static void main(String[] args) {
        String codeContent1 = "https://www.baidu.com/";
        createCodeToFile(codeContent1, null, null);

        String codeContent2 = "4c86fed8-7ac9-4db7-956e-6cfe84268059";
        createCodeToFile(codeContent2, null, null);
    }
}
