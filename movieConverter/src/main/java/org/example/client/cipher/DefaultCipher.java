package org.example.client.cipher;


import org.example.client.cipher.Cipher;
import org.example.client.cipher.CipherFunction;
import org.example.client.cipher.JsFunction;

import java.util.List;
import java.util.Map;

public class DefaultCipher implements Cipher {

    private final Map<String, org.example.client.cipher.CipherFunction> functionsMap;
    private final List<org.example.client.cipher.JsFunction> functions;

    public DefaultCipher(List<org.example.client.cipher.JsFunction> transformFunctions, Map<String, CipherFunction> transformFunctionsMap) {
        this.functionsMap = transformFunctionsMap;
        this.functions = transformFunctions;
    }

    @Override
    public String getSignature(String cipheredSignature) {
        char[] signature = cipheredSignature.toCharArray();
        for (JsFunction jsFunction : functions) {
            signature = functionsMap.get(jsFunction.getName()).apply(signature, jsFunction.getArgument());
        }
        return String.valueOf(signature);
    }

}
