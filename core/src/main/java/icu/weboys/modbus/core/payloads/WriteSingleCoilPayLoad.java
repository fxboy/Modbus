package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;
import icu.weboys.modbus.core.typed.ModbusSwitch;

public class WriteSingleCoilPayLoad extends BaseModbusPayLoad{
    public WriteSingleCoilPayLoad(int address, Boolean value) {
        super(ModbusFCode.WRITE_SINGLE_COIL, address,0,value?0xFF:0x0000);
    }

    public WriteSingleCoilPayLoad(int address, int value) {
        super(ModbusFCode.WRITE_SINGLE_COIL, address,0,value == 1?0xFF:0x0000);
    }

    public WriteSingleCoilPayLoad(int address, Short value) {
        super(ModbusFCode.WRITE_SINGLE_COIL, address,0,value == 1?0xFF:0x0000);
    }
}
