package org.example.client.cipher;


public interface CipherFunction {

    char[] apply(char[] array, String argument);
}
