package com.itgarden.common.staticdata;

public enum STATUS {

    ACTIVE {
        public String toString() {
            return "ACTIVE";
        }
    },
    PENDING {
        public String toString() {
            return "PENDING";
        }
    },
    INACTIVE {
        public String toString() {
            return "INACTIVE";
        }
    }
}
