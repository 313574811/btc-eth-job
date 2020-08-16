package com.alex.txjob.entity;

import lombok.Data;

@Data
public class BtcTx {
            private String block_height;
            private String block_hash;
            private String block_time;
            private String confirmations;
            private String fee;
            private String hash;
            private String inputs_count;
            private String inputs_value;
            private String is_coinbase;
            private String is_double_spend;
            private String is_sw_tx;
            private String lock_time;
            private String outputs_count;
            private String outputs_value;
            private String sigops;
            private String size;
            private String version;
            private String vsize;
            private String weight;
            private String witness_hash;
            private String inputs;
            private String outputs;
}
