package com.longma.mopet.gm.util;

public class SystemUtil {
    private  static SystemType currentSystemType;
    static {
        String OS_NAME = System.getProperty("os.name").toLowerCase();
        if(OS_NAME.contains("linux")){
            currentSystemType=SystemType.linux;
        }else if (OS_NAME.contains("windows")){
            currentSystemType=SystemType.window;
        }else if (OS_NAME.contains("nix")||OS_NAME.contains("nux")){
            currentSystemType=SystemType.unix;
        }else if (OS_NAME.contains("mac")){
            currentSystemType=SystemType.mac;
        }
    }
    public static SystemType currentSystemType(){
        return currentSystemType;
    }

    public enum SystemType{
        window,linux,unix,mac;
    }
}
