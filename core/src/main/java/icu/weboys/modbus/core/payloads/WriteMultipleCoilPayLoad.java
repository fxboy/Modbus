package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;
import icu.weboys.modbus.core.typed.MultipleValue;

public class WriteMultipleCoilPayLoad extends BaseModbusPayLoad{
    public WriteMultipleCoilPayLoad(int address, MultipleValue value) {
        super(ModbusFCode.WRITE_MULTIPLE_COIL, address, value.length(), value.value());
    }
}
