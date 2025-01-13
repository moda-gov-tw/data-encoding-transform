package com.fullstack.backend.util;

import org.apache.commons.lang3.StringUtils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FillerUtils {

    /**
     * 欄位填充格式化
     *
     * @param content           原始內容
     * @param columnBytesLength 規定欄位長度(Byte)
     * @param alignment         靠左靠右對齊，預設靠左，left:靠右
     * @param filled            填充字元
     * @param charset           編碼，若為null預設為UTF-8
     * @return 格式化的字串
     */
    public static String stringFiller(String content, int columnBytesLength, String alignment, String filled, Charset charset) {

        //如果是null 設為空
        if (StringUtils.isBlank(content)) {
            content = "";
        }
        //如果是null 預設UTF_8
        if (charset == null) {
            charset = StandardCharsets.UTF_8;
        }
        //如果filled為空
        if (StringUtils.isEmpty(filled)) {
            throw new IllegalArgumentException("filled為空");
        }
        // 計算原始內容Bytes長度
        int originContentBytesLength = content.getBytes(charset).length;

        if (originContentBytesLength > columnBytesLength) { // 原始內容Bytes長度不可超過欄位Bytes長度
            throw new IllegalArgumentException(content + " 原始內容Bytes長度超過欄位Bytes長度");
        }
        //計算需填充字數
        //填充字數 = (欄位總長度 - 原始內容長度) + 原始內容長度
        int fillerCount = (columnBytesLength - originContentBytesLength) + content.length();

        if (StringUtils.equals(alignment, "left")) {
            // 在左邊補
            content = StringUtils.leftPad(content, fillerCount, filled);
        } else {
            //預設在右邊補
            content = StringUtils.rightPad(content, fillerCount, filled);
        }
        return content;
    }
}
