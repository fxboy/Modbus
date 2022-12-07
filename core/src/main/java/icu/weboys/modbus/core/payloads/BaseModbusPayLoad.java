package icu.weboys.modbus.core.payloads;

public abstract class BaseModbusPayLoad implements ModbusPayLoad {
    int code;
    int address;
    int amount;
    int value;

    int[] values;

    // 查询用
    public BaseModbusPayLoad(int code, int address, int amount) {
        this.code = code;
        this.address = address;
        this.amount = amount;
    }

    // 写入用
    public BaseModbusPayLoad(int code, int address, int amount,int value) {
        this(code,address,amount);
        this.value = value;
    }

    public BaseModbusPayLoad(int code, int address, int amount,int[] values) {
        this(code,address,amount);
        this.values = values;
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
    public int value() {
        return value;
    }

    @Override
    public int[] values() {
        return this.values;
    }
}
