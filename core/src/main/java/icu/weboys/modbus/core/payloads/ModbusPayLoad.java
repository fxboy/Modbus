package icu.weboys.modbus.core.payloads;

public interface ModbusPayLoad {
    int getCode();
    int getAddress();
    int getAmount();
    int value();

    int[] values();
}
