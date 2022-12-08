package icu.weboys.modbus.core.payloads;

public abstract class BaseModbusPayLoad<T> implements ModbusPayLoad {
    int code;
    int address;
    int amount;
    T val;

    // 查询用
    public BaseModbusPayLoad(int code, int address, int amount) {
        this.code = code;
        this.address = address;
        this.amount = amount;
    }


    // 写入用
    public BaseModbusPayLoad(int code, int address, int amount,T value) {
        this(code,address,amount);
        this.val = value;
    }

    @Override
    public int getCode() {
        return code;
    }
    @Override
    public int getAddress() {
        return address;
    }
    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public T val() {
        return this.val;
    }
}
