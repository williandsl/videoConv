package org.example.client.cipher;


import org.example.client.cipher.CipherFunction;

class SpliceFunction implements CipherFunction {

    @Override
    public char[] apply(char[] array, String argument) {
        int deleteCount = Integer.parseInt(argument);
        char[] spliced = new char[array.length - deleteCount];
        System.arraycopy(array, 0, spliced, 0, deleteCount);
        System.arraycopy(array, deleteCount * 2, spliced, deleteCount, spliced.length - deleteCount);

        return spliced;
    }

}
