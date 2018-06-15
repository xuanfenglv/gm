package com.longma.mopet.gm.ftp;

import java.io.IOException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:29 2018/5/17
 * @Modified By:
 */
public class Send {

    public static void main(String[] args) throws IOException {
        FtpUtil.upload("10.71.56.112","gmuser",21,"gmuser","test","D:\\demo1.txt","demo.txt");
    }
}
