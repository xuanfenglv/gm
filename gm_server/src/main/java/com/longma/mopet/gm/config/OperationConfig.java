package com.longma.mopet.gm.config;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.config.bean.Operation;
import com.longma.mopet.gm.config.bean.SimpleOperation;

import java.util.*;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 9:57 2018/4/25
 * @Modified By:
 */
public class OperationConfig {
    private static final Map<Integer,Operation> powper = new LinkedHashMap<Integer,Operation>();
    private static List<SimpleOperation> operations;
    private static String operations_Json;
    public static Operation getOperation(int msgId) {
        return powper.get(msgId);
    }

    static {
        powper.put(1,new Operation(1,"资源更新",10,true)); //
        powper.put(2,new Operation(2,"图片上传",10,true)); //
        powper.put(3,new Operation(3,"获取图片list",10,false)); //
        powper.put(4,new Operation(4,"删除图片",10,true)); //
        // 管理员信息相关
        powper.put(101,new Operation(101,"创建管理员",10,true)); // 创建管理员
        powper.put(102,new Operation(102,"重置密码",10,true)); // 重置别人密码
        powper.put(103,new Operation(103,"修改权限",10,true)); // 修改权限
        powper.put(104,new Operation(104,"封号",10,true)); // 封号
        powper.put(105,new Operation(105,"修改密码",1,true));  // 修改自己的密码
        powper.put(106,new Operation(106,"查询管理员是否存在",10,false)); // 查询管理员是否存在
        powper.put(107,new Operation(107,"查询管理员信息",10,false)); // 查询管理员信息
        powper.put(108,new Operation(108,"管理员解封",10,true)); // 管理员解封
        powper.put(109,new Operation(109,"查询操作列表",1,false)); // 查询操作列表
        powper.put(110,new Operation(110,"查询操作日志",1,false)); // 查询操作日志

        powper.put(301,new Operation(301,"查询用户账号",1,false)); // 查询操作日志
        powper.put(302,new Operation(302,"查询角色信息",1,false)); // 查询操作日志

        powper.put(401,new Operation(401,"生成兑换码",10,true));
        powper.put(402,new Operation(402,"查询生成的兑换码记录",10,false));
        powper.put(403,new Operation(403,"操作兑换码(暂停、开启、删除）",10,true));
        powper.put(404,new Operation(404,"修改兑换码信息",10,true));
        powper.put(405,new Operation(405,"查看兑换码的值",10,true));
        powper.put(411,new Operation(411,"发送GM奖励",10,true));
        powper.put(412,new Operation(412,"查询GM奖励记录",10,true));
        powper.put(499,new Operation(499,"查询资源",1,false));

        powper.put(501,new Operation(501,"添加定时公告",10,true));
        powper.put(502,new Operation(502,"查询定时公告",1,false));
        powper.put(503,new Operation(503,"编辑定时公告",10,true));

        powper.put(504,new Operation(504,"添加登录公告",10,true));
        powper.put(505,new Operation(505,"查询登录公告",10,false));
        powper.put(506,new Operation(506,"编辑登录公告",10,true));
        powper.put(507,new Operation(507,"添加推送公告",10,true));
        powper.put(508,new Operation(508,"查询推送公告",10,false));
        powper.put(509,new Operation(509,"编辑推送公告",10,true));

        powper.put(520,new Operation(520,"操作公告(暂停、开启、删除）",10,true));

        powper.put(603,new Operation(603,"查询金币日志",5,false));

        powper.put(412,new Operation(412,"查询GM奖励记录",10,true));
        powper.put(412,new Operation(412,"查询GM奖励记录",10,true));
        powper.put(412,new Operation(412,"查询GM奖励记录",10,true));
        powper.put(412,new Operation(412,"查询GM奖励记录",10,true));




    }
    public static String getOperationList() {
        if(operations_Json!=null) {
            return operations_Json;
        }
        List<SimpleOperation> operations = new ArrayList<>();
        for(Map.Entry<Integer,Operation> item : powper.entrySet()) {
            Operation operation = item.getValue();
            if(operation.isNeedLog()) {
                operations.add(new SimpleOperation(operation.getMsgId(),operation.getName()));
            }

        }
        OperationConfig.operations = operations;
        OperationConfig.operations_Json = JSON.toJSONString(operations);
        return operations_Json;
    }
}
