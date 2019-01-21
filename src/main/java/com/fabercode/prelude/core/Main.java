package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.Result;

public class Main {

    public static void main(String[] args) {
        // write your code here or die.
        Result<String> result = Result.of(() -> String.join("\n", System.getenv().keySet()));
        System.out.println(result.value);
        if (result.error != null) result.error.printStackTrace();
    }
}
