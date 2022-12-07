package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;

public class ReadHoldingRegisterPayLoad extends BaseModbusPayLoad{
    public ReadHoldingRegisterPayLoad( int address, int amount) {
        super(ModbusFCode.READ_HOLDING_REGISTER, address, amount);
    }
}
