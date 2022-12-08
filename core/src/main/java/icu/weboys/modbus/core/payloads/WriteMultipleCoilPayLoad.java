package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;
import icu.weboys.modbus.core.value.ModbusValue;

public class WriteMultipleCoilPayLoad extends BaseModbusPayLoad{
    public WriteMultipleCoilPayLoad(int address, ModbusValue<Short> value) {
        super(ModbusFCode.WRITE_MULTIPLE_COIL, address, value.len(), value.value());
    }
}
