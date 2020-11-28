package com.itgarden.common.staticdata;

/*
 * Created by Suresh Stalin on 10 / Nov / 2020.
 */

public enum TokenType {
    ACCESS_TOKEN {
        public String toString() {
            return "ACCESS_TOKEN";
        }
    },
    REFRESH_TOKEN {
        public String toString() {
            return "REFRESH_TOKEN";
        }
    }
}
