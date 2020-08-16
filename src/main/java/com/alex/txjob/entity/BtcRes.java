package com.alex.txjob.entity;

import lombok.Data;

import java.util.List;

@Data
public class BtcRes {
    private Integer err_code;
    private Data data;
    public class Data{
        List<String> txs;

        public List<String> getTxs() {
            return txs;
        }

        public void setTxs(List<String> txs) {
            this.txs = txs;
        }
    }

}
