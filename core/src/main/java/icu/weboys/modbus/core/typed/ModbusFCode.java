package icu.weboys.modbus.core.typed;

public class ModbusFCode{
   public final static int READ_COIL_STATUS           = 0x01;
   public final static int READ_INPUT_STATUS          = 0x02;
   public final static int READ_HOLDING_REGISTER      = 0x03;
   public final static int READ_INPUT_REGISTER        = 0x04;
   public final static int WRITE_SINGLE_COIL          = 0x05;
   public final static int WRITE_SINGLE_REGISTER      = 0x06;
   public final static int WRITE_MULTIPLE_COIL        = 0x0F;
   public final static int WRITE_MULTIPLE_REGISTER    = 0x10;
}
