package cn.ac.iie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class ReadNE {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:/test/NE_17777.txt"));
        String s = null;
        Map<String, Integer> person = new HashMap<>();
        Map<String, Integer> org = new HashMap<>();
        int top = 10;
        while ((s = br.readLine()) != null) {
            JSONObject dataJson = JSON.parseObject(s);
            JSONArray sentences = dataJson.getJSONArray("sentences");
            for(int i=0; i<sentences.size(); i++){
                JSONObject jsonObject = sentences.getJSONObject(i);
                JSONArray tokens = jsonObject.getJSONArray("tokens");
                int word_num_per = 0;
                int word_num_org = 0;
                int size = tokens.size();
                for(int j = 0; j<size;j++){
                    JSONObject token = tokens.getJSONObject(j);
                    if("PERSON".equals(token.get("ner").toString())){
                        if(word_num_per==0){
                            String word = token.get("word").toString();
                            //判断附近人物
                            int flag = j;
                            while(flag!=size-1){
                                JSONObject org1 = tokens.getJSONObject(flag + 1);
                                if("PERSON".equals(org1.get("ner").toString())){
                                    word = word + " " + org1.get("word").toString();
                                    flag++;
                                    word_num_per++;
                                }else{
                                    break;
                                }
                            }
                            if(person.putIfAbsent(word, 1)!=null){
                                person.compute(word, (k, v)-> v+1);
                            }
                        }else{
                            word_num_per--;
                        }
                    }
                    if("ORGANIZATION".equals(token.get("ner").toString())){
                        if(word_num_org==0){
                            String word = token.get("word").toString();
                            //判断附近组织
                            int flag = j;
                            while(flag!=size-1){
                                JSONObject org1 = tokens.getJSONObject(flag + 1);
                                if("ORGANIZATION".equals(org1.get("ner").toString())){
                                    word = word + " " + org1.get("word").toString();
                                    flag++;
                                    word_num_org++;
                                }else{
                                    break;
                                }
                            }
                            if(org.putIfAbsent(word, 1)!=null){
                                org.compute(word, (k, v)-> v+1);
                            }
                        }else{
                            word_num_org--;
                        }
                    }
                }
            }
        }
        List<Map.Entry<String, Integer>> personlist = new ArrayList<>(person.entrySet());
        Collections.sort(personlist, (o1, o2) -> (o2.getValue() - o1.getValue()));
        List<Map.Entry<String, Integer>> orglist = new ArrayList<>(org.entrySet());
        Collections.sort(orglist, (o1, o2) -> (o2.getValue() - o1.getValue()));
//        person.forEach((k, v)-> System.out.println("k:"+k+",v:"+v));
//        org.forEach((k, v)-> System.out.println("k:"+k+",v:"+v));
        System.out.println("person top 10:");
        for(int i = 0;i<top;i++){
            System.out.println(personlist.get(i).getKey()+","+personlist.get(i).getValue());
        }
        System.out.println("org top 10:");
        for(int i = 0;i<top;i++){
            System.out.println(orglist.get(i).getKey()+","+orglist.get(i).getValue());
        }
    }
}
