package icu.weboys.modbus.core.responses;

import java.util.Arrays;

public abstract class BaseModbusReadResponse<T> implements ModbusResponse<T> {
    int flag;
    int uid;
    int code;
    int length;
    T   data;

    public BaseModbusReadResponse(int flag,int uid ,int code, int length, T data) {
        this.flag = flag;
        this.uid = uid;
        this.code = code;
        this.length = length;
        this.data = data;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public int length() {
        return this.length;
    }

    @Override
    public int flag() {
        return this.flag;
    }

    @Override
    public int uid() {
        return this.uid;
    }

    @Override
    public T data() {
        return this.data;
    }

    @Override
    public String toString() {
        return "ModbusReadResponse{" +
                "flag=" + flag +
                ", uid=" + uid +
                ", code=" + code +
                ", length=" + length +
                ", data=" + Arrays.toString((byte[])data) +
                '}';
    }
}
