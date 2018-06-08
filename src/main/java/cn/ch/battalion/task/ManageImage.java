package cn.ch.battalion.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenhang1 on 2018/4/19.
 */
@Component
public class ManageImage {

    //TODO 该功能未完成
    @Scheduled(cron = "0 0 0 ? * MON")
    public void manage(){
        //TODO
        System.out.print("图片文件清理成功");

    }

    public List<File> getFiles(String path){
        //目标集合fileList
        List<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if(file.isDirectory()){
            File []files = file.listFiles();
            for(File fileIndex:files){
                //如果这个文件是目录，则进行递归搜索
                if(fileIndex.isDirectory()){
                    getFiles(fileIndex.getPath());
                }else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }

}
