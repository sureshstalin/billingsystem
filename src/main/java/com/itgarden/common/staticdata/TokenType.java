package com.itgarden.common.staticdata;

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
