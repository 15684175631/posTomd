package com.example.posmd.controller;

import com.example.posmd.service.UserService;
import com.example.posmd.to.md.ToMdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
public class Indexcontroller {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index") // 访问路径
    public ModelAndView toIndex() {
        // 返回templates目录下index.html
        ModelAndView view = new ModelAndView("index");
        // 查询所有的用户，添加到model视图里
        view.addObject("user_list", userService.selectAllUser());
        return view;
    }

    @PostMapping("/uploadSpring")
    //@RequestParam必须，参数与表单name值对应
    public String MultipartFileUpload(@RequestParam("SpringFile") MultipartFile file)  {
        //获取文件名
        String filename = file.getOriginalFilename();
        //文件MIME类型
        String contentType = file.getContentType();
        //保存路径
        String path = "/tmp/file/";
        System.out.println(filename);
        System.out.println(contentType);

        long time = System.currentTimeMillis();

        //创建文件，用于保存上传的数据
        File outfile = new File(path + filename);
        try {
            //将上传的数据保存到指定文件中
            file.transferTo(outfile);
        } catch (IOException e) {
            e.printStackTrace();

        }
        try{
            StringBuilder posBuilder = new StringBuilder();
            ToMdUtils.toMD(path+filename, i -> {
                System.out.print(i.toString());
                posBuilder.append(i.toString());
            });
            Files.write(Paths.get("/tmp/file/go_memory_allocator_pos.md"), posBuilder.toString().getBytes());

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return "success";

    }
}