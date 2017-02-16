package com.qianmo.gawa.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BcpWrite {
	public static void BcpWriteFile(List<String> list,String filename){
		File file = new File(filename);
        FileWriter fw = null;
        BufferedWriter writer = null;
        try {
            fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            for(String elem:list){
                writer.write(elem);
                writer.newLine();//换行
            }
            writer.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                writer.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	public static void main(String[] args){
		String filename="./lallalla.txt";
		List<String> list = new ArrayList<String>();
		for(int i=0;i<10;i++){
			list.add("hello"+i);
		}
		BcpWriteFile(list,filename);
	}
}
