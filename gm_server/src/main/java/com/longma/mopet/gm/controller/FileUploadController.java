package com.longma.mopet.gm.controller;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.config.GmConstant;
import com.longma.mopet.gm.config.Config;
import com.longma.mopet.gm.controller.s2b.ImgListS2B;
import com.longma.mopet.gm.dao.gm.OperationRecordDao;
import com.longma.mopet.gm.model.OperationRecord;
import com.longma.mopet.gm.util.FtpUtil;
import com.longma.mopet.gm.util.SessionUtil;
import com.longma.mopet.gm.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:39 2018/5/17
 * @Modified By:
 */
@RestController
@RequestMapping("upload")
public class FileUploadController {
    @Autowired
    private OperationRecordDao operationRecordDao;
    /**
     * 实现多文件资源上传
     * */
    @RequestMapping(value="resource",method= RequestMethod.POST)
    public String resource(@RequestParam("files")List<MultipartFile> files,HttpSession session) {
        long beginTime = System.currentTimeMillis();
        S2BMessage result = new S2BMessage();
        String operator = SessionUtil.getOperatorName(session);
        int userPower = SessionUtil.getUserPower(operator);
        if (userPower < 10) {
            result.setResponseState(GmConstant.ResponseState.NO_PERMISSION);
            return JSON.toJSONString(result);
        }
        if(files.isEmpty()){
            result.setErrorReturn(3,"无选中的文件");
            return JSON.toJSONString(result);
        }
        boolean check = checkResource(files);
        if (!check) {
            result.setErrorReturn(3,"只允许上传'.xlsx'为后缀的文件");
            return JSON.toJSONString(result);
        }
        HashMap<String, String> nameMap = new HashMap<>();
        // 获得临时目录绝对路径
        String path = Config.UPLOAD_TEM_PATH;
        StringBuilder resources = new StringBuilder();
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            resources.append(fileName).append(",");
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                result.setErrorReturn(3,"未知bug1");
                return JSON.toJSONString(result);
            }else{
                String temName = fileName+"."+ UUIDUtil.createRealUUID();
                nameMap.put(fileName,temName);
                File dest = new File(path + "/" + temName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result.setErrorReturn(8,"文件传输失败");
                    return JSON.toJSONString(result);
                }
            }
        }

        // 校验

        // 上传
        for(String key : nameMap.keySet()){
            String srcFileName = path+File.separator+nameMap.get(key);
            try {
                FtpUtil.upload("10.71.56.112","gmuser",21,"gmuser","test",srcFileName,key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String response = JSON.toJSONString(result);
        operationRecordDao.insert(new OperationRecord(1,"资源更新",operator,beginTime,result.getRet(),resources.toString(),response));
        long endTime = System.currentTimeMillis();
        System.out.println("发出响应：" + response+",用时："+(endTime-beginTime)+"ms");
        return response;
    }

    @RequestMapping(value="img",method= RequestMethod.POST)
    public String img(@RequestParam("files")List<MultipartFile> files,HttpSession session) {
        long beginTime = System.currentTimeMillis();
        S2BMessage result = new S2BMessage();
        String operator = SessionUtil.getOperatorName(session);
        int userPower = SessionUtil.getUserPower(operator);
        if (userPower < 10) {
            result.setResponseState(GmConstant.ResponseState.NO_PERMISSION);
            return JSON.toJSONString(result);
        }
        if(files.isEmpty()){
            result.setErrorReturn(3,"无选中的文件");
            return JSON.toJSONString(result);
        }
        boolean check = checkImg(files);
        if (!check) {
            result.setErrorReturn(3,"只允许上传'.xlsx','img','png'为后缀的文件");
            return JSON.toJSONString(result);
        }
        // 获得临时目录绝对路径
        String path = Config.IMG_PATH;
        StringBuilder resources = new StringBuilder();
        for(MultipartFile file:files){
            String fileName = file.getOriginalFilename();
            resources.append(fileName).append(",");
            int size = (int) file.getSize();
            System.out.println(fileName + "-->" + size);

            if(file.isEmpty()){
                result.setErrorReturn(3,"未知bug1");
                return JSON.toJSONString(result);
            }else{
                String temName = fileName+"_"+ System.currentTimeMillis();
                File dest = new File(path + "/" + temName);
                if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                    dest.getParentFile().mkdir();
                }
                try {
                    file.transferTo(dest);
                }catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                    result.setErrorReturn(8,"文件传输失败");
                    return JSON.toJSONString(result);
                }
            }
        }

        String response = JSON.toJSONString(result);
        operationRecordDao.insert(new OperationRecord(1,"资源更新",operator,beginTime,result.getRet(),resources.toString(),response));
        long endTime = System.currentTimeMillis();
        System.out.println("发出响应：" + response+",用时："+(endTime-beginTime)+"ms");
        return response;
    }
    @RequestMapping(value="imgs",method= RequestMethod.GET)
    public String img(HttpSession session) {
        ImgListS2B s2B = new ImgListS2B();
        String path = Config.IMG_PATH;
        File folder = new File(path);
        List<String> imgs = new ArrayList<>();
        for (String img : folder.list()) {
            imgs.add(img);
        }
        imgs.remove("info.txt");
        s2B.setImgs(imgs);
        return JSON.toJSONString(s2B);
    }
    public boolean checkResource(List<MultipartFile> files) {
        return chechSuffix(files, "xlsx");
    }

    public boolean checkImg(List<MultipartFile> files) {
        return chechSuffix(files, "png","jpg","gif");
    }

    public boolean chechSuffix(List<MultipartFile> files, String...suffixs) {
        for (MultipartFile file : files) {
            String original = file.getOriginalFilename();
            boolean nowLoop = false;
            for (String suffix : suffixs) {
                if (original.endsWith('.'+suffix)) {
                    nowLoop = true;
                    break;
                }
            }
            if (nowLoop == false) {
                return false;
            }
        }

        return true;
    }

}
