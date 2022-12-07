package icu.weboys.modbus.core.responses;

public class ReadInputStatusResponse <T> extends BaseModbusReadResponse<T>{
    public ReadInputStatusResponse(int flag, int uid, int code, int length, T data) {
        super(flag, uid, code, length, data);
    }
}
