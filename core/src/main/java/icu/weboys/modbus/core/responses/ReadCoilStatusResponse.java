package icu.weboys.modbus.core.responses;

public class ReadCoilStatusResponse<T> extends BaseModbusReadResponse<T>{
    public ReadCoilStatusResponse(int flag,int uid,int code, int length, T data) {
        super(flag,uid,code, length, data);
    }
}
