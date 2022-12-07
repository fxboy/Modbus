package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;
import icu.weboys.modbus.core.typed.MultipleValue;

public class WriteMultipleRegisterPayLoad extends BaseModbusPayLoad{
    public WriteMultipleRegisterPayLoad( int address, MultipleValue value) {
        super(ModbusFCode.WRITE_MULTIPLE_REGISTER, address, value.length(), value.values());
    }
}
