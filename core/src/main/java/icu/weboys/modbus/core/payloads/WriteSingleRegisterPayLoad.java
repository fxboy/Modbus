package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;

public class WriteSingleRegisterPayLoad extends BaseModbusPayLoad{
    public WriteSingleRegisterPayLoad(int address, short value) {
        super(ModbusFCode.WRITE_SINGLE_REGISTER, address, 0,value);
    }
}
