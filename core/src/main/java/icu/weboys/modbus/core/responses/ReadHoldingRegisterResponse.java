package icu.weboys.modbus.core.responses;

public class ReadHoldingRegisterResponse <T> extends BaseModbusReadResponse<T>{
    public ReadHoldingRegisterResponse(int flag, int uid, int code, int length, T data) {
        super(flag, uid, code, length, data);
    }
}
