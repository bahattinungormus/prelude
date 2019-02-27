package com.fabercode.prelude.core;

import com.fabercode.prelude.core.functionals.Feedback;
import com.fabercode.prelude.core.functionals.Result;

public class Main {

    public static void main(String[] args) {
        // write your code here or die.
        Feedback<String> source = () -> String.join("\n", System.getenv().keySet());
        Result<String> result = source.getResult(null);
        System.out.println(result.value);
        if (result.error != null) result.error.printStackTrace();
    }
}
