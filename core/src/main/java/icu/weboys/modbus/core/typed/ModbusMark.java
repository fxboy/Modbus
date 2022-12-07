package icu.weboys.modbus.core.typed;

public class ModbusMark {
    public final static short MODBUS_FLAG       = 0x0001;
    public final static short MODBUS_POOL       = 0x0000;

    public static int flag(int flag){
        if(flag >= 0x9999){
            flag = 0x0001;
        }
        return flag;
    }

}
