package icu.weboys.modbus.core.value;

public interface ModbusValue<T> {
    T value();

    int len();

}
