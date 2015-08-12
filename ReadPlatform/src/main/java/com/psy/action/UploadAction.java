/**
 * UploadAction.java
 * com.psy.action
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年6月30日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.action;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.psy.entity.Users;
import com.psy.service.user.UsersService;
import com.psy.util.Constants;

/**
 * ClassName:UploadAction
 *
 * TODO(struts2文件上传)
 *
 * @project ReadPlatform
 *
 * @author xiao
 *
 * @date   2015年6月30日 下午3:12:43	
 *
 * @class com.psy.action.UploadAction
 *
 */ 
@SuppressWarnings("serial")
@Results({
	@Result(name="success",location="index!view.do",type="redirect"),
	@Result(name="user",location="user/users!view.do",type="redirect")
})
public class UploadAction extends ActionSupport{
    
    private File image; //上传的文件
    private String imageFileName; //文件名称
    private String imageContentType; //文件类型
    private String userId;//用户Id
    private Users user;
    @Autowired
    private UsersService usersService;
    HttpSession session = ServletActionContext.getRequest().getSession();

    public String execute() throws Exception{
		//文件存储地址
//		String realpath = ServletActionContext.getServletContext().getRealPath("/images");
        //F:\message\apache-tomcat-7.0.52\webapps\ReadPlatform\images
		String realpath = "F:\\images";
		System.out.println(userId);
		//F:\images
        System.out.println("realpath: "+realpath);
        if (image != null) {
            File savefile = new File(new File(realpath), imageFileName);
            if (!savefile.getParentFile().exists())
                savefile.getParentFile().mkdirs();
            FileUtils.copyFile(image, savefile);
            ActionContext.getContext().put("message", "文件上传成功");
            //文件存储位置
            String path = Constants.PHOTO_URL+imageFileName;
            user = usersService.getUsersById(userId);
            user.setFace(path);
            usersService.save(user);
            session.removeAttribute(Constants.SESSION_USER_KEY);
            session.setAttribute(Constants.SESSION_USER_KEY, user);
        }
//        return "success";
        return "user";
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public String getImageFileName() {
        return imageFileName;
    }

    public void setImageFileName(String imageFileName) {
        this.imageFileName = imageFileName;
    }

    public String getImageContentType() {
        return imageContentType;
    }

    public void setImageContentType(String imageContentType) {
        this.imageContentType = imageContentType;
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

}
