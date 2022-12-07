package icu.weboys.modbus.core.requests;

import icu.weboys.modbus.core.payloads.ModbusPayLoad;

public abstract class BaseModbusRequest implements ModbusRequest{
    int flag =  0x7FFF;
    short pool =  0x0000;

    short uid = 1;

    ModbusPayLoad payLoad;
    public BaseModbusRequest(ModbusPayLoad payLoad) {
        this.payLoad = payLoad;
    }

    public BaseModbusRequest(short uid,ModbusPayLoad payLoad) {
       this(payLoad);
       this.uid = uid;
    }

    public ModbusPayLoad getPayLoad() {
        return payLoad;
    }

    public int getFlag() {
        return flag;
    }

    public short getPool() {
        return pool;
    }

    @Override
    public int setFlag(int flag) {
        this.flag = (short) flag;
        return flag+1;
    }


    @Override
    public short getUid() {
        return uid;
    }
}
