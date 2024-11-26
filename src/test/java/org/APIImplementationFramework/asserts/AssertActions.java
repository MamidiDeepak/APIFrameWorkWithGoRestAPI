package org.APIImplementationFramework.asserts;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertActions {

    public static void validateTheResponseData(String actual, String expected){

        assertThat(actual).isEqualTo(expected);
    }
}
