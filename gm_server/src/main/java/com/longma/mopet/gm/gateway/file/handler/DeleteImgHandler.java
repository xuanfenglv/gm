package com.longma.mopet.gm.gateway.file.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.config.Config;
import com.longma.mopet.gm.gateway.file.b2s.DeleteImgB2S;

import java.io.File;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:07 2018/5/21
 * @Modified By:
 */
public class DeleteImgHandler extends IMsgHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        S2BMessage send = new S2BMessage();
        DeleteImgB2S b2S = (DeleteImgB2S)message;

        b2S.getImgs().forEach(img->{
            File file = new File(Config.IMG_PATH+'/'+img);
            file.delete();
        });

        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return DeleteImgB2S.class;
    }

    public static void main(String[] args) {
        File file = new File("d://aa.txt");
        file.delete();
    }
}
