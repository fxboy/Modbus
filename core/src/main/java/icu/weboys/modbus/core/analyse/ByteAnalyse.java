package icu.weboys.modbus.core.analyse;

// 解析传过来的byte
public class ByteAnalyse {
     static int[] toBinaryReverse(int val) {
        int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
        int len = Math.max(((mag + (1 - 1)) / 1), 1);
        int[] buf = new int[len];
        int i = 0;
        do {
            buf[i++] = val & (1 << 1 - 1);
            val >>>= 1;
        } while (val != 0 && i< len);
        return buf;
    }


    public static int[] input_analyse(byte[] bytes){
        return coil_analyse(bytes);
    }
    public static int[] coil_analyse(byte[] bytes){
        int[] temp = new int[0];
        int len;
        for (byte bt : bytes) {
            int[] bi = toBinaryReverse(Byte.toUnsignedInt(bt));
            len = temp.length;
            int[] temp1 = new int[len + bi.length];
            System.arraycopy(temp,0,temp1,0,len);
            System.arraycopy(bi,0,temp1,len,bi.length);
            temp = temp1;
        }
        return temp;
    }
}
