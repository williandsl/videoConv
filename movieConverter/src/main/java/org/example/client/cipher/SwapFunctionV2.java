package org.example.client.cipher;


import org.example.client.cipher.CipherFunction;

class SwapFunctionV2 implements CipherFunction {

    @Override
    public char[] apply(char[] array, String argument) {
        int position = Integer.parseInt(argument);
        char c = array[0];
        array[0] = array[position % array.length];
        array[position % array.length] = c;
        return array;
    }

}
