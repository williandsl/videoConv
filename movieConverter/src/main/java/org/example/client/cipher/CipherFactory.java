package org.example.client.cipher;


import org.example.client.YoutubeException;
import org.example.client.cipher.Cipher;
import org.example.client.cipher.CipherFunction;
import org.example.client.YoutubeException;

public interface CipherFactory {

    Cipher createCipher(String jsUrl) throws YoutubeException, YoutubeException;

    void addInitialFunctionPattern(int priority, String regex);

    void addFunctionEquivalent(String regex, CipherFunction function);
}
