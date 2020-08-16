package com.alex.txjob;

import com.alex.txjob.entity.BtcRes;
import com.alex.txjob.entity.BtcTx;
import com.alex.txjob.feign.FeignApi;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class BtcJob {


    @Autowired
    FeignApi feignApi;

    @Value("${btc.filePath}")
    String btcFilePath;

    @Scheduled(cron = "${call.cron}")
    private void ethJob(){

        JSONObject btcRes = feignApi.callBtc();
        if(btcRes.getInteger("err_code") == 200){
            JSONObject data = btcRes.getJSONObject("data");
            String list1 = data.getJSONArray("list").toJSONString();
            List<BtcTx> list = JSONArray.parseArray(list1, BtcTx.class);

            writeCVS(btcFilePath,list);
            System.out.println("write success");
        }

    }

    private void writeCVS(String btcFilePath, List<BtcTx> list) {
        CSVFormat format = CSVFormat.DEFAULT.withSkipHeaderRecord();
        try(Writer out = new FileWriter(btcFilePath);
            CSVPrinter printer = new CSVPrinter(out, format)) {
            for (BtcTx tx : list) {
                printer.printRecord(tx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
