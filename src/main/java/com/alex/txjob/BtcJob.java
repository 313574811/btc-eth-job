package com.alex.txjob;

import com.alex.txjob.entity.BtcTx;
import com.alex.txjob.feign.FeignApi;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class BtcJob {


    @Autowired
    FeignApi feignApi;

    @Value("${btc.filePath}")
    String btcFilePath;

    @PostConstruct
    public void  startBlock(){
        //如果已经存在 读取最后一条的块高
        CSVFormat format = CSVFormat.DEFAULT.withSkipHeaderRecord();
        try(Reader in = new FileReader(btcFilePath)) {
            List<CSVRecord> records = format.parse(in).getRecords();
            CSVRecord strings = records.get(records.size() - 1);
            String data = strings.get(0);
            String block_height = data.substring(data.indexOf("block_height")+13, data.indexOf(","));
            //todo 断点续接方案待定
            System.out.println(block_height);

        } catch (Exception e) {
            log.info("file does not exist");
            e.printStackTrace();
        }
    }

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
        Class<BtcTx> btcTxClass = BtcTx.class;
        String[] headers = Arrays.stream(btcTxClass.getDeclaredFields()).map(Field::getName).toArray(String[]::new);
        CSVFormat format = CSVFormat.DEFAULT.withHeader(headers).withSkipHeaderRecord();

        try(Writer out = new FileWriter(btcFilePath,true);
            CSVPrinter printer = new CSVPrinter(out, format)) {
            for (BtcTx tx : list) {
                printer.printRecord(tx);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
