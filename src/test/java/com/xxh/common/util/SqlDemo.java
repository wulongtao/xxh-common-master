package com.xxh.common.util;

import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * Created by wulongtao on 2017/7/28.
 */
public class SqlDemo {

    @Test
    public void test() throws Exception {
        List<Nick> lstNick = ExcelUtils.readExcelFile(Nick.class, "D:\\dev\\java\\xxh-common-master\\src\\main\\resources\\demo\\nick.xlsx");
//        System.out.println(lstNick);
        Map<String, String> mCityNick = new HashMap<>();
        for (Nick cityNick : lstNick) {
            String[] cityMapping = cityNick.getNick().trim().split("==");
            String cityKey = cityMapping[0];
            String nickValue = cityMapping[1];
            mCityNick.put(cityKey+"市", nickValue+","+cityKey);
        }
        System.out.println(mCityNick);


        List<City> lstCity = ExcelUtils.readExcelFile(City.class, "D:\\dev\\java\\xxh-common-master\\src\\main\\resources\\demo\\city.xlsx");
        System.out.println(lstCity);

        String ontoId = "5AB948FC-ECA0-7EFA-7C8B-AB6523BBD3D2";
        String cityClass = "FEBA3FB3-5B67-8CAA-3C0F-BDBED48E1C39";
        StringBuffer sb = new StringBuffer();
        for (City city : lstCity) {
            String termId = UUID.randomUUID().toString().toUpperCase();
            String termName = city.getCity();
            String idiom = mCityNick.get(termName);

            String termSql = "insert into edit_term(onto_id,term_id,term_name,weight,structure_type,pos,is_abstract,status) values('"+ontoId+"','"+termId+"','"+termName+"','0','1','1','0','1');";
            String nodeId = UUID.randomUUID().toString().toUpperCase();
            String termNodeSql = "insert into edit_term_node(onto_id,term_id,node_type,node_id) values('"+ontoId+"','"+termId+"','1','"+nodeId+"');";
            String classSql = "insert into edit_class(onto_id,class_id,term_id,idiom) values('"+ontoId+"','"+nodeId+"','"+termId+"','"+idiom+"');";
            String classRelationSql = "insert into edit_class_relation(onto_id,class_a_id,class_z_id) values('"+ontoId+"','"+cityClass+"','"+nodeId+"');";

            sb.append(termSql+"\n"+termNodeSql+"\n"+classSql+"\n"+classRelationSql+"\n");
        }


        FileWriter fw = null;
        File f=new File("D:\\dev\\java\\xxh-common-master\\src\\test\\java\\com\\xxh\\common\\util\\city.sql");
        fw = new FileWriter(f, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println(sb);
        pw.flush();
    }
}
