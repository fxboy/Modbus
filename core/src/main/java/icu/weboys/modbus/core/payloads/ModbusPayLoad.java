package icu.weboys.modbus.core.payloads;

public interface ModbusPayLoad<T> {
    int getCode();
    int getAddress();
    int getAmount();
    T val();
}
