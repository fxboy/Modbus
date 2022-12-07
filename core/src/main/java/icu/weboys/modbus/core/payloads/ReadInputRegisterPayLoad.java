package icu.weboys.modbus.core.payloads;

import icu.weboys.modbus.core.typed.ModbusFCode;

public class ReadInputRegisterPayLoad  extends BaseModbusPayLoad{
    public ReadInputRegisterPayLoad( int address, int amount) {
        super(ModbusFCode.READ_INPUT_REGISTER, address, amount);
    }
}
