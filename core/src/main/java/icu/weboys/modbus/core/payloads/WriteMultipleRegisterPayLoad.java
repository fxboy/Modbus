package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;
import icu.weboys.modbus.core.value.ModbusValue;

public class WriteMultipleRegisterPayLoad extends BaseModbusPayLoad{
    public WriteMultipleRegisterPayLoad( int address, ModbusValue<short[]> value) {
        super(ModbusFCode.WRITE_MULTIPLE_REGISTER, address, value.len(), value.value());
    }
}
